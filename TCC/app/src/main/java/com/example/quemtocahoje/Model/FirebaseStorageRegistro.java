package com.example.quemtocahoje.Model;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.quemtocahoje.Views.TelaUpload;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

}
