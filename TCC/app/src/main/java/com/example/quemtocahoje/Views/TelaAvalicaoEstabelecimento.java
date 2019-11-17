package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quemtocahoje.Model.AvaliacaoDAO;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.tcc.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
public class TelaAvalicaoEstabelecimento extends Activity {

    private  RatingBar rbOrganizacao;
    private  RatingBar  rbEstrutura;
    private  RatingBar  rbReceptividade;
    private  EditText txtComentario;
    private  Button btnAvaliar;
    private  Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avalicao_estabelecimento);
        // Esse código, tira a label da aplicação
        //getSupportActionBar().hide();

        rbOrganizacao =  findViewById(R.id.rbOrganizacao1);
        rbEstrutura= findViewById(R.id.rbEstrutura1);
        rbReceptividade = findViewById(R.id.rbReceptividade1);
        txtComentario = findViewById(R.id.txtComentario);
        btnAvaliar = findViewById(R.id.btnAvaliar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(validarCampos(rbOrganizacao,rbEstrutura,rbReceptividade))
                    {
                        AvaliacaoEstabelecimentoEntity avaliacao = prepararObjetoEstabelecimento();
                        AvaliacaoDAO avaliacaoEstabelecimento = new AvaliacaoDAO();
                        avaliacaoEstabelecimento.persistirAvaliacaoEstabelecimento(avaliacao,TelaAvalicaoEstabelecimento.this);

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

    private boolean validarCampos(RatingBar rbOrganizacao,RatingBar rbEstrutura, RatingBar rbReceptividade)
    {
        if(rbOrganizacao == null || rbOrganizacao == null || rbOrganizacao == null)
        {
            // Enviando mensagem na tela, caso usuário não selecione os rating bar
            String texto = "Campos não selecionados";
            Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    public AvaliacaoEstabelecimentoEntity prepararObjetoEstabelecimento()
    {
       AvaliacaoEstabelecimentoEntity obj = new AvaliacaoEstabelecimentoEntity();
       obj.setIdEvento("EVENTOTESTE");
       obj.setIdEstabelecimento("Teste id de estab mockado");
       obj.setOrganizacao(rbOrganizacao.getRating());
       obj.setEstrutura(rbEstrutura.getRating());
       obj.setReceptividade(rbReceptividade.getRating());
       obj.setTxtComentario(txtComentario.getText().toString().trim());
       return obj;
    }


}


