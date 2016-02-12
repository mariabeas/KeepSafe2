package com.app.mariabeas.keepsafe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MariaBeas on 12/2/16.
 */
public class AdapterAgenda extends BaseAdapter{
    private List<Emergencia> lista;
    private Context contexto;

    public AdapterAgenda (Context c){
        lista=new ArrayList<Emergencia>();
        this.contexto=c;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //METODO INFLATE PARA DARLE VALOR AL CONVERTVIEW CON EL VALOR DEL LAYOUT QUE HEMOS CREADO ANTES (LINEA)
            convertView=View.inflate(contexto,R.layout.linea,null);
        }
        //COGER LOS DATOS DEL LAYOUT QUE HEMOS CREADO EN LINEA
        TextView txtNombre=(TextView)convertView.findViewById(R.id.txtNombre);
        TextView txtTlf=(TextView)convertView.findViewById(R.id.txtTlf);
        Emergencia e=(Emergencia)getItem(position);
        txtNombre.setText(e.getNombre());
        txtTlf.setText(e.getTelefono());

        return convertView;
    }

    public void addEmergencia(Emergencia e){
        this.lista.add(e);
        //REFRESCAR LA INTERFAZ SI HAY ALGUN CAMBIO
        notifyDataSetChanged();
    }
}
