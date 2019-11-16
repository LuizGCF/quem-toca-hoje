package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quemtocahoje.Model.AvaliacaoDAO;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.tcc.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


public class TelaAvaliacaoMusico extends AppCompatActivity {

    private  RatingBar rbPerformance;
    private  RatingBar  rbEstilo;
    private  RatingBar  rbMusicalidade;
    private  EditText txtComentario;
    private  Button btnAvaliar;
    private  Button btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacao_musico);
        // Esse código, tira a label da aplicação
        getSupportActionBar().hide();

        rbPerformance =  findViewById(R.id.rbPerformance);
        rbMusicalidade= findViewById(R.id.rbMusicalidade);
        rbEstilo     =  findViewById(R.id.rbEstilo);
        txtComentario = findViewById(R.id.txtComentario);
        btnAvaliar = findViewById(R.id.btnAvaliarMusico);
        btnVoltar = findViewById(R.id.btnVoltar);



        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(validarCampos(rbPerformance,rbEstilo,rbMusicalidade))
                {
                    AvaliacaoMusicoEntity avaliacao = prepararObjetoAvaliacaoMusico();
                    AvaliacaoDAO avaliacaoMusico = new AvaliacaoDAO();
                    avaliacaoMusico.persistirAvaliacaoMusico(avaliacao);
                    String texto = "Avaliação realizada com sucesso!!!";
                    Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_SHORT).show();
                    finish();
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
        if(rbPerformance == null || rbEstilo == null || rbMusicalidade == null)
        {
            String texto = "Campos não selecionados";
            Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    public AvaliacaoMusicoEntity  prepararObjetoAvaliacaoMusico()
    {

        AvaliacaoMusicoEntity obj = new AvaliacaoMusicoEntity();
        obj.setIdBanda("BANDATESTE");
        obj.setIdEvento("EVENTOTESTE");
        obj.setEstilo(rbEstilo.getRating());
        obj.setPerformance(rbPerformance.getRating());
        obj.setMusicalidade(rbMusicalidade.getRating());
        obj.setTxtComentario(""+txtComentario.getText().toString().trim());


        //obj.setIdBanda(getIntent().getStringExtra("IDBANDA"));
        // obj.setIdEvento(getIntent().getStringExtra("IDEVENTO"));


        return obj;

    }

    }

