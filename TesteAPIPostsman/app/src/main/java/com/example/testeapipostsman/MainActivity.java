package com.example.testeapipostsman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private EditText edtCep;
    private Button btnEnviar;
    private TextView txtResul;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = findViewById(R.id.btnEnviar);
        edtCep =  findViewById(R.id.edtCep);
        txtResul =  findViewById(R.id.txtResul);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarEndereco();
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.postmon.com.br/v1/cep/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void solicitarEndereco() {
        String cep = edtCep.getText().toString();

        PostmonService service = retrofit.create(PostmonService.class);

        Call<Endereco> call = service.getEndereco(cep);

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful()) {
                    Endereco end = response.body();

                    String strEnd = "Cidade: " +  end.getCidade() + "\n" +
                            "Bairro: " +  end.getBairro() + "\n" +
                            "Logradouro: " +  end.getLogradouro()+ "\n" + "Estado: "+  end.getEstado();

                    txtResul.setText(strEnd );
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível realizar a requisição", Toast.LENGTH_SHORT).show();
            }
        });

    }
}