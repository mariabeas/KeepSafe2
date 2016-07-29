package com.app.mariabeas.keepsafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tvUsuario;
    Context context=this;

    LoginDataBaseAdapter loginDBAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_guardados);

        //Crear una instancia de SQLiteDataBase
        loginDBAdapter=new LoginDataBaseAdapter(this);
        loginDBAdapter=loginDBAdapter.open();

        tvUsuario=(TextView)findViewById(R.id.edtUsuario);
        //nombre=getIntent().getStringExtra("nombre");
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
        int image_link=getIntent().getIntExtra("image", R.drawable.avatar);
        image.setImageResource(image_link);

        //Declaramos el toolbar del menu datos guardados
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_datos);
        setSupportActionBar(toolbar);
        //para poner el boton de volver al menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Modificar datos");

        //RECUPERAMOS LOS REGISTROS INSERTADOS
        LoginDataBaseAdapter MDB = new LoginDataBaseAdapter(getApplicationContext());


        Log.d("TOTAL", Integer.toString(MDB.recuperarUsuario().size()));
        int[] ids = new int[MDB.recuperarUsuario().size()];
        String[] noms = new String[MDB.recuperarUsuario().size()];
        String [] apellidos = new String[MDB.recuperarUsuario().size()];
        String[] pass = new String[MDB.recuperarUsuario().size()];
        String[] fechas = new String[MDB.recuperarUsuario().size()];
        String[] sexos = new String[MDB.recuperarUsuario().size()];
        String[] sangres = new String[MDB.recuperarUsuario().size()];
        String[] numeros = new String[MDB.recuperarUsuario().size()];
        String[] emls = new String[MDB.recuperarUsuario().size()];


        for (int i = 0; i < MDB.recuperarUsuario().size(); i++) {

                ids[i] = Integer.parseInt(MDB.recuperarUsuario().get(i).getIdUsuario());
                emls[i] = MDB.recuperarUsuario().get(i).getEmailUsuario();
                pass[i] = MDB.recuperarUsuario().get(i).getPasswordUsuario();
                noms[i] = MDB.recuperarUsuario().get(i).getNombreUsuario();
                apellidos[i] = MDB.recuperarUsuario().get(i).getApellidosUsuario();
                fechas[i] = MDB.recuperarUsuario().get(i).getFechaNac();
                sexos[i] = MDB.recuperarUsuario().get(i).getSexo();
                sangres[i] = MDB.recuperarUsuario().get(i).getGrupoSanguineo();
                numeros[i] = MDB.recuperarUsuario().get(i).getNumSeguridadSocial();
                Log.e("Usuario con id: " + ids[i], "email; " + emls[i]);
                Log.e("nombre; " + noms[i], "apellido: " + apellidos[i]);
                Log.e("contraseña: " + pass[i], "fecha nacimiento; " + fechas[i]);
                Log.e("sexo: " + sexos[i], "grupo sanguineo" + sangres[i]);

        }

            //RECUPERAMOS EL REGISTRO CON EL ID 3
            String nom = MDB.recuperarUsuario(3).getNombreUsuario();
            String apellido = MDB.recuperarUsuario(3).getApellidosUsuario();
            //String password = MDB.recuperarUsuario(3).getPasswordUsuario();
            String fecha = MDB.recuperarUsuario(3).getFechaNac();
            String sexo = MDB.recuperarUsuario(3).getSexo();
            String sangre = MDB.recuperarUsuario(3).getGrupoSanguineo();
            String numero = MDB.recuperarUsuario(3).getNumSeguridadSocial();
            String eml = MDB.recuperarUsuario(3).getEmailUsuario();


            tvEmail.setText(eml.toString());
            tvNombre.setText(nom.toString());
            tvApellido.setText(apellido.toString());
            tvFecha.setText(fecha.toString());
            tvSexo.setText(sexo.toString());
            tvSangre.setText(sangre.toString());
            tvNum.setText(numero.toString());


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
