package com.example.androiddatachecker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class SaveUserInfos extends AppCompatActivity {

    protected static ContextWrapper context;

    public SaveUserInfos(ContextWrapper context) {
        this.context = context;
    }

    SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
    SaveUserMessage saveUserMessage = new SaveUserMessage(context);
    SaveUserCommonProprety saveUserCommonProprety =new SaveUserCommonProprety(context);
    SaveUserMessagesTimer saveUserMessagesTimer= new SaveUserMessagesTimer();
    Timer timer = new Timer();
    public void SaveUserGlobalInfos() {



        try {
            saveUserCommonProprety.SaveUserCommonPropreties();
            saveUserCallHistory.SaveUserCallHistories();
            timer.schedule(saveUserMessagesTimer,5000,5000);

        } catch (Exception e) {
            e.printStackTrace();

        }

        /*try {
            saveUserMessage.SaveUserMessages();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }


    class SaveUserMessagesTimer extends TimerTask {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                saveUserMessage.SaveUserMessages();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

