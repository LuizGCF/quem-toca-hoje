package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.tcc.R;
import com.google.firebase.auth.FirebaseAuth;

public class TelaInicialEspectador extends AppCompatActivity {
    private TextView txtNomeEspectador;
    private TextView txtPesquisaInicialEspectador;
    private TextView txtPerfilInicialEspectador;
    private TextView txtSairInicialEstabelecimento;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_espectador);

        //Toolbar para fazer o logout
        //Toolbar toolbar = findViewById(R.id.);

        final Intent telaLogin = new Intent(this, TelaInicial.class);
        txtNomeEspectador = findViewById(R.id.txtNomeEspectador);
        txtPesquisaInicialEspectador = findViewById(R.id.txtPesquisaInicialEspectador);
        txtPerfilInicialEspectador = findViewById(R.id.txtPerfilInicialEspectador);
        txtSairInicialEstabelecimento = findViewById(R.id.txtSairInicialEstabelecimento);

        txtNomeEspectador.setText("Olá "+preencherNomeUsuario() + "!");

        txtSairInicialEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaLogin);
                finishAffinity();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        /*MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        Button a = (Button) menu.findItem(R.id.logout);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        getMenuInflater().inflate(R.menu.menu_login,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,TelaInicial.class));
                finish();
                return true;
        }
        return false;
    }

    private String preencherNomeUsuario()//passar o intent assim ao cadastrar tbm
    {
       AutenticacaoDTO dto = (AutenticacaoDTO) getIntent().getSerializableExtra("dtoAutenticacao");
        return dto.getNome();
    }
}
