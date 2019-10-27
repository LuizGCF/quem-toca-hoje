package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Model.BandaDAO;
import com.example.tcc.R;
import com.google.firebase.auth.FirebaseAuth;

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
    private TextView txtMeusConvitesInicialMusico;
    private TextView txtCadastrarBandaInicialMusico;

    private TextView txtAvaliacaoEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_musico);
        getSupportActionBar().hide();

        final Intent telaPesquisaMusico = new Intent(this,TelaPesquisaMusico.class);
        final Intent telaLogin = new Intent(this, TelaInicial.class);

        final Intent telaNovaBanda = new Intent(this, TelaCriacaoBanda.class);

        final Intent telaAvaliacaoEstabelecimento = new Intent(this, TelaAvalicaoEstabelecimento.class);

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
        txtMeusConvitesInicialMusico = findViewById(R.id.txtMeusConvitesInicialMusico);
        txtCadastrarBandaInicialMusico = findViewById(R.id.txtCadastrarBandaInicialMusico);

        txtAvaliacaoEstabelecimento = findViewById(R.id.txtAvaliacaoEstabelecimento);

        AutenticacaoDTO dto = (AutenticacaoDTO) getIntent().getSerializableExtra("dtoAutenticacao");

        txtNomeMusico.setText("Olá " + dto.getNome() + "!");

        final Intent convidarMembro = new Intent(this, TelaConvite.class);

        txtConviteInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(convidarMembro);
            }
        });

        txtMeusConvitesInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BandaDAO dao = new BandaDAO();
                dao.recuperarConvites(dto.getEmail(), TelaInicialMusico.this);
            }
        });

        txtCadastrarBandaInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaNovaBanda.putExtra("idUsuario", dto.getIdAutenticacao());
                startActivity(telaNovaBanda);
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
                FirebaseAuth.getInstance().signOut();
                startActivity(telaLogin);
                finishAffinity();
            }
        });

        txtAvaliacaoEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(telaAvaliacaoEstabelecimento);
            }
        });
    }
}
