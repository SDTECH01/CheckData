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
        SaveUserCommonProprety saveUserCommonProprety =new SaveUserCommonProprety(context);
        try {
            saveUserCommonProprety.SaveUserCommonPropreties();

        }catch (Exception e) {
            e.printStackTrace();

        }

        try {
            saveUserCommonProprety.SaveUserCommonPropreties();
            saveUserCallHistory.SaveUserCallHistories();

        } catch (Exception e) {
            e.printStackTrace();

        }
        finally {
            saveUserMessage.SaveUserMessages();
        }
        /*try {
            saveUserMessage.SaveUserMessages();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}

