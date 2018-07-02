package com.example.androiddatachecker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SaveUserInfos extends AppCompatActivity {

    private static ContextWrapper context;

    public SaveUserInfos(ContextWrapper context) {
        this.context = context;
    }
    SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
    SaveUserMessage saveUserMessage = new SaveUserMessage(context);
    public void SaveUserGlobalInfos(){
        try {

            saveUserMessage.SaveUserMessages();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
        saveUserCallHistory.SaveUserCallHistories();

            Toast.makeText(context,"votre context "+context,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }




    }

}
