package com.example.quemtocahoje.DTO;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.quemtocahoje.POJO.Autenticacao;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestDAO {
    private FirebaseDatabase auth;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

    public TestDAO(FirebaseDatabase auth, DatabaseReference reference, FirebaseUser firebaseUser) {
        this.auth = auth;
        this.reference = reference;
        this.firebaseUser = firebaseUser;
    }

    public TestDAO(){}

    public TestDTO recuperarAutenticacao(String login, String senha){
        final TestDTO dto = new TestDTO();
        final DatabaseReference autenticacaoRef = auth.getReference("Autenticacao");
        //autenticacaoRef.orderByChild("id").equalTo(login).equalTo(senha).limitToFirst(1).addValueEventListener(new ValueEventListener() {
        String id = firebaseUser.getUid();
        autenticacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Autenticacao entidade = snapshot.getValue(Autenticacao.class);
                    Log.d("MOCK",entidade.getDataCriacao().toString());
                    Log.d("MOCK",entidade.getLogin());
                    Log.d("MOCK",entidade.getSenha());
                    Log.d("MOCK",entidade.getTipoUsuario().name());
                    if(entidade.getId().equals(firebaseUser.getUid()))
                    {

                        dto.setIdAutenticacao(entidade.getDataCriacao().toString());
                        dto.setNome(entidade.getLogin());
                        dto.setSenha(entidade.getSenha());
                        dto.setTipoUsuario(entidade.getTipoUsuario().name());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        return dto;
    }
}
