package com.example.androiddatachecker;


import android.content.ContentResolver;

import android.content.ContextWrapper;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
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

import java.io.BufferedReader;
import java.io.IOException;
//<<<<<<< HEAD
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

public class SaveUserMessage extends AppCompatActivity {

    //private static MainActivity inst;
    //données de senderId
    String result;
    InputStream isr;
    //private
    private static ContextWrapper context;
    protected String uuid_user;
    //ContextWrapper context = new ContextWrapper(contextc);
    //private static ContentResolver contentResolver;

    SimpleDateFormat heuref = new SimpleDateFormat("HH:mm");
    String heureFormatter = heuref.format(new Date());
    ////////////////date/////////////////////
    SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    String dateFormatter = datef.format(new Date());
    ///////////////////le context de l'application///////////////////
    public SaveUserMessage(ContextWrapper context,String uuid_user) {
        this.context = context;
        this.uuid_user = uuid_user;
    }

    protected void SaveUserMessages() {
        //if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            /*if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {

                return;
            }*/
        AsyncTask  getDatas=new getData().execute();
        Object resultTask=null;
        try {
            resultTask = getDatas.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String where = "date >='" + Where() + "' AND adress IN("+resultTask+")";
        ContentResolver contentResolver = context.getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/"), null, where, null, null);
        int idsms = smsInboxCursor.getColumnIndex("_id");
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        int dat = smsInboxCursor.getColumnIndex("date_sent");
        int typesms = smsInboxCursor.getColumnIndex("type");

        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        do {
            //Integer.toString(smsInboxCursor.getColumnIndex("address")),
            Date date = new Date(smsInboxCursor.getLong(dat));
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            Date heur = new Date(smsInboxCursor.getLong(dat));
            String formattedHeure = new SimpleDateFormat("HH:mm").format(heur);

            InsertData(uuid_user,
                    Integer.parseInt(smsInboxCursor.getString(idsms)),
                    smsInboxCursor.getString(indexBody),
                    TypeSms(smsInboxCursor.getString(typesms)),
                    formattedDate,
                    formattedHeure,
                    smsInboxCursor.getString(indexAddress),
                    smsInboxCursor.getString(indexAddress),
                    dateFormatter,
                    heureFormatter,
                    "actif");
        } while (smsInboxCursor.moveToNext());
        smsInboxCursor.close();

        SaveUserCommonProprety saveUserCommonProprety = new SaveUserCommonProprety(context,uuid_user);
        saveUserCommonProprety.SaveUserCommonPropreties();

    }

    protected void InsertData ( final String id_user, final int id_message,
                                final String contenu_message, final String type_message,
                                final String dat_message, final String heure_message, final String correspondant_number,
                                final String correspondant_name, final String dat_ins_message,
                                final String heure_ins_message,
                                final String etat){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                /*String NameHolder = name;
                String EmailHolder = email;*/

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

               /*nameValuePairs.add(new BasicNameValuePair("name",Integer.toString(id_user)));
                nameValuePairs.add(new BasicNameValuePair("email",Integer.toString(id_message)));*/
                nameValuePairs.add(new BasicNameValuePair("id_user", id_user));
                nameValuePairs.add(new BasicNameValuePair("id_message", Integer.toString(id_message)));
                nameValuePairs.add(new BasicNameValuePair("contenu_message", contenu_message));
                nameValuePairs.add(new BasicNameValuePair("type_message", type_message));
                nameValuePairs.add(new BasicNameValuePair("dat_message", dat_message));
                nameValuePairs.add(new BasicNameValuePair("heure_message", heure_message));
                nameValuePairs.add(new BasicNameValuePair("correspondant_number", correspondant_number));
                nameValuePairs.add(new BasicNameValuePair("correspondant_name", correspondant_name));
                nameValuePairs.add(new BasicNameValuePair("dat_ins_message", dat_ins_message));
                nameValuePairs.add(new BasicNameValuePair("heure_ins_message", heure_ins_message));
                nameValuePairs.add(new BasicNameValuePair("etat", etat));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveUserMessage/");
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

        sendPostReqAsyncTask.execute(id_user, Integer.toString(id_message), contenu_message, type_message,
                dat_message, heure_message, correspondant_number,
                correspondant_name, dat_ins_message, heure_ins_message,
                etat);
    }

    private String TypeSms(String type) {
        String typsms=null;
        switch (type) {
            case "1":
                typsms="Inbox";
            break;
            case "2":
                typsms="Sent";
            break;
            case "3":
                typsms="Draft";
                break;
            case "4":
                typsms="Outbox";
                break;
            case "5":
                typsms= "Failed";
            break;
            case "6":
                typsms="Queued";
            break;
            default:
                typsms= "All";
            break;
        }
        return typsms;
    }
    private long Where() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result = cal.getTime();

        String formatString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(result);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date formatTodate = null;
        try {
            formatTodate = sdf.parse(formatString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = formatTodate.getTime();
        return millis;
    }

    class getData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            result = "";
            isr = null;
            try {

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://smart-data-tech.com/dev/API/v1/getSenderId/"); //YOUR PHP SCRIPT ADDRESS
                HttpResponse response = httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();

                isr = entity.getContent();
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());

            }


            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "UTF-8"), 8);

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                isr.close();

                result = sb.toString().trim();

            } catch (Exception e) {
                Log.e("log_tag", "Error  converting result " + e.toString());
            }


            try {

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String results) {


        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
