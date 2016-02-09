package com.app.mariabeas.keepsafe;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Maria on 01/02/2016.
 */
public class MenuActivity extends AppCompatActivity {


    ImageView logo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        //ELEMENTOS DE LA INTERFAZ
        logo=(ImageView)findViewById(R.id.logo);
        Button btnDatos=(Button)findViewById(R.id.btnDatos);
        Button btnAgenda=(Button)findViewById(R.id.btnAgenda);
        Button btnSMS=(Button)findViewById(R.id.btnSMS);
        Button btnUbicacion=(Button)findViewById(R.id.btnUbicacion);
        Button btnInfo=(Button)findViewById(R.id.btnInfo);

        MiListener listener=new MiListener();
        btnDatos.setOnClickListener(listener);
        btnUbicacion.setOnClickListener(listener);
        btnInfo.setOnClickListener(listener);
    }
    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnDatos) {
                //PARA PASAR DE UNA PANTALLA A OTRA
                Intent intentDatos = new Intent(MenuActivity.this, DatosActivity.class);
                startActivity(intentDatos);
            }else if(v.getId()==R.id.btnUbicacion){
                Intent intentUbi=new Intent(MenuActivity.this,UbicacionActivity.class);
                startActivity(intentUbi);
            }else if(v.getId()==R.id.btnInfo){
                Intent intentInfo=new Intent(MenuActivity.this,InformacionActivity.class);
                startActivity(intentInfo);
            }


        }
    }

}
