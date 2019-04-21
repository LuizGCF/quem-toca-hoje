package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.tcc.R;

public class TelaEscolhaCadastro extends AppCompatActivity {
    private Button btnEspectador;
    private Button btnMusico;
    private Button btnEstabelecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        btnEspectador = findViewById(R.id.btnEspectador);
        btnMusico = findViewById(R.id.btnMusico);
        btnEstabelecimento = findViewById(R.id.btnEstabelecimento);

        final Intent telacadastroespectador = new Intent(this, TelaCadastroEspectador.class);

        btnEspectador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telacadastroespectador.putExtra("tipoUsuario", TipoUsuario.ESPECTADOR.name());
                startActivity(telacadastroespectador);
            }
        });
        btnMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telacadastroespectador.putExtra("tipoUsuario", TipoUsuario.MUSICO.name());
                startActivity(telacadastroespectador);
            }
        });
        btnEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telacadastroespectador.putExtra("tipoUsuario", TipoUsuario.ESTABELECIMENTO.name());
                startActivity(telacadastroespectador);
            }
        });
    }


}
