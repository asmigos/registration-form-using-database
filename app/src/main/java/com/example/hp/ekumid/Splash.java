package com.example.hp.ekumid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread th = new Thread() {


            public void run() {

                try {
                    sleep(3000);
                } catch (Exception e) {

                }

                Intent i = new Intent(Splash.this, RegistrationPage.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                finish();
            }

        };
        th.start();

    }

}
