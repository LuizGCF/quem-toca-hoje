package com.example.quemtocahoje.Views;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.ItensListaBuscaDTO;
import com.example.quemtocahoje.Enum.TipoArquivo;
import com.example.quemtocahoje.POJO.Autenticacao;
import com.example.quemtocahoje.POJO.Banda;
import com.example.quemtocahoje.POJO.Musico;
import com.example.quemtocahoje.Persistencia.Banco;
import com.example.quemtocahoje.Persistencia.Entity.ArquivoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.ConversaoArquivo;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaPesquisaMusico extends AppCompatActivity {

    //MOCK
    //TODO mudar o resultado da list para o resultado do select que vir do ROOM
    //List<Musico> musicos = new ArrayList<Musico>();
    private CustomAdapter adapter;
    private List<ItensListaBuscaDTO> allItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisa_musico);

        carregarDados();
        carregarRecyclerView();

    }
    private void carregarRecyclerView() {
        RecyclerView lstResultadoPesquisaMusico = lstResultadoPesquisaMusico = findViewById(R.id.lstResultadoPesquisaMusico);
        lstResultadoPesquisaMusico.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapter(allItens);

        lstResultadoPesquisaMusico.setLayoutManager(layoutManager);
        lstResultadoPesquisaMusico.setAdapter(adapter);
    }
    private void carregarDados() {
        allItens = new ArrayList<>();
        Banco bd = Banco.getDatabase(getApplicationContext());
        List<EstabelecimentoEntity> estabelecimentos =  bd.estabelecimentoDao().findAllEstabelecimentos();
        List<MusicoEntity> musicos =  bd.musicoDao().findAllMusicos();
        try{
            for(EstabelecimentoEntity estabelecimento : estabelecimentos)
            {
                Bitmap imagem = null;
                AutenticacaoEntity autenticacaoEntity = bd.autenticacaoDao().findAutenticacaoId(estabelecimento.getAutenticacao_id());
                byte[] arquivoEntity = bd.arquivoDao().findAnexoArquivoById(autenticacaoEntity.getIdAutenticacao(), TipoArquivo.FOTO_PERFIL.name());
                if(arquivoEntity!=null)
                {
                    imagem = ConversaoArquivo.getImagem(TipoArquivo.FOTO_PERFIL.name(),TelaPesquisaMusico.this,autenticacaoEntity.getIdAutenticacao());
                }
                allItens.add(new ItensListaBuscaDTO(null,estabelecimento.getNomeFantasia(),estabelecimento.getDescricao()));

            }
            for(MusicoEntity musico : musicos)
            {
                Bitmap imagem = null;
                AutenticacaoEntity autenticacaoEntity = bd.autenticacaoDao().findAutenticacaoId(musico.getAutenticacao_id());
                byte[] arquivoEntity = bd.arquivoDao().findAnexoArquivoById(autenticacaoEntity.getIdAutenticacao(), TipoArquivo.FOTO_PERFIL.name());
                if(arquivoEntity!=null)
                {
                    imagem= ConversaoArquivo.getImagem(TipoArquivo.FOTO_PERFIL.name(),TelaPesquisaMusico.this,autenticacaoEntity.getIdAutenticacao());
                }
                allItens.add(new ItensListaBuscaDTO(imagem,musico.getNomeArtistico(),musico.getNome()));
            }
        }
        catch (Exception e)
        {
            Log.d("ERRO",e.getMessage());
        }
        //mock sem a imagem
        /*allItens.add(new ItensListaBuscaDTO( null,"Titulo", "Subtitulo"));
        allItens.add(new ItensListaBuscaDTO(null,"AA", "BB"));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

/*class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return musicos.size();
        }

        @Override
        public Object getItem(int position) {
            return musicos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.customlistitemlayoutbanda,null);

            ImageView imgListItem = view.findViewById(R.id.imgListItem);
            TextView txtDescricaoBandaListItem = view.findViewById(R.id.txtDescricaoBandaListItem);
            TextView txtDescricaoGeneroBandaListItem = view.findViewById(R.id.txtDescricaoGeneroBandaListItem);

            imgListItem.setImageResource(R.mipmap.upload_imagem);
            txtDescricaoBandaListItem.setText(musicos.get(position).getNomeArtistico());//Seria o nome da banda no caso
            txtDescricaoGeneroBandaListItem.setText(musicos.get(position).getNomeUsuario());//Seria o nome do musico(ou esse mesmo?)

            return view;
        }
    }*/
}

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ExampleViewHolder> implements Filterable
{
    private List<ItensListaBuscaDTO> exampleList;
    private List<ItensListaBuscaDTO> exampleListFull;

    CustomAdapter(List<ItensListaBuscaDTO> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }
    class ExampleViewHolder extends RecyclerView.ViewHolder {
        ImageView imagem;
        TextView nome;
        TextView descricao;

        ExampleViewHolder(View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imgListItem);
            nome = itemView.findViewById(R.id.txtDescricaoBandaListItem);
            descricao = itemView.findViewById(R.id.txtDescricaoGeneroBandaListItem);
        }

    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitemlayoutbanda,
                viewGroup, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ItensListaBuscaDTO currentItem = exampleList.get(i);

        exampleViewHolder.imagem.setImageBitmap(currentItem.getImagem());
        exampleViewHolder.descricao.setText(currentItem.getNome());
        exampleViewHolder.nome.setText(currentItem.getDescricao());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ItensListaBuscaDTO> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ItensListaBuscaDTO item : exampleListFull) {
                    if (item.getNome().toLowerCase().contains(filterPattern)) {
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
    };
}
