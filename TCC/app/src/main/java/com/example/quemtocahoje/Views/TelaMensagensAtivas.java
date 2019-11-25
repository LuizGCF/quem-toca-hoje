package com.example.quemtocahoje.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quemtocahoje.Chat.Activitys.ConversaActivity;
import com.example.quemtocahoje.Chat.Adapter.ConversaAdapter;
import com.example.quemtocahoje.Chat.Models.Conversa;
import com.example.tcc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TelaMensagensAtivas extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Conversa> adapter;
    private ArrayList<Conversa> conversas;

    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerConversas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mensagens_ativas);

        conversas = new ArrayList<>();
        listView = findViewById(R.id.lv_conversas);
        adapter = new ConversaAdapter(this, conversas );
        listView.setAdapter( adapter );

        firebase = FirebaseDatabase.getInstance()
                .getReference()
                .child("conversas")
                .child("EkUhcjz5ZXZ2SSzmL6zTceBG5qT2");//FirebaseAuth.getInstance().getUid());//id do usuario logado?

        valueEventListenerConversas = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                conversas.clear();
                for ( DataSnapshot dados: dataSnapshot.getChildren() ){
                    Conversa conversa = dados.getValue( Conversa.class );
                    conversas.add(conversa);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Conversa conversa = conversas.get(position);
                Intent intent = new Intent(TelaMensagensAtivas.this, ConversaActivity.class );

                intent.putExtra("nome", conversa.getNome() );
                //String email = Base64Custom.decodificarBase64( conversa.getIdUsuario() );
                //intent.putExtra("email", email );

                startActivity(intent);

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerConversas);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerConversas);
    }
}
