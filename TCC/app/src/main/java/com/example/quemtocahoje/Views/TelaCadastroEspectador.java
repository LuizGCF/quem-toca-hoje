package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Utility.AESCrypt;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

public class TelaCadastroEspectador extends AppCompatActivity {

    private EditText nomeEspectador;
    private EditText edtEmailEspectador;
    private EditText loginEspectador;
    private EditText senhaEspectador;
    private EditText confirmarSenhaEspectador;
    private Button btnCadastrarEspectador;
    private Button btnCancelarEspectador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_espectador);

        final Intent telaUpload = new Intent(this, TelaUpload.class);

        //final Intent telaEndereco = new Intent(this, TelaEndereco.class);

        btnCadastrarEspectador = findViewById(R.id.btnCadastrarEspectador);
        nomeEspectador = findViewById(R.id.edtNomeEspectador);
        edtEmailEspectador = findViewById(R.id.edtEmailEspectador);
        loginEspectador = findViewById(R.id.edtLoginEspectador);
        senhaEspectador = findViewById(R.id.edtSenhaEspectador);
        confirmarSenhaEspectador = findViewById(R.id.edtConfirmarSenhaEspectador);
        btnCancelarEspectador = findViewById(R.id.btnCancelarEspectador);

        btnCadastrarEspectador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(isCamposValidos(nomeEspectador, edtEmailEspectador, loginEspectador, senhaEspectador, confirmarSenhaEspectador)){
                    if(isUsuarioUnico(loginEspectador)) {
                        if (isSenhaCorreta(senhaEspectador, confirmarSenhaEspectador)) {
                            AutenticacaoEntity a = criarObjetoAutenticacao(edtEmailEspectador, loginEspectador, senhaEspectador);
                            Long id = Banco.getDatabase(getApplicationContext()).autenticacaoDao().insertAutenticacao(a);
                            EspectadorEntity e = criarObjectoEspectador(id, nomeEspectador.getText().toString().trim(), a.getDataCriacao());
                            Banco.getDatabase(getApplicationContext()).espectadorDao().insertEspectador(e);
                            limparTela();
                            telaUpload.putExtra("idUser", id);
                            telaUpload.putExtra("nomeUser", e.getNomeEspectador());
                            startActivity(telaUpload);
                            //telaEndereco.putExtra("TipoUsuario", TipoUsuario.ESPECTADOR.getValor());
                            //startActivity(telaEndereco);
                            //Coloquei a mensagem de sucesso no Toast mesmo
                            Toast.makeText(TelaCadastroEspectador.this,"Sucesso!",Toast.LENGTH_LONG).show();

                        } else {
                            //TODO Verificar erro que era pra cair nos elses que acessam banco
                            Mensagem.notificar(TelaCadastroEspectador.this,"Senhas diferentes","A senhas diferem uma da outra.");
                        }
                    }else{
                        //TODO Verificar erro que era pra cair nos elses que acessam banco
                        Mensagem.notificar(TelaCadastroEspectador.this,"Login existente","O login digitado ja existe em nosso banco de dados.");
                    }
                }else{
                    Mensagem.notificar(TelaCadastroEspectador.this,"Campos invalidos","Um ou mais campos não foram preenchidos corretamente");
                }
            }
        });

        btnCancelarEspectador.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void limparTela() {
        nomeEspectador.setText("");
        edtEmailEspectador.setText("");
        loginEspectador.setText("");
        senhaEspectador.setText("");
        confirmarSenhaEspectador.setText("");
    }

    private boolean isUsuarioUnico(EditText loginEspectador) {
        if(Banco.getDatabase(getApplicationContext()).autenticacaoDao().findAutenticacaoByEmailLogin(edtEmailEspectador.getText().toString(), loginEspectador.getText().toString()) != null)
            return false;

        return true;
    }

    private boolean isCamposValidos(EditText nomeEspectador,EditText edtEmailEspectador, EditText loginEspectador, EditText senhaEspectador, EditText confirmarSenhaEspectador){
        if(nomeEspectador == null || nomeEspectador.getText().toString().trim().equals("")
        || edtEmailEspectador == null || edtEmailEspectador.getText().toString().trim().equals("")
        || loginEspectador == null || loginEspectador.getText().toString().trim().equals("")
        || senhaEspectador == null || senhaEspectador.getText().toString().trim().equals("")
        || confirmarSenhaEspectador == null || confirmarSenhaEspectador.getText().toString().trim().equals("")
        )
            return false;

        return true;
    }

    private boolean isSenhaCorreta(EditText senhaEspectador, EditText confirmarSenhaEspectador){
        if(senhaEspectador.getText().toString().equals(confirmarSenhaEspectador.getText().toString()))
            return true;

        return false;
    }

    //Prepara o objeto de autenticação para persistir
    private AutenticacaoEntity criarObjetoAutenticacao(EditText edtEmailEspectador, EditText loginEspectador, EditText senhaEspectador){
        String login = loginEspectador.getText().toString().trim();
        String senha = senhaEspectador.getText().toString();
        String email = edtEmailEspectador.getText().toString().trim();

        String dataCriacao = DefinirDatas.dataAtual();

        AutenticacaoEntity a = new AutenticacaoEntity(email, login, senha, TipoUsuario.ESPECTADOR.name(), dataCriacao, null);
        try{
            a.setSenha(AESCrypt.encrypt(senha));
        }catch(Exception e){
            e.getMessage();
        }

        return a;
    }

    //Prepara o objeto de Espectador para persistir
    private EspectadorEntity criarObjectoEspectador(Long idAutenticacao, String nomeEspectador, String dataCriacao){
        return new EspectadorEntity(idAutenticacao, nomeEspectador, dataCriacao);
    }
}
