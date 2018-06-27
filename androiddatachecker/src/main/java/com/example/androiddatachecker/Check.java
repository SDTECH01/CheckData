package com.example.androiddatachecker;

import android.os.AsyncTask;

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

public class Check {


    public static void InsertData( final int id_user, final int id_message, final String contenu_message, final String type_messag,
                                   final String dat_message, final String heure_message,final String correspondant_number,
                                   final String correspondant_name,final String dat_ins_message,final String heure_ins_message,
                                   final String etat) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                /*String NameHolder = name;
                String EmailHolder = email;*/

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                /*nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));*/

                /*nameValuePairs.add(new BasicNameValuePair("id_user",Integer.toString(id_user)));
                nameValuePairs.add(new BasicNameValuePair("id_message",Integer.toString(id_message)));
                nameValuePairs.add(new BasicNameValuePair("contenu_message",contenu_message));
                nameValuePairs.add(new BasicNameValuePair("type_messag",type_messag));
                nameValuePairs.add(new BasicNameValuePair("dat_message",dat_message));
                nameValuePairs.add(new BasicNameValuePair("heure_message",heure_message));
                nameValuePairs.add(new BasicNameValuePair("correspondant_number",correspondant_number));
                nameValuePairs.add(new BasicNameValuePair("correspondant_name",correspondant_name));
                nameValuePairs.add(new BasicNameValuePair("dat_ins_message",dat_ins_message));
                nameValuePairs.add(new BasicNameValuePair("heure_ins_message",heure_ins_message));
                nameValuePairs.add(new BasicNameValuePair("etat",etat));*/

                nameValuePairs.add(new BasicNameValuePair("id_user","1"));
                nameValuePairs.add(new BasicNameValuePair("id_message","254"));
                nameValuePairs.add(new BasicNameValuePair("contenu_message","pava va auc hamp"));
                nameValuePairs.add(new BasicNameValuePair("type_messag","inbox"));
                nameValuePairs.add(new BasicNameValuePair("dat_message","4521"));
                nameValuePairs.add(new BasicNameValuePair("heure_message","4782"));
                nameValuePairs.add(new BasicNameValuePair("correspondant_number","452hg"));
                nameValuePairs.add(new BasicNameValuePair("correspondant_name","khcb"));
                nameValuePairs.add(new BasicNameValuePair("dat_ins_message","45,nshj"));
                nameValuePairs.add(new BasicNameValuePair("heure_ins_message","dbjs"));
                nameValuePairs.add(new BasicNameValuePair("etat","actif"));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveUserMessage/");

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
