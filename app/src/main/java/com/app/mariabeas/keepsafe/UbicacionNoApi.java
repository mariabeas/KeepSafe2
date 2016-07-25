package com.app.mariabeas.keepsafe;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by MariaBeas on 20/7/16.
 */
public class UbicacionNoApi implements LocationListener {
    private Context context;
    LocationManager locationManager;
    String proveedor;
    private boolean networkOn;

    public UbicacionNoApi(Context context) {
        this.context = context;
        locationManager= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        proveedor=locationManager.NETWORK_PROVIDER; //PROBAR CON GPS_PROVIDER
        networkOn=locationManager.isProviderEnabled(proveedor);
        locationManager.requestLocationUpdates(proveedor,1000,1,this);
        getLocation();
    }

    private void getLocation(){
        if(networkOn){
            Location location=locationManager.getLastKnownLocation(proveedor);
            if(location!=null){
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("Altitud: ").append(location.getAltitude())
                        .append("Latitud: ").append(location.getLatitude())
                        .append("Longitud: ").append(location.getLongitude());
                Toast.makeText(context,stringBuilder.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        //esta activado el proveedor

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
