package com.example.quemtocahoje.Model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Views.TelaUpload;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseStorageRegistro {
    public FirebaseStorage storage;

    public FirebaseStorageRegistro(FirebaseStorage storage){
        this.storage = storage;
    }

    public void salvarImagem(Uri imagemUri, Context ctx,String caminho) {
        if(imagemUri != null)
        {
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(caminho);
            ref.putFile(imagemUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isComplete())
                                Toast.makeText(ctx,"Upload com sucesso",Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
    public Cursor carregarImagem(Context ctx, String caminho){//?????Cursor?
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(caminho);
        Uri uri = Uri.parse(caminho);//converto o caminho para uri pra usar o getcontentresolver abaixo
        final Cursor[] c = new Cursor[1];
        ref.getDownloadUrl()
                /*.addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                c[0] = ctx.getContentResolver().query(uri,null,null,null,null);//tentativa no onsucess
            }
        })*/.addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isComplete())
                    c[0] = ctx.getContentResolver().query(task.getResult(),null,null,null,null);//tentativa usando o oncomplete
            }
        });
        return c[0];
    }

    public String getExtensaoArquivo(Uri uri,Context ctx){//retorna o formato do arquivo colocado na Uri
        ContentResolver cr = ctx.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

}
