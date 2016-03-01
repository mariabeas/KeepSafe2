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
import android.widget.TextView;

/**
 * Created by MariaBeas on 16/2/16.
 */
public class DatosGuardadosActivity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvEmail;
    TextView tvApellido;
    TextView tvFecha;
    TextView tvSexo;
    TextView tvSangre;
    TextView tvNum;
    ImageView image;
    Context context=this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_guardados);

        tvNombre=(TextView)findViewById(R.id.edtNombreAgenda);
        tvNombre.setText(getIntent().getStringExtra("nombre"));
        tvEmail=(TextView)findViewById(R.id.edtUser);
        tvEmail.setText(getIntent().getStringExtra("email"));
        tvApellido=(TextView)findViewById(R.id.edtApellido);
        tvApellido.setText(getIntent().getStringExtra("apellido"));
        tvFecha=(TextView)findViewById(R.id.edtFecha);
        tvFecha.setText(getIntent().getStringExtra("fecha"));
        tvSexo=(TextView)findViewById(R.id.edtSexo);
        tvSexo.setText(getIntent().getStringExtra("sexo"));
        tvSangre=(TextView)findViewById(R.id.edtSangre);
        tvSangre.setText(getIntent().getStringExtra("sangre"));
        tvNum=(TextView)findViewById(R.id.edtNum);
        tvNum.setText(getIntent().getStringExtra("numSeguridad"));
        image=(ImageView)findViewById(R.id.imageView);
        int image_link=getIntent().getIntExtra("image",R.drawable.avatar);
        image.setImageResource(image_link);

        //Declaramos el toolbar del menu datos guardados
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_datos);
        setSupportActionBar(toolbar);
        //para poner el boton de volver al menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Modificar datos");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_datos_guardados, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_editar:
                editarDatos();
                return true;
            case R.id.action_logout:
                cerrarSesion();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void cerrarSesion(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // Titulo del AlertDialog
        alertDialogBuilder.setTitle("¿Seguro que desea cerrar sesión?");

        alertDialogBuilder
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentLogin =new Intent(DatosGuardadosActivity.this,MainActivity.class);
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
    public void editarDatos(){
        Intent intentEditar=new Intent(DatosGuardadosActivity.this,DatosActivity.class);
        startActivity(intentEditar);
    }


}
