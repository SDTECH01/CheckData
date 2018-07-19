package com.example.androiddatachecker;

import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class SaveUserInfos extends AppCompatActivity {

    protected static ContextWrapper context;

    protected String uuid_user;
    public SaveUserInfos(ContextWrapper context, String uuid_user) {
        this.context = context;
        this.uuid_user = uuid_user;
    }



    public void SaveUserGlobalInfos() {
    String fatigue = "woro";
        SaveUserCheckData saveUserCheckData = new SaveUserCheckData(context,uuid_user);


        try {
            saveUserCheckData.SaveUserCheckDatas();
            Toast.makeText(context,"eehhhh menu1"+fatigue,Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

