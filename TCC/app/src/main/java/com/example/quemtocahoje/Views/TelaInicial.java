package com.example.quemtocahoje.Views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.POJO.Autenticacao;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Dao.AutenticacaoDao;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Retrofit.AutenticacaoEndPoint;
import com.example.quemtocahoje.Utility.AESCrypt;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Utility.RetrofitCreator;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaInicial extends AppCompatActivity {

    private Button btnLogin;
    private TextView txtCadastro;
    private TextView txtEsqueceuSuaSenha;
    private EditText edtLogin;
    private EditText edtSenha;
    private Autenticacao autenticacao;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent telaEscolhaCadastro = new Intent(this, TelaEscolhaCadastro.class);
        final Intent telaEsqueciSenha = new Intent(this, TelaEsqueceuaSenha.class);
        //final Intent telaInicialEstabelecimento = new Intent(this, TelaInicialEstabelecimento.class);
        final Intent telaInicialMusico = new Intent(this, TelaInicialMusico.class);
        final Intent telaInicialEspectador = new Intent(this, TelaInicialEspectador.class);

        //ALGUNS MOCKS COM CADA GET CRIADO NA INTERFACE AutenticacaoEndPoint
        /*AutenticacaoEndPoint r = RetrofitCreator.criarRetrofit().create(AutenticacaoEndPoint.class);

        Call<List<Autenticacao>> aut = r.getAllAutenticacoes();
        aut.enqueue(new Callback<List<Autenticacao>>() {
            @Override
            public void onResponse(Call<List<Autenticacao>> call, Response<List<Autenticacao>> response) {
                List<Autenticacao> resulttest = response.body();
                Mensagem.notificar(TelaInicial.this,"Campos Inválidos","" + response.body().get(1).getLoginAutenticacao());
            }

            @Override
            public void onFailure(Call<List<Autenticacao>> call, Throwable t) {
                Toast.makeText(TelaInicial.this, "Something went wrong...Please try later! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        /*AutenticacaoEndPoint r = RetrofitCreator.criarRetrofit().create(AutenticacaoEndPoint.class);

        Call<Autenticacao> aut = r.getAutenticacao(1);
        aut.enqueue(new Callback<Autenticacao>() {
            @Override
            public void onResponse(Call<Autenticacao> call, Response<Autenticacao> response) {
                Autenticacao resulttest = response.body();
                Mensagem.notificar(TelaInicial.this,"Campos Inválidos","" + response.body().getLoginAutenticacao());
            }

            @Override
            public void onFailure(Call<Autenticacao> call, Throwable t) {
                Toast.makeText(TelaInicial.this, "Something went wrong...Please try later! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        /*AutenticacaoEndPoint r = RetrofitCreator.criarRetrofit().create(AutenticacaoEndPoint.class);

        Call<Autenticacao> aut = r.getAutenticaoLogin("a","a");
        aut.enqueue(new Callback<Autenticacao>() {
            @Override
            public void onResponse(Call<Autenticacao> call, Response<Autenticacao> response) {
                Autenticacao resulttest = response.body();
                Mensagem.notificar(TelaInicial.this,"Campos Inválidos","" + response.body().getLoginAutenticacao());
            }

            @Override
            public void onFailure(Call<Autenticacao> call, Throwable t) {
                Toast.makeText(TelaInicial.this, "Something went wrong...Please try later! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        setContentView(R.layout.activity_tela_inicial);
        btnLogin = findViewById(R.id.btnLogin);
        txtCadastro = findViewById(R.id.txtCadastro);
        txtEsqueceuSuaSenha = findViewById(R.id.txtEsqueceuSuaSenha);
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isCamposValidos(edtLogin, edtSenha)){
                    //AutenticacaoDTO autenticacao = autenticarLogin(edtLogin, edtSenha);
                    Autenticacao autenticacao = autenticarLogin(edtLogin,edtSenha, getApplicationContext());



                    /*if(autenticacao != null){
                        Banco.getDatabase(getApplicationContext()).autenticacaoDao().updateDataUltimoLogin(DefinirDatas.dataAtual(), autenticacao.getIdAutenticacao());
                        if(autenticacao.getTipoUsuario().equals(TipoUsuario.ESTABELECIMENTO.name())){
                            String nome = Banco.getDatabase(getApplicationContext()).estabelecimentoDao().findEstabelecimentoByAutenticacao(autenticacao.getIdAutenticacao()).getNomeDono();
                            telaInicialEstabelecimento.putExtra("nome",nome);
                            startActivity(telaInicialEstabelecimento);
                        }else if(autenticacao.getTipoUsuario().equals(TipoUsuario.MUSICO.name())){
                            String nome = Banco.getDatabase(getApplicationContext()).musicoDao().findMusicoByAutenticacao(autenticacao.getIdAutenticacao()).getNome();
                            telaInicialMusico.putExtra("nome",nome);
                            startActivity(telaInicialMusico);
                        }else{
                            //System.out.println("ID: "+autenticacao.getIdAutenticacao()+"\n TIPO: "+autenticacao.getTipoUsuario());
                            String nome = Banco.getDatabase(getApplicationContext()).espectadorDao().findEspectadorByAutenticacao(autenticacao.getIdAutenticacao()).getNomeEspectador();
                            telaInicialEspectador.putExtra("nome",nome);
                            startActivity(telaInicialEspectador);
                        }
                    }else{
                        Mensagem.notificar(TelaInicial.this,"Usuário Inválido","Login e/ou senha incorretos");
                    }
                }else{
                    Mensagem.notificar(TelaInicial.this,"Campos Inválidos","Um ou mais campos não foram preenchidos corretamente");
                }*/
            }
            else
            {
                Mensagem.notificar(TelaInicial.this,"Campos Inválidos","Um ou mais campos não foram preenchidos corretamente");
            }
        }});

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passando da tela inicial para a segunda tela

                startActivity (telaEscolhaCadastro);
            }
        });

        txtEsqueceuSuaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaEsqueciSenha);
            }
        });

    }
    //TODO Verificar como esperar o resultado da chamada assincrona
    private Autenticacao autenticarLogin(EditText l, EditText s, Context context)
    {
        final Context c = context;
        AutenticacaoEndPoint r = RetrofitCreator.criarRetrofit().create(AutenticacaoEndPoint.class);//criando uma instancia do retrofit com a interface do autenticacao
        Call<Autenticacao> aut = r.getAutenticaoLogin("a","a");//MOCK que faz a chamada da api que busca no banco alguem que tenha login "a" e senha "a"
        aut.enqueue(new Callback<Autenticacao>() {//enqueue é a chamada assincrona do get nesse caso, Onresponse traz o resultado caso certo, OnFailure caso de algum problema
            @Override
            public void onResponse(Call<Autenticacao> call, Response<Autenticacao> response) {
                if(response.isSuccessful())
                {
                    Log.d("AUTENTICAR LOGIN", "onResponse iniciado");
                    Autenticacao resulttest = response.body();//aqui ele já conseguiu trazer o resultado da api e esta populando na Model Autenticacao
                    final Intent telaInicialEstabelecimento = new Intent(c, TelaInicialEstabelecimento.class);
                    telaInicialEstabelecimento.putExtra("nome",resulttest.getLoginAutenticacao());
                    startActivity(telaInicialEstabelecimento);
                }
                else
                {
                    Toast.makeText(TelaInicial.this, "Erro ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Autenticacao> call, Throwable t) {
                Toast.makeText(TelaInicial.this, "Something went wrong...Please try later! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return autenticacao;//o return aqui fora não está trazendo o resultado pois ele não espera o resultado do onResponse
    }
    //antigo autenticar login com ROOM
    /*private AutenticacaoDTO autenticarLogin(EditText l, EditText s){
        try {
            String login = l.getText().toString();
            String senha = AESCrypt.encrypt(s.getText().toString());
            AutenticacaoDTO dto = Banco.getDatabase(getApplicationContext()).autenticacaoDao().findAutenticacaoByLoginOuEmailESenha(login, login, senha);
            if(dto != null)
                return dto;
        }catch(Exception e) {
            e.getMessage();
        }
        return null;
    }*/

    private boolean isCamposValidos(EditText l, EditText s){
        if(l == null || l.getText().toString().trim().equals("")
           || s == null || s.getText().toString().trim().equals(""))
            return false;

        return true;
    }

}
