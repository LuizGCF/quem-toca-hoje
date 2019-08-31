package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.DTO.TestDAO;
import com.example.quemtocahoje.DTO.TestDTO;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.POJO.Espectador;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Utility.AESCrypt;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TelaInicial extends AppCompatActivity {

    private Button btnLogin;
    private TextView txtCadastro;
    private TextView txtEsqueceuSuaSenha;
    private EditText edtLogin;
    private EditText edtSenha;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    DatabaseReference reference;

    //login automatico caso o usuario nao se deslogue
    @Override
    public void onStart() {
        super.onStart();

        final Intent telaCadastroEspectador = new Intent(this, TelaCadastroEspectador.class);
        final Intent telaEsqueciSenha = new Intent(this, TelaEsqueceuaSenha.class);
        final Intent telaInicialEstabelecimento = new Intent(this, TelaInicialEstabelecimento.class);
        final Intent telaInicialMusico = new Intent(this, TelaInicialMusico.class);
        final Intent telaInicialEspectador = new Intent(this, TelaInicialEspectador.class);

        firebaseUser = auth.getCurrentUser();
        if(firebaseUser!=null)
        {
            telaInicialEspectador.putExtra("nome","Nome Mockado sucesso login");
            startActivity(telaInicialEspectador);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent telaCadastroEspectador = new Intent(this, TelaCadastroEspectador.class);
        final Intent telaEsqueciSenha = new Intent(this, TelaEsqueceuaSenha.class);
        final Intent telaInicialEstabelecimento = new Intent(this, TelaInicialEstabelecimento.class);
        final Intent telaInicialMusico = new Intent(this, TelaInicialMusico.class);
        final Intent telaInicialEspectador = new Intent(this, TelaInicialEspectador.class);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

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
                    try
                    {
                        final String s = AESCrypt.encrypt(edtSenha.getText().toString());
                        auth.signInWithEmailAndPassword(edtLogin.getText().toString(), s)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            FirebaseUser user = auth.getCurrentUser();

                                            TestDAO test = new TestDAO(FirebaseDatabase.getInstance(),reference,user);
                                            TestDTO d = test.recuperarAutenticacao(edtLogin.getText().toString(),s);

                                            telaInicialEspectador.putExtra("nome","Nome Mockado sucesso login");
                                            startActivity(telaInicialEspectador);
                                           /* reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.ESPECTADOR.name()).child(user.getUid());
                                            reference.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    EspectadorEntity e = dataSnapshot.getValue(EspectadorEntity.class);

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });*/
                                        } else {
                                            Toast.makeText(TelaInicial.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    /*
                    AutenticacaoDTO autenticacao = autenticarLogin(edtLogin, edtSenha);
                    if(autenticacao != null){
                        //TODO atualizar último login no Firebase
                        //Banco.getDatabase(getApplicationContext()).autenticacaoDao().updateDataUltimoLogin(DefinirDatas.dataAtual(), autenticacao.getIdAutenticacao());
                        if(autenticacao.getTipoUsuario().equals(TipoUsuario.ESTABELECIMENTO.name())){
                            //TODO retornar nome do usuário do Firebase
                            String nome = "Nome Genérico"; // Banco.getDatabase(getApplicationContext()).estabelecimentoDao().findEstabelecimentoByAutenticacao(autenticacao.getIdAutenticacao()).getNomeDono();
                            //telaInicialEstabelecimento.putExtra("nome",nome);
                            //startActivity(telaInicialEstabelecimento);
                        }else if(autenticacao.getTipoUsuario().equals(TipoUsuario.MUSICO.name())){
                            //TODO retornar nome do usuário do Firebase
                            String nome = "Nome Genérico"; //Banco.getDatabase(getApplicationContext()).musicoDao().findMusicoByAutenticacao(autenticacao.getIdAutenticacao()).getNome();
                            //telaInicialMusico.putExtra("nome",nome);
                            startActivity(telaInicialMusico);
                        }else{
                            //System.out.println("ID: "+autenticacao.getIdAutenticacao()+"\n TIPO: "+autenticacao.getTipoUsuario());
                            //TODO retornar nome do usuário do Firebase
                            String nome = "Nome Genérico";//Banco.getDatabase(getApplicationContext()).espectadorDao().findEspectadorByAutenticacao(autenticacao.getIdAutenticacao()).getNomeEspectador();
                            //telaInicialEspectador.putExtra("nome",nome);
                            //startActivity(telaInicialEspectador);
                        }
                    }else{
                        Mensagem.notificar(TelaInicial.this,"Usuário Inválido","Login e/ou senha incorretos");
                    }*/
                }else{
                    Mensagem.notificar(TelaInicial.this,"Campos Inválidos","Um ou mais campos não foram preenchidos corretamente");
                }
            }
        });

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passando da tela inicial para a segunda tela

                startActivity (telaCadastroEspectador);
            }
        });

        txtEsqueceuSuaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaEsqueciSenha);
            }
        });

    }

    //TODO reimplementar com dados do Firebase
    private AutenticacaoDTO autenticarLogin(EditText l, EditText s){
       /*try {
            String login = l.getText().toString();
            String senha = AESCrypt.encrypt(s.getText().toString());
            AutenticacaoDTO dto = Banco.getDatabase(getApplicationContext()).autenticacaoDao().findAutenticacaoByLoginOuEmailESenha(login, login, senha);
            if(dto != null)
                return dto;
        }catch(Exception e) {
            e.getMessage();
        }
        return null;*/
       return new AutenticacaoDTO(0L, TipoUsuario.ESPECTADOR.name());
    }

    private boolean isCamposValidos(EditText l, EditText s){
        if(l == null || l.getText().toString().trim().equals("")
           || s == null || s.getText().toString().trim().equals(""))
            return false;

        return true;
    }

}
