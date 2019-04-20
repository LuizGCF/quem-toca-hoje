package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tcc.R;

public class TelaInicialMusico extends AppCompatActivity {

    private TextView txtNomeBandaInicialMusico;
    private TextView txtPerfilInicialMusico;
    private TextView txtPesquisarInicialMusico;
    private TextView txtPropostasInicialMusico;
    private TextView txtAgendaInicialMusico;
    private TextView txtHistoricoInicialMusico;
    private TextView txtVisualizacaoInicialMusico;
    private TextView txtSairInicialMusico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_musico);

        txtNomeBandaInicialMusico = findViewById(R.id.txtNomeBandaInicialMusico);
        txtPerfilInicialMusico = findViewById(R.id.txtPerfilInicialMusico);
        txtPesquisarInicialMusico = findViewById(R.id.txtPesquisarInicialMusico);
        txtPropostasInicialMusico = findViewById(R.id.txtPropostasInicialMusico);
        txtAgendaInicialMusico = findViewById(R.id.txtAgendaInicialMusico);
        txtHistoricoInicialMusico = findViewById(R.id.txtHistoricoInicialMusico);
        txtVisualizacaoInicialMusico = findViewById(R.id.txtVisualizacaoInicialMusico);
        txtSairInicialMusico = findViewById(R.id.txtSairInicialMusico);
    }
}
