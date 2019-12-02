package com.example.quemtocahoje.Views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AvaliacoesPendentesDTO;
import com.example.quemtocahoje.Enum.StatusProposta;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Model.AvaliacaoDAO;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaAvaliacoesPendentes extends AppCompatActivity {

    private List<AvaliacoesPendentesDTO> avaliacoesPendentesAdapters;
    private RecyclerView lstAvaliacoesPendentes;
    private AvaliacoesPendentesAdapter adapter;
    private String tipoUsuario;
    private String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacoes_pendentes);
        lstAvaliacoesPendentes = findViewById(R.id.lstAvaliacoesPendentes);
        tipoUsuario = getIntent().getStringExtra("tipoUsuario");
        idUsuario = getIntent().getStringExtra("idUsuario");

        avaliacoesPendentesAdapters = new ArrayList<>();
        avaliacoesPendentesAdapters = (ArrayList<AvaliacoesPendentesDTO>) getIntent().getSerializableExtra("listaAvaliacoes");
        carregarRecyclerView();

    }

    private void carregarRecyclerView() {
        if(avaliacoesPendentesAdapters != null) {
            lstAvaliacoesPendentes.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            adapter = new AvaliacoesPendentesAdapter(avaliacoesPendentesAdapters, tipoUsuario, idUsuario, TelaAvaliacoesPendentes.this);

            lstAvaliacoesPendentes.setLayoutManager(layoutManager);
            lstAvaliacoesPendentes.setAdapter(adapter);

        }
    }
}

class AvaliacoesPendentesAdapter extends RecyclerView.Adapter<com.example.quemtocahoje.Views.AvaliacoesPendentesAdapter.ExampleViewHolder>{
        List<AvaliacoesPendentesDTO> lista;
        String tipoUsuario;
        String idUsuario;
        Context ctx;
        AvaliacoesPendentesAdapter(List <AvaliacoesPendentesDTO> lista, String tipoUsuario, String idUsuario, Context ctx) {
        this.lista = lista;
        this.tipoUsuario = tipoUsuario;
        this.ctx = ctx;
        this.idUsuario = idUsuario;
    }

        class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView nome;
            TextView data;
            String id;

            ExampleViewHolder(View itemView) {
                super(itemView);
                nome = itemView.findViewById(R.id.txtDescricaoAvaliacaoPendente);
                data = itemView.findViewById(R.id.txtDataAvaliacaoPendente);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                AvaliacoesPendentesDTO dto = lista.get(getAdapterPosition());
                if(tipoUsuario.equals(TipoUsuario.BANDA.name())){
                    Intent intent = new Intent(ctx, TelaAvalicaoEstabelecimento.class);
                    intent.putExtra("idEvento", dto.getId());
                    intent.putExtra("idEstabelecimento", dto.getNome());
                    intent.putExtra("idBanda", idUsuario);
                    ctx.startActivity(intent);
                }else if(tipoUsuario.equals(TipoUsuario.ESTABELECIMENTO.name())){
                    Intent intent = new Intent(ctx, TelaAvaliacaoMusico.class);
                    intent.putExtra("idEvento", dto.getId());
                    intent.putExtra("idBanda", dto.getNome());
                    intent.putExtra("idEstabelecimento", idUsuario);

                    ctx.startActivity(intent);
                }

            }
        }

    @NonNull
    @Override
    public com.example.quemtocahoje.Views.AvaliacoesPendentesAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitemavaliacoespendentes,
                viewGroup, false);
        return new com.example.quemtocahoje.Views.AvaliacoesPendentesAdapter.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.quemtocahoje.Views.AvaliacoesPendentesAdapter.ExampleViewHolder exampleViewHolder, int i) {
        if(lista != null) {
            AvaliacoesPendentesDTO currentItem = lista.get(i);

            //exampleViewHolder.imagem.setImageBitmap(currentItem.getImagem());
            //  if (currentItem.getImagem() != "" && currentItem.getImagem() != null)
            //     Picasso.get().load(Uri.parse(currentItem.getImagem())).into(exampleViewHolder.imagem);
            exampleViewHolder.nome.setText(currentItem.getNome());
            exampleViewHolder.data.setText(currentItem.getData());
            exampleViewHolder.id = currentItem.getId();
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
   }