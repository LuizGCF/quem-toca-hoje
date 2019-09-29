package com.example.quemtocahoje.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.EnderecoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Persistencia.Entity.MusicoEntity;
import com.example.quemtocahoje.Utility.DefinirDatas;
import com.example.quemtocahoje.Utility.EncodeBase64;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
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

    public FirebaseRegistro(){}

    public void registro(final String login, final String tipoUsuario, final String email, final String senha, final EspectadorEntity e, final MusicoEntity m, final EstabelecimentoEntity estab, final EnderecoEntity endereco){
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            final FirebaseUser usuariofirebase = auth.getCurrentUser();
                            String idusuario = EncodeBase64.toBase64(email);//getIdToken(false).toString();

                            reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Autenticacao.name()).child(idusuario);

                            HashMap<String,String> hashMap = new HashMap<>();

                            hashMap.put("id", idusuario);
                            hashMap.put("login",login);
                            hashMap.put("email", email);
                            hashMap.put("tipoUsuario",tipoUsuario);
                            hashMap.put("senha", senha);
                            Log.d("FIM HASH", "FIM HASH");

                            reference.setValue(hashMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                            Log.d("ON COMPLETE", "aaa");
                                            if(tipoUsuario.equals(TipoUsuario.ESPECTADOR.name()))
                                               registroEspectador(e, idusuario);
                                            else if(tipoUsuario.equals(TipoUsuario.MUSICO.name()))
                                                registroMusico(m, idusuario);
                                            else if(tipoUsuario.equals(TipoUsuario.ESTABELECIMENTO.name()))
                                                registrarEstabelecimento(estab,endereco, idusuario);
                                            }
                                        }
                                    });
                        }
                    }
                }
                );

    }

    public void registroEspectador(final EspectadorEntity espectador, String idespectador){
        Log.d("REGISTRO ESPEC", "ESPEC");
        espectador.setAutenticacao_id(idespectador);

        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.ESPECTADOR.name()).child(idespectador);
        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("idEspectador", idespectador);
        hashMap.put("autenticacao_id",espectador.getAutenticacao_id());
        hashMap.put("nomeEspectador",espectador.getNomeEspectador());
        hashMap.put("dataCriacao", DefinirDatas.dataAtual());
        reference.setValue(hashMap);
    }

    public void registroMusico(final MusicoEntity musico, final String idmusico) {
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.MUSICO.name()).child(idmusico);

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("idMusico", idmusico);
        hashMap.put("autenticacao_id",idmusico);
        hashMap.put("nome",musico.getNome());
        hashMap.put("nomeArtistico", musico.getNomeArtistico());
        hashMap.put("telefone", musico.getTelefone());
        hashMap.put("cidade", musico.getCidade());
        hashMap.put("descricao", musico.getDescricao());
        hashMap.put("carreiraSoloAtiva", musico.isCarreiraSoloAtiva());
        hashMap.put("dataCriacao", DefinirDatas.dataAtual());
        reference.setValue(hashMap);
    }

    public void registrarEstabelecimento(final EstabelecimentoEntity estab, final EnderecoEntity endereco, final String idEstab){
        estab.setAutenticacao_id(idEstab);
        estab.setEndereco_id(idEstab);

        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.ESTABELECIMENTO.name()).child(idEstab);

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("idEstabelecimento", idEstab);
        hashMap.put("autenticacao_id",idEstab);
        hashMap.put("enderecoId",estab.getEndereco_id());
        hashMap.put("nomeDono",estab.getNomeDono());
        hashMap.put("razaoSocial", estab.getRazaoSocial());
        hashMap.put("cnpj", estab.getCnpj());
        hashMap.put("nomeFantasia", estab.getNomeFantasia());
        hashMap.put("horaInicio", estab.getHoraInicio());
        hashMap.put("horaTermino", estab.getHoraTermino());
        hashMap.put("telefone", estab.getTelefone());
        hashMap.put("descricao",estab.getDescricao());
        hashMap.put("dataCriacao", DefinirDatas.dataAtual());
        reference.setValue(hashMap)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                        registrarEndereco(endereco, idEstab);
                }
            });

    }

    private void registrarEndereco(final EnderecoEntity endereco, String idendereco){
        reference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Endereco.name()).child(idendereco);

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("idEndereco", idendereco);
        hashMap.put("logradouro", endereco.getLogradouro());
        hashMap.put("bairro",endereco.getBairro());
        hashMap.put("cidade",endereco.getCidade());
        hashMap.put("cep", endereco.getCep());
        hashMap.put("uf", endereco.getUf());
        hashMap.put("complemento", endereco.getComplemento());
        hashMap.put("dataCriacao", DefinirDatas.dataAtual());
        reference.setValue(hashMap);
    }

}
