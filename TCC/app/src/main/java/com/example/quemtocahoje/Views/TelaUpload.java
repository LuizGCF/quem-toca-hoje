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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quemtocahoje.Enum.TipoArquivo;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Entity.ArquivoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
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
    private ImageView imgVideoUsuarioUpload1;
    private AppCompatImageView imgVideoUsuarioUpload2;
    private AppCompatImageView imgVideoUsuarioUpload3;

    ByteArrayOutputStream bos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_upload);

        txtNomeUsuarioUpload = findViewById(R.id.txtNomeUsuarioUpload);
        imgFotoUsuarioUpload = findViewById(R.id.imgFotoUsuarioUpload);
        imgVideoUsuarioUpload1 = findViewById(R.id.imgVideoUsuarioUpload1);
        imgVideoUsuarioUpload2 = findViewById(R.id.imgVideoUsuarioUpload2);
        imgVideoUsuarioUpload3 = findViewById(R.id.imgVideoUsuarioUpload3);
        Long idUser = getIntent().getLongExtra("idUser", -1L);

        imgFotoUsuarioUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
                if(bos != null) {
                    persistirArquivo(TipoArquivo.FOTO_PERFIL.name());
                    imgFotoUsuarioUpload.setImageBitmap(getImagem(TipoArquivo.FOTO_PERFIL.name()));
                    //TODO botao pra encerrar o cadastro e voltar pra tela inicial
                }else{
                    Mensagem.notificar(TelaUpload.this,"Erro","Erro ao enviar imagem.");
                }
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
            }catch(IOException e){
                e.getMessage();
                bos = null;
            }
        }
    }

    protected Bitmap getImagem(String tipoArquivo){
        Long idUser = getIntent().getLongExtra("idUser", -1L);
        byte[] b = Banco.getDatabase(getApplicationContext()).arquivoDao().findAnexoArquivoById(idUser, tipoArquivo);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(b);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        return theImage;
    }

    private Long persistirArquivo(String tipoArquivo){
        Long idUser = getIntent().getLongExtra("idUser", -1L);
        ArquivoEntity arquivo = new ArquivoEntity(idUser, bos.toByteArray(), tipoArquivo, DefinirDatas.dataAtual());
        return Banco.getDatabase(getApplicationContext()).arquivoDao().insertArquivo(arquivo);
    }


}
