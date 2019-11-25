package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Model.AvaliacaoDAO;
import com.example.quemtocahoje.Model.PropostaDAO;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.tcc.R;
import com.google.firebase.auth.FirebaseAuth;

public class TelaInicialEstabelecimento extends AppCompatActivity {

    private TextView txtNomeEstabelecimento;
    private TextView txtPesquisarInicialEstabelecimento;
    private TextView txtPropostasInicialEstabelecimento;
    private TextView txtAgendaInicialEstabelecimento;
    private TextView txtHistoricoInicialEstabelecimento;
    private TextView txtSairInicialEstabelecimento;
    private TextView txtAvaliacaoMusico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_estabelecimento);
        getSupportActionBar().hide();
        final Intent telaLogin = new Intent(this, TelaInicial.class);
        final Intent telaPesquisaMusico = new Intent(this,TelaPesquisaMusico.class);
        final Intent telaProposta = new Intent(this,TelaProposta.class);
        final Intent telaAvaliacaoMusico = new Intent(this,TelaAvaliacaoMusico.class);

        txtNomeEstabelecimento = findViewById(R.id.txtNomeEstabelecimento);
        txtPesquisarInicialEstabelecimento = findViewById(R.id.txtPesquisarInicialEstabelecimento);
        txtPropostasInicialEstabelecimento = findViewById(R.id.txtPropostasInicialEstabelecimento);
        txtAgendaInicialEstabelecimento = findViewById(R.id.txtAgendaInicialEstabelecimento);
        txtHistoricoInicialEstabelecimento = findViewById(R.id.txtHistoricoInicialEstabelecimento);
        txtSairInicialEstabelecimento = findViewById(R.id.txtSairInicialEstabelecimento);
        txtAvaliacaoMusico = findViewById(R.id.txtAvaliacaoMusico);

        txtNomeEstabelecimento.setText("Olá " + preencherNomeUsuario() + "!");

        txtPesquisarInicialEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaPesquisaMusico);
            }
        });

        txtSairInicialEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
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

        txtAvaliacaoMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaAvaliacaoMusico);
            }
        });

        txtHistoricoInicialEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropostaDAO dao = new PropostaDAO();
                dao.recuperarEventos("estab", TipoUsuario.ESTABELECIMENTO.name(), "HISTORICO", TelaInicialEstabelecimento.this);

            }
        });
    }

    private String preencherNomeUsuario()
    {
        AutenticacaoDTO dto = (AutenticacaoDTO) getIntent().getSerializableExtra("dtoAutenticacao");
        return dto.getNome();
    }
}
