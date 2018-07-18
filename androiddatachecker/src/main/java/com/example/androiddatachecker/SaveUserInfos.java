package com.example.androiddatachecker;

import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;



public class SaveUserInfos extends AppCompatActivity {

    protected static ContextWrapper context;

    protected String uuid_user;
    public SaveUserInfos(ContextWrapper context, String uuid_user) {
        this.context = context;
        this.uuid_user = uuid_user;
    }



    public void SaveUserGlobalInfos() {

        SaveUserCheckData saveUserCheckData = new SaveUserCheckData(context,uuid_user);


        try {
            saveUserCheckData.SaveUserCheckDatas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

