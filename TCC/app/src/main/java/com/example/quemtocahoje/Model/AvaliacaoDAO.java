package com.example.quemtocahoje.Model;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class AvaliacaoDAO {

    private DatabaseReference reference;

    public AvaliacaoDAO() {
    }

    //Chamado pelo estabelecimento para avaliar a banda
    public void persistirAvaliacaoMusico(final AvaliacaoMusicoEntity avaliacaoMus) {
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                .child(avaliacaoMus.getIdBanda())
                .child(avaliacaoMus.getIdEvento())
                .push();
        reference.setValue(avaliacaoMus);
    }

    //Chamado pela banda para avaliar o estabelecimento
    public void persistirAvaliacaoEstabelecimento(final AvaliacaoEstabelecimentoEntity avaliacaoEstab) {
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                .child(avaliacaoEstab.getIdEstabelecimento())
                .child(avaliacaoEstab.getIdEvento());
        reference.setValue(avaliacaoEstab);
    }
}
