package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.POJO.Endereco;
import com.example.quemtocahoje.POJO.Estabelecimento;
import com.example.quemtocahoje.POJO.Musico;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaPesquisaEstabelecimento extends AppCompatActivity {

    //MOCK
    //TODO mudar o resultado da list para o resultado do select que vir do ROOM
    List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

    private ListView lstResultadoPesquisaEstabelecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisa_estabelecimento);

        lstResultadoPesquisaEstabelecimento = findViewById(R.id.lstResultadoPesquisaEstabelecimento);

        estabelecimentos.add(new Estabelecimento("Gabriel",null,null,null,null,null,null,"Rua 123",null,null,null,
                new Endereco(null,"Parque Itaberaba",null,null,null,null),null,null,null));
        estabelecimentos.add(new Estabelecimento("Matheus",null,null,null,null,null,null,"Rua 321",null,null,null,
                new Endereco(null,"Freguesia do Ó",null,null,null,null),null,null,null));

        lstResultadoPesquisaEstabelecimento.setAdapter(new CustomAdapter());
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return estabelecimentos.size();
        }

        @Override
        public Object getItem(int position) {
            return estabelecimentos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            view = getLayoutInflater().inflate(R.layout.customlistitemlayoutestabelecimento,null);

            ImageView imgListItemEstabelecimento = view.findViewById(R.id.imgListItemEstabelecimento);
            TextView txtDescricaoEstabelecimentoListItem = view.findViewById(R.id.txtDescricaoEstabelecimentoListItem);
            TextView txtLocalEstabelecimentoListItem = view.findViewById(R.id.txtLocalEstabelecimentoListItem);

            imgListItemEstabelecimento.setImageResource(R.mipmap.upload_imagem);
            txtDescricaoEstabelecimentoListItem.setText(estabelecimentos.get(position).getDescricao());
            txtLocalEstabelecimentoListItem.setText(estabelecimentos.get(position).getEndereco().getBairro());

            return view;
        }
    }
}
