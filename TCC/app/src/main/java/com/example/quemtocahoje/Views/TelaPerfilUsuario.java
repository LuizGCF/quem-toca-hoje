package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AvaliacaoDTO;
import com.example.quemtocahoje.POJO.Avaliacao;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TelaPerfilUsuario extends AppCompatActivity {

    ImageView imgPerfil;
    TextView txtNomePerfil;
    TextView txtDescricaoPerfil;
    ImageView imgImagemUsuario1;
    ImageView imgImagemUsuario2;
    ImageView imgImagemUsuario3;
    ListView lstAvaliacoes;
    Button btnMensagem;

    AvaliacaoDTO a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_usuario);

        imgPerfil = findViewById(R.id.imgPerfil);
        txtNomePerfil = findViewById(R.id.txtNomePerfil);
        txtDescricaoPerfil = findViewById(R.id.txtDescricaoPerfil);
        imgImagemUsuario1 = findViewById(R.id.imgImagemUsuario1);
        imgImagemUsuario2 = findViewById(R.id.imgImagemUsuario2);
        imgImagemUsuario3 = findViewById(R.id.imgImagemUsuario3);
        lstAvaliacoes = findViewById(R.id.lstAvaliacoes);

        a = (AvaliacaoDTO) getIntent().getSerializableExtra("usuario");

        txtNomePerfil.setText("Olá "+a.getNomePerfil());
        txtDescricaoPerfil.setText(a.getDescricaoGenero());



        carregarImagemPerfil();

        carregarImagensDemonstracao();

        //TODO Verificar perfil Banda qual id esta utilizando para a parte da imagem
        //TODO receber os dados da tela de pesquisa anterior para preencher os campos, Avaliacao depois que tiver o dto da avaliacao
        //Com os cados recebidos, iniciar a conversa
        //Criar uma tela com as conversas do perfil x
    }

    private void carregarImagensDemonstracao() {
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(a.getIdUsuario()+"/imagem1.png");
        StorageReference ref1 = FirebaseStorage.getInstance().getReference().child(a.getIdUsuario()+"/imagem2.png");
        StorageReference ref2 = FirebaseStorage.getInstance().getReference().child(a.getIdUsuario()+"/imagem3.png");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(uri.toString() != "" && uri != null)
                    Picasso.get().load(Uri.parse(a.getImagemURI())).into(imgImagemUsuario1);
            }
        });
        ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(uri.toString() != "" && uri != null)
                    Picasso.get().load(Uri.parse(a.getImagemURI())).into(imgImagemUsuario2);
            }
        });
        ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(uri.toString() != "" && uri != null)
                    Picasso.get().load(Uri.parse(a.getImagemURI())).into(imgImagemUsuario3);
            }
        });

    }

    private void carregarImagemPerfil() {
        if(a.getImagemURI() != "" && a.getImagemURI() != null)
            Picasso.get().load(Uri.parse(a.getImagemURI())).into(imgPerfil);
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
