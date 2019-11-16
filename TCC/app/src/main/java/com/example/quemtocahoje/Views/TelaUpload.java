package com.example.quemtocahoje.Views;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quemtocahoje.Adapter.AutenticacaoDTOAdapter;
import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Model.FirebaseStorageRegistro;
import com.example.quemtocahoje.POJO.Estabelecimento;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.ConversaoArquivo;
import com.example.quemtocahoje.Model.FirebaseRegistro;
import com.example.quemtocahoje.Utility.EncodeBase64;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TelaUpload extends AppCompatActivity {

    private static final int IMAGEMFOTO = 1;
    private static final int IMAGEMDEMO1 = 2;
    private static final int IMAGEMDEMO2 = 3;
    private static final int IMAGEMDEMO3= 4;

    Uri imagemUri = null;
    ByteArrayOutputStream bos = null;
    Cursor cursor = null;

    FirebaseAuth auth;

    private FirebaseRegistro registro;

    private int STORAGE_PERMISSION_CODE = 23;
    private TextView txtNomeUsuarioUpload;
    private AppCompatImageView imgFotoUsuarioUpload;
    private ImageView imgImagemUsuarioUpload1;
    private AppCompatImageView imgImagemUsuarioUpload2;
    private AppCompatImageView imgImagemUsuarioUpload3;
    private Button btnEncerrar;
    private LinearLayout linearImagensDemonstracao;
    private FirebaseStorageRegistro firebaseStorageRegistro;

    private Uri[] uris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_upload);
        getSupportActionBar().hide();

        uris = new Uri[4];

        registro = new FirebaseRegistro();
        firebaseStorageRegistro = new FirebaseStorageRegistro(FirebaseStorage.getInstance());

        final Intent telaInicial = new Intent(this,TelaInicial.class);

        final Intent telaInicialEspectador = new Intent(this,TelaInicialEspectador.class);
        final Intent telaInicialEstabelecimento = new Intent(this,TelaInicialEstabelecimento.class);
        final Intent telaInicialMusico = new Intent(this,TelaInicialMusico.class);

        txtNomeUsuarioUpload = findViewById(R.id.txtNomeUsuarioUpload);
        imgFotoUsuarioUpload = findViewById(R.id.imgFotoUsuarioUpload);
        imgImagemUsuarioUpload1 = findViewById(R.id.imgImagemUsuarioUpload1);
        imgImagemUsuarioUpload2 = findViewById(R.id.imgImagemUsuarioUpload2);
        imgImagemUsuarioUpload3 = findViewById(R.id.imgImagemUsuarioUpload3);
        btnEncerrar = findViewById(R.id.btnEncerrar);
        linearImagensDemonstracao = findViewById(R.id.linearImagensDemonstracao);

        txtNomeUsuarioUpload.setText("Olá "+preencherNomeUsuario() + "!");
        verificarVisibilidadeImagensDemonstracao();
        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persistirNovoUsuario();
                //startActivity(telaInicial);
                //finishAffinity();
                }
        });

        linearImagensDemonstracao = findViewById(R.id.linearImagensDemonstracao);
        imgFotoUsuarioUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria(IMAGEMFOTO);
            }
        });
        imgImagemUsuarioUpload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria(IMAGEMDEMO1);
            }
        });
        imgImagemUsuarioUpload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria(IMAGEMDEMO2);
            }
        });
        imgImagemUsuarioUpload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria(IMAGEMDEMO3);
            }
        });
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    private void abrirGaleria(int resultcode){
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, resultcode);
    }

    //recupera o URI da imagem selecionada para usar no método de conversão para BLOB
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGEMFOTO) {
            imagemUri = data.getData();
            cursor = getContentResolver().query(imagemUri, null, null, null, null);
            imgFotoUsuarioUpload.setImageURI(imagemUri);
            uris[0] = imagemUri;
            //if(imagemUri!= null)
            //    firebaseStorageRegistro.salvarImagem(imagemUri,this,"imagemfoto.png");
        }
        else if (resultCode == RESULT_OK && requestCode == IMAGEMDEMO1)
        {
            imagemUri = data.getData();
            cursor = getContentResolver().query(imagemUri, null, null, null, null);
            imgImagemUsuarioUpload1.setImageURI(imagemUri);
            uris[1] = imagemUri;
        }
        else if (resultCode == RESULT_OK && requestCode == IMAGEMDEMO2)
        {
            imagemUri = data.getData();
            cursor = getContentResolver().query(imagemUri, null, null, null, null);
            imgImagemUsuarioUpload2.setImageURI(imagemUri);
            uris[2] = imagemUri;
        }
        else if (resultCode == RESULT_OK && requestCode == IMAGEMDEMO3)
        {
            imagemUri = data.getData();
            cursor = getContentResolver().query(imagemUri, null, null, null, null);
            imgImagemUsuarioUpload3.setImageURI(imagemUri);
            uris[3] = imagemUri;

        }
        //uris.add(imagemUri);
    }



    private void persistirNovoUsuario(){

        String tipo = getIntent().getStringExtra("tipoUsuario");
        AutenticacaoEntity a = (AutenticacaoEntity) getIntent().getSerializableExtra("objetoAutenticacao");

        if(tipo.equals(TipoUsuario.ESPECTADOR.name())){
            EspectadorEntity e = (EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador");
            registro.registro(a.getLogin(),tipo,a.getEmail(),a.getSenha(),e, null, null, null, TelaUpload.this,uris);
        }else if(tipo.equals(TipoUsuario.ESTABELECIMENTO.name())){
            EnderecoEntity end = (EnderecoEntity) getIntent().getSerializableExtra("objetoEndereco");
            EstabelecimentoEntity estab = (EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento");
            registro.registro(a.getLogin(),tipo, a.getEmail(),a.getSenha(), null, null, estab, end, TelaUpload.this,uris);
        }else if(tipo.equals(TipoUsuario.MUSICO.name())){
            MusicoEntity m = (MusicoEntity) getIntent().getSerializableExtra("objetoMusico");
            registro.registro(a.getLogin(), tipo, a.getEmail(), a.getSenha(),null, m, null, null, TelaUpload.this,uris);
        }

        //TODO Verificar como fazer quando der erro no cadastro - Email existente, valido, etc; pois ele ainda ira salvar a imagem no banco
        if(cursor != null || imagemUri != null) {
            //bos = ConversaoArquivo.converterImagem(cursor, imagemUri);
            //String idusuario = EncodeBase64.toBase64(a.getEmail());
            //firebaseStorageRegistro.salvarImagem(imagemUri, this, idusuario+"/imagemfoto.png");
            //ArquivoEntity arquivo = new ArquivoEntity(idUser, bos.toByteArray(), TipoArquivo.FOTO_PERFIL.name(), DefinirDatas.dataAtual());
            //Banco.getDatabase(getApplicationContext()).arquivoDao().insertArquivo(arquivo);
        }
    }

    private void verificarVisibilidadeImagensDemonstracao()
    {
        String tipo = getIntent().getStringExtra("tipoUsuario");
        if (tipo.equals(TipoUsuario.ESTABELECIMENTO.name()) || tipo.equals(TipoUsuario.MUSICO.name()))
        {
            linearImagensDemonstracao.setVisibility(View.VISIBLE);
        }
        else
        {
            linearImagensDemonstracao.setVisibility(View.GONE);
        }
    }

    private String preencherNomeUsuario()
    {
        String tipo = getIntent().getStringExtra("tipoUsuario");
        String pessoa = "";
        if (tipo.equals(TipoUsuario.ESTABELECIMENTO.name()))
        {
            EstabelecimentoEntity estab = (EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento");
            pessoa = estab.getNomeDono();
        }
        else if (tipo.equals(TipoUsuario.MUSICO.name()))
        {
            MusicoEntity m = (MusicoEntity) getIntent().getSerializableExtra("objetoMusico");
            pessoa = m.getNome();
        }
        else
        {
            EspectadorEntity e = (EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador");
            pessoa = e.getNomeEspectador();
        }
        return pessoa;
    }
}
