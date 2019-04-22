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
import android.widget.Toast;

import com.example.quemtocahoje.Enum.TipoArquivo;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Entity.ArquivoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TelaUpload extends AppCompatActivity {

    private static final int IMAGEM = 1;
    Uri imagemUri;

    private int STORAGE_PERMISSION_CODE = 23;
    private TextView txtNomeUsuarioUpload;
    private AppCompatImageView imgFotoUsuarioUpload;
    private ImageView imgImagemUsuarioUpload1;
    private AppCompatImageView imgImagemUsuarioUpload2;
    private AppCompatImageView imgImagemUsuarioUpload3;
    private Button btnEncerrar;
    private LinearLayout linearImagensDemonstracao;
    Long idUser;

    ByteArrayOutputStream bos = null;



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
                String tipo = getIntent().getStringExtra("tipoUsuario");
                if (tipo.equals(TipoUsuario.ESPECTADOR.name())) {
                    String nome = ((EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador")).getNomeEspectador();
                    telaInicialEspectador.putExtra("nome",nome);
                    startActivity(telaInicialEspectador);
                }
                else if (tipo.equals(TipoUsuario.ESTABELECIMENTO.name()))
                {
                    String nome = ((EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento")).getNomeDono();
                    telaInicialEstabelecimento.putExtra("nome",nome);
                    startActivity(telaInicialEstabelecimento);
                }
                else if(tipo.equals(TipoUsuario.MUSICO.name()))
                {
                   String nome = ((MusicoEntity) getIntent().getSerializableExtra("objetoMusico")).getNome();
                   telaInicialMusico.putExtra("nome",nome);
                   startActivity(telaInicialMusico);
                }
            }
        });

        linearImagensDemonstracao = findViewById(R.id.linearImagensDemonstracao);
        persistirNovoUsuario();
        imgFotoUsuarioUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
                    //TODO botao pra encerrar o cadastro e voltar pra tela inicial
            }
        });
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    private void abrirGaleria(){
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, IMAGEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGEM){
            imagemUri = data.getData();
            Cursor cursor = getContentResolver().query(imagemUri, null, null, null, null);

            String result;
            if (cursor == null) {
                result = imagemUri.getPath();
            } else {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
                cursor.close();
            }

            File f = new File(result);
            try {
                FileInputStream fis = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                bos = new ByteArrayOutputStream();
                for (int len; (len = fis.read(buffer)) != -1;) {
                    bos.write(buffer, 0, len);
                }

                fis.close();

                persistirArquivo(TipoArquivo.FOTO_PERFIL.name());
                imgFotoUsuarioUpload.setImageURI(imagemUri);
            }catch(IOException e){
                e.getMessage();
                bos = null;
                Mensagem.notificar(TelaUpload.this,"Erro","Erro ao enviar imagem.");
            }
        }
    }

    protected Bitmap getImagem(String tipoArquivo){
        byte[] b = Banco.getDatabase(getApplicationContext()).arquivoDao().findAnexoArquivoById(idUser, tipoArquivo);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(b);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        return theImage;
    }

    private Long persistirArquivo(String tipoArquivo){
        ArquivoEntity arquivo = new ArquivoEntity(idUser, bos.toByteArray(), tipoArquivo, DefinirDatas.dataAtual());
        return Banco.getDatabase(getApplicationContext()).arquivoDao().insertArquivo(arquivo);
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
