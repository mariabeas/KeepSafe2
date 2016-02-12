package com.app.mariabeas.keepsafe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        vistaMapa = (MapView) findViewById(R.id.miMapa);

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

   /* public void onMapReady(GoogleMap googleMap) {
        googleMapa=googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
    public void onLocationChanged(Location loc){
        //ESTE METODO SE EJECUTA CADA VEZ  QUE EL GPS RECIBE NUEVAS COORDENADAS
        loc.getLatitude();
        loc.getAltitude();
        String text="Mi ubicación actual es: "+"\n Lat= "+ loc.getLatitude() + "\n Alt= "+ loc.getAltitude();

    }
    /*
    * @Override
	public void onLocationChanged(Location loc) {
		// Este mŽtodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
		// debido a la detecci—n de un cambio de ubicacion
		loc.getLatitude();
		loc.getLongitude();
		String Text = "Mi ubicaci—n actual es: " + "\n Lat = "
				+ loc.getLatitude() + "\n Long = " + loc.getLongitude();
		messageTextView.setText(Text);
		this.mainActivity.setLocation(loc);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// Este mŽtodo se ejecuta cuando el GPS es desactivado
		messageTextView.setText("GPS Desactivado");
	}

	@Override
	public void onProviderEnabled(String provider) {
		// Este mŽtodo se ejecuta cuando el GPS es activado
		messageTextView.setText("GPS Activado");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// Este mŽtodo se ejecuta cada vez que se detecta un cambio en el
		// status del proveedor de localizaci—n (GPS)
		// Los diferentes Status son:
		// OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
		// TEMPORARILY_UNAVAILABLE -> Temp˜ralmente no disponible pero se
		// espera que este disponible en breve
		// AVAILABLE -> Disponible
	}*/
}
