package com.example.quemtocahoje.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class AvaliacaoDAO {

    private DatabaseReference reference;

    public AvaliacaoDAO() {
    }

    //Chamado pelo estabelecimento para avaliar a banda
    public void persistirAvaliacaoMusico(final AvaliacaoMusicoEntity avaliacaoMus, Context ctx) {
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                .child(avaliacaoMus.getIdBanda())
                .child(avaliacaoMus.getIdEvento())
                .push();
        reference.setValue(avaliacaoMus).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()) {
                    String texto = "Avaliação realizada com sucesso!!!";
                    Mensagem.notificarFecharAtividade(ctx, "Sucesso!", texto);
                }
            }
        });

    }

    //Chamado pela banda para avaliar o estabelecimento
    public void persistirAvaliacaoEstabelecimento(final AvaliacaoEstabelecimentoEntity avaliacaoEstab, Context ctx) {
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                .child(avaliacaoEstab.getIdEstabelecimento())
                .child(avaliacaoEstab.getIdEvento());
        reference.setValue(avaliacaoEstab).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()) {
                    String texto = "Avaliação realizada com sucesso!!!";
                    Mensagem.notificarFecharAtividade(ctx, "Sucesso!", texto);
                }
            }
        });
    }
}
