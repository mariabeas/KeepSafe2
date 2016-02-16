package com.app.mariabeas.keepsafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by MariaBeas on 16/2/16.
 */
public class DatosGuardadosActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_guardados);
        Button btnVolverMenu=(Button)findViewById(R.id.btnVolverMenu);
        MiListener listener=new MiListener();
        btnVolverMenu.setOnClickListener(listener);
    }

    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnVolverMenu){
                Intent intentVolver=new Intent (DatosGuardadosActivity.this,MenuActivity.class);
                startActivity(intentVolver);
            }
        }

        }
}
