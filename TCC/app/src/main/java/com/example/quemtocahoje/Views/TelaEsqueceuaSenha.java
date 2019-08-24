package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.quemtocahoje.Enum.StatusToken;
import com.example.quemtocahoje.Persistencia.Entity.TokenEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Email;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Utility.Token;
import com.example.tcc.R;

public class TelaEsqueceuaSenha extends AppCompatActivity {

    LinearLayout layoutToken;
    Button btnConfirmar;
    Button btnToken;
    EditText email;
    EditText token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_esqueceua_senha);

        layoutToken = findViewById(R.id.CamposToken);
        btnConfirmar = findViewById(R.id.btnConfirmarRecuperarSenha);
        btnToken = findViewById(R.id.btnValidarRecuperarSenha);
        email = findViewById(R.id.edtEmailEsqueceu);
        token = findViewById(R.id.edtTokenEsqueceu);
        final Intent telaRedefinirSenha = new Intent(this, TelaRedefinirSenha.class);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCampoValido(email)){
                    final Long idUser = isUsuarioCadastrado(email);
                    if(idUser != null){
                        enviarEmail(idUser);
                        btnToken.setVisibility(View.VISIBLE);
                        layoutToken.setVisibility(View.VISIBLE);

                        btnToken.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(validarToken(idUser)) {
                                    telaRedefinirSenha.putExtra("idUser", idUser);
                                    startActivity(telaRedefinirSenha);
                                }
                                else
                                    Mensagem.notificar(TelaEsqueceuaSenha.this,"Token inválido","O token digitado é inválido.");
                            }
                        });

                    }else{
                        Mensagem.notificar(TelaEsqueceuaSenha.this,"Usuário não cadastrado","O usuário não existe em nosso sistema.");
                    }
                }else{
                    Mensagem.notificar(TelaEsqueceuaSenha.this,"Campo Inválido","O campo não foi preenchido corretamente.");
                }
            }
        });

    }

    protected boolean isCampoValido(EditText email){
        if(email == null || email.getText().toString().equals(""))
            return false;

        return true;
    }

    //TODO reimplementar com Firebase
    protected Long isUsuarioCadastrado(EditText email){
        String e = email.getText().toString();
        Long id = 0L; //dado do firebase;
        if(id != null)
            return id;

        return null;
    }

    //TODO reimplementar com Firebase
    private void enviarEmail(Long idUser) {
        //Conteudo do email
        String destinatario = email.getText().toString().trim();
        String subject = "Redefinir senha";
        String token = Token.gerarToken();
        String message = "Para redefinir sua senha, insira o seguinte token no seu aplicativo: " + token;
        persistirToken(idUser, destinatario, token);

        //SendMail object
        Email sm = new Email(this, destinatario, subject, message);

        sm.execute();
    }

    //TODO reimplementar com Firebase
    private boolean validarToken(Long idEspec){
        Long id = 0L; // Banco.getDatabase(getApplicationContext()).tokenDao().findAutenticacaoIdByToken(token.getText().toString());
        if(id == idEspec){
            //Banco.getDatabase(getApplicationContext()).tokenDao().updateTokenValido(StatusToken.INATIVO.name(), token.getText().toString());
            return true;
        }
        return false;

    }

    //TODO reimplementar com Firebase
    private void persistirToken(Long idUser, String email, String token){
        TokenEntity tokenEntity = new TokenEntity(idUser,email, token, StatusToken.ABERTO.name(), DefinirDatas.dataAtual());
//        Banco.getDatabase(getApplicationContext()).tokenDao().insertToken(tokenEntity);
    }
}
