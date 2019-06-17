package com.example.quemtocahoje.Views;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Utility.ServicoCEP.Endereco;
import com.example.quemtocahoje.Utility.ServicoCEP.PostmonService;
import com.example.tcc.R;

public class TelaEndereco extends AppCompatActivity {

    private EditText edtLogradouroEndereco;
    private EditText edtBairroEndereco;
    private EditText edtCidadeEndereco;
    private EditText edtComplementoEndereco;
    private EditText edtCEPEndereco;
    private EditText edtUFEndereco;

    private Button btnConfirmarEndereco;

    // Mudanças  para buscar cep
    private Button btnBuscarCep;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_endereco);

        final Intent telaUpload = new Intent(this, TelaUpload.class);

        edtLogradouroEndereco = findViewById(R.id.edtLogradouroEndereco);
        edtBairroEndereco = findViewById(R.id.edtBairroEndereco);
        edtCidadeEndereco = findViewById(R.id.edtCidadeEndereco);
        edtComplementoEndereco = findViewById(R.id.edtComplementoEndereco);
        edtCEPEndereco = findViewById(R.id.edtCEPEndereco);
        edtUFEndereco = findViewById(R.id.edtUFEndereco);



        btnConfirmarEndereco = findViewById(R.id.btnConfirmarEndereco);

        btnConfirmarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCamposValidos(edtLogradouroEndereco,edtBairroEndereco,edtCidadeEndereco,edtCEPEndereco,edtUFEndereco)) {
                    telaUpload.putExtra("tipoUsuario", TipoUsuario.ESTABELECIMENTO.name());
                    telaUpload.putExtra("objetoEndereco", prepararObjetoEndereco());

                    AutenticacaoEntity a = (AutenticacaoEntity)  getIntent().getSerializableExtra("objetoAutenticacao");
                    telaUpload.putExtra("objetoAutenticacao", a);

                    EstabelecimentoEntity e = (EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento");
                    telaUpload.putExtra("objetoEstabelecimento", e);
                    startActivity(telaUpload);
                }
                else
                    Mensagem.notificar(TelaEndereco.this,"Campos Inválidos","Um ou mais campos não foram preenchidos corretamente.");
            }
        });



        // Configuração para buscar cep

        btnBuscarCep = findViewById(R.id.btnBuscarCep);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                solicitarEndereco();
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.postmon.com.br/v1/cep/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void solicitarEndereco()
    {
        String cep = edtCEPEndereco.getText().toString();

        PostmonService service = retrofit.create(PostmonService.class);

        Call<Endereco> call = service.getEndereco(cep);

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful()) {
                    Endereco end = response.body();
                    edtLogradouroEndereco.setText(end.getLogradouro());
                    edtBairroEndereco.setText(end.getBairro());
                    edtCidadeEndereco.setText(end.getCidade());
                    edtUFEndereco.setText(end.getEstado());

                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(TelaEndereco.this, "Não foi possível realizar a requisição", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isCamposValidos(EditText edtLogradouroEndereco, EditText edtBairroEndereco, EditText edtCidadeEndereco, EditText edtCEPEndereco, EditText edtUFEndereco)
    {
        //TODO Validar CEP
        if(edtLogradouroEndereco == null || edtLogradouroEndereco.getText().toString().trim().equals("")
                || edtBairroEndereco == null || edtBairroEndereco.getText().toString().trim().equals("")
                || edtCidadeEndereco == null || edtCidadeEndereco.getText().toString().trim().equals("")
                || edtCEPEndereco == null || edtCEPEndereco.getText().toString().trim().equals("")
                || edtUFEndereco == null || edtUFEndereco.getText().toString().trim().equals(""))
            return false;
        return true;

    }

    private EnderecoEntity prepararObjetoEndereco(){
        EnderecoEntity end = new EnderecoEntity(
                edtLogradouroEndereco.getText().toString()
                ,edtBairroEndereco.getText().toString()
                ,edtCidadeEndereco.getText().toString()
                ,edtComplementoEndereco.getText().toString()
                ,Integer.parseInt(edtCEPEndereco.getText().toString())
                ,edtUFEndereco.getText().toString()
                ,DefinirDatas.dataAtual()
        );
        return end;
    }
}
