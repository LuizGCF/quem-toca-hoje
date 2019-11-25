package com.example.quemtocahoje.Views;

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

import com.example.quemtocahoje.DTO.EventoDTO;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.tcc.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TelaHistorico extends AppCompatActivity {
    private List<EventoDTO> listaEvento;
    private CustomAdapterHistorico adapter;
    private RecyclerView lstHistoricoEventos;
    private String tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);

        lstHistoricoEventos = findViewById(R.id.lstHistoricoEventos);
        listaEvento = (ArrayList<EventoDTO>) getIntent().getSerializableExtra("eventosDTO");
        tipoUsuario = getIntent().getStringExtra("tipoUsuario");

        if(listaEvento.size() > 1) {
            Collections.sort(listaEvento, new Comparator<EventoDTO>() {
                DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                public int compare(EventoDTO o1, EventoDTO o2) {
                    try {
                        return f.parse(o1.getProposta().getDataEvento()).compareTo(f.parse(o2.getProposta().getDataEvento()));
                    } catch (ParseException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });
        }

        carregarRecyclerView();

    }

    private void carregarRecyclerView() {
        if(listaEvento != null) {
            lstHistoricoEventos.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            adapter = new CustomAdapterHistorico(listaEvento, tipoUsuario);

            lstHistoricoEventos.setLayoutManager(layoutManager);
            lstHistoricoEventos.setAdapter(adapter);

        }
    }
}

class CustomAdapterHistorico extends RecyclerView.Adapter<com.example.quemtocahoje.Views.CustomAdapterHistorico.ExampleViewHolder> {
    private List<EventoDTO> exampleList;
    private String tipoUsuario;

    CustomAdapterHistorico(List<EventoDTO> exampleList, String tipoUsuario) {
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
    public com.example.quemtocahoje.Views.CustomAdapterHistorico.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitemlayoutbanda,
                viewGroup, false);
        return new com.example.quemtocahoje.Views.CustomAdapterHistorico.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.quemtocahoje.Views.CustomAdapterHistorico.ExampleViewHolder exampleViewHolder, int i) {
        if(exampleList != null) {
            EventoDTO currentItem = exampleList.get(i);

            //exampleViewHolder.imagem.setImageBitmap(currentItem.getImagem());
            //  if (currentItem.getImagem() != "" && currentItem.getImagem() != null)
            //     Picasso.get().load(Uri.parse(currentItem.getImagem())).into(exampleViewHolder.imagem);
            String nome = (tipoUsuario.equals(TipoUsuario.BANDA.name()) ? currentItem.getProposta().getIdEstabelecimento():currentItem.getProposta().getIdBanda()) + " - " + currentItem.getProposta().getDataEvento();
            String descricao = tipoUsuario.equals(TipoUsuario.BANDA.name()) ?
                    currentItem.getAvaliacaoMusico() == null ?
                            "" :
                            "Comentário: " + currentItem.getAvaliacaoMusico().getTxtComentario()
                            + "\nPontualidade: " + currentItem.getAvaliacaoMusico().getPerformance()
                            + "\nComportamento: " + currentItem.getAvaliacaoMusico().getEstilo()
                            + "\nInteração: " + currentItem.getAvaliacaoMusico().getMusicalidade()
                    :currentItem.getAvaliacaoEstab() == null ?
                            "" :
                            "Comentário: " + currentItem.getAvaliacaoEstab().getTxtComentario()
                            +"\nReceptividade: " + currentItem.getAvaliacaoEstab().getReceptividade()
                            +"\nEstrutura: " + currentItem.getAvaliacaoEstab().getEstrutura()
                            +"\nOrganização: " + currentItem.getAvaliacaoEstab().getOrganizacao();

            exampleViewHolder.descricao.setText(descricao);
            exampleViewHolder.nome.setText(nome);
        }
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }
}


