package com.example.quemtocahoje.Views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.GeneroMusical;
import com.example.quemtocahoje.Enum.StatusConvite;
import com.example.quemtocahoje.Model.BandaDAO;
import com.example.quemtocahoje.Persistencia.Entity.BandaEntity;
import com.example.quemtocahoje.Persistencia.Entity.ConviteEntity;
import com.example.quemtocahoje.Spinner.MultiSelectionSpinner;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TelaCriacaoBanda extends AppCompatActivity {

    private MultiSelectionSpinner spnGeneroBanda;
    private ListView lstEmailIntegrantes;
    private FloatingActionButton btnAddIntegrante;
    private Button btnCadastrarBanda;
    private Button btnVoltarBanda;
    private EditText edtNomeBanda;
    private EditText edtEmailIntegrante;
    private RadioGroup rgpCadastroTipoBandaSolo;
    private TextView txtEmailIntegrante;
    private TextView txtNomeBanda;
    private ArrayAdapter<String> adapterIntegrantes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tela_criacao_banda);

        btnAddIntegrante = findViewById(R.id.btnAddIntegrante);
        btnVoltarBanda = findViewById(R.id.btnVoltarBanda);
        btnCadastrarBanda = findViewById(R.id.btnCadastrarBanda);
        edtNomeBanda = findViewById(R.id.edtNomeBanda);
        edtEmailIntegrante = findViewById(R.id.edtEmailIntegrante);
        spnGeneroBanda = findViewById(R.id.spnGeneroBanda);
        rgpCadastroTipoBandaSolo = findViewById(R.id.rgpCadastroTipoBandaSolo);
        txtEmailIntegrante = findViewById(R.id.txtEmailIntegrante);
        txtNomeBanda = findViewById(R.id.txtNomeBanda);

        final List<String> adapter = GeneroMusical.getGenerosMusicais();
        spnGeneroBanda.setItems(adapter);

        adapterIntegrantes = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1);
        lstEmailIntegrantes = findViewById(R.id.lstEmailIntegrantes);

        rgpCadastroTipoBandaSolo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton r = findViewById(rgpCadastroTipoBandaSolo.getCheckedRadioButtonId());
                String valorBandaSolo = r.getText().toString().trim();
                if(valorBandaSolo.equals("Banda")){
                    btnAddIntegrante.setVisibility(View.VISIBLE);
                    edtEmailIntegrante.setVisibility(View.VISIBLE);
                    txtEmailIntegrante.setVisibility(View.VISIBLE);
                    lstEmailIntegrantes.setVisibility(View.VISIBLE);
                    txtNomeBanda.setText("Nome da Banda:");
                }else{
                    btnAddIntegrante.setVisibility(View.GONE);
                    edtEmailIntegrante.setVisibility(View.GONE);
                    txtEmailIntegrante.setVisibility(View.GONE);
                    lstEmailIntegrantes.setVisibility(View.GONE);
                    txtNomeBanda.setText("Nome de Apresentação:");
                }
            }
        });


        btnAddIntegrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailIntegrante.getText().toString().trim();
                if (email.isEmpty())
                    Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Digite um email para ser adicionado");
                else{
                    if(isEmailValido(email)) {
                        if (validarEmailInserido(email)) {
                            adapterIntegrantes.add(email);
                            adapterIntegrantes.notifyDataSetChanged();
                            edtEmailIntegrante.setText("");
                        } else {
                            Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Este email já foi adicionado");
                        }
                    }else {
                        Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Formato de email inválido");
                    }
                }
            }
        });

        btnCadastrarBanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCamposValidos()){
                    BandaEntity banda = montarObjeto();
                    if(tipoEscolhido().equals("Artista Solo")) banda.setConvite(new ArrayList<>());
                    BandaDAO dao = new BandaDAO();
                    dao.cadastrarNovaBanda(banda, TelaCriacaoBanda.this);
                }
            }
        });

        lstEmailIntegrantes.setAdapter(adapterIntegrantes);

        btnVoltarBanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lstEmailIntegrantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(TelaCriacaoBanda.this);
                String item = adapterIntegrantes.getItem(position);
                adb.setTitle("Remover convidado?");
                adb.setMessage("Deseja remover " + item + " da lista?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        adapterIntegrantes.remove(item);
                        adapterIntegrantes.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });
    }

    private boolean isCamposValidos(){
        if(edtNomeBanda.getText().toString().trim().isEmpty()){
            Mensagem.notificar(TelaCriacaoBanda.this,"Atenção!","O nome é obrigatório");
            return false;
        }else if(spnGeneroBanda.getSelectedIndicies().isEmpty()) {
            Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Ao menos um gênero deve ser selecionado");
            return false;
        }else if(lstEmailIntegrantes.getCount() < 1 && tipoEscolhido().equals("Banda")){
            Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Ao menos um email deve ser informado");
            return false;
        }

        return true;
    }

    private boolean validarEmailInserido(String email){
        List<String> lista = new ArrayList<>();

        for(int i = 0; i < adapterIntegrantes.getCount(); i++){
            lista.add(adapterIntegrantes.getItem(i));
        }

        if(lista.contains(email)) return false;

        return true;
    }

    private String tipoEscolhido(){
        int r = rgpCadastroTipoBandaSolo.getCheckedRadioButtonId();
        RadioButton b = (RadioButton) findViewById(r);
        return b.getText().toString();
    }

    private BandaEntity montarObjeto(){
        List<String> integrantes = new ArrayList<>();

        for(int i = 0; i < adapterIntegrantes.getCount(); i++){
            integrantes.add(adapterIntegrantes.getItem(i));
        }

        List<ConviteEntity> convite = integrantes.stream().map(e -> new ConviteEntity(
                e, StatusConvite.ABERTO.name(), DefinirDatas.dataAtual()
        )).collect(Collectors.toList());


        return new BandaEntity(
                edtNomeBanda.getText().toString().trim()
                ,getIntent().getStringExtra("idUsuario")
                ,DefinirDatas.dataAtual()
                ,spnGeneroBanda.getSelectedStrings().stream().collect(Collectors.joining(","))
                ,new ArrayList<>()
                ,"SIM"
                ,convite
                ,tipoEscolhido()
        );
    }

    private boolean isEmailValido(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}