package com.example.quemtocahoje.Views;

import android.app.Activity;
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

import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.tcc.R;

import java.util.ArrayList;
import java.util.List;

public class TelaAgendaUsuarios extends AppCompatActivity {

    RecyclerView lstAgendaUsuarios;
    List<PropostaEntity> listaagenda;
    AgendaUsuariosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_agenda_usuarios);

        getSupportActionBar().hide();

        lstAgendaUsuarios = findViewById(R.id.lstAgendaUsuarios);

         listaagenda= new ArrayList<>();
        //trazer a lista com a agenda do usuario
        //listaagenda.add(new PropostaEntity("Nirvana Cover","idestab","statusproposta","03:00","06:00","local","descricao",0.00,"27/11/2019","26/11/2019"));
        //listaagenda.add(new PropostaEntity("Nirvana Cover","idestab","statusproposta","03:00","06:00","local","descricao",0.00,"27/11/2019","26/11/2019"));
        //listaagenda.add(new PropostaEntity("Nirvana Cover","idestab","statusproposta","03:00","06:00","local","descricao",0.00,"27/11/2019","26/11/2019"));
        //listaagenda.add(new PropostaEntity("Bossa Nova Brasil Comércio Ltda.","idestab","statusproposta","20:00","23:55","local","descricao",0.00,"27/11/2019","26/11/2019"));

        carregarRecyclerView();
    }

    private void carregarRecyclerView() {
        if(listaagenda != null) {
            lstAgendaUsuarios.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            adapter = new AgendaUsuariosAdapter(listaagenda, this);

            lstAgendaUsuarios.setLayoutManager(layoutManager);
            lstAgendaUsuarios.setAdapter(adapter);

        }
    }

}
class AgendaUsuariosAdapter extends RecyclerView.Adapter<AgendaUsuariosAdapter.ExampleViewHolder> {
    private List<PropostaEntity> lista;
    private Activity act;

    public AgendaUsuariosAdapter(List<PropostaEntity> lista, Activity act){
        this.lista = lista;
        this.act = act;
    }
    class ExampleViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView txtAgendaEvento;
        TextView txtAgendaData;
        TextView txtAgendaHorarioInicio;
        TextView txtAgendaHorarioFim;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAgendaEvento = itemView.findViewById(R.id.txtAgendaEvento);
            txtAgendaData = itemView.findViewById(R.id.txtAgendaData);
            txtAgendaHorarioInicio = itemView.findViewById(R.id.txtAgendaHorarioInicio);
            txtAgendaHorarioFim = itemView.findViewById(R.id.txtAgendaHorarioFim);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PropostaEntity proposta = lista.get(getAdapterPosition());

            Intent telaAgenda = new Intent(v.getContext(),TelaAgenda.class);
            telaAgenda.putExtra("proposta",proposta);
            v.getContext().startActivity(telaAgenda);

        }
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitemagenda,
                viewGroup, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        PropostaEntity currentItem = lista.get(i);

        exampleViewHolder.txtAgendaEvento.setText(currentItem.getIdBanda());

        exampleViewHolder.txtAgendaData.setText(currentItem.getDataEvento());

        exampleViewHolder.txtAgendaHorarioInicio.setText(currentItem.getHorarioInicio() + " às ");

        exampleViewHolder.txtAgendaHorarioFim.setText(currentItem.getHorarioFim());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    /*
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
        View view = act.getLayoutInflater().inflate(R.layout.customlistitemagenda,parent,false);
        TextView txtAgendaEvento = view.findViewById(R.id.txtAgendaEvento);
        TextView txtAgendaData = view.findViewById(R.id.txtAgendaData);
        TextView txtAgendaHorarioInicio = view.findViewById(R.id.txtAgendaHorarioInicio);
        TextView txtAgendaHorarioFim = view.findViewById(R.id.txtAgendaHorarioFim);

        //TODO receber o tipo usuario para preencher com os valores certos
        txtAgendaEvento.setText(lista.get(position).getIdBanda());

        txtAgendaData.setText(lista.get(position).getDataEvento());

        txtAgendaHorarioInicio.setText(lista.get(position).getHorarioInicio() + " às ");

        txtAgendaHorarioFim.setText(lista.get(position).getHorarioFim());
        return view;
    }*/

}
