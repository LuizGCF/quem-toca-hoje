package com.example.quemtocahoje.Utility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.tcc.R;

import static org.apache.harmony.awt.internal.nls.Messages.getString;

public class Notificacao {

    public void notificar(Context context, String titulo, String conteudo, String CHANNEL_ID){
        //TODO remover essa linha quando a criação estiver na inicialização do app
        createNotificationChannel(context);

        Intent intent = new Intent(context, Notificacao.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_menu_gallery)
                .setContentTitle(titulo)
                .setContentText(conteudo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(1, builder.build());
    }

    //TODO Mover para inicialização do aplicativo
    private void createNotificationChannel(Context context) {
        // Cria o NotificationChannel, mas apenas em API 26+ pois é uma classe recente
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString("canal");
            String description = getString("descricao");
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            // Registra o canal no sistema; não é possível mudar a importância ou outros comportamentos de notificação depois daqui
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
