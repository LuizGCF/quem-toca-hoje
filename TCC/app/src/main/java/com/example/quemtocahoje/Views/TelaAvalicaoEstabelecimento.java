package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quemtocahoje.Model.AvaliacaoDAO;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
public class TelaAvalicaoEstabelecimento extends Activity {

    private  RatingBar rbOrganizacao;
    private  RatingBar  rbEstrutura;
    private  RatingBar  rbReceptividade;
    private  EditText txtComentario;
    private  Button btnAvaliar;
    private  Button btnVoltar;
    private String idEstabelecimento;
    private String idEvento;
    private String idBanda;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avalicao_estabelecimento);

        rbOrganizacao =  findViewById(R.id.rbOrganizacao1);
        rbEstrutura= findViewById(R.id.rbEstrutura1);
        rbReceptividade = findViewById(R.id.rbReceptividade1);
        txtComentario = findViewById(R.id.txtComentario);
        btnAvaliar = findViewById(R.id.btnAvaliar);
        btnVoltar = findViewById(R.id.btnVoltar);
        idEstabelecimento = getIntent().getStringExtra("idEstabelecimento");
        idEvento = getIntent().getStringExtra("idEvento");
        idBanda = getIntent().getStringExtra("idBanda");

        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(validarCampos(rbOrganizacao,rbEstrutura,rbReceptividade))
                    {
                        AvaliacaoEstabelecimentoEntity avaliacao = prepararObjetoEstabelecimento();
                        AvaliacaoDAO avaliacaoEstabelecimento = new AvaliacaoDAO();
                        avaliacaoEstabelecimento.persistirAvaliacaoEstabelecimento(idBanda, avaliacao, TelaAvalicaoEstabelecimento.this);
                    }else{
                    Mensagem.notificar(TelaAvalicaoEstabelecimento.this, "Erro.","Preencher todos os campos.");
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
        if(rbOrganizacao.getRating() == 0 || rbOrganizacao.getRating() == 0 || rbOrganizacao.getRating() == 0 || txtComentario.getText().toString().trim().isEmpty())
            return false;
        else
            return true;
    }

    public AvaliacaoEstabelecimentoEntity prepararObjetoEstabelecimento()
    {
       AvaliacaoEstabelecimentoEntity obj = new AvaliacaoEstabelecimentoEntity();
       obj.setIdEvento(idEvento);
       obj.setIdEstabelecimento(idEstabelecimento);
       obj.setOrganizacao(rbOrganizacao.getRating());
       obj.setEstrutura(rbEstrutura.getRating());
       obj.setReceptividade(rbReceptividade.getRating());
       obj.setTxtComentario(txtComentario.getText().toString().trim());
       return obj;
    }


}


