package com.example.quemtocahoje.Model;

import android.app.ProgressDialog;
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
    private ProgressDialog progressDialog;
    private Context ctx;
    public AvaliacaoDAO() {
    }

    //Chamado pelo estabelecimento para avaliar a banda
    public void persistirAvaliacaoMusico(final AvaliacaoMusicoEntity avaliacaoMus, Context ctx) {
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
                progressDialog.dismiss();
                Mensagem.notificarFecharAtividade(ctx, "Sucesso!", "Sua avaliação foi enviada com sucesso.");
            }
        });
    }

    //Chamado pela banda para avaliar o estabelecimento
    public void persistirAvaliacaoEstabelecimento(final AvaliacaoEstabelecimentoEntity avaliacaoEstab, Context ctx) {
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
                progressDialog.dismiss();
                Mensagem.notificarFecharAtividade(ctx, "Sucesso!", "Sua avaliação foi enviada com sucesso.");

            }
        });
    }
}
