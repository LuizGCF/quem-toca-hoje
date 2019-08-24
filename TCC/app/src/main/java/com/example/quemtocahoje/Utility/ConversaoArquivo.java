package com.example.quemtocahoje.Utility;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConversaoArquivo {


    public static ByteArrayOutputStream converterImagem(Cursor cursor, Uri imagemUri){
        ByteArrayOutputStream bos = null;

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

//            persistirArquivo(TipoArquivo.FOTO_PERFIL.name());
//            imgFotoUsuarioUpload.setImageURI(imagemUri);
        }catch(IOException e){
            e.getMessage();
            bos = null;
        }

        return bos;
    }

    //TODO atualizar com dados do Firebase
    public static Bitmap getImagem(String tipoArquivo, Context context, Long idUser){
        byte[] b = null; //retorno do banco
        ByteArrayInputStream imageStream = new ByteArrayInputStream(b);
        Bitmap imagem = BitmapFactory.decodeStream(imageStream);
        return imagem;
    }


}
