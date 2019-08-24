package com.example.quemtocahoje.Utility;

import android.support.annotation.NonNull;

import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.POJO.Musico;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;

public class FirebaseRegistro implements Serializable {

    private FirebaseAuth auth;
    private DatabaseReference reference;

    public FirebaseRegistro(FirebaseAuth auth, DatabaseReference reference){

        this.auth = auth;
        this.reference = reference;
    }

    public String registro(final String usuario, final String tipoUsuario,String email, String senha){
        final String[] idusuario = new String[1];
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser usuariofirebase = auth.getCurrentUser();
                        idusuario[0] = usuariofirebase.getUid();

                        reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(idusuario[0]);

                        HashMap<String,String> hashMap = new HashMap<>();

                        hashMap.put("id", idusuario[0]);
                        hashMap.put("usuario",usuario);
                        hashMap.put("tipoUsuario",tipoUsuario);
                        //hashMap.put("id",idusuario);//colocaria as outras informaçoes abaixo atraves desse hash para cadastrar no firebase?
                        reference.setValue(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                               /* .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {

                                }
                            }
                        })*/

                    }
                }
                );

        return idusuario[0];

    }

    public void registroMusico(final MusicoEntity musico, String usuario, String email, String senha) {
       String idAut = registro(usuario, TipoUsuario.MUSICO.name(), email, senha);
       musico.setAutenticacao_id(idAut);

        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser usuariofirebase = auth.getCurrentUser();
                    String idmusico = usuariofirebase.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(TipoUsuario.MUSICO.name()).child(idmusico);

                    HashMap<String,String> hashMap = new HashMap<>();

                    hashMap.put("idMusico", idmusico);
                    hashMap.put("autenticacao_id",musico.getAutenticacao_id());
                    hashMap.put("nome",musico.getNome());
                    hashMap.put("nomeArtistico", musico.getNomeArtistico());
                    hashMap.put("telefone", musico.getTelefone());
                    hashMap.put("dataCriacao", DefinirDatas.dataAtual());
                    hashMap.put("descricao", musico.getDescricao());
                    hashMap.put("carreiraSoloAtiva", musico.isCarreiraSoloAtiva()?"SIM":"NÃO");
                    //hashMap.put("id",idusuario);//colocaria as outras informaçoes abaixo atraves desse hash para cadastrar no firebase?
                    reference.setValue(hashMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
                               /* .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {

                                }
                            }
                        })*/

                                           }
                                       }
                );

    }

}
