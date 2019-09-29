package com.example.quemtocahoje.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Persistencia.Entity.BandaEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Mensagem;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.stream.Collectors;

public class BandaDAO {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

    public BandaDAO(FirebaseDatabase database, DatabaseReference reference, FirebaseUser firebaseUser) {
        this.database = database;
        this.reference = reference;
        this.firebaseUser = firebaseUser;
    }

    public BandaDAO(){}

    public void cadastrarNovaBanda(BandaEntity banda, Context ctx){
        Log.d("CADASTRO BANDA", banda.toString());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Banda.name())
                .child(banda.getNome());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())  Mensagem.notificar(ctx, "Banda inválida", "Uma banda com este nome já consta em nosso sistema.");
                else{
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("banda_id", banda.getNome());
                    hashMap.put("idCriador", banda.getIdCriador());
                    hashMap.put("nome", banda.getNome());
                    hashMap.put("dataCriacao", banda.getDataCriacao());
                    hashMap.put("bandaAtiva", banda.isBandaAtiva());
                    hashMap.put("generos", banda.getGeneros().stream().collect(Collectors.joining(",")));
                    hashMap.put("integrantes", banda.getIntegrantes().stream().collect(Collectors.joining(",")));

                    databaseReference.setValue(hashMap);

                    Mensagem.notificarFecharAtividade(ctx,"Sucesso!", "Banda cadastrada com sucesso.");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Mensagem.notificar(ctx, "Erro na aplicação", "Ocorreu um erro ao efetuar o cadastro de nova banda.");
                Log.d("ERRO FIREBASE", databaseError.getDetails());
            }
        });

    }

}
