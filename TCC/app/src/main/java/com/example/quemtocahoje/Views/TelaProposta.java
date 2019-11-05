package com.example.quemtocahoje.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.quemtocahoje.Utility.Mensagem;
import com.example.tcc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public class TelaProposta extends Activity implements DatePickerDialog.OnDateSetListener {

    private ImageButton imgSelecionarDataProposta;
    private ImageButton imgSelecionarHorarioInicio;
    private ImageButton imgSelecionarHorarioFim;
    private EditText txtDataPropostaEscolhida;
    private EditText edtLocalProposta;
    private EditText edtCacheProposta;
    private EditText edtDescricaoProposta;
    private Button btnEnviarProposta;
    private Button btnVoltarProposta;
    private EditText edtHorarioProposta;
    private EditText edtHorarioFim;
    private TextView lblNomeDestinatario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_proposta_enviar);
        edtHorarioProposta = findViewById(R.id.edtHorarioProposta);
        imgSelecionarDataProposta = findViewById(R.id.imgSelecionarDataProposta);
        txtDataPropostaEscolhida = findViewById(R.id.txtDataPropostaEscolhida);
        edtLocalProposta = findViewById(R.id.edtLocalProposta);
        edtCacheProposta = findViewById(R.id.edtCacheProposta);
        edtDescricaoProposta = findViewById(R.id.edtDescricaoProposta);
        btnEnviarProposta = findViewById(R.id.btnEnviarProposta);
        btnVoltarProposta = findViewById(R.id.btnVoltarProposta);
        edtHorarioFim = findViewById(R.id.edtHorarioFim);
        imgSelecionarHorarioInicio = findViewById(R.id.imgSelecionarHorarioInicio);
        imgSelecionarHorarioFim = findViewById(R.id.imgSelecionarHorarioFim);
        lblNomeDestinatario = findViewById(R.id.lblNomeDestinatario);
        Calendar dataAtual = Calendar.getInstance();

        //lblNomeDestinatario.setText(getIntent().getStringExtra("labelDestinatario"));
        lblNomeDestinatario.setText("Angelo arrombado");
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                TelaProposta.this, TelaProposta.this, dataAtual.get(Calendar.YEAR), dataAtual.get(Calendar.MONTH), dataAtual.get(Calendar.DAY_OF_MONTH));


        imgSelecionarDataProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        btnEnviarProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validaCampos()){
                    if(!validaCache()){

                    }else{
                        Mensagem.notificar(TelaProposta.this,"Aviso", "Valor do cachê inválido");
                    }
                }else{
                    Mensagem.notificar(TelaProposta.this,"Aviso", "Preencher todos os campos");
                }
            }
        });

        //Time Picker que resgata o horário direto no EditText - Horário Inicio
        Calendar c = Calendar.getInstance();
        int hr = c.get(HOUR);
        int min = c.get(MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                edtHorarioProposta.setText(ajustarHoraMinuto(i,i1));
            }
        }, hr, min, true);


        imgSelecionarHorarioInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog.show();

            }

        });

        //Time Picker que resgata o horário direto no EditText - Horário Fim
        int hrF = c.get(HOUR);
        int minF = c.get(MINUTE);
        TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                edtHorarioFim.setText(ajustarHoraMinuto(i,i1));
            }
        }, hrF, minF, true);

        imgSelecionarHorarioFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                timePickerDialog2.show();

            }
        });


    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String dia = i2 < 10 ? "0" + i2 : "" + i2;
        i1 += 1;
        String mes = i1 < 10 ? "0" + i1 : "" + i1;

        txtDataPropostaEscolhida.setText(dia + "/" + mes + "/" + i);


    }

    private String ajustarHoraMinuto(int hora, int minuto){
        String h = hora < 10 ? "0" + hora : ""+ hora;
        String m = minuto < 10 ? "0" + minuto : "" + minuto;
        return h +":"+m;
    }

    private boolean validaCampos() {
        boolean res = false;
        String txtDPE = txtDataPropostaEscolhida.getText().toString();
        String edtLP = edtLocalProposta.getText().toString();
        String edtDP = edtDescricaoProposta.getText().toString();
        String edtCP = edtCacheProposta.getText().toString();
        String horarioInicio = edtHorarioProposta.getText().toString();
        String horarioFim = edtHorarioFim.getText().toString();

        if (res = isCampoVazio(txtDPE)) {
            txtDataPropostaEscolhida.requestFocus();
        } else if (res = isCampoVazio(edtLP)) {
            edtLocalProposta.requestFocus();
        } else if (res = isCampoVazio(edtDP)) {
            edtDescricaoProposta.requestFocus();
        } else if (res = isCampoVazio(edtCP)) {
            edtCacheProposta.requestFocus();
        }else if (res = isCampoVazio(horarioInicio)){
            imgSelecionarHorarioInicio.requestFocus();
        }else if(res = isCampoVazio(horarioFim)){
            imgSelecionarHorarioFim.requestFocus();
        }

        return res;


    }

    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }


    private boolean validaCache() {
        boolean res = false;
        String cache = edtCacheProposta.getText().toString().trim().replace(",", ".");
        Double vlrcache = Double.parseDouble(cache);

        if (vlrcache <= 0.0) {
            res = true;
            edtCacheProposta.requestFocus();
        } else res = false;

        return res;
    }


    private void escolheHora() {

        /*Calendar c = Calendar.getInstance();
        int hr = c.get(HOUR);
        int min = c.get(MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                edtHorarioProposta.setText(hr + ":" + min);
            }
        }, hr, min, true);
        timePickerDialog.show();
        */

    }
}
