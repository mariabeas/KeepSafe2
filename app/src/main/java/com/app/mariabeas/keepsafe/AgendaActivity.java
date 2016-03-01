package com.app.mariabeas.keepsafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MariaBeas on 11/2/16.
 */
public class AgendaActivity extends AppCompatActivity {
    private AdapterAgenda adaptador;
    private EditText edtNombreAgenda;
    private EditText edtTelefono;
    Context context=this;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);
        //Declaramos el toolbar del menu datos
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_datos);
        setSupportActionBar(toolbar);
        //para poner el boton de volver al menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //DECLARAR ELEMENTOS DE LA INTERFAZ
        ListView lista=(ListView)findViewById(R.id.listaTelefonos);
        edtNombreAgenda=(EditText)findViewById(R.id.edtNombreAgenda);
        edtTelefono=(EditText)findViewById(R.id.edtTlf);
        adaptador=new AdapterAgenda(this);
        lista.setAdapter(adaptador);

        FloatingActionButton btnAniadir=(FloatingActionButton)findViewById(R.id.btnAniadir);
        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=edtNombreAgenda.getText().toString();
                String telefono=edtTelefono.getText().toString();

                Emergencia e=new Emergencia();
                e.setNombre(nombre);
                e.setTelefono(telefono);
                adaptador.addEmergencia(e);
            }
        });


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
                        Intent intentLogin =new Intent(AgendaActivity.this,MainActivity.class);
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

    private class MiListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnAniadir) {
                //PARA PASAR DE UNA PANTALLA A OTRA
                Intent intentAdd = new Intent(AgendaActivity.this, AgendaActivity.class);
                startActivity(intentAdd);
                //this.lista.add(e);
                //REFRESCAR LA INTERFAZ SI HAY ALGUN CAMBIO
                adaptador.notifyDataSetChanged();
            }
        }
    }

}
