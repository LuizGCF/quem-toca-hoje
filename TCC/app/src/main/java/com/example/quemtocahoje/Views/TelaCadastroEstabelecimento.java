package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tcc.R;

public class TelaCadastroEstabelecimento extends AppCompatActivity {

    private EditText edtRazaoSocial;
    private EditText edtCelular;
    private EditText edtCNPJ;
    private EditText edtEmail;
    private EditText edtNumero;
    private EditText edtBairro;
    private EditText edtCidade;
    private EditText edtLoginEstabelecimento;
    private EditText edtSenhaEstabelecimento;
    private Button btnCadastrarEstabelecimento;
    private Button btnCancelarEstabelecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_estabelecimento);

        edtRazaoSocial = findViewById(R.id.edtRazaoSocial);
        edtCelular = findViewById(R.id.edtCelular);
        edtCNPJ = findViewById(R.id.edtCNPJ);
        edtEmail = findViewById(R.id.edtEmail);
        edtNumero = findViewById(R.id.edtNumero);
        edtBairro = findViewById(R.id.edtBairro);
        edtCidade = findViewById(R.id.edtCidade);
        edtLoginEstabelecimento = findViewById(R.id.edtLoginEstabelecimento);
        edtSenhaEstabelecimento = findViewById(R.id.edtSenhaEstabelecimento);
        btnCadastrarEstabelecimento = findViewById(R.id.btnCadastrarEstabelecimento);
        btnCancelarEstabelecimento = findViewById(R.id.btnCancelarEstabelecimento);

        btnCadastrarEstabelecimento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });

        btnCancelarEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
