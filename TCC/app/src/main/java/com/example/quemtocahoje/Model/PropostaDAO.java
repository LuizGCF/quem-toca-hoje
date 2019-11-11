package com.example.quemtocahoje.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.quemtocahoje.Enum.StatusProposta;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;

public class PropostaDAO {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private ProgressDialog progressDialog;
    private Context ctx;

    public PropostaDAO(){}

    public void enviarNovaProposta(PropostaEntity proposta, Context ctx){
        this.ctx = ctx;
        progressDialog = new ProgressDialog(this.ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde enquanto enviamos sua proposta");
        progressDialog.setTitle("Enviando proposta");
        progressDialog.show();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                .child(TipoUsuario.ESTABELECIMENTO.name())
                .child(proposta.getIdEstabelecimento())
                .push();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("ENVIO PROPOSTA", proposta.toString());

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("idBanda", proposta.getIdBanda());
                hashMap.put("idEstabelecimento", proposta.getIdEstabelecimento());
                hashMap.put("statusProposta", StatusProposta.ABERTO.name());
                hashMap.put("horarioInicio", proposta.getHorarioInicio());

                hashMap.put("horarioFim", proposta.getHorarioFim());
                hashMap.put("local", proposta.getLocal());
                hashMap.put("descricao", proposta.getDescricao());
                hashMap.put("cache", proposta.getCache());
                hashMap.put("dataEvento", proposta.getDataEvento());
                hashMap.put("dataEnvioProposta", proposta.getDataEnvioProposta());

                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String id = databaseReference.getKey();
                        FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                                .child(TipoUsuario.BANDA.name())
                                .child(proposta.getIdBanda())
                                .child(id)
                                .setValue(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                    }
                                });
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Mensagem.notificar(ctx, "Erro na aplicação", "Ocorreu um erro ao enviar a proposta.");
                Log.d("ERRO FIREBASE", databaseError.getDetails());
                progressDialog.dismiss();
            }
        });
    }

    public void cancelarProposta(String idProposta, String idBanda, String idEstabelecimento, Context ctx){

    }

}
