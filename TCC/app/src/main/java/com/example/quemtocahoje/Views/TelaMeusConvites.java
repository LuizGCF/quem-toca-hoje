package com.example.quemtocahoje.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.DTO.ConvitesRecebidosDTO;
import com.example.quemtocahoje.DTO.ItensListaBuscaDTO;
import com.example.quemtocahoje.Enum.StatusProposta;
import com.example.quemtocahoje.Model.BandaDAO;
import com.example.tcc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class TelaMeusConvites extends AppCompatActivity{

    private List<ConvitesRecebidosDTO> convitesRecebidosDTO;
    private CustomAdapterConvites adapter;
    private RecyclerView lstMeusConvites;
    private String autenticacaoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tela_meus_convites);

         lstMeusConvites = findViewById(R.id.lstMeusConvites);
        autenticacaoEmail = getIntent().getStringExtra("emailAutenticacao");
        convitesRecebidosDTO = (ArrayList<ConvitesRecebidosDTO>) getIntent().getSerializableExtra("listaConvites");

        carregarRecyclerView();

    }

    private void carregarRecyclerView() {
        if(convitesRecebidosDTO != null) {
            //RecyclerView lstMeusConvites = findViewById(R.id.lstMeusConvites);
            lstMeusConvites.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            adapter = new CustomAdapterConvites(convitesRecebidosDTO, autenticacaoEmail);

            lstMeusConvites.setLayoutManager(layoutManager);
            lstMeusConvites.setAdapter(adapter);

        }
    }

}



class CustomAdapterConvites extends RecyclerView.Adapter<com.example.quemtocahoje.Views.CustomAdapterConvites.ExampleViewHolder> {
    private List<ConvitesRecebidosDTO> exampleList;
    private String email;

    CustomAdapterConvites(List<ConvitesRecebidosDTO> exampleList, String email) {
        this.exampleList = exampleList;
        this.email = email;
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
            AlertDialog.Builder adb=new AlertDialog.Builder(v.getContext());
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

        }
    }

    @NonNull
    @Override
    public com.example.quemtocahoje.Views.CustomAdapterConvites.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitemlayoutbanda,
                viewGroup, false);
        return new com.example.quemtocahoje.Views.CustomAdapterConvites.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.quemtocahoje.Views.CustomAdapterConvites.ExampleViewHolder exampleViewHolder, int i) {
            if(exampleList != null) {
                ConvitesRecebidosDTO currentItem = exampleList.get(i);

                //exampleViewHolder.imagem.setImageBitmap(currentItem.getImagem());
              //  if (currentItem.getImagem() != "" && currentItem.getImagem() != null)
               //     Picasso.get().load(Uri.parse(currentItem.getImagem())).into(exampleViewHolder.imagem);
                exampleViewHolder.descricao.setText(currentItem.getGeneros());
                exampleViewHolder.nome.setText(currentItem.getNomeBanda());
            }
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

   /* @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ConvitesRecebidosDTO> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ConvitesRecebidosDTO item : exampleListFull) {
                    if (item.getNomeBanda().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/
}
