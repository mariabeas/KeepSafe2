package com.app.mariabeas.keepsafe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MariaBeas on 16/2/16.
 */
public class AdapterUsuario extends BaseAdapter {
    private ArrayList<Usuario> usuarios;
    private Context contexto;

    public AdapterUsuario (Context c){
        usuarios=new ArrayList<>();
        this.contexto=c;
    }



    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //METODO INFLATE PARA DARLE VALOR AL CONVERTVIEW CON EL VALOR DEL LAYOUT QUE HEMOS CREADO ANTES (LINEA)
            convertView=View.inflate(contexto,R.layout.datos,null);
        }
        //COGER LOS DATOS DEL LAYOUT QUE HEMOS INTRODUCIDO EN DATOS
        EditText edtUserEmail=(EditText)convertView.findViewById(R.id.edtUser);
        EditText edtNombreAgenda=(EditText)convertView.findViewById(R.id.edtNombreAgenda);
        EditText edtApellido=(EditText)convertView.findViewById(R.id.edtApellido);
        EditText edtFecha=(EditText)convertView.findViewById(R.id.edtFecha);
        EditText edtSexo=(EditText)convertView.findViewById(R.id.edtSexo);
        EditText edtSangre=(EditText)convertView.findViewById(R.id.edtSangre);
        EditText edtNum=(EditText)convertView.findViewById(R.id.edtNum);


        Usuario u=(Usuario)getItem(position);

        edtUserEmail.setText(u.getEmailUsuario());
        edtNombreAgenda.setText(u.getNombreUsuario());
        edtApellido.setText(u.getApellidosUsuario());
        edtFecha.setText(u.getFechaNac());
        edtSexo.setText(u.getSexo());
        edtSangre.setText(u.getGrupoSanguineo());
        edtNum.setText(u.getNumSeguridadSocial());


        return convertView;

    }

    public void addUsuario(Usuario u){
        this.usuarios.add(u);
        //REFRESCAR LA INTERFAZ SI HAY ALGUN CAMBIO
        notifyDataSetChanged();
    }
}
