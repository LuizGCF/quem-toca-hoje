package com.example.quemtocahoje.Model;

import android.support.annotation.NonNull;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AvaliacaoMusicoDAO {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

    public AvaliacaoMusicoDAO(FirebaseDatabase database, DatabaseReference reference, FirebaseUser firebaseUser) {
        this.database = database;
        this.reference = reference;
        this.firebaseUser = firebaseUser;
    }

    public AvaliacaoMusicoDAO() {
    }

    public void persistirAvaliacaoMusico(final AvaliacaoMusicoEntity avaliacao, String idAvaliacaoMusico) {
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.AvaliacaoMusico.name()).child(idAvaliacaoMusico);

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("idAvaliacaoMusico", idAvaliacaoMusico);
        hashMap.put("estilo", avaliacao.getEstilo());
        hashMap.put("musicalidade", avaliacao.getMusicalidade());
        hashMap.put("performance", avaliacao.getPerformance());
        hashMap.put("txtComentario", avaliacao.getTxtComentario());
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    persistirAvaliacaoMusico(avaliacao, idAvaliacaoMusico);
            }
        });
    }
}
