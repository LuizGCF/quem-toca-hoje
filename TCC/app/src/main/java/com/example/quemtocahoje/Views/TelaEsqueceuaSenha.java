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
import android.widget.Toast;

import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Utility.Email;
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
                    if(isUsuarioCadastrado(email)){
//                        enviarEmail();
                        btnToken.setVisibility(View.VISIBLE);
                        layoutToken.setVisibility(View.VISIBLE);

                    }else{
                        //TODO mensagem usuario nao cadastrado
                    }
                }else{
                    //TODO mensagem campo invalido
                }
            }
        });

    }

    protected boolean isCampoValido(EditText email){
        if(email == null || email.getText().toString().equals(""))
            return false;

        return true;
    }

    protected boolean isUsuarioCadastrado(EditText email){
        String e = email.getText().toString();
        if(Banco.getDatabase(getApplicationContext()).autenticacaoDao().findAutenticacaoByLogin(e) != null)
            return true;

        return false;
    }

    private void enviarEmail() {
        //Getting content for email
        String destinatario = email.getText().toString().trim();
        String subject = "Redefinir senha";
        String message = "Para redefinir sua senha, insira o seguinte token no seu aplicativo: " + Token.gerarToken();

        //Creating SendMail object
        Email sm = new Email(this, destinatario, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
