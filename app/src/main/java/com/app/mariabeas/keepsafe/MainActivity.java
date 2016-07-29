package com.app.mariabeas.keepsafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    EditText edtRecuperar;
    Context context=this;
    LoginDataBaseAdapter loginDBAdapter;
    EditText edtNombre;

    String nombre;


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
        edtNombre=(EditText)findViewById(R.id.edtNombreAgenda);
        nombre=getIntent().getStringExtra("nombre");


        MiListener listener = new MiListener();
        btnInicio.setOnClickListener(listener);
        btnRegistro.setOnClickListener(listener);
        btnRecuperar.setOnClickListener(listener);

    }



    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            if(v.getId()==R.id.btnInicio) {
                String user=edtUser.getText().toString();
                String contra=edtPassword.getText().toString();
                String storedPassword=loginDBAdapter.getSingleEntry(user);

                if (contra.equals(storedPassword)) {

                    Log.d("entrando", "e");
                    Toast.makeText(getApplicationContext(), "¡Bienvenido " + user + "!", Toast.LENGTH_LONG).show();

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
                    Intent intentRecuperar=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intentRecuperar);
                    //DIALOGO PARA RECUPERAR CONTRASEÑA
                  /*  final View addView=getLayoutInflater().inflate(R.layout.recuperar_contrasenia_dialog,null);

                    AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getParent());

                    //TITULO DEL ALERT DIALOG
                    alertDialogBuilder.setTitle("Recuperar contraseña");
                    alertDialogBuilder.setView(addView);
                    edtRecuperar=(EditText)addView.findViewById(R.id.edtRecuperar);

                    alertDialogBuilder
                            .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edtRecuperar = (EditText) addView.findViewById(R.id.edtRecuperar);
                                    String email = edtRecuperar.getEditableText().toString();
                                    Usuario u = new Usuario();
                                    u.setEmailUsuario(email);
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                  dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();*/

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

