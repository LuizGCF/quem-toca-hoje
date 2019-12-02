package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.tv.TvView;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.DTO.AvaliacaoDTO;
import com.example.quemtocahoje.DTO.EventoDTO;
import com.example.quemtocahoje.DTO.ItensListaBuscaDTO;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoArquivo;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Model.FirebaseStorageRegistro;
import com.example.quemtocahoje.POJO.Banda;
import com.example.quemtocahoje.POJO.Estabelecimento;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoEstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.AvaliacaoMusicoEntity;
import com.example.quemtocahoje.Persistencia.Entity.BandaEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.ConversaoArquivo;
import com.example.quemtocahoje.Utility.EncodeBase64;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TelaPesquisaMusico extends AppCompatActivity {

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    DatabaseReference reference;

    FirebaseStorageRegistro firebaseStorageRegistro;

    AutenticacaoDTO dtoAutenticacao;
    private CustomAdapter adapter;
    private List<ItensListaBuscaDTO> allItens;
    //TODO Carregar a imagem de cada um dos tipos de usuario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisa_musico);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        firebaseStorageRegistro = new FirebaseStorageRegistro(FirebaseStorage.getInstance());

        dtoAutenticacao =  (AutenticacaoDTO) getIntent().getSerializableExtra("dtoAutenticacao");
        RecyclerView lstResultadoPesquisaMusico = findViewById(R.id.lstResultadoPesquisaMusico);
        lstResultadoPesquisaMusico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        carregarDados();

    }
    private void carregarDados(){
        allItens = new ArrayList<>();
        //listarMusicos();
        if(dtoAutenticacao.getTipoUsuario().equals(TipoUsuario.ESTABELECIMENTO.name()) || dtoAutenticacao.getTipoUsuario().equals(TipoUsuario.ESPECTADOR.name()))
            listarBandas();
        if(dtoAutenticacao.getTipoUsuario().equals(TipoUsuario.BANDA.name()) || dtoAutenticacao.getTipoUsuario().equals(TipoUsuario.MUSICO.name())|| dtoAutenticacao.getTipoUsuario().equals(TipoUsuario.ESPECTADOR.name()))
            listarEstabelecimentos();
    }
//Cadastrar um estabelecimento pra testar depois

    private void listarEstabelecimentos() {
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.ESTABELECIMENTO.name());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    EstabelecimentoEntity e = snapshot.getValue(EstabelecimentoEntity.class);
                    try{
                        StorageReference ref = FirebaseStorage.getInstance().getReference().child(e.getAutenticacao_id()+"/imagemfoto.png");

                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {//retornando corretamente a imagem na uri, converter para bitmap agora
                                allItens.add(new ItensListaBuscaDTO(uri.toString(),e.getAutenticacao_id(),e.getNomeFantasia(),e.getDescricao(),TipoUsuario.ESTABELECIMENTO.name()));
                                carregarRecyclerView();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception ex) {
                                allItens.add(new ItensListaBuscaDTO(null,e.getAutenticacao_id(),e.getNomeFantasia(),e.getDescricao(),TipoUsuario.ESTABELECIMENTO.name()));
                                carregarRecyclerView();
                            }
                        });
                        carregarRecyclerView();
                    }
                    catch (Exception ex)
                    {

                    }
                }
                listarBandas();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void listarBandas(){
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Banda.name());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    BandaEntity b = snapshot.getValue(BandaEntity.class);
                    if(b.isBandaAtiva().equals("SIM"))
                    {
                        //allItens.add(new ItensListaBuscaDTO(null,b.getGeneros(),b.getNome()));//b.getGeneros().stream().collect(Collectors.joining(","))));
                        try{
                            StorageReference ref = FirebaseStorage.getInstance().getReference().child(b.getIdCriador()+"/imagemfoto.png");

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    allItens.add(new ItensListaBuscaDTO(uri.toString(),b.getBanda_id(), b.getNome(), b.getGeneros(),TipoUsuario.BANDA.name()));
                                    carregarRecyclerView();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    allItens.add(new ItensListaBuscaDTO(null,b.getBanda_id(), b.getNome(),b.getGeneros(),TipoUsuario.BANDA.name()));
                                    carregarRecyclerView();
                                }
                            });
                            carregarRecyclerView();
                        }
                        catch (Exception ex)
                        {

                        }
                    }
                }
                carregarRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void listarMusicos() {

        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.MUSICO.name());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    MusicoEntity m = snapshot.getValue(MusicoEntity.class);
                    try {
                        //String idusuario = EncodeBase64.toBase64(m.getAutenticacao_id());

                        StorageReference ref = FirebaseStorage.getInstance().getReference().child(m.getAutenticacao_id()+"/imagemfoto.png");

                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {//retornando corretamente a imagem na uri, converter para bitmap agora
                                allItens.add(new ItensListaBuscaDTO(uri.toString(),m.getAutenticacao_id(), m.getNomeArtistico(),m.getDescricao(),TipoUsuario.MUSICO.name()));
                                carregarRecyclerView();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                allItens.add(new ItensListaBuscaDTO(null,m.getAutenticacao_id(), m.getNomeArtistico(),m.getDescricao(),TipoUsuario.MUSICO.name()));
                                carregarRecyclerView();
                            }
                        });
                        carregarRecyclerView();
                    }
                    catch (Exception e){

                    }
                }
                listarEstabelecimentos();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);


            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void carregarRecyclerView() {
        RecyclerView lstResultadoPesquisaMusico = lstResultadoPesquisaMusico = findViewById(R.id.lstResultadoPesquisaMusico);
        lstResultadoPesquisaMusico.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapter(allItens);

        lstResultadoPesquisaMusico.setLayoutManager(layoutManager);
        lstResultadoPesquisaMusico.setAdapter(adapter);
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

/*class CustomAdapter extends BaseAdapter{

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
            txtDescricaoBandaListItem.setText(musicos.get(position).getNomeArtistico());//Seria o nome da banda no caso
            txtDescricaoGeneroBandaListItem.setText(musicos.get(position).getNomeUsuario());//Seria o nome do musico(ou esse mesmo?)

            return view;
        }
    }*/

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ExampleViewHolder> implements Filterable
    {
        private List<ItensListaBuscaDTO> exampleList;
        private List<ItensListaBuscaDTO> exampleListFull;

        CustomAdapter(List<ItensListaBuscaDTO> exampleList) {
            this.exampleList = exampleList;
            exampleListFull = new ArrayList<>(exampleList);
        }
        class ExampleViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
            ImageView imagem;
            TextView nome;
            TextView descricao;

            ExampleViewHolder(View itemView) {
                super(itemView);
                imagem = itemView.findViewById(R.id.imgListItem);
                nome = itemView.findViewById(R.id.txtDescricaoGeneroBandaListItem);
                descricao = itemView.findViewById(R.id.txtDescricaoBandaListItem);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                //pegar os parametros e ir para a tela de resultado
                ItensListaBuscaDTO ilb = exampleList.get(getAdapterPosition());

                //Idusuario para pegar autenticacao na proxima tela
                AvaliacaoDTO a = new AvaliacaoDTO(ilb.getImagem(),ilb.getId(),ilb.getNome(),ilb.getId(),ilb.getDescricao(),ilb.getTipoUsuario(),null);
                carregarAvaliacoes(a, v.getContext());
                //colocar dentro do carregarAvaliacoes
                //Intent i = new Intent(v.getContext(),TelaPerfilUsuario.class);
                //i.putExtra("usuario", a);
                //v.getContext().startActivity(i);


                //AlertDialog.Builder adb = new AlertDialog.Builder(v.getContext());
                //adb.setTitle("Funciona");
                //adb.setMessage("Confima?");
                //adb.setPositiveButton("Aceitar", new AlertDialog.OnClickListener() {
                //    @Override
                //    public void onClick(DialogInterface dialog, int which) {
                //        exampleList.remove(getAdapterPosition());
                //        //notifyDataSetChanged();
                //        CustomAdapter.this.notifyDataSetChanged();
                //    }
                //});
                //adb.show();
            }
        }
        @NonNull
        @Override
        public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customlistitemlayoutbanda,
                    viewGroup, false);
            return new ExampleViewHolder(v);
        }


        @Override
        public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
            ItensListaBuscaDTO currentItem = exampleList.get(i);

            //exampleViewHolder.imagem.setImageBitmap(currentItem.getImagem());
            if(currentItem.getImagem() != "" && currentItem.getImagem() != null)
                Picasso.get().load(Uri.parse(currentItem.getImagem())).into(exampleViewHolder.imagem);
            exampleViewHolder.descricao.setText(currentItem.getNome());
            exampleViewHolder.nome.setText(currentItem.getDescricao());
        }

        @Override
        public int getItemCount() {
            return exampleList.size();
        }

        @Override
        public Filter getFilter() {
            return exampleFilter;
        }

        private Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ItensListaBuscaDTO> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(exampleListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (ItensListaBuscaDTO item : exampleListFull) {
                        if (item.getNome().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                exampleList.clear();
                exampleList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
        //Continuar depois
        private void carregarAvaliacoes(AvaliacaoDTO avaliacao, Context context){//String usuario) {
            List avaliacoes = new ArrayList<>();
            if(avaliacao.getTipoUsuario().equals(TipoUsuario.ESTABELECIMENTO.name()))
                avaliacoes = new ArrayList<AvaliacaoEstabelecimentoEntity>();
            else if(avaliacao.getTipoUsuario().equals(TipoUsuario.BANDA.name()) || avaliacao.getTipoUsuario().equals(TipoUsuario.MUSICO.name()))
                avaliacoes = new ArrayList<AvaliacaoMusicoEntity>();
            ArrayList<EventoDTO> dtoFinal = new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Autenticacao.name())
                    .child(avaliacao.getIdUsuario());
            List finalAvaliacoes = avaliacoes;
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    AutenticacaoEntity a = dataSnapshot.getValue(AutenticacaoEntity.class);
                    if(a != null) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Avaliacao.name())
                                .child(a.getLogin());
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Iterator<DataSnapshot> data = dataSnapshot.getChildren().iterator();
                                while (data.hasNext()) {
                                    if (a.getTipoUsuario().equals(TipoUsuario.ESTABELECIMENTO.name())) {
                                        AvaliacaoEstabelecimentoEntity avEstab = data.next().getValue(AvaliacaoEstabelecimentoEntity.class);
                                        dtoFinal.add(new EventoDTO("", avEstab, null, null));
                                        finalAvaliacoes.add(avEstab);


                                    } else if (a.getTipoUsuario().equals(TipoUsuario.BANDA.name()) || a.getTipoUsuario().equals(TipoUsuario.MUSICO.name())) {
                                        AvaliacaoMusicoEntity avMusico = data.next().getValue(AvaliacaoMusicoEntity.class);
                                        dtoFinal.add(new EventoDTO("", null, avMusico, null));
                                        finalAvaliacoes.add(avMusico);
                                    }
                                }
                                if (finalAvaliacoes.size() > 0)
                                    avaliacao.setListaAvaliacoes(finalAvaliacoes);
                                Intent i = new Intent(context, TelaPerfilUsuario.class);
                                i.putExtra("usuario", avaliacao);
                                i.putExtra("dtoAutenticacao",dtoAutenticacao);
                                context.startActivity(i);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {//??
                                Intent i = new Intent(context, TelaPerfilUsuario.class);
                                i.putExtra("usuario", avaliacao);
                                i.putExtra("dtoAutenticacao",dtoAutenticacao);
                                context.startActivity(i);
                            }
                        });
                    }
                    else
                    {
                        Intent i = new Intent(context, TelaPerfilUsuario.class);
                        i.putExtra("usuario", avaliacao);
                        i.putExtra("dtoAutenticacao",dtoAutenticacao);
                        context.startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

}
