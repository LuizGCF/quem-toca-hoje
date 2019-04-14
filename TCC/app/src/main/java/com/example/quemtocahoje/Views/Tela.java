package com.example.quemtocahoje.Views;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quemtocahoje.Banco.Banco;
import com.example.quemtocahoje.Banco.BancoFail;
import com.example.quemtocahoje.POJO.Espectador;
import com.example.tcc.R;

public class Tela extends AppCompatActivity {

    private Button btnTeste;
    private TextView txtNome;
    private TextView txtTipo;
    private Banco banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela);
        banco = new Banco(getApplicationContext());//??
        btnTeste = findViewById(R.id.btnTeste);
        txtNome = findViewById(R.id.txtNome);
        txtTipo = findViewById(R.id.txtTipo);
        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InserirDados();
                Espectador spec = (Espectador)Selecionar(1);

                txtNome.setText(spec.getNomeEspectador());
                txtTipo.setText(spec.getTipoUsuario());
            }
        });
    }
    public void InserirDados()
        {
            try
            {
                // Gets the data repository in write mode
                SQLiteDatabase db = banco.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put("NomeEspectador", "Gabriel");
                values.put("TipoUsuario", "Espectador");

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert("Espectador", null, values);

                values = new ContentValues();
                values.put("NomeEspectador", "Matheus");
                values.put("TipoUsuario", "Espectador");
                newRowId = db.insert("Espectador", null, values);
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }
        public Object Selecionar(int id)
        {
            SQLiteDatabase db = banco.getReadableDatabase();
            //Cursor cursor = db.rawQuery("SELECT * FROM Espectador where idEspectador = " + id,null);

            Espectador espectador = new Espectador();
            String[] colunas = {"idEspectador","NomeEspectador","TipoUsuario"};
            //String[] argumentos = { ""+id };
            Cursor cursor = db.query("Espectador",colunas,"idEspectador = "+id,null,null,null,"idEspectador ASC");
            cursor.moveToFirst();
            //while(cursor != null)
            //{
                espectador.setIdEspectador(Integer.parseInt(cursor.getString(cursor.getColumnIndex("idEspectador"))));
                espectador.setNomeEspectador(cursor.getString(cursor.getColumnIndex("NomeEspectador")));
                //espectador.setDataCriacao(cursor.getDate);//nao sei, passei reto
                espectador.setTipoUsuario(cursor.getString((cursor.getColumnIndex("TipoUsuario"))));
            //}

            return espectador;
        }
}
