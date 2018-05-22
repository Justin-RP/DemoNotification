package com.example.a16022916.demonotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int requestCode = 123;
    int notificationID = 888;
    Button btnNotify1, btnNotify2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify1 = findViewById(R.id.mainBtnNotify1);

        btnNotify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
//                    NotificationChannel channel = new NotificationChannel("default",
//                            "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

                    // Set Notification to High Priority (Additional A)
                    NotificationChannel channel = new NotificationChannel("default",
                            "Default Channel", NotificationManager.IMPORTANCE_HIGH);

                    channel.setDescription("This is for default notification");
                    notificationManager.createNotificationChannel(channel);
                }

                Context thisContext = MainActivity.this;
                Class toClass = MainActivity.class;
                Intent intent = new Intent(thisContext,toClass);
                PendingIntent pIntent = PendingIntent.getActivity(thisContext, requestCode,
                        intent,PendingIntent.FLAG_CANCEL_CURRENT);

                // Set Big Text!! (Additional B)
                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Big Text - Long Content");
                bigText.bigText("This is one big text" + " - A quick brown fox jumps over a lazy " +
                        "brown dog \nLorem ipsum dolor sit amet, sea eu quod des");
                bigText.setSummaryText("Reflection Journal?");

                // Build notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(thisContext,"default");
                builder.setContentTitle("Amazing Offer!");
                builder.setContentText("Subject");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setStyle(bigText);  // SetStyle to Big Text (Additional B)
                builder.setAutoCancel(true);

                // To Set High Priority (Additional A)
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(uri);
                builder.setPriority(Notification.PRIORITY_HIGH);

                Notification n = builder.build();

                // An integer good to have, for you to programmatically cancel
                notificationManager.notify(notificationID,n);
                finish();
            }
        });
    }
}
