package com.example.androiddatachecker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


    public class SaveUserCallHistory extends AppCompatActivity {

        public static final int INCOMING = CallLog.Calls.INCOMING_TYPE;
        public static final int OUTGOING = CallLog.Calls.OUTGOING_TYPE;
        public static final int MISSED = CallLog.Calls.MISSED_TYPE;
        public static final int TOTAL = 579;
        //public static final int NUMERO_USER = TelephonyManager.
        private static String[] requiredPermissions = {Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS};

        private static ContextWrapper context;
        //public Context context = (Application)getBaseContext();

        ///////////////////le context de l'application///////////////////
        public SaveUserCallHistory(ContextWrapper context) {
                this.context = context;
            }
            //public Context context = (Application)getBaseContext();



            protected void SaveUserCallHistories () {



                SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);

                if (ActivityCompat.checkSelfPermission((Activity) context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.READ_CALL_LOG}, 10);
                } else {
                    //if (requiredPermissions=="Manifest.permission.READ_CALL_LOG" &&requiredPermissions=="android.permission.READ_CONTACTS"){
                    Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
                    int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                    int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
                    int datAp = cursor.getColumnIndex(CallLog.Calls.DATE);
                    int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
                    Toast.makeText(context,"on est bien dans SAVE call history",Toast.LENGTH_LONG).show();
                    while (cursor.moveToNext()) {

                        Date date = new Date();
                        String formatted = new SimpleDateFormat("dd/MM/yyyy").format(date);

                        Date dat = new Date(cursor.getLong(datAp));
                        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(dat);
                        Date heur = new Date(cursor.getLong(datAp));
                        String formattedHeure = new SimpleDateFormat("HH:mm").format(heur);
                        InsertData(cursor.getColumnIndex(CallLog.Calls.NUMBER),
                                cursor.getColumnIndex(CallLog.Calls.NUMBER),
                                cursor.getString(duration),
                                cursor.getString(number),
                                findNameByNumber(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))),
                                AppelType(type),
                                formattedDate,
                                formattedHeure,
                                formatted,
                                "acitf");
                    }

                    cursor.close();


                }
                /*SaveUserMessage saveUserMessage = new SaveUserMessage(context);
                saveUserMessage.SaveUserMessages();*/
            }

        private String AppelType(int type) {
            switch (type) {
                case INCOMING:
                    return "Appel reçu";
                //break;
                case OUTGOING:
                    return "Appel emis";
                //break;
                case MISSED:
                    return "Appel manqué";
                default:
                    return "Inconnu";
                //break;
            }
        }

            public int getOutgoingDuration () {
                int sum = 0;
                if (ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") == PackageManager.PERMISSION_GRANTED) {

                    Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                            CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE, null, null);

                    int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

                    while (cursor.moveToNext()) {
                        String callDuration = cursor.getString(duration);
                        sum += Integer.parseInt(callDuration);
                    }

                    cursor.close();
                }
                return sum;
            }

            public int getIncomingDuration() {
                int sum = 0;
                if (ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") == PackageManager.PERMISSION_GRANTED) {

                    Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                            CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE, null, null);

                    int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

                    while (cursor.moveToNext()) {
                        String callDuration = cursor.getString(duration);
                        sum += Integer.parseInt(callDuration);
                    }

                    cursor.close();

                }
                return sum;
            }

            public int getTotalDuration () {
                int sum = 0;
                if (ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") == PackageManager.PERMISSION_GRANTED) {


                    Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);

                    int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

                    while (cursor.moveToNext()) {
                        String callDuration = cursor.getString(duration);
                        sum += Integer.parseInt(callDuration);
                    }

                    cursor.close();
                }
                return sum;
            }

            protected void InsertData ( final int id_user, final int id_call, final String call_duration,
            final String correspondant_number, final String correspondant_name,
            final String type_call, final String call_dat,
            final String call_heure, final String dat_ins_call_history, final String etat){

                class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
                    @Override
                    protected String doInBackground(String... params) {

                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("id_user", Integer.toString(id_user)));
                        nameValuePairs.add(new BasicNameValuePair("id_call", Integer.toString(id_call)));
                        nameValuePairs.add(new BasicNameValuePair("call_duration", call_duration));
                        nameValuePairs.add(new BasicNameValuePair("correspondant_number", correspondant_number));
                        nameValuePairs.add(new BasicNameValuePair("correspondant_name", correspondant_name));
                        nameValuePairs.add(new BasicNameValuePair("type_call", type_call));
                        nameValuePairs.add(new BasicNameValuePair("call_dat", call_dat));
                        nameValuePairs.add(new BasicNameValuePair("call_heure", call_heure));
                        nameValuePairs.add(new BasicNameValuePair("dat_ins_call_history", dat_ins_call_history));
                        nameValuePairs.add(new BasicNameValuePair("etat", etat));

                        try {
                            HttpClient httpClient = new DefaultHttpClient();

                            HttpPost httpPost = new HttpPost("http://smart-data-tech.com/dev/API/v1/saveUserCallHistory/");
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

                sendPostReqAsyncTask.execute(Integer.toString(id_user), Integer.toString(id_call), call_duration, correspondant_number,
                        correspondant_name, type_call, call_dat,
                        call_heure, dat_ins_call_history,
                        etat);
            }

            private String findNameByNumber(final String phoneNumber){
                ContentResolver cr = context.getContentResolver();

                Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

                Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null);
                if (cursor == null) {
                    return null;
                }

                String contactName = null;

                if (cursor.moveToFirst()) {
                    contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                }

                if (!cursor.isClosed()) {
                    cursor.close();
                }

                return (contactName == null) ? phoneNumber : contactName;
            }


        }