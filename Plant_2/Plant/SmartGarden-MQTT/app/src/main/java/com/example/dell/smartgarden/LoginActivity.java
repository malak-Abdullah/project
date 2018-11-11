package com.example.dell.smartgarden;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnSignUp, btnLogin;
    private ProgressDialog PD;
    private FirebaseUser user;
    private String uid;
    public Intent intent;
    public MqttAndroidClient client;
    public  Intent intent2;
    public PahoMqttClient pahoMqttClient;

   public SharedPreferences.Editor editor;

   public SharedPreferences test_name;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Launcher);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        test_name = this.getSharedPreferences("NAME", 0);
        editor = test_name.edit();


        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);
        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnLogin = (Button) findViewById(R.id.sign_in_button);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                try {

                    if (password.length() > 0 && email.length() > 0) {
                        PD.show();
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(
                                                    LoginActivity.this,
                                                    "Email or Password is incorrect ",
                                                    Toast.LENGTH_LONG).show();
                                            //  Log.v("error", task.getResult().toString());
                                        } else {

                                            user = FirebaseAuth.getInstance().getCurrentUser();
                                            uid = user.getUid();
                                            //editor.putString("ID",uid);
                                           // editor.apply();
                                            pahoMqttClient = new PahoMqttClient();
                                            client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, uid);
                                            client.setTraceEnabled(true);


                                            Intent intent2 = new Intent(LoginActivity.this, MqttMessageService.class);
                                            ContextCompat.startForegroundService(LoginActivity.this, intent2  );
                                            startService(intent2);

                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        PD.dismiss();
                                    }
                                });
                    } else {
                        Toast.makeText(
                                LoginActivity.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(intent);
            }
        });

        findViewById(R.id.forget_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 0));
            }
        });

    }

    @Override    protected void onResume() {
        if (auth.getCurrentUser() != null) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            uid = user.getUid();
            //editor.putString("ID",uid);
            //editor.apply();
            pahoMqttClient = new PahoMqttClient();
            client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, uid);
            client.setTraceEnabled(true);
             intent2 = new Intent(LoginActivity.this, MqttMessageService.class);
            ContextCompat.startForegroundService(LoginActivity.this, intent2  );
            startService(intent2);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        super.onResume();
    }
}
