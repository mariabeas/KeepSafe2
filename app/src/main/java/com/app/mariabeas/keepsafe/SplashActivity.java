package com.app.mariabeas.keepsafe;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Maria on 02/02/2016.
 */
public class SplashActivity extends Activity {
    ImageView logo;
    private static final long SPLASH_SCREEN_DELAY=3000;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Establecer orientacion VERTICAL
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Esconder barra de titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        //ELEMENTOS DE LA INTERFAZ
        logo=(ImageView)findViewById(R.id.logo);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                //Empezar la nueva actividad
                Intent mainIntent=new Intent().setClass(SplashActivity.this,MainActivity.class);
                startActivity(mainIntent);

                //Terminar la actividad para que el usuario no pueda volver a la pantalla del splash
                finish();
            }
        };
        //Simular un largo proceso de carga al iniciar la app
        Timer timer=new Timer();
        timer.schedule(task,SPLASH_SCREEN_DELAY);
    }
}
