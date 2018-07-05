package com.example.androiddatachecker;

import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class SaveUserInfos extends AppCompatActivity {

    protected static ContextWrapper context;

    public SaveUserInfos(ContextWrapper context) {
        this.context = context;
    }



    public void SaveUserGlobalInfos() {

        SaveUserCheckData saveUserCheckData = new SaveUserCheckData(context);
       // SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(this);

        try {
            saveUserCheckData.SaveUserCheckDatas();
            //saveUserCallHistory.SaveUserCallHistories();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

