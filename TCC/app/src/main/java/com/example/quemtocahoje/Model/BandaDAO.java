package com.example.quemtocahoje.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.example.quemtocahoje.Enum.StatusConvite;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.BandaEntity;
import com.example.quemtocahoje.Persistencia.Entity.ConviteEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.Email;
import com.example.quemtocahoje.Utility.Mensagem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public BandaDAO() {
    }

    public void cadastrarNovaBanda(BandaEntity banda, Context ctx) {
        Log.d("CADASTRO BANDA", banda.toString());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Banda.name())
                .child(banda.getNome());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    Mensagem.notificar(ctx, "Banda inválida", "Uma banda com este nome já consta em nosso sistema.");
                else {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("banda_id", banda.getNome());
                    hashMap.put("idCriador", banda.getIdCriador());
                    hashMap.put("nome", banda.getNome());
                    hashMap.put("dataCriacao", banda.getDataCriacao());
                    hashMap.put("bandaAtiva", banda.isBandaAtiva());
                    hashMap.put("generos", banda.getGeneros().stream().collect(Collectors.joining(",")));

                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child(TabelasFirebase.Convite.name()).setValue(banda.getConvites());
                                convidarMembros(banda.getConvites(), ctx, banda.getNome());

                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Mensagem.notificar(ctx, "Erro na aplicação", "Ocorreu um erro ao efetuar o cadastro de nova banda.");
                Log.d("ERRO FIREBASE", databaseError.getDetails());
            }
        });

    }

    public void convidarMembros(List<ConviteEntity> convites, Context ctx, String banda) {
        List<String> emails = convites.stream().map(e -> e.getEmailConvidado()).collect(Collectors.toList());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(TabelasFirebase.Autenticacao.name());
        List<String> emailsExistentes = new ArrayList<>();
        for (String email : emails) {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AutenticacaoEntity autenticacaoEntity = snapshot.getValue(AutenticacaoEntity.class);
                        if (autenticacaoEntity.getEmail().equals(email) && autenticacaoEntity.getTipoUsuario().equals(TipoUsuario.MUSICO.name()))
                            emailsExistentes.add(email);
                    }

                    if (!emails.isEmpty()) emails.forEach(e -> notificarUsuario(e, ctx, banda));

                    //TODO notificar usuários do aplicativo
                    if(!emails.isEmpty()) Log.d("OSEMAILSLA", emailsExistentes.stream().collect(Collectors.joining(",")));

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Mensagem.notificar(ctx, "Erro na aplicação", "Ocorreu um erro ao efetuar o cadastro de nova banda.");
                    Log.d("ERRO FIREBASE", databaseError.getDetails());
                }
            });
        }

        Mensagem.notificarFecharAtividade(ctx, "Sucesso!", "Banda cadastrada com sucesso.");
    }

    private void notificarUsuario(String email, Context ctx, String banda) {
        //if(Banco.getDatabase(getApplicationContext()).autenticacaoDao().findAutenticacaoByEmail(edtEmailConvite.getText().toString()) == null){
        String destinatario = email.trim();
        String subject = "Convite para juntar-se à banda";
        String message = "Você foi convidado para a banda "+banda+". Instale nosso aplicativo para integrá-la!";

        Email sm = new Email(ctx, destinatario, subject, message);
        sm.execute();
        //}

    }
}