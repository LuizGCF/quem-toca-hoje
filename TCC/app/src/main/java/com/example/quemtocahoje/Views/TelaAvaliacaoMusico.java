package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Model.AvaliacaoMusicoDAO;
import com.example.quemtocahoje.Model.FirebaseRegistro;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.HashMap;


public class TelaAvaliacaoMusico extends AppCompatActivity {

    private  RatingBar rbPerformance;
    private  RatingBar  rbEstilo;
    private  RatingBar  rbMusicalidade;
    private  EditText txtComentario;
    private  Button btnAvaliar;
    private  Button btnVoltar;
    private float estilo;
    private float musicalidade;
    private float performance;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacao_musico);
        // Esse código, tira a label da aplicação
        getSupportActionBar().hide();

        final Intent telaInicialEstabelecimento = new Intent(this,TelaInicialEstabelecimento.class);

        rbPerformance =  findViewById(R.id.rbPerformance);
        rbMusicalidade= findViewById(R.id.rbMusicalidade);
        rbEstilo     =  findViewById(R.id.rbEstilo);
        txtComentario = findViewById(R.id.txtComentario);
        btnAvaliar = findViewById(R.id.btnAvaliar);
        btnVoltar = findViewById(R.id.btnVoltar);
        estilo = rbEstilo.getRating();
        musicalidade = rbMusicalidade.getRating();
        performance = rbPerformance.getRating();

        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

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



    }

