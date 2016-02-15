package com.app.mariabeas.keepsafe;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enviar_sms);
        //ELEMENTOS DE LA INTERFAZ
        logo = (ImageView) findViewById(R.id.logo);
        Button btnEnviarSMS=(Button)findViewById(R.id.btnEnviarSMS);
        edtContacto=(EditText)findViewById(R.id.edtContacto);
        edtSMS=(EditText)findViewById(R.id.edtSMS);

        MiListener listener=new MiListener();
        btnEnviarSMS.setOnClickListener(listener);

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
               // sendSMS("5556", "Hi You got a message!");
                /*
                *  Intent intentSMS = new Intent(EnviarSMSActivity.this, .class);
                    startActivity(intentSMS);*/
            }





    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("phoneNumber", null, "message", null, null);
    }
}
