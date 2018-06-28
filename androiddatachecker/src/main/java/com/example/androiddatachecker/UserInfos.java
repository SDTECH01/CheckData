package com.example.androiddatachecker;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.widget.Toast;

public class UserInfos extends Application {
    public ContextWrapper context;
    SaveUserCallHistory saveUserCallHistory = new SaveUserCallHistory(context);
    SaveUserMessage saveUserMessage = new SaveUserMessage(context);

    public void SaveUserInfos(){
        try {
            Toast.makeText(context,"votre context "+context,Toast.LENGTH_LONG).show();
            saveUserMessage.SaveUserMessages();
            //saveUserCallHistory.SaveUserCallHistories();

            //SaveUserCallHistory.SaveUserCallHistory();
            //SaveUserMessage.SaveUserMessage();
            //Toast.makeText(context,"votre context est:"+SaveUserMessage.context,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Envoi(){

    }

}
