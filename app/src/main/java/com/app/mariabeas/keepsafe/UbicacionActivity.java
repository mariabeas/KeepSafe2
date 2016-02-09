package com.app.mariabeas.keepsafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

/**
 * Created by Maria on 03/02/2016.
 */
public class UbicacionActivity extends AppCompatActivity {
    ImageView logo;
    GoogleMap googleMapa;
    MapView vistaMapa;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);

        //ELEMENTOS DE LA INTERFAZ
        logo = (ImageView) findViewById(R.id.logo);
        vistaMapa=(MapView)findViewById(R.id.miMapa);

        vistaMapa.onCreate(savedInstanceState);
        //inicializar google map
        googleMapa=vistaMapa.getMap();
        //tipo de mapa que queramos mostrar
        googleMapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //poner a disponible la ubicacion para poder utilizar
        googleMapa.setMyLocationEnabled(true);
    }


    @Override
    protected void onPause() {
        super.onPause();
        vistaMapa.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vistaMapa.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vistaMapa.onResume();
    }
}
