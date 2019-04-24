package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tcc.R;

public class TelaInicialMusico extends AppCompatActivity {

    private TextView txtNomeMusico;
    private TextView txtNomeBandaInicialMusico;
    private TextView txtPerfilInicialMusico;
    private TextView txtPesquisarInicialMusico;
    private TextView txtPropostasInicialMusico;
    private TextView txtAgendaInicialMusico;
    private TextView txtHistoricoInicialMusico;
    private TextView txtVisualizacaoInicialMusico;
    private TextView txtSairInicialMusico;
    private TextView txtConviteInicialMusico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_musico);

        final Intent telaPesquisaMusico = new Intent(this,TelaPesquisaMusico.class);
        final Intent telaLogin = new Intent(this, TelaInicial.class);

        txtNomeMusico = findViewById(R.id.txtNomeMusico);
        txtNomeBandaInicialMusico = findViewById(R.id.txtNomeBandaInicialMusico);
        txtPerfilInicialMusico = findViewById(R.id.txtPerfilInicialMusico);
        txtPesquisarInicialMusico = findViewById(R.id.txtPesquisarInicialMusico);
        txtPropostasInicialMusico = findViewById(R.id.txtPropostasInicialMusico);
        txtAgendaInicialMusico = findViewById(R.id.txtAgendaInicialMusico);
        txtHistoricoInicialMusico = findViewById(R.id.txtHistoricoInicialMusico);
        txtVisualizacaoInicialMusico = findViewById(R.id.txtVisualizacaoInicialMusico);
        txtSairInicialMusico = findViewById(R.id.txtSairInicialMusico);
        txtConviteInicialMusico = findViewById(R.id.txtConviteInicialMusico);

        txtNomeMusico.setText("Olá " + preencherNomeUsuario() + "!");

        final Intent convidarMembro = new Intent(this, TelaConvite.class);

        txtConviteInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(convidarMembro);
            }
        });

        txtPesquisarInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaPesquisaMusico);
            }
        });
        txtSairInicialMusico.setOnClickListener(new View.OnClickListener() {
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
