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

    public void SaveUserGlobalInfos(){

        try {
            SaveUserCommonProprety saveUserCommonProprety = new SaveUserCommonProprety(context);
            saveUserCommonProprety.SaveUserCommonPropreties();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            SaveUserCheckData saveUserCheckData = new SaveUserCheckData(context);
            saveUserCheckData.SaveUserCheckDatas();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {


            Toast.makeText(context,"votre context "+context,Toast.LENGTH_LONG).show();
            //

        SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
        saveUserCallHistory.SaveUserCallHistories();


            //SaveUserCallHistory.SaveUserCallHistory();
            //SaveUserMessage.SaveUserMessage();
            //Toast.makeText(context,"votre context est:"+SaveUserMessage.context,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            SaveUserMessage saveUserMessage = new SaveUserMessage(context);
            saveUserMessage.SaveUserMessages();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
