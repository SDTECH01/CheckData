package com.example.androiddatachecker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;


public class SaveUserInfos extends AppCompatActivity {

    protected static ContextWrapper context;

    public SaveUserInfos(ContextWrapper context) {
        this.context = context;
    }

    public void SaveUserGlobalInfos() {
        SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
        SaveUserMessage saveUserMessage = new SaveUserMessage(context);

        try {
            saveUserMessage.SaveUserMessages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            saveUserCallHistory.SaveUserCallHistories();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

