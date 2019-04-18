package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tcc.R;

public class TelaEscolhaCadastro extends AppCompatActivity {
    private Button btnEspectador;
    private Button btnMusico;
    private Button btnEstabelecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        btnEspectador = findViewById(R.id.btnEspectador);
        btnMusico = findViewById(R.id.btnMusico);
        btnEstabelecimento = findViewById(R.id.btnEstabelecimento);

        final Intent telacadastroespectador = new Intent(this, TelaCadastroEspectador.class);
        final Intent telacadastromusico = new Intent(this, TelaCadastroMusico.class);
        final Intent telacadastroestabelecimento = new Intent(this, TelaCadastroEstabelecimento.class);

        btnEspectador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telacadastroespectador);
            }
        });
        btnMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telacadastromusico);
            }
        });
        btnEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telacadastroestabelecimento);
            }
        });
    }
}
