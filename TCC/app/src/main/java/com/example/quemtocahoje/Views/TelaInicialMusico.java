package com.example.quemtocahoje.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Model.AvaliacaoDAO;
import com.example.quemtocahoje.Model.BandaDAO;
import com.example.quemtocahoje.Model.PropostaDAO;
import com.example.quemtocahoje.Persistencia.Entity.BandaEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TelaInicialMusico extends AppCompatActivity {

    private TextView txtNomeMusico;
    private TextView txtNomeBandaInicialMusico;
    private TextView txtMensagensMusico;
    private TextView txtPesquisarInicialMusico;
    private TextView txtPropostasInicialMusico;
    private TextView txtAgendaInicialMusico;
    private TextView txtHistoricoInicialMusico;
    private TextView txtVisualizacaoInicialMusico;
    private TextView txtSairInicialMusico;
    private TextView txtConviteInicialMusico;
    private TextView txtMeusConvitesInicialMusico;
    private TextView txtCadastrarBandaInicialMusico;
    private TextView txtVisualizandoComo;
    private ValueEventListener valueEventListener;
    private boolean foiClick;

    private TextView txtAvaliacaoEstabelecimento;
    AutenticacaoDTO dto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_musico);
        getSupportActionBar().hide();

        final Intent telaPesquisaMusico = new Intent(this,TelaPesquisaMusico.class);
        final Intent telaLogin = new Intent(this, TelaInicial.class);

        final Intent telaNovaBanda = new Intent(this, TelaCriacaoBanda.class);

        final Intent telaAvaliacaoEstabelecimento = new Intent(this, TelaAvaliacoesPendentes.class);

        final Intent telaMensagensAtivas = new Intent(this,TelaMensagensAtivas.class);

        txtNomeMusico = findViewById(R.id.txtNomeMusico);
        txtNomeBandaInicialMusico = findViewById(R.id.txtNomeBandaInicialMusico);
        txtMensagensMusico = findViewById(R.id.txtMensagensMusico);
        txtPesquisarInicialMusico = findViewById(R.id.txtPesquisarInicialMusico);
        txtPropostasInicialMusico = findViewById(R.id.txtPropostasInicialMusico);
        txtAgendaInicialMusico = findViewById(R.id.txtAgendaInicialMusico);
        txtHistoricoInicialMusico = findViewById(R.id.txtHistoricoInicialMusico);
        txtVisualizacaoInicialMusico = findViewById(R.id.txtVisualizacaoInicialMusico);
        txtSairInicialMusico = findViewById(R.id.txtSairInicialMusico);
        txtConviteInicialMusico = findViewById(R.id.txtConviteInicialMusico);
        txtMeusConvitesInicialMusico = findViewById(R.id.txtMeusConvitesInicialMusico);
        txtCadastrarBandaInicialMusico = findViewById(R.id.txtCadastrarBandaInicialMusico);
        txtVisualizandoComo = findViewById(R.id.txtVisualizandoComo);

        txtAvaliacaoEstabelecimento = findViewById(R.id.txtAvaliacaoEstabelecimento);


        dto = (AutenticacaoDTO) getIntent().getSerializableExtra("dtoAutenticacao");

        txtNomeMusico.setText("Olá " + dto.getNome() + "!");

        //carregarBandaInicial();

        final Intent convidarMembro = new Intent(this, TelaConvite.class);

        txtMensagensMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaMensagensAtivas.putExtra("AutenticacaoDTO",dto);
                startActivity(telaMensagensAtivas);
            }
        });

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
            public void onClick(View v)
            {
                telaPesquisaMusico.putExtra("dtoAutenticacao",dto);

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
        //Desabilitar os 4 abaixo se não existir banda?
        txtAvaliacaoEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AvaliacaoDAO dao = new AvaliacaoDAO();
                dao.recuperarListaAvaliacoesPendentes(dto.getNome(), TipoUsuario.BANDA.name(),TelaInicialMusico.this);

            }
        });

        txtPropostasInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropostaDAO dao = new PropostaDAO();
                dao.recuperarPropostasUsuario(dto.getNome(), TipoUsuario.BANDA.name(), TelaInicialMusico.this);//a banda
            }
        });

        txtHistoricoInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropostaDAO dao = new PropostaDAO();
                dao.recuperarEventos(dto.getNome(), TipoUsuario.BANDA.name(), "HISTORICO",TelaInicialMusico.this,getIntent());//ta
            }
        });

        //agenda vazia só esta abrindo o dialog e fechando
        txtAgendaInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropostaDAO dao = new PropostaDAO();
                dao.recuperarEventos(dto.getNome(), TipoUsuario.BANDA.name(), "AGENDA", TelaInicialMusico.this,getIntent());//estab

                //Intent telaagendausuarios = new Intent(TelaInicialMusico.this,TelaAgendaUsuarios.class);
                //telaagendausuarios.putExtra("dtoAutenticacao",dto);
                //startActivity(telaagendausuarios);
            }
        });

        txtVisualizacaoInicialMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foiClick = true;
                atualizarVisualizacaoBanda();
            }
            private void atualizarVisualizacaoBanda() {

                List<BandaEntity> bandas = new ArrayList<>();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Banda.name());
                ref.addValueEventListener(valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ref.removeEventListener(valueEventListener);
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            final BandaEntity entidade = snapshot.getValue(BandaEntity.class);
                            if(entidade.getIdCriador().equals(dto.getIdAutenticacao()) || (entidade.getIntegrantes() != null && entidade.getIntegrantes().contains(dto.getEmail())))
                            {
                                bandas.add(entidade);
                                //txtNomeBandaInicialMusico.setText(entidade.getNome());

                            }
                        }
                        if(foiClick)
                            carregarListaBandas(bandas);
                    }

                    private void carregarListaBandas(List<BandaEntity> bandas) {
                        List<String> nomebandas = new ArrayList<>();
                        for (BandaEntity banda :bandas) {
                            nomebandas.add(banda.getNome());
                        }
                        if(bandas.size() > 0) {
                            foiClick = false;
                            AlertDialog.Builder b = new AlertDialog.Builder(TelaInicialMusico.this);
                            b.setTitle("Escolha a banda: ");
                            //Spinner popupSpinner = new Spinner(getApplicationContext(), Spinner.MODE_DIALOG);//??
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TelaInicialMusico.this, android.R.layout.simple_spinner_dropdown_item, nomebandas);
                            b.setAdapter(adapter, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtVisualizandoComo.setVisibility(View.VISIBLE);
                                    txtNomeBandaInicialMusico.setVisibility(View.VISIBLE);
                                    txtNomeBandaInicialMusico.setText(nomebandas.get(which));
                                    dto.setNome(bandas.get(which).getNome());

                                    txtMensagensMusico.setVisibility(View.VISIBLE);
                                    txtAvaliacaoEstabelecimento.setVisibility(View.VISIBLE);
                                    txtPropostasInicialMusico.setVisibility(View.VISIBLE);
                                    txtHistoricoInicialMusico.setVisibility(View.VISIBLE);
                                    txtAgendaInicialMusico.setVisibility(View.VISIBLE);
                                    txtConviteInicialMusico.setVisibility(View.VISIBLE);

                                    dialog.dismiss();
                                }

                            });
                            b.show();
                        }
                        else {
                            if (foiClick) {
                                Mensagem.notificar(TelaInicialMusico.this, "Bandas indisponíveis", "Não existem bandas cadastradas nesse perfil, cadastre uma banda");
                                foiClick = false;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    //por estar no onresume, se tiver mais de uma banda vai sempre voltar para a primeira
    @Override
    protected void onStart() {
        super.onStart();
        carregarBandaInicial();
    }

    public void carregarBandaInicial()//perfil musico sem banda cadastrada ainda pesquisa?
    {
        List<BandaEntity> bandas = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Banda.name());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final BandaEntity entidade = snapshot.getValue(BandaEntity.class);
                    if(entidade.getIdCriador().equals(dto.getIdAutenticacao()))
                    {
                        bandas.add(entidade);
                        //

                    }
                }
                if(bandas.size()>0) {
                    txtNomeBandaInicialMusico.setText(bandas.get(0).getNome());
                    dto.setNome(bandas.get(0).getNome());
                    txtVisualizandoComo.setVisibility(View.VISIBLE);
                    txtNomeBandaInicialMusico.setVisibility(View.VISIBLE);

                    txtMensagensMusico.setVisibility(View.VISIBLE);
                    txtAvaliacaoEstabelecimento.setVisibility(View.VISIBLE);
                    txtPropostasInicialMusico.setVisibility(View.VISIBLE);
                    txtHistoricoInicialMusico.setVisibility(View.VISIBLE);
                    txtAgendaInicialMusico.setVisibility(View.VISIBLE);
                    txtConviteInicialMusico.setVisibility(View.VISIBLE);
                }
                else {
                    txtVisualizandoComo.setVisibility(View.INVISIBLE);
                    txtNomeBandaInicialMusico.setVisibility(View.INVISIBLE);

                    txtMensagensMusico.setVisibility(View.GONE);
                    txtAvaliacaoEstabelecimento.setVisibility(View.GONE);
                    txtPropostasInicialMusico.setVisibility(View.GONE);
                    txtHistoricoInicialMusico.setVisibility(View.GONE);
                    txtAgendaInicialMusico.setVisibility(View.GONE);
                    txtConviteInicialMusico.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
