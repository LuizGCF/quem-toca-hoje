package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tcc.R;

import java.util.List;

public class TelaPerfilUsuario extends AppCompatActivity {

    ImageView imgPerfil;
    TextView txtNomePerfil;
    TextView txtDescricaoPerfil;
    ListView lstAvaliacoes;
    Button btnMensagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_usuario);

        imgPerfil = findViewById(R.id.imgPerfil);
        txtNomePerfil = findViewById(R.id.txtNomePerfil);
        txtDescricaoPerfil = findViewById(R.id.txtDescricaoPerfil);
        lstAvaliacoes = findViewById(R.id.lstAvaliacoes);
        //TODO receber os dados da tela de pesquisa anterior para preencher os campos, Avaliacao depois que tiver o dto da avaliacao
        //Com os cados recebidos, iniciar a conversa
        //Criar uma tela com as conversas do perfil x
    }
}

class AvaliacaoAdapter extends BaseAdapter{

    List lista;
    Activity act;
    AvaliacaoAdapter(List lista, Activity act){
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


        View view = act.getLayoutInflater().inflate(R.layout.customlistitemlayoutavaliacao, parent, false);

        //Curso curso = cursos.get(position);

        ImageView imgPerfil = view.findViewById(R.id.imgPerfil);
        TextView txtNomePerfil = view.findViewById(R.id.txtNomePerfil);
        TextView txtDescricaoPerfil = view.findViewById(R.id.txtDescricaoPerfil);


        //txtNomePerfil.setText(curso.getNome());
        //txtDescricaoPerfil.setText(curso.getDescricao());
        //imgPerfil.setImageResource(R.drawable.java);

        return view;
    }
}
