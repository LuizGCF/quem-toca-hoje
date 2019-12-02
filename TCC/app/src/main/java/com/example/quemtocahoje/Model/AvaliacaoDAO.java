package com.example.quemtocahoje.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.quemtocahoje.DTO.AvaliacoesPendentesDTO;
import com.example.quemtocahoje.DTO.PropostasDTO;
import com.example.quemtocahoje.Enum.StatusProposta;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Views.TelaAvaliacaoMusico;
import com.example.quemtocahoje.Views.TelaAvaliacoesPendentes;
import com.example.quemtocahoje.Views.TelaAvalicaoEstabelecimento;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AvaliacaoDAO {

    private DatabaseReference reference;
    private ProgressDialog progressDialog;
    private Context ctx;
    private ValueEventListener valueEventListener;
    private HashMap<DatabaseReference, ValueEventListener> hashMap;
    public AvaliacaoDAO() {
        hashMap = new HashMap<>();
    }

    //Chamado pelo estabelecimento para avaliar a banda
    public void persistirAvaliacaoMusico(final String nomeEstab, final AvaliacaoMusicoEntity avaliacaoMus, Context ctx) {
        this.ctx = ctx;
        progressDialog = new ProgressDialog(this.ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde enquanto enviamos sua avaliação");
        progressDialog.setTitle("Enviando avaliação");
        progressDialog.show();

        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                .child(avaliacaoMus.getIdBanda())
                .child(avaliacaoMus.getIdEvento());
        reference.setValue(avaliacaoMus).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                        .child(TipoUsuario.ESTABELECIMENTO.name())
                        .child(nomeEstab)
                        .child(avaliacaoMus.getIdEvento())
                        .child("estabAvaliou");

                reference.setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                                .child(TipoUsuario.BANDA.name())
                                .child(avaliacaoMus.getIdBanda())
                                .child(avaliacaoMus.getIdEvento())
                                .child("estabAvaliou");

                        reference.setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                Mensagem.notificarFecharAtividade(ctx, "Sucesso!", "Sua avaliação foi enviada com sucesso.");
                            }
                        });
                    }
                });


            }
        });
    }

    //Chamado pela banda para avaliar o estabelecimento
    public void persistirAvaliacaoEstabelecimento(final String nomeBanda, final AvaliacaoEstabelecimentoEntity avaliacaoEstab, Context ctx) {
        this.ctx = ctx;
        progressDialog = new ProgressDialog(this.ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde enquanto enviamos sua avaliação");
        progressDialog.setTitle("Enviando avaliação");
        progressDialog.show();

        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                .child(avaliacaoEstab.getIdEstabelecimento())
                .child(avaliacaoEstab.getIdEvento());
        reference.setValue(avaliacaoEstab).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                        .child(TipoUsuario.BANDA.name())
                        .child(nomeBanda)
                        .child(avaliacaoEstab.getIdEvento())
                        .child("bandaAvaliou");

                reference.setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                                .child(TipoUsuario.ESTABELECIMENTO.name())
                                .child(avaliacaoEstab.getIdEstabelecimento())
                                .child(avaliacaoEstab.getIdEvento())
                                .child("bandaAvaliou");
                        reference.setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                Mensagem.notificarFecharAtividade(ctx, "Sucesso!", "Sua avaliação foi enviada com sucesso.");
                            }
                        });

                    }
                });



            }
        });
    }

    public void recuperarListaAvaliacoesPendentes(final String idUsuario, final String tipoUsuario, Context ctx){
        ArrayList<AvaliacoesPendentesDTO> dto = new ArrayList<>();
        ArrayList<PropostaEntity> propostaDto = new ArrayList<>();
        this.ctx = ctx;
        progressDialog = new ProgressDialog(this.ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde");
        progressDialog.setTitle("Recuperando avaliações");
        progressDialog.show();

        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                .child(tipoUsuario)
                .child(idUsuario);

        reference.addValueEventListener(valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hashMap.put(reference,valueEventListener);
                if(dataSnapshot != null){
                    removeValueEventListener(hashMap);
                    Iterator<DataSnapshot> dataSnapshotIterator = dataSnapshot.getChildren().iterator();
                    while(dataSnapshotIterator.hasNext()){
                        PropostaEntity proposta = dataSnapshotIterator.next().getValue(PropostaEntity.class);
                        if(proposta.getStatusProposta().equals(StatusProposta.FINALIZADO.name())) {
                            if ((tipoUsuario.equals(TipoUsuario.BANDA.name()) && !proposta.isBandaAvaliou())
                                    || (tipoUsuario.equals(TipoUsuario.ESTABELECIMENTO.name()) && !proposta.isEstabAvaliou()))
                                propostaDto.add(proposta);
                        }
                    }
                    if(!propostaDto.isEmpty()){
                        for(PropostaEntity prop : propostaDto){
                            String idRemetente = tipoUsuario.equals(TipoUsuario.BANDA.name()) ? prop.getIdEstabelecimento():prop.getIdBanda();
                            dto.add(new AvaliacoesPendentesDTO(prop.getIdProposta(), idRemetente, prop.getDataEvento()));
                        }
                        progressDialog.dismiss();
                        Intent intent = new Intent(ctx,TelaAvaliacoesPendentes.class);
                        intent.putExtra("tipoUsuario", tipoUsuario);
                        intent.putExtra("idUsuario", idUsuario);
                        intent.putExtra("listaAvaliacoes", dto);
                        ctx.startActivity(intent);
                    }else{
                        progressDialog.dismiss();
                        Mensagem.notificar(ctx, "Sem avaliações pendentes", "Você não possui eventos sem avaliação");
                    }
                }else{
                    progressDialog.dismiss();
                    Mensagem.notificar(ctx, "Sem avaliações pendentes", "Você não possui eventos sem avaliação");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                removeValueEventListener(hashMap);
            }
        });
    }

    public void recuperarAvaliacaoUnica(final String idUsuario, final String idEvento, final String tipoUsuario, Context ctx){
        this.ctx = ctx;
        progressDialog = new ProgressDialog(this.ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde");
        progressDialog.setTitle("Preparando avaliação");
        progressDialog.show();

        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Proposta.name())
                .child(tipoUsuario)
                .child(idUsuario)
                .child(idEvento);

        reference.addListenerForSingleValueEvent(valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hashMap.put(reference, valueEventListener);
                if(dataSnapshot != null){
                    removeValueEventListener(hashMap);
                    PropostaEntity proposta = dataSnapshot.getValue(PropostaEntity.class);
                    if(tipoUsuario.equals(TipoUsuario.BANDA.name())){
                        progressDialog.dismiss();
                        Intent intent = new Intent(ctx, TelaAvalicaoEstabelecimento.class);
                        intent.putExtra("idEvento", idEvento);
                        intent.putExtra("idEstabelecimento", proposta.getIdEstabelecimento());
                        ctx.startActivity(intent);
                    }else if(tipoUsuario.equals(TipoUsuario.ESTABELECIMENTO.name())){
                        progressDialog.dismiss();
                        Intent intent = new Intent(ctx, TelaAvaliacaoMusico.class);
                        intent.putExtra("idEvento", idEvento);
                        intent.putExtra("idBanda", proposta.getIdBanda());
                        ctx.startActivity(intent);
                    }
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
