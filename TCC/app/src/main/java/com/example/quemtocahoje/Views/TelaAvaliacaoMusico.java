package com.example.quemtocahoje.Views;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quemtocahoje.Model.AvaliacaoDAO;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;


public class TelaAvaliacaoMusico extends Activity {

    private  RatingBar rbPerformance;
    private  RatingBar  rbEstilo;
    private  RatingBar  rbMusicalidade;
    private  EditText txtComentario;
    private  Button btnAvaliar;
    private  Button btnVoltar;
    private String idEvento;
    private String idBanda;
    private String idEstabelecimento;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacao_musico);

        rbPerformance =  findViewById(R.id.rbPerformance);
        rbMusicalidade= findViewById(R.id.rbMusicalidade);
        rbEstilo     =  findViewById(R.id.rbEstilo);
        txtComentario = findViewById(R.id.txtComentario);
        btnAvaliar = findViewById(R.id.btnAvaliarMusico);
        btnVoltar = findViewById(R.id.btnVoltar);
        idEvento = getIntent().getStringExtra("idEvento");
        idBanda = getIntent().getStringExtra("idBanda");
        idEstabelecimento = getIntent().getStringExtra("idEstabelecimento");



        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(validarCampos(rbPerformance,rbEstilo,rbMusicalidade)) {
                    AvaliacaoMusicoEntity avaliacao = prepararObjetoAvaliacaoMusico();
                    AvaliacaoDAO avaliacaoMusico = new AvaliacaoDAO();
                    avaliacaoMusico.persistirAvaliacaoMusico(idEstabelecimento, avaliacao, TelaAvaliacaoMusico.this);
                }else{
                    Mensagem.notificar(TelaAvaliacaoMusico.this, "Erro!", "Preencher todos os campos.");
                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    private boolean validarCampos( RatingBar rbPerformance,RatingBar rbEstilo, RatingBar rbMusicalidade)
    {
        if(rbPerformance.getRating() == 0 || rbEstilo.getRating() == 0 || rbMusicalidade.getRating() == 0 || txtComentario.getText().toString().trim().isEmpty())
            return false;
        else
            return true;
    }

    public AvaliacaoMusicoEntity  prepararObjetoAvaliacaoMusico()
    {

        AvaliacaoMusicoEntity obj = new AvaliacaoMusicoEntity();
        obj.setIdBanda(idBanda);
        obj.setIdEvento(idEvento);
        obj.setEstilo(rbEstilo.getRating());
        obj.setPerformance(rbPerformance.getRating());
        obj.setMusicalidade(rbMusicalidade.getRating());
        obj.setTxtComentario(""+txtComentario.getText().toString().trim());

        return obj;

    }

    }

