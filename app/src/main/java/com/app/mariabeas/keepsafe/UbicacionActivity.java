package com.app.mariabeas.keepsafe;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Maria on 03/02/2016.
 */
public class UbicacionActivity extends AppCompatActivity {
    ImageView logo;
    GoogleMap googleMapa;
    MapView vistaMapa;
    TextView tvUbicacion;
    TextView tvDireccion;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);

        //Para agregar al LocationManager un nuevo LocationListener
        // que escuche las actualización de ubicación del GPS.
        LocationManager locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener locListener = new MyLocationListener();
        locListener.setUbicacionActivity(this);
        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0 ,(LocationListener)locListener);
       // locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) locListener);


        //ELEMENTOS DE LA INTERFAZ
        tvUbicacion = (TextView) findViewById(R.id.tvUbi);
        tvDireccion = (TextView) findViewById(R.id.tvDireccion);
        logo = (ImageView) findViewById(R.id.logo);
        vistaMapa = (MapView) findViewById(R.id.miMapa);
        Button btnActualizar=(Button)findViewById(R.id.btnActualizar);


        vistaMapa.onCreate(savedInstanceState);
        //inicializar google map
        googleMapa = vistaMapa.getMap();
        //tipo de mapa que queramos mostrar
        googleMapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //poner a disponible la ubicacion para poder utilizar
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMapa.setMyLocationEnabled(true);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    public void onClick(View v) {
        if(v.getId()==R.id.btnActualizar) {
           // setLocation();
        }
    }
    public void setLocation(Location location){
        //Obtener la direcci—n de la calle a partir de la latitud y la longitud
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    tvDireccion.setText("Mi direcci—n es: \n" + address.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getLocation(Location location) {
        //OBTENER LA DIRECCION DE LA CALLE A PARTIR DE LA LATITUD Y LA LONGITUD
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address direccion = list.get(0);
                    tvDireccion.setText("Mi dirección es: \n" + String.valueOf(direccion.getAddressLine(0)));
                    //String ubicacion="Mi ubicación actual es: "+"\n Latitud= "+location.getLatitude()+"\n Longitud= "+location.getLongitude();
                    String ubicacion = "Mi ubicación actual es: " + "\n Latitud: " + String.valueOf(location.getLatitude()) + "\n Longitud: "
                            + String.valueOf(location.getLongitude());
                    tvUbicacion.setText(ubicacion);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Ubicacion Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.app.mariabeas.keepsafe/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Ubicacion Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.app.mariabeas.keepsafe/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
