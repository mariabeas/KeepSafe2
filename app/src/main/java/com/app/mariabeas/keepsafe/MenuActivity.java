package com.app.mariabeas.keepsafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Maria on 01/02/2016.
 */
public class MenuActivity extends AppCompatActivity {


    ImageView logo;
    Context context=this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //Declaramos el toolbar del menu datos
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_datos);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("");

        //ELEMENTOS DE LA INTERFAZ
        logo=(ImageView)findViewById(R.id.logo);
        Button btnDatos=(Button)findViewById(R.id.btnDatos);
        Button btnAgenda=(Button)findViewById(R.id.btnAgenda);
        Button btnSMS=(Button)findViewById(R.id.btnSMS);
        Button btnUbicacion=(Button)findViewById(R.id.btnUbicacion2);
        Button btnInfo=(Button)findViewById(R.id.btnInfo);

        MiListener listener=new MiListener();
        btnDatos.setOnClickListener(listener);
        btnUbicacion.setOnClickListener(listener);
        btnInfo.setOnClickListener(listener);
        btnAgenda.setOnClickListener(listener);
        btnSMS.setOnClickListener(listener);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                cerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void cerrarSesion(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // Titulo del AlertDialog
        alertDialogBuilder.setTitle("¿Seguro que desea cerrar sesión?");

        alertDialogBuilder
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentLogin =new Intent(MenuActivity.this,MainActivity.class);
                        startActivity(intentLogin);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }




    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnDatos) {
                //PARA PASAR DE UNA PANTALLA A OTRA
                Intent intentDatos = new Intent(MenuActivity.this, DatosGuardadosActivity.class);
                startActivity(intentDatos);
            }else if(v.getId()==R.id.btnUbicacion2){
                Intent intentUbi=new Intent(MenuActivity.this,UbicacionActivity.class);
                startActivity(intentUbi);
            }else if(v.getId()==R.id.btnInfo){
                Intent intentInfo=new Intent(MenuActivity.this,InformacionActivity.class);
                startActivity(intentInfo);
            }else if(v.getId()==R.id.btnAgenda){
                Intent intentAgenda=new Intent(MenuActivity.this,AgendaActivity.class);
                startActivity(intentAgenda);
            }else if(v.getId()==R.id.btnSMS){
                Intent intentSMS=new Intent(MenuActivity.this,EnviarSMSActivity.class);
                startActivity(intentSMS);
            }


        }
    }


}
