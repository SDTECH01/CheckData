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
        SaveUserMessagesTimer saveUserMessagesTimer= new SaveUserMessagesTimer();
        Timer timer = new Timer();
        try {
            timer.schedule(saveUserMessagesTimer,1000,12000);

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
            SaveUserCommonProprety saveUserCommonProprety =new SaveUserCommonProprety(context);
            SaveUserMessage saveUserMessage = new SaveUserMessage(context);
            SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
            try {
                Toast.makeText(context,"on est dans le timer",Toast.LENGTH_LONG).show();
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
}

