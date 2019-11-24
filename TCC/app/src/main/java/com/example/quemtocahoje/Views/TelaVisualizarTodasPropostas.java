package com.example.quemtocahoje.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaVisualizarTodasPropostas extends AppCompatActivity {

    private List<PropostaEntity> propostasRecebidas;
    private CustomAdapterProposta adapter;
    private RecyclerView lstMinhasPropostas;
    private String tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visualizar_todas_propostas);

        lstMinhasPropostas = findViewById(R.id.lstMinhasPropostas);
        propostasRecebidas = (ArrayList<PropostaEntity>) getIntent().getSerializableExtra("listaPropostas");
        tipoUsuario = getIntent().getStringExtra("tipoUsuario");
        carregarRecyclerView();

    }

    private void carregarRecyclerView() {
        if(propostasRecebidas != null) {
            lstMinhasPropostas.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            adapter = new CustomAdapterProposta(propostasRecebidas, tipoUsuario);

            lstMinhasPropostas.setLayoutManager(layoutManager);
            lstMinhasPropostas.setAdapter(adapter);

        }
    }
}

class CustomAdapterProposta extends RecyclerView.Adapter<com.example.quemtocahoje.Views.CustomAdapterProposta.ExampleViewHolder> {
    private List<PropostaEntity> exampleList;
    private String tipoUsuario;

    CustomAdapterProposta(List<PropostaEntity> exampleList, String tipoUsuario) {
        this.exampleList = exampleList;
        this.tipoUsuario = tipoUsuario;
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imagem;
        TextView nome;
        TextView descricao;

        ExampleViewHolder(View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imgListItem);
            nome = itemView.findViewById(R.id.txtDescricaoBandaListItem);
            descricao = itemView.findViewById(R.id.txtDescricaoGeneroBandaListItem);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
        /*    AlertDialog.Builder adb=new AlertDialog.Builder(v.getContext());
            String item = nome.getText().toString();
            adb.setTitle("Responder convite");
            adb.setMessage("Deseja aceitar o convite para a banda " + item + "?");
            adb.setNegativeButton("Recusar", new AlertDialog.OnClickListener(){
                public void onClick(DialogInterface dialog, int which) {
                    exampleList.remove(getAdapterPosition());
                    CustomAdapterConvites.this.notifyDataSetChanged();
                    BandaDAO dao = new BandaDAO();
                    dao.atualizarConvite(email,nome.getText().toString(), StatusProposta.RECUSADO.name(), v.getContext());
                }});
            adb.setPositiveButton("Aceitar", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    exampleList.remove(getAdapterPosition());
                    CustomAdapterConvites.this.notifyDataSetChanged();
                    BandaDAO dao = new BandaDAO();
                    dao.atualizarConvite(email,nome.getText().toString(), StatusProposta.ACEITO.name(), v.getContext());
                }});
            adb.show();
*/
        }
    }

    @NonNull
    @Override
    public com.example.quemtocahoje.Views.CustomAdapterProposta.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitemlayoutbanda,
                viewGroup, false);
        return new com.example.quemtocahoje.Views.CustomAdapterProposta.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.quemtocahoje.Views.CustomAdapterProposta.ExampleViewHolder exampleViewHolder, int i) {
        if(exampleList != null) {
            PropostaEntity currentItem = exampleList.get(i);

            //exampleViewHolder.imagem.setImageBitmap(currentItem.getImagem());
            //  if (currentItem.getImagem() != "" && currentItem.getImagem() != null)
            //     Picasso.get().load(Uri.parse(currentItem.getImagem())).into(exampleViewHolder.imagem);
            exampleViewHolder.descricao.setText(currentItem.getDataEvento() + " - " + currentItem.getHorarioInicio() + " até "
                    + currentItem.getHorarioFim() + " - R$" + currentItem.getCache());
            exampleViewHolder.nome.setText(this.tipoUsuario.equals(TipoUsuario.BANDA.name())? currentItem.getIdEstabelecimento():currentItem.getIdBanda());
        }
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }
}

