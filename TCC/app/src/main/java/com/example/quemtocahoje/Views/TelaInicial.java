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
import com.example.quemtocahoje.POJO.Espectador;
import com.example.quemtocahoje.POJO.Estabelecimento;
import com.example.quemtocahoje.POJO.Musico;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Dao.AutenticacaoDao;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Retrofit.AutenticacaoResource;
import com.example.quemtocahoje.Retrofit.EspectadorResource;
import com.example.quemtocahoje.Retrofit.EstabelecimentoResource;
import com.example.quemtocahoje.Retrofit.MusicoResource;
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
                    autenticarLogin(edtLogin,edtSenha, getApplicationContext());

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
    private void autenticarLogin(EditText l, EditText s, Context context)
    {
        final Context c = context;
        AutenticacaoResource r = RetrofitCreator.criarRetrofit().create(AutenticacaoResource.class);//criando uma instancia do retrofit com a interface do autenticacao
        //Call<Autenticacao> aut = r.getAutenticaoLogin("a","a");//MOCK que faz a chamada da api que busca no banco alguem que tenha login "a" e senha "a"
        Call<Autenticacao> aut = r.getAutenticaoLogin(l.getText().toString(),s.getText().toString());
        aut.enqueue(new Callback<Autenticacao>() {//enqueue é a chamada assincrona do get nesse caso, Onresponse traz o resultado caso certo, OnFailure caso de algum problema
            @Override
            public void onResponse(Call<Autenticacao> call, Response<Autenticacao> response) {
                if(response.isSuccessful())
                {
                    Log.d("AUTENTICAR LOGIN", "onResponse iniciado");
                    Autenticacao resultado = response.body();//aqui ele já conseguiu trazer o resultado da api e esta populando na Model Autenticacao
                    Log.d("CONDICAO1", resultado.getTipoUsuarioAutenticacao().toUpperCase());
                    Log.d("CONDICAO2", TipoUsuario.ESTABELECIMENTO.name().toUpperCase());
                    if(resultado.getTipoUsuarioAutenticacao().toUpperCase().equals(TipoUsuario.ESTABELECIMENTO.name().toUpperCase()))
                    {
                        Log.d("CONDICAO", "ESTABELECIMENTO");
                        autenticarEstabelecimento(resultado.getIdAutenticacao());
                    }
                    else if(resultado.getTipoUsuarioAutenticacao().toUpperCase().equals(TipoUsuario.MUSICO.name().toUpperCase()))
                    {
                        Log.d("CONDICAO", "MUSICO");
                        autenticarMusico(resultado.getIdAutenticacao());
                    }
                    else
                    {
                        Log.d("CONDICAO", "ESPECTADOR");
                        autenticarEspectador(resultado.getIdAutenticacao());
                    }
                }
                else
                {
                    Toast.makeText(TelaInicial.this, "Login e/ou senha incorretos! ", Toast.LENGTH_SHORT).show();//nao encontrou resultados
                }
            }

            @Override
            public void onFailure(Call<Autenticacao> call, Throwable t) {
                Toast.makeText(TelaInicial.this, "Erro: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
            private void autenticarEspectador(int idAutenticacao) {
                EspectadorResource er = RetrofitCreator.criarRetrofit().create(EspectadorResource.class);
                Call<Espectador> spec = er.geEspectadorByIdAutenticacao(idAutenticacao);
                spec.enqueue(new Callback<Espectador>() {
                    @Override
                    public void onResponse(Call<Espectador> call, Response<Espectador> response) {
                        if(response.isSuccessful())
                        {
                            Espectador espectador = response.body();
                            Log.d("ESPECTADOR", "onResponse espectador iniciado");
                            Log.d("ESPECTADOR body",espectador.getNomeEspectador());
                            final Intent telaInicialEspectador = new Intent(c, TelaInicialEspectador.class);
                            telaInicialEspectador.putExtra("nome",espectador.getNomeEspectador());
                            startActivity(telaInicialEspectador);
                        }
                    }

                    @Override
                    public void onFailure(Call<Espectador> call, Throwable t) {
                        Toast.makeText(TelaInicial.this, "Erro ao receber os dados do espectador", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void autenticarMusico(Integer idAutenticacao) {
                MusicoResource er = RetrofitCreator.criarRetrofit().create(MusicoResource.class);
                Call<Musico> music = er.getMusicoByIdAutenticacao(idAutenticacao);
                music.enqueue(new Callback<Musico>() {
                    @Override
                    public void onResponse(Call<Musico> call, Response<Musico> response) {
                        if(response.isSuccessful())
                        {
                            Musico musico = response.body();
                            Log.d("MUSICO", "onResponse musico iniciado");

                            final Intent telaInicialMusico = new Intent(c, TelaInicialMusico.class);
                            telaInicialMusico.putExtra("nome",musico.getNomeMusico());//nome usuario ou artistico
                            startActivity(telaInicialMusico);
                        }
                    }

                    @Override
                    public void onFailure(Call<Musico> call, Throwable t) {
                        Toast.makeText(TelaInicial.this, "Erro ao receber os dados do musico", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void autenticarEstabelecimento(Integer idAutenticacao) {
                EstabelecimentoResource er = RetrofitCreator.criarRetrofit().create(EstabelecimentoResource.class);
                Call<Estabelecimento> estab = er.getEstabelecimentoByIdAutenticacao(idAutenticacao);
                estab.enqueue(new Callback<Estabelecimento>() {
                    @Override
                    public void onResponse(Call<Estabelecimento> call, Response<Estabelecimento> response) {
                        if(response.isSuccessful())
                        {
                            Estabelecimento estabelecimento = response.body();
                            Log.d("ESPECTADOR", "onResponse espectador iniciado");

                            final Intent telaInicialEstabelecimento = new Intent(c, TelaInicialEstabelecimento.class);
                            telaInicialEstabelecimento.putExtra("nome",estabelecimento.getNomeDono());
                            startActivity(telaInicialEstabelecimento);
                        }
                    }

                    @Override
                    public void onFailure(Call<Estabelecimento> call, Throwable t) {
                        Toast.makeText(TelaInicial.this, "Erro ao receber os dados do espectador", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean isCamposValidos(EditText l, EditText s){
        if(l == null || l.getText().toString().trim().equals("")
           || s == null || s.getText().toString().trim().equals(""))
            return false;

        return true;
    }

}
