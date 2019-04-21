package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

public class TelaCadastroMusico extends AppCompatActivity {

    private EditText edtNomeMusico;
    private EditText edtNomeArtisticoMusico;
    private EditText edtCelularMusico;
    private EditText edtEmailMusico;
    private EditText edtLoginMusico;
    private EditText edtSenhaMusico;
    private EditText edtConfirmarSenhaMusico;
    private EditText edtDescricaoMusico;
    private Button btnCadastrarMusico;
    private Button btnCancelarMusico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_musico);

        final Intent telaEndereco = new Intent(this, TelaEndereco.class);

        edtNomeMusico = findViewById(R.id.edtNomeMusico);
        edtNomeArtisticoMusico = findViewById(R.id.edtNomeArtisticoMusico);
        edtCelularMusico = findViewById(R.id.edtCelularMusico);
        edtEmailMusico = findViewById(R.id.edtEmailMusico);
        edtLoginMusico = findViewById(R.id.edtLoginMusico);
        edtSenhaMusico = findViewById(R.id.edtSenhaMusico);
        edtConfirmarSenhaMusico = findViewById(R.id.edtConfirmarSenhaMusico);
        edtDescricaoMusico = findViewById(R.id.edtDescricaoMusico);
        btnCadastrarMusico = findViewById(R.id.btnCadastrarMusico);
        btnCancelarMusico = findViewById(R.id.btnCancelarMusico);

        btnCadastrarMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO putExtra dados validados
                if(isCamposValidos(edtNomeMusico,edtNomeArtisticoMusico,edtCelularMusico,edtEmailMusico,edtLoginMusico,edtSenhaMusico,edtConfirmarSenhaMusico,edtDescricaoMusico))
                {
                    if(isSenhaCorreta(edtSenhaMusico,edtConfirmarSenhaMusico))
                    {
                        telaEndereco.putExtra("TipoUsuario", TipoUsuario.MUSICO.getValor());
                        startActivity(telaEndereco);
                        Toast.makeText(TelaCadastroMusico.this, "Sucesso!", Toast.LENGTH_LONG).show();
                    }
                    else
                        Mensagem.notificar(TelaCadastroMusico.this,"Senhas diferentes","As senhas diferem uma da outra.");

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
    private boolean isCamposValidos(EditText edtNomeMusico, EditText edtNomeArtisticoMusico, EditText edtCelularMusico, EditText edtEmailMusico, EditText edtLoginMusico, EditText edtSenhaMusico, EditText edtConfirmarSenhaMusico, EditText edtDescricaoMusico)
    {
        if(edtNomeMusico == null || edtNomeMusico.getText().toString().trim().equals("")
        || edtNomeArtisticoMusico == null || edtNomeArtisticoMusico.getText().toString().trim().equals("")
        || edtCelularMusico == null || edtCelularMusico.getText().toString().trim().equals("")
        || edtEmailMusico == null || edtEmailMusico.getText().toString().trim().equals("")
        || edtLoginMusico == null || edtLoginMusico.getText().toString().trim().equals("")
        || edtSenhaMusico == null || edtSenhaMusico.getText().toString().trim().equals("")
        || edtConfirmarSenhaMusico == null || edtConfirmarSenhaMusico.getText().toString().trim().equals("")
        || edtDescricaoMusico == null || edtDescricaoMusico.getText().toString().trim().equals(""))
            return false;
        return  true;
    }
    private boolean isSenhaCorreta(EditText edtSenhaMusico, EditText edtConfirmarSenhaMusico)
    {
        if(edtSenhaMusico.getText().toString().equals(edtConfirmarSenhaMusico.getText().toString()))
            return true;
        return false;
    }
}
