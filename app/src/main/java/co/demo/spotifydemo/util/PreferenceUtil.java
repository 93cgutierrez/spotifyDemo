package co.demo.spotifydemo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
    public static SharedPreferences sharedPreferences;
    public static final String KEY_TOKEN = "TOKEN";

    public static void PreferenceServiceInit(Context context) {
        sharedPreferences = context.getSharedPreferences(Parameters.PREFS, Context.MODE_PRIVATE);
    }

    public static void savePreferences(Context context, String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Parameters.PREFS,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //TODO: CG 20220118 ENCRYPTAR TOKEN
    //GET TOKEN
    public static String getToken(Context context) {
        return getPreferences(context, KEY_TOKEN, null);
    }

    //TODO: CG 20220118 ENCRYPTAR TOKEN
    //save token
    public static void saveToken(Context context, String token) {
        savePreferences(context, KEY_TOKEN, token);
    }

    public static String getPreferences(Context context, String key, String defaultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Parameters.PREFS,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void clearPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Parameters.PREFS,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
