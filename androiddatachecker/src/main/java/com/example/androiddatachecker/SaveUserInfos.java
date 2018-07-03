package com.example.androiddatachecker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SaveUserInfos extends AppCompatActivity {

    protected ContextWrapper context;

    public SaveUserInfos(ContextWrapper context) {
        this.context = context;
    }

    public void SaveUserGlobalInfos(){
        SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);


        try {
            saveUserCallHistory.SaveUserCallHistories();
            Toast.makeText(context,"votre context "+context,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }






    }

}
