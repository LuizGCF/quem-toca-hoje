package com.example.quemtocahoje.Model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.quemtocahoje.Adapter.AutenticacaoDTOAdapter;
import com.example.quemtocahoje.DTO.AutenticacaoDTO;
import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Views.TelaInicial;
import com.example.quemtocahoje.Views.TelaInicialEspectador;
import com.example.quemtocahoje.Views.TelaInicialEstabelecimento;
import com.example.quemtocahoje.Views.TelaInicialMusico;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AutenticacaoDAO {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private Long contador;

    public AutenticacaoDAO(FirebaseDatabase database, DatabaseReference reference, FirebaseUser firebaseUser) {
        this.database = database;
        this.reference = reference;
        this.firebaseUser = firebaseUser;
    }

    public AutenticacaoDAO(){}

    public void autenticar(final String login, final String senha, final FirebaseAuth auth, final Context ctx){
        //final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Autenticacao.name());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                setContador(0L);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){//para cada usuario no autenticacao, verificar login/senha para
                    final AutenticacaoEntity entidade = snapshot.getValue(AutenticacaoEntity.class);
                    setContador(getContador()+1);

                    if((entidade.getLogin().equals(login) || entidade.getEmail().equals(login))&& entidade.getSenha().equals(senha))
                    {
                        auth.signInWithEmailAndPassword(entidade.getEmail(),senha)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = auth.getCurrentUser();
                                            if (entidade.getTipoUsuario().equals(TipoUsuario.MUSICO.name())) {
                                                loginMusico(user.getUid(), ctx);
                                            } else if (entidade.getTipoUsuario().equals(TipoUsuario.ESTABELECIMENTO.name())) {
                                                loginEstabelecimento(user.getUid(), ctx);
                                            } else if (entidade.getTipoUsuario().equals(TipoUsuario.ESPECTADOR.name())) {
                                                loginEspectador(user.getUid(), ctx);
                                            }
                                        }
                                    }
                                });
                        break;
                    }else if(getContador() == dataSnapshot.getChildrenCount()){
                        Mensagem.notificar(ctx, "Usuário Inválido", "Login e/ou senha incorretos.");
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Mensagem.notificar(ctx, "Erro na aplicação", "Ocorreu um erro ao efetuar o login do usuário.");
                Log.d("ERRO FIREBASE", databaseError.getDetails());

            }
        });
    }

    public void loginEspectador(final String id, final Context ctx){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name());
        databaseReference = databaseReference.child(TipoUsuario.ESPECTADOR.name()).child(id);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null)
                {
                    EspectadorEntity entidade = dataSnapshot.getValue(EspectadorEntity.class);

                    AutenticacaoDTO dto = AutenticacaoDTOAdapter.espectadorToAutenticacaoDTO(entidade);
                    Intent telaInicialEspectador = new Intent(ctx, TelaInicialEspectador.class);
                    telaInicialEspectador.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    telaInicialEspectador.putExtra("dtoAutenticacao", dto);
                    ctx.startActivity(telaInicialEspectador );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loginMusico(final String id, final Context ctx){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.MUSICO.name()).child(id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!= null)
                {
                    MusicoEntity entidade = dataSnapshot.getValue(MusicoEntity.class);
                    if (entidade.getAutenticacao_id().equals(id))
                    {
                        Log.d("DENTROUSUARIO",entidade.getNome());
                        Log.d("DENTROUSUARIO",entidade.getNomeArtistico());

                        AutenticacaoDTO dto = AutenticacaoDTOAdapter.musicoToAutenticacaoDTO(entidade);
                        Intent telaInicialMusico = new Intent(ctx, TelaInicialMusico.class);
                        telaInicialMusico.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        telaInicialMusico.putExtra("dtoAutenticacao",dto);
                        ctx.startActivity(telaInicialMusico);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loginEstabelecimento(final String id, final Context ctx){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.ESTABELECIMENTO.name()).child(id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null)
                {
                    EstabelecimentoEntity entidade = dataSnapshot.getValue(EstabelecimentoEntity.class);
                    if (entidade.getAutenticacao_id().equals(id))
                    {
                        Log.d("DENTROUSUARIO",entidade.getNomeDono());
                        Log.d("DENTROUSUARIO",entidade.getNomeFantasia());

                        AutenticacaoDTO dto = AutenticacaoDTOAdapter.estabelecimentoToAutenticacaoDTO(entidade);
                        Intent telaInicialEstabelecimento = new Intent(ctx, TelaInicialEstabelecimento.class);
                        telaInicialEstabelecimento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        telaInicialEstabelecimento.putExtra("dtoAutenticacao",dto);
                        ctx.startActivity(telaInicialEstabelecimento);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

   /* public AutenticacaoDTO recuperarAutenticacao(String login, final String senha){
        final AutenticacaoDTO dto = new AutenticacaoDTO();
        final DatabaseReference autenticacaoRef = database.getReference("Autenticacao");
        //autenticacaoRef.orderByChild("id").equalTo(login).equalTo(senha).limitToFirst(1).addValueEventListener(new ValueEventListener() {
        String id = firebaseUser.getUid();
        autenticacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                Log.d("MOCK","" + dataSnapshot.getChildrenCount());
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    AutenticacaoEntity entidade = snapshot.getValue(AutenticacaoEntity.class);
                    Log.d("MOCK",entidade.getLogin());
                    Log.d("MOCK",entidade.getSenha());
                    Log.d("MOCK",entidade.getTipoUsuario());
                    if(entidade.getId().equals(firebaseUser.getUid()))
                    {

                        dto.setIdAutenticacao(entidade.getDataCriacao().toString());
                        dto.setNome(entidade.getLogin());
                        dto.setSenha(entidade.getSenha());
                        dto.setTipoUsuario(entidade.getTipoUsuario());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        return dto;
    }*/

    public Long getContador() {
        return contador;
    }

    public void setContador(Long contador) {
        this.contador = contador;
    }
}
