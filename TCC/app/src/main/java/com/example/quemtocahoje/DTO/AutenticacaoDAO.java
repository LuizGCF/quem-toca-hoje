package com.example.quemtocahoje.DTO;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AutenticacaoDAO {
    private FirebaseDatabase auth;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

    public AutenticacaoDAO(FirebaseDatabase auth, DatabaseReference reference, FirebaseUser firebaseUser) {
        this.auth = auth;
        this.reference = reference;
        this.firebaseUser = firebaseUser;
    }

    public AutenticacaoDAO(){}

    public void autenticar(){
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Autenticacao");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    AutenticacaoEntity entidade = snapshot.getValue(AutenticacaoEntity.class);
                    Log.d("MOCK",entidade.getLogin());
                    Log.d("MOCK",entidade.getSenha());
                    Log.d("MOCK",entidade.getTipoUsuario());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public AutenticacaoDTO recuperarAutenticacao(String login, String senha){
        final AutenticacaoDTO dto = new AutenticacaoDTO();
        final DatabaseReference autenticacaoRef = auth.getReference("Autenticacao");
        //autenticacaoRef.orderByChild("id").equalTo(login).equalTo(senha).limitToFirst(1).addValueEventListener(new ValueEventListener() {
        String id = firebaseUser.getUid();
        autenticacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                Log.d("MOCK","" + dataSnapshot.getChildrenCount());
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    AutenticacaoEntity entidade = snapshot.getValue(AutenticacaoEntity.class);
                    Log.d("MOCK",entidade.getLogin());
                    Log.d("MOCK",entidade.getSenha());
                    Log.d("MOCK",entidade.getTipoUsuario());
                    if(entidade.getId().equals(firebaseUser.getUid()))
                    {

                        dto.setIdAutenticacao(entidade.getDataCriacao().toString());
                        dto.setNome(entidade.getLogin());
                        dto.setSenha(entidade.getSenha());
                        dto.setTipoUsuario(entidade.getTipoUsuario());
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
