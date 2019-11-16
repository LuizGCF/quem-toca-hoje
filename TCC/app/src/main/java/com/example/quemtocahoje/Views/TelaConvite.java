package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quemtocahoje.Enum.StatusConvite;
import com.example.quemtocahoje.Persistencia.Entity.ConviteEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Email;
import com.example.tcc.R;

public class TelaConvite extends AppCompatActivity {

    private EditText edtEmailConvite;
    private Button btnEnviarConvite;
    private Button btnCancelarConvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_convite);
        getSupportActionBar().hide();

        edtEmailConvite = findViewById(R.id.edtEmailConvite);
        btnEnviarConvite = findViewById(R.id.btnEnviarConvite);
        btnCancelarConvite = findViewById(R.id.btnCancelarConvite);


        btnEnviarConvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCampoValido()){
                    notificarUsuario();
                    edtEmailConvite.setText("");
                }

            }
        });

        btnCancelarConvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isCampoValido(){
        if(edtEmailConvite == null || edtEmailConvite.getText().toString().equals(""))
            return false;

        return true;

    }

    private void notificarUsuario(){
        //TODO verificar se o convidado não é cadastrado no banco do Firebase
        //if(Banco.getDatabase(getApplicationContext()).autenticacaoDao().findAutenticacaoByEmail(edtEmailConvite.getText().toString()) == null){
            String destinatario = edtEmailConvite.getText().toString().trim();
            String subject = "Convite para juntar-se à banda";
            String message = "Você foi convidado para uma banda. Instale nosso aplicativo para integrá-la!";

            Email sm = new Email(this, destinatario, subject, message);
            sm.execute();
        //}

        //TODO alterar o banda_id quando a tabela dela estiver pronta
        ConviteEntity c = new ConviteEntity(
                edtEmailConvite.getText().toString().trim()
                , StatusConvite.ABERTO.name()
                , DefinirDatas.dataAtual()
        );
        //TODO persistir convite
        //Banco.getDatabase(getApplicationContext()).conviteDao().insertConvite(c);
        Toast.makeText(TelaConvite.this,"Convite enviado!",Toast.LENGTH_LONG).show();
    }

}
