package com.app.mariabeas.keepsafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_guardados);
        Button btnVolverMenu=(Button)findViewById(R.id.btnVolverMenu);
        Button btnEditarDatos=(Button)findViewById(R.id.btnEditarDatos);
        MiListener listener=new MiListener();
        btnVolverMenu.setOnClickListener(listener);
        btnEditarDatos.setOnClickListener(listener);
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

    }

    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnVolverMenu){
                Intent intentVolver=new Intent (DatosGuardadosActivity.this,MenuActivity.class);
                startActivity(intentVolver);
            }else if(v.getId()==R.id.btnEditarDatos){
                Intent intentEditar=new Intent(DatosGuardadosActivity.this,DatosActivity.class);
                startActivity(intentEditar);
            }
        }

        }
}
