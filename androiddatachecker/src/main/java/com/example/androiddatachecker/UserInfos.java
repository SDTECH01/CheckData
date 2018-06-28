package com.example.androiddatachecker;

import android.app.Application;
import android.widget.Toast;

public class UserInfos extends Application {
    public static void SaveUserInfos(){
        try {
            //SaveUserCallHistory.SaveUserCallHistory();
            SaveUserMessage.SaveUserMessage();
            Toast.makeText(SaveUserMessage.context,"votre context est:"+SaveUserMessage.context,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
