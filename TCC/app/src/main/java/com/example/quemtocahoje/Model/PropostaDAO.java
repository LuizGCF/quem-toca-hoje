package com.example.quemtocahoje.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.quemtocahoje.Enum.StatusProposta;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.POJO.Proposta;
import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Views.TelaProposta;
import com.example.quemtocahoje.Views.TelaVisualizarTodasPropostas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PropostaDAO {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private ProgressDialog progressDialog;
    private Context ctx;
    private HashMap<DatabaseReference, ValueEventListener> hashMap;
    private ValueEventListener valueEventListener;

    public PropostaDAO(){ hashMap = new HashMap<>();}

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

    public void atualizarProposta(String idProposta, String idBanda, String idEstabelecimento, String statusProposta, Context ctx){
        this.ctx = ctx;
        progressDialog = new ProgressDialog(this.ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde enquanto atualizamos sua proposta");
        progressDialog.setTitle("Atualizando");
        progressDialog.show();

        FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                .child(TipoUsuario.ESTABELECIMENTO.name())
                .child(idEstabelecimento)
                .child(idProposta)
                .child("statusProposta")
                .setValue(statusProposta).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                        .child(TipoUsuario.BANDA.name())
                        .child(idBanda)
                        .child(idProposta)
                        .child("statusProposta")
                        .setValue(statusProposta).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Mensagem.notificarFecharAtividade(ctx, "Atualizado", "Concluído com sucesso!");
                            progressDialog.dismiss();
                            Log.d("PROPOSTA","SUCESSO");
                        }
                    });
            }
        });
    }

    public void recuperarEventos(String idUsuario,String tipoUsuario, String tipoPesquisa, Context ctx)  {
        this.ctx = ctx;
        progressDialog = new ProgressDialog(this.ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde enquanto recuperamos os dados");
        progressDialog.setTitle("Pesquisando");
        progressDialog.show();
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                .child(tipoUsuario).child(idUsuario);
        firebaseDatabase.addValueEventListener(valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hashMap.put(firebaseDatabase, valueEventListener);
                List<PropostaEntity> eventos = new ArrayList<>();
                Iterator<DataSnapshot> snapshot = dataSnapshot.getChildren().iterator();
                while(snapshot.hasNext()){
                    PropostaEntity proposta = snapshot.next().getValue(PropostaEntity.class);
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date dataEvento = sdf.parse(proposta.getDataEvento() + " " + proposta.getHorarioFim() + ":00");
                            Date dataAtual = Calendar.getInstance().getTime();

                            if(tipoPesquisa.equals("HISTORICO")){
                                if(dataAtual.after(dataEvento) && proposta.getStatusProposta().equals(StatusProposta.FINALIZADO.name())){
                                    eventos.add(proposta);

                                    removeValueEventListener(hashMap);
                                }
                            }else { //agenda
                                if (dataAtual.before(dataEvento) && proposta.getStatusProposta().equals(StatusProposta.ACEITO.name())) {
                                    eventos.add(proposta);

                                    removeValueEventListener(hashMap);
                                }
                            }


                        } catch (ParseException e) {
                            e.printStackTrace();
                            removeValueEventListener(hashMap);
                        }
                    }
                    progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                removeValueEventListener(hashMap);
            }

        });
    }

    public void recuperarPropostasUsuario(String idUsuario, String tipoUsuario, Context ctx){
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                        .child(tipoUsuario).child(idUsuario);
                firebaseDatabase.addValueEventListener(valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hashMap.put(firebaseDatabase, valueEventListener);
                Iterator<DataSnapshot> snapshot = dataSnapshot.getChildren().iterator();
                ArrayList<PropostaEntity> listaPropostas = new ArrayList<>();
                while(snapshot.hasNext()) {
                    PropostaEntity proposta = snapshot.next().getValue(PropostaEntity.class);
                    if(proposta.getStatusProposta().equals(StatusProposta.ABERTO.name()))
                        listaPropostas.add(proposta);
                }

                if(!listaPropostas.isEmpty()){
                    Log.d("LISTAPROPOSTAS", listaPropostas.toString());
                    Intent intent = new Intent(ctx, TelaVisualizarTodasPropostas.class);
                    intent.putExtra("listaPropostas", listaPropostas);
                    intent.putExtra("tipoUsuario", tipoUsuario);
                    removeValueEventListener(hashMap);
                    ctx.startActivity(intent);
                }else{
                    Mensagem.notificar(ctx, "Sem resultados", "Você não possui propostas pendentes");
                    removeValueEventListener(hashMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                removeValueEventListener(hashMap);
            }
        });

    }

    public static void removeValueEventListener(HashMap<DatabaseReference, ValueEventListener> hashMap) {
        for (Map.Entry<DatabaseReference, ValueEventListener> entry : hashMap.entrySet()) {
            DatabaseReference databaseReference = entry.getKey();
            ValueEventListener valueEventListener = entry.getValue();
            databaseReference.removeEventListener(valueEventListener);
        }
    }
}
