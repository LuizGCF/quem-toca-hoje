package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tcc.R;

public class TelaInicialEspectador extends AppCompatActivity {
    private TextView txtNomeEspectador;
    private TextView txtPesquisaInicialEspectador;
    private TextView txtPerfilInicialEspectador;
    private TextView txtSairInicialEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_espectador);
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

    private String preencherNomeUsuario()
    {
        return getIntent().getStringExtra("nome");
    }
}
