package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.PropostasDTO;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaPropostasPendentes extends AppCompatActivity {

    ListView lstPropostasPendentes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_propostas_pendentes);

        getSupportActionBar().hide();

        lstPropostasPendentes = findViewById(R.id.lstPropostasPendentes);
        List<PropostasDTO> listapropostas = new ArrayList<>();
        listapropostas.add(new PropostasDTO("umidai","Bossa Nova Brasil Comércio Ltda","27/11/2019      8:00 às 12:00"));
        lstPropostasPendentes.setAdapter(new PropostasPendentesAdapter(listapropostas,this));
    }
}

class PropostasPendentesAdapter extends BaseAdapter{

    private List<PropostasDTO> lista;
    private Activity act;

    public PropostasPendentesAdapter(List<PropostasDTO> lista, Activity act)
    {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.customlistitempropostaspendentes,parent,false);
        TextView txtPropostaEstabelecimentoPendente = view.findViewById(R.id.txtPropostaEstabelecimentoPendente);
        TextView txtPropostaPeriodo = view.findViewById(R.id.txtPropostaPeriodo);

        txtPropostaEstabelecimentoPendente.setText(lista.get(position).getEstabelecimento());
        txtPropostaPeriodo.setText(lista.get(position).getPeriodo());

        return view;
    }
}
