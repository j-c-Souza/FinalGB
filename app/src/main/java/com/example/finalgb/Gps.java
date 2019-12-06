package com.example.finalgb;


import android.content.Context;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Gps {
    private LocationRequest mLocationRequest;
    Context mContext;
    private double lng;
    private double lat;

    public Gps(Context mContext) {
        this.mContext = mContext;
    }

    public String buscaData(){
        SimpleDateFormat dateFormatbra = new SimpleDateFormat("yyyy/MM/dd");

        String dataAMD = dateFormatbra.format((data()));
        return dataAMD;
    }
    public String buscaHora(){
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");

        String dataHour = dateFormat_hora.format((data()));
        return dataHour;
    }

    private Date data(){
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        return data_atual;
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(100000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void configurarServico(){
        try {
            LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    setLat(location.getLatitude());
                    setLng(location.getLongitude());
                    atualizar(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) { }

                public void onProviderEnabled(String provider) { }

                public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locationListener);
        }catch(SecurityException ex){
            Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void atualizar(Location location)
    {
        Double latPoint = location.getLatitude();
        Double lngPoint = location.getLongitude();

        String latitude = latPoint.toString();
         Log.i("GPSLATITUDE:", latitude);
        String longitude = lngPoint.toString();
         Log.i("GPSLONGITUDE:", longitude);

        String coordenadas = latitude + "#" + longitude;

        MainActivity.fire.armazenarLocal(buscaData(), buscaHora(), latitude,longitude);

    }

    public void atualizar(){
        MainActivity.fire.armazenarLocal(buscaData(), buscaHora(), Double.toString(lat), Double.toString(lng));
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
