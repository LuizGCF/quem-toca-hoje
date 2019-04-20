package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tcc.R;

public class TelaInicialEstabelecimento extends AppCompatActivity {

    private TextView txtPesquisarInicialEstabelecimento;
    private TextView txtPropostasInicialEstabelecimento;
    private TextView txtAgendaInicialEstabelecimento;
    private TextView txtHistoricoInicialEstabelecimento;
    private TextView txtSairInicialEstabelecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_estabelecimento);

        txtPesquisarInicialEstabelecimento = findViewById(R.id.txtPesquisarInicialEstabelecimento);
        txtPropostasInicialEstabelecimento = findViewById(R.id.txtPropostasInicialEstabelecimento);
        txtAgendaInicialEstabelecimento = findViewById(R.id.txtAgendaInicialEstabelecimento);
        txtHistoricoInicialEstabelecimento = findViewById(R.id.txtHistoricoInicialEstabelecimento);
        txtSairInicialEstabelecimento = findViewById(R.id.txtSairInicialEstabelecimento);


    }
}
