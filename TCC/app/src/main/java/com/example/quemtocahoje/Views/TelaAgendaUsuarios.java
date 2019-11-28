package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    ListView lstAgendaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_agenda_usuarios);

        getSupportActionBar().hide();

        lstAgendaUsuarios = findViewById(R.id.lstAgendaUsuarios);

        List<PropostaEntity> list = new ArrayList<>();
        list.add(new PropostaEntity("Nirvana Cover","idestab","statusproposta","03:00","06:00","local","descricao",0.00,"27/11/2019","26/11/2019"));
        //list.add(new PropostaEntity("Bossa Nova Brasil Comércio Ltda.","idestab","statusproposta","20:00","23:55","local","descricao",0.00,"27/11/2019","26/11/2019"));
        lstAgendaUsuarios.setAdapter(new AgendaUsuariosAdapter(list,this));
    }

}
class AgendaUsuariosAdapter extends BaseAdapter{
    private List<PropostaEntity> lista;
    private Activity act;

    public AgendaUsuariosAdapter(List<PropostaEntity> lista, Activity act){
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
    }
}
