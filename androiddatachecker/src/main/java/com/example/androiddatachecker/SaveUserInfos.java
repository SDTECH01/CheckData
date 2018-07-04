package com.example.androiddatachecker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class SaveUserInfos extends AppCompatActivity {

    protected static ContextWrapper context;

    public SaveUserInfos(ContextWrapper context) {
        this.context = context;
    }



    public void SaveUserGlobalInfos() {

        //SaveUserCheckData saveUserCheckData = new SaveUserCheckData(context); celui là
        /*SaveUserCommonProprety saveUserCommonProprety = new SaveUserCommonProprety(context);
        SaveUserMessage saveUserMessage = new SaveUserMessage(context);*/
        SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
        /*SaveUserPhoneNumber saveUserPhoneNumber = new SaveUserPhoneNumber(context);*/
        try {
            Toast.makeText(context, "on est dans le timer", Toast.LENGTH_LONG).show();
            /*saveUserCallHistory.SaveUserCallHistories();
            Thread.sleep(500);
            saveUserMessage.SaveUserMessages();
            Thread.sleep(500);*/
            saveUserCallHistory.SaveUserCallHistories();
            //saveUserPhoneNumber.SaveUserPhoneNumbers();*/
            //saveUserCheckData.SaveUserCheckDatas(); celui ci
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*class SaveUserMessagesTimer extends TimerTask {
        runOnUiThread(new Runnable() {
            @Override
            public void run () {
                SaveUserCommonProprety saveUserCommonProprety = new SaveUserCommonProprety(context);
                SaveUserMessage saveUserMessage = new SaveUserMessage(context);
                SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
                try {
                    Toast.makeText(context, "on est dans le timer", Toast.LENGTH_LONG).show();
                    Thread.sleep(1000);
                    saveUserCallHistory.SaveUserCallHistories();

                    Thread.sleep(2000);
                    saveUserMessage.SaveUserMessages();
                    Thread.sleep(2000);
                    saveUserCommonProprety.SaveUserCommonPropreties();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}

