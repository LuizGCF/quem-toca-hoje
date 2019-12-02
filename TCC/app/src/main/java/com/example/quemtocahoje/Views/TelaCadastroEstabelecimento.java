package com.example.quemtocahoje.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quemtocahoje.Enum.TabelasFirebase;
import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.AutenticacaoEntity;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Utility.MaskEditUtil;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Utility.VerificadorCpfCnpj;
import com.example.tcc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class TelaCadastroEstabelecimento extends AppCompatActivity {

    private EditText edtRazaoSocial;
    public EditText edtCNPJ;
    private EditText edtNomeFantasia;
    private EditText edtTelefone;
    private EditText edtInicioExpediente;
    private EditText edtFimExpediente;
    private EditText edtDescricao;
    private Button btnCadastrarEstabelecimento;
    private Button btnCancelarEstabelecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_estabelecimento);
        getSupportActionBar().hide();

        edtRazaoSocial = findViewById(R.id.edtRazaoSocial);
        edtCNPJ = findViewById(R.id.edtCNPJ);
        edtNomeFantasia = findViewById(R.id.edtNomeFantasia);
        edtTelefone = findViewById(R.id.edtTelefoneEstabelecimento);
        edtInicioExpediente = findViewById(R.id.edtInicioExpediente);
        edtFimExpediente = findViewById(R.id.edtFimExpediente);
        edtDescricao = findViewById(R.id.edtDescricaoEstabelecimento);
        btnCadastrarEstabelecimento = findViewById(R.id.btnCadastrarEstabelecimento);
        btnCancelarEstabelecimento = findViewById(R.id.btnCancelarEstabelecimento);

        // Aplicação das mascaras
        edtCNPJ.addTextChangedListener(MaskEditUtil.mask(edtCNPJ,MaskEditUtil.FORMAT_CNPJ));
        edtTelefone.addTextChangedListener(MaskEditUtil.mask(edtTelefone,MaskEditUtil.FORMAT_FONE));
        edtInicioExpediente.addTextChangedListener(MaskEditUtil.mask(edtInicioExpediente,MaskEditUtil.FORMAT_HORA));
        edtFimExpediente.addTextChangedListener(MaskEditUtil.mask(edtFimExpediente,MaskEditUtil.FORMAT_HORA));




        btnCadastrarEstabelecimento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String s = edtCNPJ.getText().toString();
                s = s.replace("-","").replace("/","").replace(".","");

                if(isCamposValidos(edtRazaoSocial,edtCNPJ,edtNomeFantasia,edtTelefone,edtInicioExpediente,edtFimExpediente,edtDescricao))
                {

                    if(VerificadorCpfCnpj.isCNPJValido(s))
                    {
                      verificarCnpjUnico(TelaCadastroEstabelecimento.this);
                    }
                    else
                        Mensagem.notificar(TelaCadastroEstabelecimento.this,"CNPJ Inválido","Formato de CNPJ inválido.");
                }
                else
                    Mensagem.notificar(TelaCadastroEstabelecimento.this,"Campos inválidos","Um ou mais campos não foram preenchidos corretamente.");

            }
        });

        btnCancelarEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isCamposValidos(EditText edtRazaoSocial, EditText edtCNPJ, EditText edtNomeFantasia, EditText edtTelefone, EditText edtInicioExpediente, EditText edtFimExpediente,EditText edtDescricao)
    {
        if(edtRazaoSocial ==null || edtRazaoSocial.getText().toString().trim().equals("")
        || edtCNPJ ==null || edtCNPJ.getText().toString().trim().equals("")
        || edtNomeFantasia ==null || edtNomeFantasia.getText().toString().trim().equals("")
        || edtTelefone ==null || edtTelefone.getText().toString().trim().equals("")
        || edtInicioExpediente ==null || edtInicioExpediente.getText().toString().trim().equals("")
        || edtFimExpediente ==null || edtFimExpediente.getText().toString().trim().equals("")
        || edtDescricao ==null || edtDescricao.getText().toString().trim().equals(""))
            return false;
        return true;
    }

    private EstabelecimentoEntity prepararObjetoEstabelecimento(){
        EspectadorEntity es = (EspectadorEntity) getIntent().getSerializableExtra("objetoEspectador");
        EstabelecimentoEntity e = new EstabelecimentoEntity(null
                , null
                ,es.getNomeEspectador()
                ,edtRazaoSocial.getText().toString().trim()
                ,edtCNPJ.getText().toString().trim()
                ,edtNomeFantasia.getText().toString().trim()
                ,edtInicioExpediente.getText().toString().trim()
                ,edtFimExpediente.getText().toString().trim()
                ,edtTelefone.getText().toString().trim()
                ,edtDescricao.getText().toString().trim()
                ,es.getDataCriacao());
        return e;
    }

    private void verificarCnpjUnico(Context ctx){
        final Intent telaEndereco = new Intent(ctx, TelaEndereco.class);

        ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde enquanto validamos os dados");
        progressDialog.setTitle("Validando");
        progressDialog.show();

        final DatabaseReference   databaseReference = FirebaseDatabase.getInstance().getReference(TabelasFirebase.Usuarios.name()).child(TipoUsuario.ESTABELECIMENTO.name());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0L) {
                    EstabelecimentoEntity e = prepararObjetoEstabelecimento();
                    telaEndereco.putExtra("tipoUsuario", TipoUsuario.ESTABELECIMENTO.name());
                    telaEndereco.putExtra("objetoAutenticacao", getIntent().getSerializableExtra("objetoAutenticacao"));
                    telaEndereco.putExtra("objetoEstabelecimento", e);
                    progressDialog.dismiss();
                    startActivity(telaEndereco);
                }else{
                    Iterator<DataSnapshot> snapshot = dataSnapshot.getChildren().iterator();
                    while (snapshot.hasNext()) {
                        EstabelecimentoEntity est = snapshot.next().getValue(EstabelecimentoEntity.class);
                        if (est.getCnpj().equals(edtCNPJ.getText().toString().trim())) {
                            Mensagem.notificar(ctx, "CNPJ Existente", "Este CNPJ já consta em nosso sistema");
                            progressDialog.dismiss();
                            break;
                        } else if (!snapshot.hasNext()) {
                            EstabelecimentoEntity e = prepararObjetoEstabelecimento();
                            telaEndereco.putExtra("tipoUsuario", TipoUsuario.ESTABELECIMENTO.name());
                            telaEndereco.putExtra("objetoAutenticacao", getIntent().getSerializableExtra("objetoAutenticacao"));
                            telaEndereco.putExtra("objetoEstabelecimento", e);
                            progressDialog.dismiss();
                            startActivity(telaEndereco);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

}
