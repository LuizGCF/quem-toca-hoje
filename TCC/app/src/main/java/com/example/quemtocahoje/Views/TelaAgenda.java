package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.tcc.R;

public class TelaAgenda extends Activity {

    private ImageButton imgSelecionarDataProposta;
    private ImageButton imgSelecionarHorarioInicio;
    private ImageButton imgSelecionarHorarioFim;
    private EditText txtDataPropostaEscolhida;
    private EditText edtLocalProposta;
    private EditText edtCacheProposta;
    private EditText edtDescricaoProposta;
    private Button btnEnviarProposta;
    private Button btnVoltarProposta;
    private Button btnRecusarProposta;
    private EditText edtHorarioProposta;
    private EditText edtHorarioFim;
    private TextView lblNomeDestinatario;
    private TextView txtDescricaoProposta;
    private TextView txtDePara;
    private TextView txtCache;
    //Tela com o resultado ao clicar em algum valor da agenda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_proposta_enviar);

        edtHorarioProposta = findViewById(R.id.edtHorarioProposta);
        imgSelecionarDataProposta = findViewById(R.id.imgSelecionarDataProposta);
        txtDataPropostaEscolhida = findViewById(R.id.txtDataPropostaEscolhida);
        edtLocalProposta = findViewById(R.id.edtLocalProposta);
        edtCacheProposta = findViewById(R.id.edtCacheProposta);
        edtDescricaoProposta = findViewById(R.id.edtDescricaoProposta);
        btnEnviarProposta = findViewById(R.id.btnEnviarProposta);
        btnVoltarProposta = findViewById(R.id.btnVoltarProposta);
        btnRecusarProposta = findViewById(R.id.btnRecusarProposta);
        edtHorarioFim = findViewById(R.id.edtHorarioFim);
        imgSelecionarHorarioInicio = findViewById(R.id.imgSelecionarHorarioInicio);
        imgSelecionarHorarioFim = findViewById(R.id.imgSelecionarHorarioFim);
        lblNomeDestinatario = findViewById(R.id.lblNomeDestinatario);
        txtDescricaoProposta = findViewById(R.id.txtDescricaoProposta);
        txtDePara = findViewById(R.id.txtDePara);
        txtCache = findViewById(R.id.txtCache);

        PropostaEntity proposta = (PropostaEntity) getIntent().getSerializableExtra("proposta");
        if(proposta != null)
        {
            edtHorarioProposta.setEnabled(false);
            imgSelecionarDataProposta.setEnabled(false);
            txtDataPropostaEscolhida.setEnabled(false);
            edtLocalProposta.setEnabled(false);
            edtDescricaoProposta.setEnabled(false);
            edtHorarioFim.setEnabled(false);
            imgSelecionarHorarioInicio.setEnabled(false);
            imgSelecionarHorarioFim.setEnabled(false);
            lblNomeDestinatario.setEnabled(false);

            edtDescricaoProposta.setVisibility(View.GONE);
            txtDescricaoProposta.setVisibility(View.GONE);
            btnEnviarProposta.setVisibility(View.GONE);
            //txtDePara.setVisibility(View.GONE);
            txtCache.setVisibility(View.GONE);
            edtCacheProposta.setVisibility(View.GONE);

            edtHorarioProposta.setText(proposta.getHorarioInicio());
            txtDataPropostaEscolhida.setText(proposta.getDataEnvioProposta());
            edtLocalProposta.setText(proposta.getLocal());
            txtDePara.setText(proposta.getIdBanda());
            //edtCacheProposta.setText(proposta.getCache().toString());
            //edtDescricaoProposta.setText(proposta.getDescricao());
            edtHorarioFim.setText(proposta.getHorarioFim());
            lblNomeDestinatario.setText(proposta.getHorarioFim());

        }
    }
}
