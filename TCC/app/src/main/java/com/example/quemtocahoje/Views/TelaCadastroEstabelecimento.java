package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quemtocahoje.Enum.TipoUsuario;
import com.example.quemtocahoje.Persistencia.Entity.EspectadorEntity;
import com.example.quemtocahoje.Persistencia.Entity.EstabelecimentoEntity;
import com.example.quemtocahoje.Utility.MaskEditUtil;
import com.example.quemtocahoje.Utility.Mensagem;
import com.example.quemtocahoje.Utility.VerificadorCpfCnpj;
import com.example.tcc.R;

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

        final Intent telaEndereco = new Intent(this, TelaEndereco.class);

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
                        //if(!isCnpjCadastrado()) {
                            EstabelecimentoEntity e = prepararObjetoEstabelecimento();
                            telaEndereco.putExtra("tipoUsuario", TipoUsuario.ESTABELECIMENTO.name());
                            telaEndereco.putExtra("objetoAutenticacao", getIntent().getSerializableExtra("objetoAutenticacao"));
                            telaEndereco.putExtra("objetoEstabelecimento", e);

                            startActivity(telaEndereco);
                       /* }else{
                            Mensagem.notificar(TelaCadastroEstabelecimento.this, "CNPJ Cadastrado", "Esse CNPJ já foi cadastrado");
                        }*/
                    }
                    else
                        Mensagem.notificar(TelaCadastroEstabelecimento.this,"CNPJ Inválido","CNPJ não existente.");
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

    //TODO reimplementar com dados do Firebase
    private boolean isCnpjCadastrado(){
        if(true)
            return true;

        return false;
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

}
