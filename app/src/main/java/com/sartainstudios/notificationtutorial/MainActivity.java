package com.sartainstudios.notificationtutorial;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;

        // Procura seu botão no layout XML
        Button createNotificationButton = findViewById(R.id.button_create_notification);

        Button setAlarm = findViewById(R.id.btnSetAlarm);

        // Aguarda você clicar no botão
        createNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts the function below
                addNotification();
            }
        });


        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlarm();
            }
        });
    }

    // Cria e mostra a notificação
    private void addNotification() {
        // Constroi a notificacao
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("PD26S - Programacao MOBILE")
                .setContentText("Ola professor, tudo bem? Pensa com carinho na nossa nota hehe");

        // Cria a estrutura necessaria para a notificacao
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Adiciona como notificacao
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void addAlarm() {
        EditText tempo = findViewById(R.id.etVal);
        int time = Integer.parseInt(tempo.getText().toString());
        Intent intent = new Intent(this.context, AlarmReceiver.class);
        intent.putExtra("extra", "iniciar");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time * 1000, pendingIntent);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this.context, AlarmReceiver.class);
        intent.putExtra("extra", "desligar");
        sendBroadcast(intent);

    }
}