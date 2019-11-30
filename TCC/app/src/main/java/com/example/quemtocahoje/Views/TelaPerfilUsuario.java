package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.quemtocahoje.Chat.Activitys.ConversaActivity;
import com.example.quemtocahoje.DTO.AvaliacaoDTO;
import com.example.quemtocahoje.DTO.EventoDTO;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.POJO.Avaliacao;
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

        //Acho que esta certo, mas verificar porque nao recebe o valor certo da lista as vezes
        btnMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =  FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String idRemetente = EncodeBase64.toBase64(email);
                //String test = EncodeBase64.toBase64("d@gmail.com");
                Intent telaConversa = new Intent(TelaPerfilUsuario.this, ConversaActivity.class);

                telaConversa.putExtra("remetente", idRemetente);
                telaConversa.putExtra("destinatario",a.getIdUsuario());
                //put extra id usuarios
                startActivity(telaConversa);
            }
        });

        btnProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaProposta = new Intent(TelaPerfilUsuario.this, TelaProposta.class);

                startActivity(telaProposta);
            }
        });

        hashMap = new HashMap<>();

        a = (AvaliacaoDTO) getIntent().getSerializableExtra("usuario");

        if(a != null) {
            txtNomePerfil.setText("Olá " + a.getNomePerfil());
            txtDescricaoPerfil.setText(a.getDescricaoGenero());

        }

        carregarAvaliacoes("estab");

        carregarImagemPerfil();

        carregarImagensDemonstracao();

        //TODO Verificar que o usuario logado tbm aparece na lista de pesquisa, talvez carregar por perfil ja resolva isso
        //TODO Verificar perfil Banda qual id esta utilizando para a parte da imagem
        //TODO receber os dados da tela de pesquisa anterior para preencher os campos, Avaliacao depois que tiver o dto da avaliacao
        //Com os cados recebidos, iniciar a conversa
        //Criar uma tela com as conversas do perfil x
    }

    //Continuar depois
    private void carregarAvaliacoes(String usuario) {
        ArrayList<EventoDTO> dtoFinal = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                .child(usuario);//a.getIdUsuario());//mockado por enqnt
        ref.addValueEventListener(valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hashMap.put(ref, valueEventListener);
                Iterator<DataSnapshot> data = dataSnapshot.getChildren().iterator();
                while (data.hasNext()) {
                    if (a.getTipoUsuario().equals(TipoUsuario.ESTABELECIMENTO.name())) {
                        AvaliacaoEstabelecimentoEntity avEstab = data.next().getValue(AvaliacaoEstabelecimentoEntity.class);
                        dtoFinal.add(new EventoDTO("", avEstab, null, null));

                    } else if (a.getTipoUsuario().equals(TipoUsuario.BANDA.name()) || a.getTipoUsuario().equals(TipoUsuario.MUSICO.name())) {
                        AvaliacaoMusicoEntity avMusico = data.next().getValue(AvaliacaoMusicoEntity.class);
                        dtoFinal.add(new EventoDTO("", null, avMusico, null));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
