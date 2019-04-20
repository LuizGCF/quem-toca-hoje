package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.example.tcc.R;

public class TelaUpload extends AppCompatActivity {

    private TextView txtNomeUsuarioUpload;
    private AppCompatImageView imgFotoUsuarioUpload;
    private AppCompatImageView imgVideoUsuarioUpload1;
    private AppCompatImageView imgVideoUsuarioUpload2;
    private AppCompatImageView imgVideoUsuarioUpload3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_upload);

        txtNomeUsuarioUpload = findViewById(R.id.txtNomeUsuarioUpload);
        imgFotoUsuarioUpload = findViewById(R.id.imgFotoUsuarioUpload);
        imgVideoUsuarioUpload1 = findViewById(R.id.imgVideoUsuarioUpload1);
        imgVideoUsuarioUpload2 = findViewById(R.id.imgVideoUsuarioUpload2);
        imgVideoUsuarioUpload3 = findViewById(R.id.imgVideoUsuarioUpload3);
    }
}
