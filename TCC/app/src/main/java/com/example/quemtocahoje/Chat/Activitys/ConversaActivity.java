package com.example.quemtocahoje.Chat.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quemtocahoje.Chat.Adapter.MensagemAdapter;
import com.example.quemtocahoje.Chat.Models.Conversa;
import com.example.quemtocahoje.Chat.Models.Mensagem;
import com.example.tcc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConversaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btMensagem;
    private DatabaseReference firebase;
    private ListView listView;
    private ArrayList<Mensagem> mensagens;
    private ArrayAdapter<Mensagem> adapter;
    private ValueEventListener valueEventListenerMensagem;

    // dados do destinatário
    private String nomeUsuarioDestinatario;
    private String idUsuarioDestinatario;

    // dados do rementente
    public static String idUsuarioRemetente;
    private String nomeUsuarioRemetente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);



        toolbar = (Toolbar) findViewById(R.id.tb_conversa);
        editMensagem = (EditText) findViewById(R.id.edit_mensagem);
        btMensagem = (ImageButton) findViewById(R.id.bt_enviar);
        listView = (ListView) findViewById(R.id.lv_conversas);

        // dados do usuário logado
        //Preferencias preferencias = new Preferencias(ConversaActivity.this);
        //idUsuarioRemetente = preferencias.getIdentificador();
        //nomeUsuarioRemetente = preferencias.getNome();

        Bundle extra = getIntent().getExtras();

        if( extra != null ){
            nomeUsuarioDestinatario = extra.getString("nome");
            String emailDestinatario = extra.getString("email");
            //idUsuarioDestinatario = //Base64Custom.codificarBase64( emailDestinatario );
        }
        idUsuarioRemetente = "1V0UQy6KBHeoN9oiOH97YRgpTsI2";//Virá no extra tbm//EkUhcjz5ZXZ2SSzmL6zTceBG5qT2
        idUsuarioDestinatario = "EkUhcjz5ZXZ2SSzmL6zTceBG5qT2";//virá no extra do destinatario, por enquanto isso só para teste//1V0UQy6KBHeoN9oiOH97YRgpTsI2
        // Configura toolbar
        //toolbar.setTitle( nomeUsuarioDestinatario );
        //toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        //setSupportActionBar(toolbar);

        // Monta listview e adapter
        mensagens = new ArrayList<>();
        adapter = new MensagemAdapter(ConversaActivity.this, mensagens);

        listView.setDivider(null);
        listView.setAdapter( adapter );

        // Recuperar mensagens do Firebase
        firebase = FirebaseDatabase.getInstance()
                    .getReference()
                    .child("mensagens")
                    .child( idUsuarioRemetente )//mudar para idremetente e iddesinatario?
                    .child( idUsuarioDestinatario );

        // Cria listener para mensagens
        valueEventListenerMensagem = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Limpar mensagens
                mensagens.clear();

                // Recupera mensagens
                for ( DataSnapshot dados: dataSnapshot.getChildren() ){
                    Mensagem mensagem = dados.getValue( Mensagem.class );
                    mensagens.add( mensagem );
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener( valueEventListenerMensagem );


        // Enviar mensagem
        btMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoMensagem = editMensagem.getText().toString();

                if( textoMensagem.isEmpty() ){
                    Toast.makeText(ConversaActivity.this, "Digite uma mensagem para enviar!", Toast.LENGTH_LONG).show();
                }else{

                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario( idUsuarioRemetente );
                    mensagem.setMensagem( textoMensagem );

                    // salvamos mensagem para o remetente
                    Boolean retornoMensagemRemetente = salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario , mensagem );
                    if( !retornoMensagemRemetente ){
                        Toast.makeText(
                                ConversaActivity.this,
                                "Problema ao salvar mensagem, tente novamente!",
                                Toast.LENGTH_LONG
                        ).show();
                    }else {

                        // salvamos mensagem para o destinatario
                        Boolean retornoMensagemDestinatario = salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente , mensagem );
                        if( !retornoMensagemDestinatario ){
                            Toast.makeText(
                                    ConversaActivity.this,
                                    "Problema ao enviar mensagem para o destinatário, tente novamente!",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                    }

                    // salvamos Conversa para o remetente
                    Conversa conversa = new Conversa();
                    conversa.setIdUsuario( idUsuarioDestinatario );
                    conversa.setNome( nomeUsuarioDestinatario );
                    conversa.setMensagem( textoMensagem );
                    Boolean retornoConversaRemetente = salvarConversa(idUsuarioRemetente, idUsuarioDestinatario, conversa);
                    if( !retornoConversaRemetente ){
                        Toast.makeText(
                                ConversaActivity.this,
                                "Problema ao salvar conversa, tente novamente!",
                                Toast.LENGTH_LONG
                        ).show();
                    }else {
                        // salvamos Conversa para o Destinatario

                        conversa = new Conversa();
                        conversa.setIdUsuario( idUsuarioRemetente );
                        conversa.setNome( nomeUsuarioRemetente );
                        conversa.setMensagem(textoMensagem);

                        Boolean retornoConversaDestinatario = salvarConversa(idUsuarioDestinatario, idUsuarioRemetente, conversa );
                        if( !retornoConversaDestinatario ){
                            Toast.makeText(
                                    ConversaActivity.this,
                                    "Problema ao salvar conversa para o destinatário, tente novamente!",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                    }




                    editMensagem.setText("");


                }

            }
        });



    }

    private boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem mensagem){
        try {

            firebase = FirebaseDatabase.getInstance().getReference().child("mensagens");

            firebase.child( idRemetente )
                    .child( idDestinatario )
                    .push()
                    .setValue( mensagem );

            return true;

        }catch ( Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean salvarConversa(String idRemetente, String idDestinatario, Conversa conversa){
        try {
            firebase = FirebaseDatabase.getInstance().getReference().child("conversas");
            firebase.child( idRemetente )
                    .child( idDestinatario )
                    .setValue( conversa );

            return true;

        }catch ( Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMensagem);
    }
}
