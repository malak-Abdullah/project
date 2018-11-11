package com.example.dell.smartgarden;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_HUMIDITY;
import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_LIGHT_SENSOR;
import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_SOILMOISTURE;
import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_TEMPERTURE;
import static com.example.dell.smartgarden.Constants.SUBSCRIBE_TOPIC_WATER_LEVEL;


public class SensorsFragment extends Fragment{

    View view;

    public MqttAndroidClient client;
    private String TAG = "SensorsFragment";
    public PahoMqttClient pahoMqttClient;
    private SeekBar SeekBarWater;
    private Handler seekBarHandler;

    private ProgressBar ProgressBarAirHum, ProgressBarAirTemp, ProgressBarLightInt, ProgressBarSoil;
    private TextView TextViewAirHum, TextViewSoil, TextViewAirTemp,  TextViewLightInt;
    private Handler mHandler ;

    int messageInt;
    String value;


    private FirebaseUser user;
    private String uid;

     SharedPreferences.Editor editor;

     SharedPreferences test_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sensors, container, false);


        pahoMqttClient = new PahoMqttClient();

        seekBarHandler = new Handler(); // must be created in the same thread that created the SeekBar
        SeekBarWater =  view.findViewById(R.id.seekBarWater);


        mHandler = new Handler();
        ProgressBarAirHum=  view.findViewById(R.id.progressBarAirHum);
        TextViewAirHum =  view.findViewById(R.id.textViewAirHum);

        ProgressBarSoil=  view.findViewById(R.id.progressBarSoil);
        TextViewSoil =  view.findViewById(R.id.textViewSoil);

        ProgressBarAirTemp=  view.findViewById(R.id.progressBarAirTemp);
        TextViewAirTemp =  view.findViewById(R.id.textViewAirTemp);

        ProgressBarLightInt= view.findViewById(R.id.progressBarLightInt);
        TextViewLightInt =  view.findViewById(R.id.textViewLightInt);



        SeekBarWater.setMax(100);

        SeekBarWater.refreshDrawableState();



         test_name = this.getActivity().getSharedPreferences("NAME", 0);
        editor = test_name.edit();

        SeekBarWater.setProgress(test_name.getInt("waterProgress",0));

        TextViewAirHum.setText(test_name.getString("humText", ""));
        ProgressBarAirHum.setProgress(test_name.getInt("humProgress",0));


        TextViewSoil.setText(test_name.getString("soilText", ""));
        ProgressBarSoil.setProgress(test_name.getInt("soilProgress",0));

        TextViewAirTemp.setText(test_name.getString("tempText", ""));
        ProgressBarAirTemp.setProgress(test_name.getInt("tempProgress",0));

        TextViewLightInt.setText(test_name.getString("lightText", ""));
        ProgressBarLightInt.setProgress(test_name.getInt("lightProgress",0));


        SeekBarWater.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        client = pahoMqttClient.getMqttClient(getActivity().getApplicationContext(), Constants.MQTT_BROKER_URL, uid);

        client.setTraceEnabled(true);



        pahoMqttClient.mqttAndroidClient.setCallback(new MqttCallback() {




            @Override
            public void connectionLost(Throwable cause) {



            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.d(TAG, "massege recived");
                messageInt= Integer.valueOf(message.toString());

                switch (topic) {
                    case SUBSCRIBE_TOPIC_HUMIDITY: {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // if (ProgressBarAirHum != null) {
                                ProgressBarAirHum.setMax(100);
                                ProgressBarAirHum.setProgress(0);
                                ProgressBarAirHum.setProgress(messageInt);
                                editor.putInt("humProgress",ProgressBarAirHum.getProgress());
                                editor.apply();
                                // }
                            }
                        });
                        value = message.toString()+"%";
                        TextViewAirHum.setText(value);

                        editor.putString("humText", TextViewAirHum.getText().toString());
                        editor.apply();
                        break;
                    }
                    case SUBSCRIBE_TOPIC_LIGHT_SENSOR:{

                        mHandler.post(new Runnable(){
                            @Override
                            public void run() {
                                //if (ProgressBarLightInt != null) {
                                ProgressBarLightInt.setMax(5000);
                                ProgressBarLightInt.setProgress(messageInt);
                                editor.putInt("lightProgress",ProgressBarAirHum.getProgress());
                                editor.apply();
                                //}
                            }
                        });
                        value = message.toString()+"LX";
                        TextViewLightInt.setText(value);
                        editor.putString("lightText", TextViewLightInt.getText().toString());
                        editor.apply();
                        break;
                    }
                    case SUBSCRIBE_TOPIC_SOILMOISTURE:{
                        mHandler.post(new Runnable(){
                            @Override
                            public void run() {
                                if (ProgressBarSoil != null) {
                                    ProgressBarSoil.setMax(100);
                                    ProgressBarSoil.setProgress(messageInt);
                                    editor.putInt("soilProgress",ProgressBarSoil.getProgress());
                                    editor.apply();
                                }
                            }
                        });
                        value = message.toString()+"%";
                        TextViewSoil.setText(value);
                        editor.putString("soilText", TextViewSoil.getText().toString());
                        editor.apply();
                        break;
                    }
                    case SUBSCRIBE_TOPIC_TEMPERTURE:{
                        mHandler.post(new Runnable(){
                            @Override
                            public void run() {
                                if (ProgressBarAirTemp != null) {
                                    ProgressBarAirTemp.setMax(100);
                                    ProgressBarAirTemp.setProgress(messageInt);
                                    editor.putInt("tempProgress",ProgressBarAirTemp.getProgress());
                                    editor.apply();
                                }
                            }
                        });
                        value = message.toString()+"C";
                        TextViewAirTemp.setText(value);
                        editor.putString("tempText", TextViewAirTemp.getText().toString());
                        editor.apply();
                        break;
                    }

                    case SUBSCRIBE_TOPIC_WATER_LEVEL:{
                        seekBarHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (SeekBarWater != null) {
                                    SeekBarWater.setMax(100);
                                    SeekBarWater.setProgress(messageInt);
                                    SeekBarWater.refreshDrawableState();
                                    editor.putInt("waterProgress",SeekBarWater.getProgress());
                                    editor.apply();
                                }
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });





        return view;




    }
}
