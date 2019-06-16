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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quemtocahoje.Adapter.AutenticacaoCreateDTOAdapter;
import com.example.quemtocahoje.Adapter.EspectadorCreateDTOAdapter;
import com.example.quemtocahoje.DTO.AutenticacaoCreateDTO;
import com.example.quemtocahoje.DTO.EspectadorCreateDTO;
import com.example.quemtocahoje.Enum.TipoArquivo;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.POJO.Autenticacao;
import com.example.quemtocahoje.POJO.Espectador;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Entity.ArquivoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Retrofit.AutenticacaoResource;
import com.example.quemtocahoje.Retrofit.EspectadorResource;
import com.example.quemtocahoje.Utility.ConversaoArquivo;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.RetrofitCreator;
import com.example.tcc.R;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaUpload extends AppCompatActivity {

    private static final int IMAGEM = 1;
    Uri imagemUri;
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
                persistirAutenticacao();
                String tipo = getIntent().getStringExtra("tipoUsuario");
                if (tipo.equals(TipoUsuario.ESPECTADOR.name())) {
                    String nome = ((Espectador) getIntent().getSerializableExtra("objetoEspectador")).getNomeEspectador();
                    telaInicialEspectador.putExtra("nome",nome);
                    startActivity(telaInicialEspectador);
                }
                else if (tipo.equals(TipoUsuario.ESTABELECIMENTO.name()))
                {
                    String nome = ((EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento")).getNomeDono();
                    telaInicialEstabelecimento.putExtra("nome", nome);
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

    private void persistirAutenticacao(){
        //Persistindo a autenticação
        AutenticacaoResource r = RetrofitCreator.criarRetrofit().create(AutenticacaoResource.class); //criando uma instancia do retrofit com a interface do autenticacao

        AutenticacaoCreateDTO autenticacaoCreateDTO = AutenticacaoCreateDTOAdapter.toAutenticacaoCreateDTO(
                (Autenticacao) getIntent().getSerializableExtra("objetoAutenticacao"));
        Log.d("AUTENTICACAOCREATEDTO", "valor: "+autenticacaoCreateDTO.getLogin());
        Call<Autenticacao> aut = r.createAutenticacao(autenticacaoCreateDTO);
        aut.enqueue(new Callback<Autenticacao>() {
            @Override
            public void onResponse(Call<Autenticacao> call, Response<Autenticacao> response) {
                if(response.isSuccessful())
                {
                    //Recuperando o ID da autenticação
                    Autenticacao autenticacaoResposta = response.body();
                    Log.d("PERSISTIR AUTENTICACAO","Login: "+autenticacaoResposta.getLoginAutenticacao());
                    String tipo = getIntent().getStringExtra("tipoUsuario");
                    if(tipo.equals(TipoUsuario.ESPECTADOR.name())){
                        Espectador e = (Espectador) getIntent().getSerializableExtra("objetoEspectador");
                        e.setIdAutenticacao(autenticacaoResposta.getIdAutenticacao());
                        EspectadorCreateDTO espectadorDto = EspectadorCreateDTOAdapter.toEspectadorCreateDTO(e);
                        persistirEspectador(espectadorDto);
                    }
                }
            }

            @Override
            public void onFailure(Call<Autenticacao> call, Throwable t) {
                Log.d("FALHA AUTENTICACAO", "erro");
                Toast.makeText(TelaUpload.this, "Erro: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//                if(tipo.equals(TipoUsuario.ESTABELECIMENTO.name())){
//                EnderecoEntity end = (EnderecoEntity) getIntent().getSerializableExtra("objetoEndereco");
//                Long idEndereco = bd.enderecoDao().insertEndereco(end);
//
//                EstabelecimentoEntity estab = (EstabelecimentoEntity) getIntent().getSerializableExtra("objetoEstabelecimento");
//                estab.setAutenticacao_id(idUser);
//                estab.setEndereco_id(idEndereco);
//                bd.estabelecimentoDao().insertEstabelecimento(estab);
//            }else if(tipo.equals(TipoUsuario.MUSICO.name())){
//                MusicoEntity m = (MusicoEntity) getIntent().getSerializableExtra("objetoMusico");
//                m.setAutenticacao_id(idUser);
//
//                bd.musicoDao().insertMusico(m);
//            }


        //TODO alterar para identificar os tipos de arquivos certos
 //       bos = ConversaoArquivo.converterImagem(cursor, imagemUri);
 //       ArquivoEntity arquivo = new ArquivoEntity(idUser, bos.toByteArray(), TipoArquivo.FOTO_PERFIL.name(), DefinirDatas.dataAtual());
       // Banco.getDatabase(getApplicationContext()).arquivoDao().insertArquivo(arquivo);
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
            Espectador e = (Espectador) getIntent().getSerializableExtra("objetoEspectador");
            pessoa = e.getNomeEspectador();
        }
        return pessoa;
    }

    private void persistirEspectador(EspectadorCreateDTO espectador){
        EspectadorResource r = RetrofitCreator.criarRetrofit().create(EspectadorResource.class);
        Call<Espectador> esp = r.createEspectador(espectador);
        esp.enqueue(new Callback<Espectador>() {

            @Override
            public void onResponse(Call<Espectador> call, Response<Espectador> response) {
                if(response.isSuccessful()){

                    Espectador espectadorResposta = response.body();
                    Log.d("PERSISTIR ESPECTADOR", espectadorResposta.getIdEspectador() + " " + espectadorResposta.getNomeEspectador());
                    //TODO persistir arquivos
                }
            }

            @Override
            public void onFailure(Call<Espectador> call, Throwable t) {
                Log.d("FALHA ESPECTADOR", "erro");
                Toast.makeText(TelaUpload.this, "Erro: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }

            });
        }

}
