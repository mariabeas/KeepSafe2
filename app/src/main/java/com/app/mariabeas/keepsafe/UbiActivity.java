package com.app.mariabeas.keepsafe;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;

import static java.lang.String.*;

/**
 * Created by MariaBeas on 7/7/16.
 */
public class UbiActivity extends AppCompatActivity implements LocationListener {
    ImageView logo;
    GoogleMap googleMapa;
    MapView vistaMapa;
    TextView tvUbi;
    TextView tvDireccion;
    Location location;
    LocationListener locationListener;
    Context context=this;
    //private Context context;
    LocationManager locationManager;
    String proveedor;
    private boolean networkOn;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client2;

    public UbiActivity(Context context) {
        this.context = context;
        locationManager= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        proveedor=locationManager.NETWORK_PROVIDER; //PROBAR CON GPS_PROVIDER
        networkOn=locationManager.isProviderEnabled(proveedor);
        locationManager.requestLocationUpdates(proveedor, 1000, 1, this);
        getLocation();

    }
    public UbiActivity(){


    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubi);
        getLocation();

        //Declaramos el toolbar del menu datos
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_datos);
        setSupportActionBar(toolbar);
        //para poner el boton de volver al menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Obtener ubicación");

        //ELEMENTOS DE LA INTERFAZ
        tvUbi = (TextView) findViewById(R.id.tvUbi);

        tvDireccion = (TextView) findViewById(R.id.tvDireccion);
        logo = (ImageView) findViewById(R.id.logo);
        vistaMapa = (MapView) findViewById(R.id.miMapa);
        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);
        vistaMapa.onCreate(savedInstanceState);
        //inicializar google map
        googleMapa = vistaMapa.getMap();
        //tipo de mapa que queramos mostrar
        googleMapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        setUpMapIfNeeded();

        MiListener listener = new MiListener();
        btnActualizar.setOnClickListener(listener);

       locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            }
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        googleMapa.setMyLocationEnabled(true);
        MostrarLocalizacion();


       /* locationListener=new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
               MostrarLocalizacion();
                getLocation();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };*/

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                cerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void cerrarSesion(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // Titulo del AlertDialog
        alertDialogBuilder.setTitle("¿Seguro que desea cerrar sesión?");

        alertDialogBuilder
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentLogin =new Intent(UbiActivity.this,MainActivity.class);
                        startActivity(intentLogin);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    @Override
        protected void onPause(){
            super.onPause();
        vistaMapa.onPause();
        }

    @Override
        protected void onDestroy(){
            super.onDestroy();
            vistaMapa.onDestroy();
        }

    @Override
        protected void onResume () {
            super.onResume();
            vistaMapa.onResume();
        }



    public void MostrarLocalizacion() {
        location.getLatitude();
        location.setLatitude(29239);
        location.getLongitude();

        //String ubicacion="Mi ubicación actual es: "+"\n Latitud= "+loc.getLatitude()+"\n Longitud= "+loc.getLongitude();
        String ubicacion = "Mi ubicación actual es: " + "\n Latitud: " + valueOf(location.getLatitude() + Math.random() * 10) + "\n Longitud: "
                + valueOf(location.getLongitude());
        Toast.makeText(getApplicationContext(), "Ubicación actualizada", Toast.LENGTH_SHORT).show();
        tvDireccion.setText(ubicacion);
        Toast.makeText(context,ubicacion, Toast.LENGTH_LONG).show();
        this.tvDireccion.setText(valueOf(location));

    }
    private void getLocation(){
        if(networkOn){
            tvUbi.setText((int) location.getLatitude());
            Location location=locationManager.getLastKnownLocation(proveedor);
            if(location!=null){
                tvUbi.setText((int) location.getLatitude());
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("Altitud: ").append(location.getAltitude())
                        .append("Latitud: ").append(location.getLatitude())
                        .append("Longitud: ").append(location.getLongitude());
                Toast.makeText(context,stringBuilder.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    /*public void setLocation(Location location) {
        this.location = location;
    }*/

    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        getLocation();

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private class MiListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnActualizar) {
                tvDireccion.refreshDrawableState();
                getLocation();

            }
        }
    }

    private void setUpMapIfNeeded() {
// Configuramos el objeto GoogleMaps con valores iniciales.

        if (googleMapa == null) {
            //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
            googleMapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
            if (googleMapa != null) {
                // El objeto GoogleMap ha sido referenciado correctamente
                //ahora podemos manipular sus propiedades

                //Seteamos el tipo de mapa
                googleMapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                //Activamos la capa o layer MyLocation
                googleMapa.setMyLocationEnabled(true);
            }
        }
    }


}