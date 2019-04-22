package com.example.quemtocahoje.Views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Dao.AutenticacaoDao;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.AESCrypt;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

public class TelaInicial extends AppCompatActivity {

    private Button btnLogin;
    private TextView txtCadastro;
    private TextView txtEsqueceuSuaSenha;
    private EditText edtLogin;
    private EditText edtSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent telaEscolhaCadastro = new Intent(this, TelaEscolhaCadastro.class);
        final Intent telaEsqueciSenha = new Intent(this, TelaEsqueceuaSenha.class);
        final Intent telaInicialEstabelecimento = new Intent(this, TelaInicialEstabelecimento.class);
        final Intent telaInicialMusico = new Intent(this, TelaInicialMusico.class);
        final Intent telaInicialEspectador = new Intent(this, TelaInicialEspectador.class);

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
                    AutenticacaoDTO autenticacao = autenticarLogin(edtLogin, edtSenha);
                    if(autenticacao != null){
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
                }
            }
        });

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

    private AutenticacaoDTO autenticarLogin(EditText l, EditText s){
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
    }

    private boolean isCamposValidos(EditText l, EditText s){
        if(l == null || l.getText().toString().trim().equals("")
           || s == null || s.getText().toString().trim().equals(""))
            return false;

        return true;
    }

}
