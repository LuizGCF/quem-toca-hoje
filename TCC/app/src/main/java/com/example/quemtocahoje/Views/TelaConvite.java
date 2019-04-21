package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tcc.R;

public class TelaConvite extends AppCompatActivity {

    private EditText edtEmailConvite;
    private Button btnEnviarConvite;
    private Button btnCancelarConvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_convite);

        edtEmailConvite = findViewById(R.id.edtEmailConvite);
        btnEnviarConvite = findViewById(R.id.btnEnviarConvite);
        btnCancelarConvite = findViewById(R.id.btnCancelarConvite);

        btnEnviarConvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancelarConvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
