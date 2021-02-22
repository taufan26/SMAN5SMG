package app.klikgss.sman5smg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class userManagement {

    Context context;
    SharedPreferences sharedPreferences;;
    public SharedPreferences.Editor editor;

    public static final String PREF_NAME = "User_Login";
    public static final String LOGIN = "is_user_login";
    public static final String USERNAME = "username";



    public userManagement(Context context){
        this.context = context;

        sharedPreferences = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public boolean isUserLogin(){

        return sharedPreferences.getBoolean(LOGIN, false);
    }
    public void UsersessonManage(String username){
        editor.putBoolean(LOGIN, true);

        editor.putString(USERNAME,username);
    }
    public void checkLogin(){

        if (!this.isUserLogin()){
            Intent intent = new Intent(context,LoginActivity.class);
            context.startActivity(intent);
            ((MainActivity)context).finish();
        }
    }

    public void logout(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
        ((MainActivity)context).finish();
    }
}
