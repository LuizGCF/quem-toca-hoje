package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

public class TelaCadastroMusico extends AppCompatActivity {

    private EditText edtNomeArtisticoMusico;
    private EditText edtCelularMusico;
    private EditText edtCidadeMusico;
    private EditText edtDescricaoMusico;

    private Button btnCadastrarMusico;
    private Button btnCancelarMusico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_musico);

        final Intent telaUpload = new Intent(this, TelaUpload.class);

        edtNomeArtisticoMusico = findViewById(R.id.edtNomeArtisticoMusico);
        edtCelularMusico = findViewById(R.id.edtCelularMusico);
        edtCidadeMusico = findViewById(R.id.edtCidadeMusico);
        edtDescricaoMusico = findViewById(R.id.edtDescricaoMusico);

        btnCadastrarMusico = findViewById(R.id.btnCadastrarMusico);
        btnCancelarMusico = findViewById(R.id.btnCancelarMusico);

        btnCadastrarMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCamposValidos(edtNomeArtisticoMusico,edtCelularMusico,edtCidadeMusico,edtDescricaoMusico))
                {
                    MusicoEntity m = prepararObjetoMusico();
                    telaUpload.putExtra("tipoUsuario", TipoUsuario.MUSICO.name());
                    telaUpload.putExtra("objetoAutenticacao", getIntent().getSerializableExtra("objetoAutenticacao"));
                    telaUpload.putExtra("objetoMusico", m);
                    startActivity(telaUpload);
                }
                else
                    Mensagem.notificar(TelaCadastroMusico.this,"Campos Inválidos","Um ou mais campos não foram preenchidos corretamente");
            }
        });

        btnCancelarMusico.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private boolean isCamposValidos(EditText edtNomeArtisticoMusico, EditText edtCelularMusico, EditText edtCidadeMusico, EditText edtDescricaoMusico)
    {
        if(edtNomeArtisticoMusico == null || edtNomeArtisticoMusico.getText().toString().trim().equals("")
        || edtCelularMusico == null || edtCelularMusico.getText().toString().trim().equals("")
        || edtCidadeMusico == null || edtCidadeMusico.getText().toString().trim().equals("")
        || edtDescricaoMusico == null || edtDescricaoMusico.getText().toString().trim().equals(""))
            return false;

        return  true;
    }

    private MusicoEntity prepararObjetoMusico(){
        EspectadorEntity es = (EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador");
        MusicoEntity m = new MusicoEntity(null
                ,es.getNomeEspectador()
                ,edtNomeArtisticoMusico.getText().toString().trim()
                ,edtCelularMusico.getText().toString().trim()
                ,es.getDataCriacao()
                ,edtDescricaoMusico.getText().toString().trim()
        );

        return m;
    }
}
