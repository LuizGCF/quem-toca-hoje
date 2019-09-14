package com.example.quemtocahoje.Views;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quemtocahoje.Adapter.AutenticacaoDTOAdapter;
import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.POJO.Estabelecimento;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.ConversaoArquivo;
import com.example.quemtocahoje.Model.FirebaseRegistro;
import com.example.tcc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.io.ByteArrayOutputStream;

public class TelaUpload extends AppCompatActivity {

    private static final int IMAGEM = 1;
    Uri imagemUri = null;
    ByteArrayOutputStream bos = null;
    Cursor cursor = null;

    FirebaseAuth auth;
    DatabaseReference reference;

    private FirebaseRegistro registro;

    private int STORAGE_PERMISSION_CODE = 23;
    private TextView txtNomeUsuarioUpload;
    private AppCompatImageView imgFotoUsuarioUpload;
    private ImageView imgImagemUsuarioUpload1;
    private AppCompatImageView imgImagemUsuarioUpload2;
    private AppCompatImageView imgImagemUsuarioUpload3;
    private Button btnEncerrar;
    private LinearLayout linearImagensDemonstracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_upload);
        registro = new FirebaseRegistro(auth, reference);

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
                String tipo = getIntent().getStringExtra("tipoUsuario");
                if (tipo.equals(TipoUsuario.ESPECTADOR.name())) {
                    String nome = ((EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador")).getNomeEspectador();
                    AutenticacaoDTO dto =  AutenticacaoDTOAdapter.espectadorToAutenticacaoDTO((EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador"));
                    telaInicialEspectador.putExtra("dtoAutenticacao",dto);
                    startActivity(telaInicialEspectador);
                    finishAffinity();
                }
                else if (tipo.equals(TipoUsuario.ESTABELECIMENTO.name()))
                {
                    String nome = ((EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento")).getNomeDono();
                    AutenticacaoDTO dto =  AutenticacaoDTOAdapter.estabelecimentoToAutenticacaoDTO((EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento"));
                    telaInicialEstabelecimento.putExtra("dtoAutenticacao", dto);
                    startActivity(telaInicialEstabelecimento);
                    finishAffinity();
                }
                else if(tipo.equals(TipoUsuario.MUSICO.name()))
                {
                   String nome = ((MusicoEntity) getIntent().getSerializableExtra("objetoMusico")).getNome();
                   AutenticacaoDTO dto =  AutenticacaoDTOAdapter.musicoToAutenticacaoDTO((MusicoEntity) getIntent().getSerializableExtra("objetoMusico"));
                   telaInicialMusico.putExtra("dtoAutenticacao",dto);
                   startActivity(telaInicialMusico);
                   finishAffinity();
                }
            }
        });

        linearImagensDemonstracao = findViewById(R.id.linearImagensDemonstracao);
        imgFotoUsuarioUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    private void abrirGaleria(){
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, IMAGEM);
    }

    //recupera o URI da imagem selecionada para usar no método de conversão para BLOB
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGEM) {
            imagemUri = data.getData();
            cursor = getContentResolver().query(imagemUri, null, null, null, null);
            //TODO atribuir dinamicamente ao image view certo
            imgFotoUsuarioUpload.setImageURI(imagemUri);
        }
    }

    private void persistirNovoUsuario(){

        String tipo = getIntent().getStringExtra("tipoUsuario");
        AutenticacaoEntity a = (AutenticacaoEntity) getIntent().getSerializableExtra("objetoAutenticacao");

        if(tipo.equals(TipoUsuario.ESPECTADOR.name())){
            EspectadorEntity e = (EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador");
            registro.registro(a.getLogin(),tipo,a.getEmail(),a.getSenha(),e, null, null, null);
        }else if(tipo.equals(TipoUsuario.ESTABELECIMENTO.name())){
            EnderecoEntity end = (EnderecoEntity) getIntent().getSerializableExtra("objetoEndereco");
            EstabelecimentoEntity estab = (EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento");
            registro.registro(a.getLogin(),tipo, a.getEmail(),a.getSenha(), null, null, estab, end);
        }else if(tipo.equals(TipoUsuario.MUSICO.name())){
            MusicoEntity m = (MusicoEntity) getIntent().getSerializableExtra("objetoMusico");
            registro.registro(a.getLogin(), tipo, a.getEmail(), a.getSenha(),null, m, null, null);
        }

        //TODO reimplementar com Firebase
        if(cursor != null || imagemUri != null) {
            //TODO alterar para identificar os tipos de arquivos certos
            bos = ConversaoArquivo.converterImagem(cursor, imagemUri);
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
