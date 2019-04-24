package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tcc.R;

public class TelaInicialEstabelecimento extends AppCompatActivity {

    private TextView txtNomeEstabelecimento;
    private TextView txtPesquisarInicialEstabelecimento;
    private TextView txtPropostasInicialEstabelecimento;
    private TextView txtAgendaInicialEstabelecimento;
    private TextView txtHistoricoInicialEstabelecimento;
    private TextView txtSairInicialEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_estabelecimento);
        final Intent telaLogin = new Intent(this, TelaInicial.class);
        final Intent telaPesquisaEstabelecimento = new Intent(this,TelaPesquisaEstabelecimento.class);
        final Intent telaProposta = new Intent(this,TelaProposta.class);
        txtNomeEstabelecimento = findViewById(R.id.txtNomeEstabelecimento);
        txtPesquisarInicialEstabelecimento = findViewById(R.id.txtPesquisarInicialEstabelecimento);
        txtPropostasInicialEstabelecimento = findViewById(R.id.txtPropostasInicialEstabelecimento);
        txtAgendaInicialEstabelecimento = findViewById(R.id.txtAgendaInicialEstabelecimento);
        txtHistoricoInicialEstabelecimento = findViewById(R.id.txtHistoricoInicialEstabelecimento);
        txtSairInicialEstabelecimento = findViewById(R.id.txtSairInicialEstabelecimento);

        txtNomeEstabelecimento.setText("Olá " + preencherNomeUsuario() + "!");

        txtPesquisarInicialEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaPesquisaEstabelecimento);
            }
        });

        txtSairInicialEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaLogin);
                finishAffinity();
            }
        });

        txtPropostasInicialEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaProposta);
            }
        });
    }

    private String preencherNomeUsuario()
    {
        return getIntent().getStringExtra("nome");
    }
}
