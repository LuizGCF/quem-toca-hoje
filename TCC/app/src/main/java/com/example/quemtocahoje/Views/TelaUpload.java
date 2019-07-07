package com.example.quemtocahoje.Views;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
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

import com.example.quemtocahoje.Enum.TipoArquivo;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Entity.ArquivoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.ConversaoArquivo;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.tcc.R;

import java.io.ByteArrayOutputStream;

public class TelaUpload extends AppCompatActivity {

    private static final int IMAGEM = 1;
    Uri imagemUri = null;
    ByteArrayOutputStream bos = null;
    Cursor cursor = null;

    private int STORAGE_PERMISSION_CODE = 23;
    private TextView txtNomeUsuarioUpload;
    private AppCompatImageView imgFotoUsuarioUpload;
    private ImageView imgImagemUsuarioUpload1;
    private AppCompatImageView imgImagemUsuarioUpload2;
    private AppCompatImageView imgImagemUsuarioUpload3;
    private Button btnEncerrar;
    private LinearLayout linearImagensDemonstracao;
    Long idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_upload);

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
                    telaInicialEspectador.putExtra("nome",nome);
                    startActivity(telaInicialEspectador);
                    finishAffinity();
                }
                else if (tipo.equals(TipoUsuario.ESTABELECIMENTO.name()))
                {
                    String nome = ((EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento")).getNomeDono();
                    telaInicialEstabelecimento.putExtra("nome", nome);
                    startActivity(telaInicialEstabelecimento);
                    finishAffinity();
                }
                else if(tipo.equals(TipoUsuario.MUSICO.name()))
                {
                   String nome = ((MusicoEntity) getIntent().getSerializableExtra("objetoMusico")).getNome();
                   telaInicialMusico.putExtra("nome",nome);
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

        Banco bd = Banco.getDatabase(getApplicationContext());
        String tipo = getIntent().getStringExtra("tipoUsuario");
        AutenticacaoEntity a = (AutenticacaoEntity) getIntent().getSerializableExtra("objetoAutenticacao");
        idUser = bd.autenticacaoDao().insertAutenticacao(a);

        if(tipo.equals(TipoUsuario.ESPECTADOR.name())){
            EspectadorEntity e = (EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador");
            e.setAutenticacao_id(idUser);
            bd.espectadorDao().insertEspectador(e);
        }else if(tipo.equals(TipoUsuario.ESTABELECIMENTO.name())){
            EnderecoEntity end = (EnderecoEntity) getIntent().getSerializableExtra("objetoEndereco");
            Long idEndereco = bd.enderecoDao().insertEndereco(end);

            EstabelecimentoEntity estab = (EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento");
            estab.setAutenticacao_id(idUser);
            estab.setEndereco_id(idEndereco);
            bd.estabelecimentoDao().insertEstabelecimento(estab);
        }else if(tipo.equals(TipoUsuario.MUSICO.name())){
            MusicoEntity m = (MusicoEntity) getIntent().getSerializableExtra("objetoMusico");
            m.setAutenticacao_id(idUser);

            bd.musicoDao().insertMusico(m);
        }


        if(cursor != null || imagemUri != null) {
            //TODO alterar para identificar os tipos de arquivos certos
            bos = ConversaoArquivo.converterImagem(cursor, imagemUri);
            ArquivoEntity arquivo = new ArquivoEntity(idUser, bos.toByteArray(), TipoArquivo.FOTO_PERFIL.name(), DefinirDatas.dataAtual());
            Banco.getDatabase(getApplicationContext()).arquivoDao().insertArquivo(arquivo);
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
