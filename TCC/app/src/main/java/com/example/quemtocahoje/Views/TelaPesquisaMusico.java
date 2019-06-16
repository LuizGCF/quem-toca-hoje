package com.example.quemtocahoje.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.POJO.Autenticacao;
import com.example.quemtocahoje.POJO.Banda;
import com.example.quemtocahoje.POJO.Musico;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaPesquisaMusico extends AppCompatActivity {

    //MOCK
    //TODO mudar o resultado da list para o resultado do select que vir do ROOM
    List<Musico> musicos = new ArrayList<Musico>();
    private ListView lstResultadoPesquisaMusico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisa_musico);

        lstResultadoPesquisaMusico = findViewById(R.id.lstResultadoPesquisaMusico);

        /*musicos.add(new Musico("Gabriel",null,null,"Gaaab","11950601650",null,null,null,null));
        musicos.add(new Musico("Matheus",null,null,"Maaat","11912345678",null,null,null,null));*/

        lstResultadoPesquisaMusico.setAdapter(new CustomAdapter());
    }


    class CustomAdapter extends BaseAdapter{

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
            //txtDescricaoBandaListItem.setText(musicos.get(position).getNomeArtistico());//Seria o nome da banda no caso
            //txtDescricaoGeneroBandaListItem.setText(musicos.get(position).getNomeUsuario());//Seria o nome do musico(ou esse mesmo?)

            return view;
        }
    }
}


