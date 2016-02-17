package com.app.mariabeas.keepsafe;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by MariaBeas on 17/2/16.
 */
public class MyLocationListener implements LocationListener {
    TextView tvUbicacion;
    UbicacionActivity ubicacionActivity;


    public UbicacionActivity getUbicacionActivity() {
        return ubicacionActivity;
    }

    public void setUbicacionActivity(UbicacionActivity ubicacionActivity) {
        this.ubicacionActivity = ubicacionActivity;
    }

    @Override
    public void onLocationChanged(Location loc) {
        //Metodo que se ejecuta cada vez que el GPS recibe nuevas coordenadas al detectar un cambio de ubicacion
        loc.getAltitude();
        loc.getLongitude();
        String ubicacion="Mi ubicación actual es: "+"\n Latitud= "+loc.getLatitude()+"\n Longitud= "+loc.getLongitude();
        Toast.makeText(getApplicationContext(), "Ubicación actualizada", Toast.LENGTH_SHORT).show();
        tvUbicacion.setText(ubicacion);
        //this.ubicacionActivity.set
        this.ubicacionActivity.setLocation(loc);

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        /*Metodo que se ejecuta cada vez que se detecta un cambio en el status del proveedor de la localizacion (GPS)
        * Los diferentes status son:
        * OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
        * TEMPORARILY_UNAVAILABLE ->Temporalmente no disponible pero se espera que este disponible en breve
        * AVAILABLE -> Disponible*/


    }

    @Override
    public void onProviderEnabled(String provider) {
        //GPS ACTIVADO
        Toast.makeText(getApplicationContext(), "GPS Activado", Toast.LENGTH_SHORT).show();
        tvUbicacion.setText("GPS ACTIVADO");
    }

    @Override
    public void onProviderDisabled(String provider) {
        //GPS DESACTIVADO
        Toast.makeText(getApplicationContext(), "GPS Desactivado", Toast.LENGTH_SHORT).show();
        tvUbicacion.setText("GPS DESACTIVADO");
    }
}
