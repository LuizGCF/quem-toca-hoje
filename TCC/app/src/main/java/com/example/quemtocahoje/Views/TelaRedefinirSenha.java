package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quemtocahoje.Utility.AESCrypt;
import com.example.quemtocahoje.Utility.Mensagem;
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
                        //Utilizei o Toast mesmo para mensagens de sucesso
                        Toast.makeText(TelaRedefinirSenha.this,"Sucesso!",Toast.LENGTH_LONG).show();
                        startActivity(telaInicial);
                    }
                    else
                        Mensagem.notificar(TelaRedefinirSenha.this,"Senhas Divergentes","As senhas digitadas divergem uma da outra.");
                }
                else
                    Mensagem.notificar(TelaRedefinirSenha.this,"Campos Inválidos","Um ou mais campos não foram preenchidos corretamente.");
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
    //TODo reimplementar com Firebase
    private void atualizarSenha(){
        try {
            Long idUser = getIntent().getLongExtra("idUser", -1L);
            String senha = AESCrypt.encrypt(novaSenha.getText().toString());
            //Banco.getDatabase(getApplicationContext()).autenticacaoDao().updateSenhaById(senha, idUser);
        }catch(Exception e) {
            e.getMessage();
        }
    }
}
