package com.example.androiddatachecker;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SaveUserCommonProprety extends ActivityCompat implements LocationListener {
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 0; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;
    protected String  mprovider;
    ////Le context à utiliser
    private static ContextWrapper context;
    //////////////////heure///////////////////
    SimpleDateFormat heuref = new SimpleDateFormat("HH:mm");
    String heureFormatter = heuref.format(new Date());
    ////////////////date/////////////////////
    SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    String dateFormatter = datef.format(new Date());

    ////Le constructeur de la classe, il doit prendre la context puis le retourner
    public SaveUserCommonProprety(ContextWrapper context) {
        this.context = context;
    }

    public void SaveUserCommonPropreties(){

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, (LocationListener) this);


            if (location != null) {
                onLocationChanged(location);
            }

        }

    }





    private void InsertData ( final int id_user, final int level_battery, final double longitude, final double latitude, final String dat_ins_proprety,
                              final String heure_proprety,final String liberty1,final String liberty2,
                              final String liberty3,final String liberty4, final String liberty5,final String liberty6,final String liberty7,
                              final String last_update,final String etat){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                /*String NameHolder = name;
                String EmailHolder = email;*/

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id_user",Integer.toString(id_user)));
                nameValuePairs.add(new BasicNameValuePair("level_battery",Integer.toString(level_battery)));
                nameValuePairs.add(new BasicNameValuePair("longitude", Double.toString(longitude)));
                nameValuePairs.add(new BasicNameValuePair("latitude", Double.toString(latitude)));
                nameValuePairs.add(new BasicNameValuePair("dat_ins_proprety", dat_ins_proprety));
                nameValuePairs.add(new BasicNameValuePair("heure_proprety", heure_proprety));
                nameValuePairs.add(new BasicNameValuePair("liberty1", liberty1));
                nameValuePairs.add(new BasicNameValuePair("liberty2", liberty2));
                nameValuePairs.add(new BasicNameValuePair("liberty3", liberty3));
                nameValuePairs.add(new BasicNameValuePair("liberty4", liberty4));
                nameValuePairs.add(new BasicNameValuePair("liberty5", liberty5));
                nameValuePairs.add(new BasicNameValuePair("liberty6", liberty6));
                nameValuePairs.add(new BasicNameValuePair("liberty7", liberty7));
                nameValuePairs.add(new BasicNameValuePair("last_update", last_update));
                nameValuePairs.add(new BasicNameValuePair("etat", etat));


                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveUserCommonProprety/");

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                //Toast.makeText(MainActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(Integer.toString(id_user), Integer.toString(level_battery), Double.toString(longitude), Double.toString(latitude),
                dat_ins_proprety, heure_proprety, liberty1,liberty2,liberty3,liberty4,liberty5,liberty6,liberty7,
                last_update, etat);
    }

    public int getBatteryPercentage() {

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);

        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

        float batteryPct = level / (float) scale;

        return (int) (batteryPct * 100);
    }



    @Override
    public void onLocationChanged(Location location) {
        Log.e("votre longitude","est "+location.getLongitude());
        Log.e("votre latitude","est "+location.getLatitude());
        Toast.makeText(context,"Nous sommes dans commonsProperties",Toast.LENGTH_LONG).show();
        InsertData(1,getBatteryPercentage(),location.getLongitude(),location.getLatitude(),dateFormatter,heureFormatter,"liberty1","liberty2","liberty3",
                "liberty4","liberty5","liberty6","liberty7",dateFormatter,"actif");

        SaveUserPhoneNumber saveUserPhoneNumber = new SaveUserPhoneNumber(context);
        saveUserPhoneNumber.SaveUserPhoneNumbers();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
