package app.klikgss.sman5smg;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

import app.klikgss.sman5smg.model.login.Data;

public class SessionManger {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID_LOGIN = "id_login";
    public static final String USERNAME = "username";
    public static final String NAMA = "nama";
    public static final String FOTO = "foto";
    public static final String ID_PEGAWAI = "id_pegawai";
    public static final String ID_SISWA = "id_siswa";

    public SessionManger (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(Data user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID_LOGIN, user.getIdLogin());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(NAMA, user.getNama());
        editor.putString(FOTO, user.getFoto());
        editor.putString(ID_PEGAWAI, user.getIdPegawai());
        editor.putString(ID_SISWA, user.getIdSiswa());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(ID_LOGIN, sharedPreferences.getString(ID_LOGIN,null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME,null));
        user.put(NAMA, sharedPreferences.getString(NAMA,null));
        user.put(FOTO, sharedPreferences.getString(FOTO,null));
        user.put(ID_PEGAWAI, sharedPreferences.getString(ID_PEGAWAI,null));
        user.put(ID_SISWA, sharedPreferences.getString(ID_SISWA,null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }



}
