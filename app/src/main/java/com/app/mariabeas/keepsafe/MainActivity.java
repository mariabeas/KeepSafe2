package com.app.mariabeas.keepsafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    EditText edtUser;
    EditText edtPassword;
    CheckBox box;

    LoginDataBaseAdapter loginDBAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear una instancia de SQLiteDataBase
        loginDBAdapter = new LoginDataBaseAdapter(this);
        loginDBAdapter = loginDBAdapter.open();

        //ELEMENTOS DE LA INTERFAZ
        Button btnInicio = (Button) findViewById(R.id.btnInicio);
        Button btnRegistro = (Button) findViewById(R.id.btnRegistro);
        box = (CheckBox) findViewById(R.id.checkBox);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        logo = (ImageView) findViewById(R.id.logo);
        Button btnRecuperar = (Button) findViewById(R.id.btnRecuperar);

        MiListener listener = new MiListener();
        btnInicio.setOnClickListener(listener);
        btnRegistro.setOnClickListener(listener);

        MiListener listener2=new MiListener();
        btnRecuperar.setOnClickListener(listener2);

    }



    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            if(v.getId()==R.id.btnInicio) {
                String user=edtUser.getText().toString();
                String contra=edtPassword.getText().toString();
                String storedPassword=loginDBAdapter.getSingleEntry(user);
                if (contra.equals(storedPassword)) {
                    Toast.makeText(getApplicationContext(), "Inicio de sesión correcto", Toast.LENGTH_LONG).show();
                    //PASAR A LA SIGUIENTE PANTALLA
                    Intent nuevoIntent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(nuevoIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }else{
                if(v.getId()==R.id.btnRegistro) {
                    //PARA PASAR DE UNA PANTALLA A OTRA
                    Intent intentactivity = new Intent(MainActivity.this, RegistroActivity.class);
                    startActivity(intentactivity);
                }if(v.getId()==R.id.btnRecuperar){
                    //DIALOGO PARA RECUPERAR CONTRASEÑA

                }
            }



            }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //CERRAR LA DB
        loginDBAdapter.close();
    }

    }
