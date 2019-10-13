package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tcc.R;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
public class TelaAvalicaoEstabelecimento extends AppCompatActivity {

    private  RatingBar rbOrganizacao;
    private  RatingBar  rbEstrutura;
    private  RatingBar  rbReceptividade;
    private  EditText txtComentario;
    private  Button btnAvaliar;
    private  Button btnVoltar;
    private float organizacao;
    private float estrutura;
    private float receptividade;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacao_musico);

        final Intent telaInicialMusico = new Intent(this,TelaInicialMusico.class);

        // Esse código, tira a label da aplicação
        getSupportActionBar().hide();

        rbOrganizacao =  findViewById(R.id.rbOrganizacao);
        rbEstrutura= findViewById(R.id.rbEstrutura);
        rbReceptividade = findViewById(R.id.rbReceptividade);
        txtComentario = findViewById(R.id.txtComentario);
        btnAvaliar = findViewById(R.id.btnAvaliar);
        btnVoltar = findViewById(R.id.btnVoltar);

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

    private boolean validarCampos(RatingBar rbPerformace,RatingBar rbEstilo, RatingBar rbMusicalidade)
    {
        if(rbPerformace == null || rbEstilo == null || rbMusicalidade == null)
        {
            // Enviando mensagem na tela, caso usuário não selecione os rating bar
            String texto = "Campos não selecionados";
            Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }


}

/*

         organizacao = rbOrganizacao.getNumStars();
         estrutura = rbEstrutura.getNumStars();
         receptividade = rbReceptividade.getNumStars();

        rbReceptividade.setNumStars(0);
        rbEstrutura.setNumStars(0);
        rbOrganizacao.setNumStars(0);
 */