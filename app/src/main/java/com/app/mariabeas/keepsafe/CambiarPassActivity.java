package com.app.mariabeas.keepsafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Maria on 02/02/2016.
 */
public class CambiarPassActivity extends AppCompatActivity {
    ImageView logo;
    ImageView candado;
    TextView passActual;
    TextView nuevaPass;
    TextView confirPass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambiar_password);

        //ELEMENTOS DE LA INTERFAZ
        logo=(ImageView)findViewById(R.id.logo);
        candado=(ImageView)findViewById(R.id.avatarGuardado);
        Button btnAceptar=(Button)findViewById(R.id.btnAceptar);
        passActual=(TextView)findViewById(R.id.edtPassActual);
        nuevaPass=(TextView)findViewById(R.id.edtNuevaPass);
        confirPass=(TextView)findViewById(R.id.edtConfirPass);

        MiListener listener=new MiListener();
        btnAceptar.setOnClickListener(listener);
    }
    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnDatos) {
                //PARA PASAR DE UNA PANTALLA A OTRA
                Intent intentactivity = new Intent(CambiarPassActivity.this, MainActivity.class);
                startActivity(intentactivity);
            }


        }
    }
}
