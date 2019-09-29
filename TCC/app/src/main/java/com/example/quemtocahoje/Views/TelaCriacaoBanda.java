package com.example.quemtocahoje.Views;

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

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.GeneroMusical;
import com.example.quemtocahoje.Model.BandaDAO;
import com.example.quemtocahoje.Persistencia.Entity.BandaEntity;
import com.example.quemtocahoje.Spinner.MultiSelectionSpinner;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelaCriacaoBanda extends AppCompatActivity {

    private MultiSelectionSpinner spnGeneroBanda;
    private ListView lstEmailIntegrantes;
    private FloatingActionButton btnAddIntegrante;
    private Button btnCadastrarBanda;
    private Button btnVoltarBanda;
    private EditText edtNomeBanda;
    private EditText edtEmailIntegrante;
    private ArrayAdapter<String> adapterIntegrantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criacao_banda);

        btnAddIntegrante = findViewById(R.id.btnAddIntegrante);
        btnVoltarBanda = findViewById(R.id.btnVoltarBanda);
        btnCadastrarBanda = findViewById(R.id.btnCadastrarBanda);
        edtNomeBanda = findViewById(R.id.edtNomeBanda);
        edtEmailIntegrante = findViewById(R.id.edtEmailIntegrante);
        spnGeneroBanda = findViewById(R.id.spnGeneroBanda);

        final List<String> adapter = GeneroMusical.getGenerosMusicais();
        spnGeneroBanda.setItems(adapter);

        adapterIntegrantes = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1);
        lstEmailIntegrantes = findViewById(R.id.lstEmailIntegrantes);



        btnAddIntegrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailIntegrante.getText().toString().trim();
                if (email.isEmpty())
                    Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Digite um email para ser adicionado");
                else{
                    if (validarEmailInserido(email)) {
                        adapterIntegrantes.add(email);
                        adapterIntegrantes.notifyDataSetChanged();
                        edtEmailIntegrante.setText("");
                    } else Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Este email já foi adicionado");
                }
            }
        });

        btnCadastrarBanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCamposValidos()){
                    BandaEntity banda = montarObjeto();
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
                adb.setMessage("Deseja remover o email " + item + " da lista?");
                final int positionToRemove = position;
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
            Mensagem.notificar(TelaCriacaoBanda.this,"Atenção!","O nome da banda é obrigatório");
            return false;
        }else if(spnGeneroBanda.getSelectedIndicies().isEmpty()) {
            Mensagem.notificar(TelaCriacaoBanda.this, "Atenção!", "Ao menos um gênero deve ser selecionado");
            return false;
        }else if(lstEmailIntegrantes.getCount() < 1){
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

    private BandaEntity montarObjeto(){
        List<String> integrantes = new ArrayList<>();
        for(int i = 0; i < adapterIntegrantes.getCount(); i++){
            integrantes.add(adapterIntegrantes.getItem(i));
        }

        return new BandaEntity(
                edtNomeBanda.getText().toString().trim(),
                getIntent().getStringExtra("idUsuario"),
                DefinirDatas.dataAtual(),
                spnGeneroBanda.getSelectedStrings(),
                integrantes,
                "SIM"
        );
    }
}