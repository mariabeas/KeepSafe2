package com.app.mariabeas.keepsafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    /*private Context contexto;
    private List<Emergencia> lista;
    public AgendaActivity (Context c){
        lista=new ArrayList<Emergencia>();
        this.contexto=c;
    }*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);

        //DECLARAR ELEMENTOS DE LA INTERFAZ
        ListView lista=(ListView)findViewById(R.id.listaTelefonos);
        edtNombreAgenda=(EditText)findViewById(R.id.edtNombreAgenda);
        edtTelefono=(EditText)findViewById(R.id.edtTlf);
        adaptador=new AdapterAgenda(this);
        lista.setAdapter(adaptador);

        Button btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
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
        /*MiListener listener=new MiListener();
        btnAdd.setOnClickListener(listener);*/

    }
    private class MiListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnAdd) {
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
