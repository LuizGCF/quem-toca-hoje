package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quemtocahoje.Chat.Activitys.ConversaActivity;
import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.DTO.AvaliacaoDTO;
import com.example.quemtocahoje.DTO.EventoDTO;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Model.AutenticacaoDAO;
import com.example.quemtocahoje.POJO.Autenticacao;
import com.example.quemtocahoje.POJO.Avaliacao;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.quemtocahoje.Persistencia.Entity.PropostaEntity;
import com.example.quemtocahoje.Utility.EncodeBase64;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    Button btnProposta;
    TextView txtMediaAvaliacoes;
    private HashMap<DatabaseReference, ValueEventListener> hashMap;
    private ValueEventListener valueEventListener;

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
        btnMensagem = findViewById(R.id.btnMensagem);
        btnProposta = findViewById(R.id.btnProposta);
        txtMediaAvaliacoes = findViewById(R.id.txtMediaAvaliacoes);

        btnMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =  FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String idRemetente = EncodeBase64.toBase64(email);
                //String test = EncodeBase64.toBase64("d@gmail.com");
                Intent telaConversa = new Intent(TelaPerfilUsuario.this, ConversaActivity.class);

                telaConversa.putExtra("remetente", idRemetente);
                telaConversa.putExtra("destinatario",a.getIdUsuario());
                startActivity(telaConversa);
            }
        });

        btnProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaProposta = new Intent(TelaPerfilUsuario.this, TelaProposta.class);
                telaProposta.putExtra("intentTela","ENVIAR");
                telaProposta.putExtra("labelDestinatario","ta");
                telaProposta.putExtra("nomeEstabelecimento","estab");
                startActivity(telaProposta);
            }
        });

        hashMap = new HashMap<>();

        a = (AvaliacaoDTO) getIntent().getSerializableExtra("usuario");

        if(a != null) {
            txtNomePerfil.setText(a.getNomePerfil());
            txtDescricaoPerfil.setText(a.getDescricaoGenero());
            if(a.getListaAvaliacoes() != null && a.getListaAvaliacoes().size() > 0){
                AvaliacaoAdapter avaliacaoAdapter = new AvaliacaoAdapter(a.getListaAvaliacoes(),this,a.getTipoUsuario());
                lstAvaliacoes.setAdapter(avaliacaoAdapter);
                txtMediaAvaliacoes.setText("Média: "+ calcularMedia(a.getTipoUsuario(),a.getListaAvaliacoes()));
            }
            else
            {
                lstAvaliacoes.setVisibility(View.GONE);
                txtMediaAvaliacoes.setVisibility(View.GONE);
            }
        }
        //carregarAvaliacoes(a.getIdUsuario());

        carregarImagemPerfil();

        carregarImagensDemonstracao();

        //TODO Verificar que o usuario logado tbm aparece na lista de pesquisa, talvez carregar por perfil ja resolva isso
        //TODO Verificar perfil Banda qual id esta utilizando para a parte da imagem
        //TODO receber os dados da tela de pesquisa anterior para preencher os campos, Avaliacao depois que tiver o dto da avaliacao
        //Com os cados recebidos, iniciar a conversa
        //Criar uma tela com as conversas do perfil x
    }

    private String calcularMedia(String tipoUsuario, List listaAvaliacoes) {
        double media = 0;
        if(tipoUsuario.equals(TipoUsuario.ESTABELECIMENTO.name()))
        {
            for (Object avaliacao: listaAvaliacoes) {
                media += (((AvaliacaoEstabelecimentoEntity) avaliacao).getReceptividade() +((AvaliacaoEstabelecimentoEntity) avaliacao).getEstrutura() + ((AvaliacaoEstabelecimentoEntity) avaliacao).getOrganizacao())/3;

            }
        }
        else
        {
            for (Object avaliacao: listaAvaliacoes) {
                media += (((AvaliacaoMusicoEntity) avaliacao).getEstilo() +((AvaliacaoMusicoEntity) avaliacao).getPerformance() + ((AvaliacaoMusicoEntity) avaliacao).getMusicalidade())/3;

            }
        }
        return ""+media/listaAvaliacoes.size();
    }


    private void carregarImagensDemonstracao() {
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(a.getIdUsuario()+"/imagem1.png");
        StorageReference ref1 = FirebaseStorage.getInstance().getReference().child(a.getIdUsuario()+"/imagem2.png");
        StorageReference ref2 = FirebaseStorage.getInstance().getReference().child(a.getIdUsuario()+"/imagem3.png");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(uri.toString() != "" && uri != null)
                    Picasso.get().load(uri).into(imgImagemUsuario1);
            }
        });
        ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(uri.toString() != "" && uri != null)
                    Picasso.get().load(uri).into(imgImagemUsuario2);
            }
        });
        ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(uri.toString() != "" && uri != null)
                    Picasso.get().load(uri).into(imgImagemUsuario3);
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
    String tipoUsuario;
    AvaliacaoAdapter(List lista, Activity act,String tipoUsuario){
        this.lista = lista;
        this.act = act;
        this.tipoUsuario = tipoUsuario;
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

        TextView txtAvaliacao1 = view.findViewById(R.id.txtAvaliacao1);
        TextView txtAvaliacao2 = view.findViewById(R.id.txtAvaliacao2);
        TextView txtAvaliacao3 = view.findViewById(R.id.txtAvaliacao3);
        RatingBar rbAvaliacao1 = view.findViewById(R.id.rbAvaliacao1);
        RatingBar rbAvaliacao2 = view.findViewById(R.id.rbAvaliacao2);
        RatingBar rbAvaliacao3 = view.findViewById(R.id.rbAvaliacao3);
        TextView txtComentarioFeito = view.findViewById(R.id.txtComentarioFeito);

        if(tipoUsuario.equals(TipoUsuario.ESTABELECIMENTO.name()))
        {
            txtAvaliacao1.setText("Organização");
            txtAvaliacao2.setText("Estrutura");
            txtAvaliacao3.setText("Receptividade");

            int organizacao = (int)((AvaliacaoEstabelecimentoEntity) lista.get(position)).getOrganizacao();
            int estrutura = (int)((AvaliacaoEstabelecimentoEntity) lista.get(position)).getEstrutura();
            int receptividade = (int)((AvaliacaoEstabelecimentoEntity) lista.get(position)).getReceptividade();

            rbAvaliacao1.setRating(organizacao);
            rbAvaliacao2.setRating(estrutura);
            rbAvaliacao3.setRating(receptividade);

            txtComentarioFeito.setText(((AvaliacaoEstabelecimentoEntity) lista.get(position)).getTxtComentario());
        }
        else
        {
            txtAvaliacao1.setText("Estilo");
            txtAvaliacao2.setText("Musicalidade");
            txtAvaliacao3.setText("Performance");

            int estilo = (int)((AvaliacaoMusicoEntity) lista.get(position)).getEstilo();
            int musicalidade = (int)((AvaliacaoMusicoEntity) lista.get(position)).getMusicalidade();
            int performance = (int)((AvaliacaoMusicoEntity) lista.get(position)).getPerformance();

            rbAvaliacao1.setRating(estilo);
            rbAvaliacao2.setRating(musicalidade);
            rbAvaliacao3.setRating(performance);


            txtComentarioFeito.setText(((AvaliacaoMusicoEntity) lista.get(position)).getTxtComentario());

        }

        return view;
    }
}
