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

public class SaveUserPhoneNumber {


    private void InsertData ( final int id_user, final int id_phone_number, final String phone_number,
                              final String number_name, final String dat_ins_number,
                              final String img_number,final String type_number,final String groupe_number,
                              final String local_number,final String etat_user_phone_number, final String dat_user_phone_number){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                /*String NameHolder = name;
                String EmailHolder = email;*/

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id_user",Integer.toString(id_user)));
                nameValuePairs.add(new BasicNameValuePair("id_phone_number",Integer.toString(id_phone_number)));
                nameValuePairs.add(new BasicNameValuePair("phone_number", phone_number));
                nameValuePairs.add(new BasicNameValuePair("number_name", number_name));
                nameValuePairs.add(new BasicNameValuePair("dat_ins_number", dat_ins_number));
                nameValuePairs.add(new BasicNameValuePair("img_number", img_number));
                nameValuePairs.add(new BasicNameValuePair("type_number", type_number));
                nameValuePairs.add(new BasicNameValuePair("groupe_number", groupe_number));
                nameValuePairs.add(new BasicNameValuePair("local_number", local_number));
                nameValuePairs.add(new BasicNameValuePair("etat_user_phone_number", etat_user_phone_number));
                nameValuePairs.add(new BasicNameValuePair("dat_user_phone_number", dat_user_phone_number));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveUserCommonProprety/");
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

        sendPostReqAsyncTask.execute(Integer.toString(id_user), Integer.toString(id_phone_number), phone_number, number_name,
                dat_ins_number, img_number, type_number,groupe_number,local_number,etat_user_phone_number,dat_user_phone_number);

    }
}
