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
public class RegistroActivity extends AppCompatActivity {
    ImageView logo;
    ImageView avatar;
    EditText edtUsuario;
    EditText edtPass;
    EditText edtConfiPass;

    LoginDataBaseAdapter loginDBAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        //Crear una instancia de SQLiteDataBase
        loginDBAdapter=new LoginDataBaseAdapter(this);
        loginDBAdapter=loginDBAdapter.open();
        //ELEMENTOS DE LA INTERFAZ
        logo=(ImageView)findViewById(R.id.logo);
        avatar=(ImageView)findViewById(R.id.candado);
        edtUsuario=(EditText)findViewById(R.id.edtUsuario);
        edtPass=(EditText)findViewById(R.id.edtPass);
        edtConfiPass=(EditText)findViewById(R.id.edtConfiPass);
        Button btnAceptar=(Button)findViewById(R.id.btnAceptar);

        MiListener listener=new MiListener();
        btnAceptar.setOnClickListener(listener);
    }
    //clase privada para implementar la funcionalidad de los botones de la activity
    private class MiListener implements View.OnClickListener{

        public void onClick(View v) {
            String usuario=edtUsuario.getText().toString();
            String pass=edtPass.getText().toString();
            String confiPass=edtConfiPass.getText().toString();
            if(v.getId()==R.id.btnAceptar) {
                if(usuario.isEmpty()||pass.isEmpty()||confiPass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Compruebe que los campos no esten vacíos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pass.equals(confiPass)){
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    LoginDataBaseAdapter.insertEntry(usuario,pass);
                    Toast.makeText(getApplicationContext(), "Registro completado", Toast.LENGTH_SHORT).show();
                    //PARA PASAR DE UNA PANTALLA A OTRA
                    Intent intentactivity = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(intentactivity);
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
