package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AvaliacoesPendentesDTO;
import com.example.tcc.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TelaAvaliacoesPendentes extends AppCompatActivity {

    ListView lstAvaliacoesPendentes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacoes_pendentes);
        lstAvaliacoesPendentes = findViewById(R.id.lstAvaliacoesPendentes);

        List<AvaliacoesPendentesDTO> avaliacoesPendentesAdapters = new ArrayList<>();
        avaliacoesPendentesAdapters.add(new AvaliacoesPendentesDTO("idtest","nomee","25/11/2019"));
        lstAvaliacoesPendentes.setAdapter(new AvaliacoesPendentesAdapter(avaliacoesPendentesAdapters,this));


    }
}

class AvaliacoesPendentesAdapter extends BaseAdapter implements View.OnClickListener
{
    List<AvaliacoesPendentesDTO> lista;
    Activity act;
    TextView txtDescricaoAvaliacaoPendente;
    TextView txtDataAvaliacaoPendente;
    AvaliacoesPendentesAdapter(List<AvaliacoesPendentesDTO> lista, Activity act){
        this.lista = lista;
        this.act = act;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.customlistitemavaliacoespendentes, parent, false);
        txtDescricaoAvaliacaoPendente = view.findViewById(R.id.txtDescricaoAvaliacaoPendente);
        txtDataAvaliacaoPendente = view.findViewById(R.id.txtDataAvaliacaoPendente);

        txtDescricaoAvaliacaoPendente.setText(lista.get(position).getNome());
        txtDataAvaliacaoPendente.setText(lista.get(position).getData());

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}