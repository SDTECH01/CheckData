package com.example.androiddatachecker;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class UserInfos extends Application {
    public static Context context;
    public void SaveUserInfos(){
        try {
            //SaveUserCallHistory.SaveUserCallHistory();
            SaveUserMessage.SaveUserMessage();
            //Toast.makeText(context,"votre context est:"+SaveUserMessage.context,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void Envoi(){
        SaveUserCallHistory.SaveUserCallHistory();
    }
}
