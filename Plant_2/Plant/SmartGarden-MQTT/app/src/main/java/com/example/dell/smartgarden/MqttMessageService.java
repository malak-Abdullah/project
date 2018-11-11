package com.example.dell.smartgarden;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_HUMIDITY;
import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_SOILMOISTURE;
import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_TEMPERTURE;
import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_WATER_LEVEL;

public class MqttMessageService extends Service {

    private static final String TAG = "MqttMessageService";
    private PahoMqttClient pahoMqttClient;
    public MqttAndroidClient mqttAndroidClient;
    final static String MY_ACTION = "MY_ACTION";


    private FirebaseUser user;
    private String uid;

    SharedPreferences.Editor editor;

    SharedPreferences test_name;
    //private final Handler handler = new Handler();
    private Intent intent = null;
    int messageInt;
String message;

    public MqttMessageService() {
    }



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");


        //intent = new Intent( MY_ACTION );



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        //pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        pahoMqttClient = new PahoMqttClient();
        mqttAndroidClient = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, uid);

        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {


            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

                messageInt = Integer.valueOf(mqttMessage.toString());
                switch (s) {
                    case SUBSCRIBE_TOPIC_SOILMOISTURE: {
                        if ((messageInt-1) < 60) {
                            setMessageNotification(s, "Seems like your plants are dry! What's about watering them?");
                            //message = "Seems like your plants are dry! What's about watering them?";
                            Log.d(TAG, "massege recived");
                        }
                        break;
                    }

                    case SUBSCRIBE_TOPIC_TEMPERTURE: {
                        if ((messageInt-1) > 40) {
                            setMessageNotification(s, "Tempreture degree is High! What's about turning the fans on? ");
                            Log.d(TAG, "massege recived");


                        }
                        break;
                    }
                    case SUBSCRIBE_TOPIC_WATER_LEVEL: {
                        if (messageInt < 30) {
                            setMessageNotification(s, "Water in the tank is almost finshed!");
                            Log.d(TAG, "massege recived");

                        }
                        break;
                    }


                    //setMessageNotification(" ", message);
                    //Log.d(TAG, "massege recived");

                }

            }



            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });



        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind");

        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        try {
            if (mqttAndroidClient != null) {
                mqttAndroidClient.disconnect();
            }
        } catch (Exception e) {
            Log.d(TAG, "! Disconnect failed!");
            e.printStackTrace();
        }
        Log.d(TAG, "onDestroy Service!");


    }


    private void setMessageNotification(@NonNull String topic, @NonNull String msg) {
        Log.d(TAG, "insidd setMessageNotification");
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(topic) // title for notification
                .setContentText(msg)// message for notification
                // set alarm sound for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }


}


