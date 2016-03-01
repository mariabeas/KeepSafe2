package com.app.mariabeas.keepsafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by MariaBeas on 15/2/16.
 */
public class EnviarSMSActivity extends AppCompatActivity {

    ImageView logo;
    EditText edtContacto;
    EditText edtSMS;
    Context context=this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enviar_sms);
        //Declaramos el toolbar del menu datos
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_datos);
        setSupportActionBar(toolbar);
        //para poner el boton de volver al menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //ELEMENTOS DE LA INTERFAZ
        logo = (ImageView) findViewById(R.id.logo);
        Button btnEnviarSMS=(Button)findViewById(R.id.btnEnviarSMS);
        edtContacto=(EditText)findViewById(R.id.edtContacto);
        edtSMS=(EditText)findViewById(R.id.edtSMS);

        MiListener listener=new MiListener();
        btnEnviarSMS.setOnClickListener(listener);

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
                        Intent intentLogin =new Intent(EnviarSMSActivity.this,MainActivity.class);
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
            if(v.getId()==R.id.btnEnviarSMS){
                PendingIntent sentIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("SMS_SENT"), 0);

                registerReceiver(new BroadcastReceiver() {

                    @Override
                    public void onReceive(Context context, Intent intent) {
                        switch (getResultCode()) {
                            case Activity.RESULT_OK:
                                Toast.makeText(getApplicationContext(), "SMS enviado", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                Toast.makeText(getApplicationContext(), "No se pudo enviar SMS", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NO_SERVICE:
                                Toast.makeText(getApplicationContext(), "Servicio no diponible", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                Toast.makeText(getApplicationContext(), "PDU (Protocol Data Unit) es NULL", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                Toast.makeText(getApplicationContext(), "Failed because radio was explicitly turned off", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

            }, new IntentFilter("SMS_SENT"));

            SmsManager sms = SmsManager.getDefault();
            if( edtContacto.getText().toString().length()> 0 &&
                    edtSMS.getText().toString().length()>0 )
            {
                sms.sendTextMessage(edtContacto.getText().toString() , null, edtSMS.getText().toString() , sentIntent, null);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No se puede enviar, los datos son incorrectos", Toast.LENGTH_SHORT).show();
            }
        }};

            }



}
