package com.example.androiddatachecker;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
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

public class SaveUserCheckData  extends AppCompatActivity {
    private static ContextWrapper context;

    SimpleDateFormat heuref = new SimpleDateFormat("HH:mm");
    String heureFormatter = heuref.format(new Date());
    ////////////////date/////////////////////
    SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    String dateFormatter = datef.format(new Date());


    ////Le constructeur de la classe, il doit prendre la context puis le retourner
    public SaveUserCheckData(ContextWrapper context) {
        this.context = context;
    }
   // GPSTrackers local = new GPSTrackers(context);


    public void SaveUserCheckDatas(){
        InsertData("tel1","tel2","tel3","tel4","getPhoneIMEI()",version_phone(),ModelPhone(),"duree",
                "gmail","twitter","fb",dateFormatter,heureFormatter,dateFormatter,"actif","actif");

        SaveUserCommonProprety saveUserCommonProprety = new SaveUserCommonProprety(context);
        saveUserCommonProprety.SaveUserCommonPropreties();
    }


    private String ModelPhone(){
        // TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //String manufacturer = Build.MANUFACTURER;
        String model_phone = Build.MODEL;
        return model_phone;
    }
    private String version_phone(){
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(android.os.Build.VERSION.RELEASE.substring(0, 3));
        String version = strBuild.toString();
        return version;
    }

    /*public String getPhoneIMEI() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        String Android_ID = System.getString(this.getContentResolver(), System.ANDROID_ID);

       /* TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String Imei= telephonyManager.getDeviceId();*/
        //return Imei;
   // }*/

    private void InsertData ( final String tel1, final String tel2, final String tel3, final String tel4, final String imei,
                              final String version,final String model,final String duree_activite,
                              final String gmail,final String twitter, final String fb,final String dat_ins,final String heure_ins,
                              final String last_update, final String etat,final String statut){



        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                /*String NameHolder = name;
                String EmailHolder = email;*/

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("tel1",tel1));
                nameValuePairs.add(new BasicNameValuePair("tel2",tel2));
                nameValuePairs.add(new BasicNameValuePair("tel3", tel3));
                nameValuePairs.add(new BasicNameValuePair("tel4", tel4));
                nameValuePairs.add(new BasicNameValuePair("imei", imei));
                nameValuePairs.add(new BasicNameValuePair("version", version));
                nameValuePairs.add(new BasicNameValuePair("model", model));
                nameValuePairs.add(new BasicNameValuePair("duree_activite", duree_activite));
                nameValuePairs.add(new BasicNameValuePair("gmail", gmail));
                nameValuePairs.add(new BasicNameValuePair("twitter", twitter));
                nameValuePairs.add(new BasicNameValuePair("fb", fb));
                nameValuePairs.add(new BasicNameValuePair("dat_ins", dat_ins));
                nameValuePairs.add(new BasicNameValuePair("heure_ins", heure_ins));
                nameValuePairs.add(new BasicNameValuePair("last_update", last_update));
                nameValuePairs.add(new BasicNameValuePair("etat", etat));
                nameValuePairs.add(new BasicNameValuePair("statut", statut));


                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveuser/");
                    // HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/fr/crud.php");

                    //HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/fr/crud.php");
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

        sendPostReqAsyncTask.execute(tel1, tel2, tel3, tel4, imei, version, model,duree_activite,gmail,twitter,fb,dat_ins,heure_ins,last_update,
                etat, statut);
    }
}
