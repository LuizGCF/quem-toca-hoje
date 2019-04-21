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

public class TelaCadastroEstabelecimento extends AppCompatActivity {

    private EditText edtRazaoSocial;
    private EditText edtCelular;
    private EditText edtCNPJ;
    private EditText edtEmail;
    private EditText edtLoginEstabelecimento;
    private EditText edtSenhaEstabelecimento;
    private EditText edtConfirmarSenhaSenhaEstabelecimento;
    private Button btnCadastrarEstabelecimento;
    private Button btnCancelarEstabelecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_estabelecimento);

        final Intent telaEndereco = new Intent(this, TelaEndereco.class);

        edtRazaoSocial = findViewById(R.id.edtRazaoSocial);
        edtCelular = findViewById(R.id.edtCelular);
        edtCNPJ = findViewById(R.id.edtCNPJ);
        edtEmail = findViewById(R.id.edtEmail);
        edtLoginEstabelecimento = findViewById(R.id.edtLoginEstabelecimento);
        edtSenhaEstabelecimento = findViewById(R.id.edtSenhaEstabelecimento);
        edtConfirmarSenhaSenhaEstabelecimento = findViewById(R.id.edtConfirmarSenhaSenhaEstabelecimento);
        btnCadastrarEstabelecimento = findViewById(R.id.btnCadastrarEstabelecimento);
        btnCancelarEstabelecimento = findViewById(R.id.btnCancelarEstabelecimento);

        btnCadastrarEstabelecimento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(isCamposValidos(edtRazaoSocial,edtCelular,edtCNPJ,edtEmail,edtLoginEstabelecimento,edtSenhaEstabelecimento,edtConfirmarSenhaSenhaEstabelecimento))
                {
                    if(isSenhaCorreta(edtSenhaEstabelecimento, edtConfirmarSenhaSenhaEstabelecimento))
                    {
                        telaEndereco.putExtra("TipoUsuario", TipoUsuario.ESTABELECIMENTO.getValor());
                        startActivity(telaEndereco);
                        Toast.makeText(TelaCadastroEstabelecimento.this,"Sucesso!",Toast.LENGTH_LONG).show();
                    }
                    else
                        Mensagem.notificar(TelaCadastroEstabelecimento.this,"Senhas diferentes","As senhas diferem uma da outra.");
                }
                else
                    Mensagem.notificar(TelaCadastroEstabelecimento.this,"Campos inválidos","Um ou mais campos não foram preenchidos corretamente.");
                //TODO putExtra dados validados

            }
        });

        btnCancelarEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isCamposValidos(EditText edtRazaoSocial, EditText edtCelular, EditText edtCNPJ, EditText edtEmail, EditText edtLoginEstabelecimento, EditText edtSenhaEstabelecimento,EditText edtConfirmarSenhaSenhaEstabelecimento)
    {
        if(edtRazaoSocial ==null || edtRazaoSocial.getText().toString().trim().equals("")
        || edtCelular ==null || edtCelular.getText().toString().trim().equals("")
        || edtCNPJ ==null || edtCNPJ.getText().toString().trim().equals("")
        || edtEmail ==null || edtEmail.getText().toString().trim().equals("")
        || edtLoginEstabelecimento ==null || edtLoginEstabelecimento.getText().toString().trim().equals("")
        || edtSenhaEstabelecimento ==null || edtSenhaEstabelecimento.getText().toString().trim().equals("")
        || edtConfirmarSenhaSenhaEstabelecimento ==null || edtConfirmarSenhaSenhaEstabelecimento.getText().toString().trim().equals(""))
            return false;
        return true;
    }

    private boolean isSenhaCorreta(EditText edtSenhaEstabelecimento, EditText edtConfirmarSenhaSenhaEstabelecimento)
    {
        if(edtSenhaEstabelecimento.getText().toString().equals(edtConfirmarSenhaSenhaEstabelecimento.getText().toString()))
            return true;
        return false;
    }
}
