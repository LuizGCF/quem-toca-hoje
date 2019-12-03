package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.PropostasDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Model.PropostaDAO;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaPropostasPendentes extends AppCompatActivity {
    private ArrayList<PropostasDTO> listapropostas;
    private RecyclerView lstPropostasPendentes;
    private PropostasPendentesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_propostas_pendentes);

        getSupportActionBar().hide();

        lstPropostasPendentes = findViewById(R.id.lstPropostasPendentes);
        listapropostas = (ArrayList<PropostasDTO>) getIntent().getSerializableExtra("listaPropostas");
        carregarRecyclerView();

    }

    private void carregarRecyclerView() {
        if(listapropostas != null) {
            lstPropostasPendentes.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            adapter = new PropostasPendentesAdapter(listapropostas, getIntent().getStringExtra("nomeBanda"),TelaPropostasPendentes.this );

            lstPropostasPendentes.setLayoutManager(layoutManager);
            lstPropostasPendentes.setAdapter(adapter);

        }
    }
}

class PropostasPendentesAdapter extends RecyclerView.Adapter<com.example.quemtocahoje.Views.PropostasPendentesAdapter.ExampleViewHolder>{
    List<PropostasDTO> lista;
    String nomeBanda;
    Context ctx;
    PropostasPendentesAdapter(List <PropostasDTO> lista,String nomeBanda, Context ctx) {
        this.lista = lista;
        this.ctx = ctx;
        this.nomeBanda = nomeBanda;
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtPropostaEstabelecimentoPendente ;
        TextView txtPropostaPeriodo ;
        String id;

        ExampleViewHolder(View itemView) {
            super(itemView);
            txtPropostaEstabelecimentoPendente = itemView.findViewById((R.id.txtPropostaEstabelecimentoPendente));
            txtPropostaPeriodo = itemView.findViewById(R.id.txtPropostaPeriodo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PropostaDAO dao = new PropostaDAO();
            dao.abrirPropostaParaResposta(lista.get(getAdapterPosition()).getIdProposta(),nomeBanda, ctx);
        }
    }

    @NonNull
    @Override
    public com.example.quemtocahoje.Views.PropostasPendentesAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitempropostaspendentes,
                viewGroup, false);
        return new com.example.quemtocahoje.Views.PropostasPendentesAdapter.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.quemtocahoje.Views.PropostasPendentesAdapter.ExampleViewHolder exampleViewHolder, int i) {
        if(lista != null) {
            PropostasDTO currentItem = lista.get(i);
            exampleViewHolder.txtPropostaEstabelecimentoPendente.setText(currentItem.getEstabelecimento());
            exampleViewHolder.txtPropostaPeriodo.setText(currentItem.getPeriodo());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
