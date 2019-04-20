package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Utility.AESCrypt;
import com.example.tcc.R;

public class TelaRedefinirSenha extends AppCompatActivity {

    EditText novaSenha;
    EditText confirmarNovaSenha;
    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_redefinir_senha);

        novaSenha = findViewById(R.id.edtNovaSenha);
        confirmarNovaSenha = findViewById(R.id.edtConfirmarNovaSenha);
        btnConfirmar = findViewById(R.id.btnConfirmarRecuperarSenha);
        final Intent telaInicial = new Intent(this, TelaInicial.class);

                btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCamposValidos()){
                    if(isSenhaValida()){
                        atualizarSenha();
                        //TODO mensagem de sucesso
                        startActivity(telaInicial);
                    }//TODO else mensagem senhas divergentes
                }//TODO else mensagem campos invalidos
            }
        });


    }

    private boolean isCamposValidos(){
        if(novaSenha == null || novaSenha.getText().toString() == ""
        || confirmarNovaSenha == null || confirmarNovaSenha.getText().toString() == "")
            return false;

        return true;
    }

    private boolean isSenhaValida(){
        if(novaSenha.getText().toString().equals(novaSenha.getText().toString()))
            return true;

        return false;
    }

    private void atualizarSenha(){
        try {
            Long idUser = getIntent().getLongExtra("idUser", -1L);
            String senha = AESCrypt.encrypt(novaSenha.getText().toString());
            Banco.getDatabase(getApplicationContext()).autenticacaoDao().updateSenhaById(senha, idUser);
        }catch(Exception e) {
            e.getMessage();
        }
    }
}
