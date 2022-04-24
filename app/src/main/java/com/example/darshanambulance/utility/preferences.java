package com.example.darshanambulance.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class preferences {
    private static final String DATA_LOGIN = "user_login";
    private static final String DATA_AS = "as";
    private static final String DATA_EMAIL = "email";
    private static final String DATA_PHONE = "phone";
    private static final String DATA_FULLNAME = "fullname";
    private static final String DATA_USERID = "userid";



    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setDataAs(Context context, String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_AS,data);
        editor.apply();
    }

    public static String getDataAs(Context context){
        return getSharedPreferences(context).getString(DATA_AS,"");
    }

    public static String getDataEmail(Context context) {
        return getSharedPreferences(context).getString(DATA_EMAIL,"");
    }

    public static String getDataPhone(Context context) {
        return getSharedPreferences(context).getString(DATA_PHONE,"");
    }

    public static String getDataFullname(Context context) {
        return getSharedPreferences(context).getString(DATA_FULLNAME,"");
    }

    public static String getDataUserid(Context context) {
        return getSharedPreferences(context).getString(DATA_USERID,"");
    }

    public static void setDataEmail(Context context, String email){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_EMAIL,email);
        editor.apply();
    }

    public static void setDataUserid(Context context, String userid){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_USERID,userid);
        editor.apply();
    }


    public static void setDataPhone(Context context, String phone){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_PHONE,phone);
        editor.apply();
    }

    public static void setDataFullname(Context context, String fullname){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_FULLNAME,fullname);
        editor.apply();
    }

    public static void setDataLogin(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(DATA_LOGIN,status);
        editor.apply();
    }

    public static boolean getDataLogin(Context context){
        return getSharedPreferences(context).getBoolean(DATA_LOGIN,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(DATA_AS);
        editor.remove(DATA_EMAIL);
        editor.remove(DATA_PHONE);
        editor.remove(DATA_LOGIN);
        editor.apply();
    }
}
