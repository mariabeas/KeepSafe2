package com.app.mariabeas.keepsafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Maria on 02/02/2016.
 */
public class DatosActivity extends AppCompatActivity {
    ImageView logo;
    ImageView avatar;
    EditText edtUser;
    EditText edtNombre;
    EditText edtApellido;
    EditText edtFecha;
    EditText edtSexo;
    EditText edtSangre;
    EditText edtNum;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);

        //ELEMENTOS DE LA INTERFAZ
        Button btnCambiar=(Button)findViewById(R.id.btnCambiar);
        Button btnFoto=(Button)findViewById(R.id.btnFoto);
        logo=(ImageView)findViewById(R.id.logo);
        avatar=(ImageView)findViewById(R.id.candado);
        edtUser=(EditText)findViewById(R.id.edtUser);
        edtNombre=(EditText)findViewById(R.id.edtNombre);
        edtApellido=(EditText)findViewById(R.id.edtApellido);
        edtFecha=(EditText)findViewById(R.id.edtFecha);
        edtSexo=(EditText)findViewById(R.id.edtSexo);
        edtSangre=(EditText)findViewById(R.id.edtSangre);
        edtNum=(EditText)findViewById(R.id.edtNum);

        MiListener listener=new MiListener();
        btnCambiar.setOnClickListener(listener);
    }
    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String usuario=((EditText)findViewById(R.id.edtUser)).getText().toString();
            String nombre=((EditText)findViewById(R.id.edtNombre)).getText().toString();
            String apellido=((EditText)findViewById(R.id.edtApellido)).getText().toString();
            String fecha=((EditText)findViewById(R.id.edtFecha)).getText().toString();
            String sexo=((EditText)findViewById(R.id.edtSexo)).getText().toString();
            String sangre=((EditText)findViewById(R.id.edtSangre)).getText().toString();
            String num=((EditText)findViewById(R.id.edtNum)).getText().toString();

            //elementos vacios
            if (nombre.equals(null) || usuario.equals(null) || apellido.equals(null) ||
                    fecha.equals(null) || sexo.equals(null) || sangre.equals(null) || num.equals(null)) {
                Toast.makeText(getApplicationContext(), "Completa los datos", Toast.LENGTH_SHORT).show();
            }else{
                if(v.getId()==R.id.btnCambiar) {
                    //PARA PASAR DE UNA PANTALLA A OTRA
                    Intent intentactivity = new Intent(DatosActivity.this, CambiarPassActivity.class);
                    startActivity(intentactivity);
                }
            }
        }
    }
}
