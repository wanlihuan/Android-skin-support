package skin.support.utils;

import android.content.Context;
import android.content.SharedPreferences;

import skin.support.SkinCompatManager;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinPreference {
    private static final String FILE_NAME = "meta-data";

    private static final String KEY_SKIN_NAME = "skin-name";
    private static final String KEY_SKIN_STRATEGY = "skin-strategy";
    private static final String KEY_SKIN_COLORS = "skin-colors";
    private static SkinPreference sInstance;
    private final Context mApp;
    private final SharedPreferences mPref;
    private final SharedPreferences.Editor mEditor;

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinPreference.class) {
                if (sInstance == null) {
                    sInstance = new SkinPreference(context.getApplicationContext());
                }
            }
        }
    }

    public static SkinPreference getInstance() {
        return sInstance;
    }

    private SkinPreference(Context applicationContext) {
        mApp = applicationContext;
        mPref = mApp.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public SkinPreference setSkinName(String skinName) {
        mEditor.putString(KEY_SKIN_NAME, skinName);
        return this;
    }

    public String getSkinName() {
        return mPref.getString(KEY_SKIN_NAME, "");
    }

    public SkinPreference setSkinStrategy(int strategy) {
        mEditor.putInt(KEY_SKIN_STRATEGY, strategy);
        return this;
    }

    public int getSkinStrategy() {
        return mPref.getInt(KEY_SKIN_STRATEGY, SkinCompatManager.SKIN_LOADER_STRATEGY_NONE);
    }

    public SkinPreference setColors(String colors) {
        mEditor.putString(KEY_SKIN_COLORS, colors);
        return this;
    }

    public String getColors() {
        return mPref.getString(KEY_SKIN_COLORS, "");
    }

    public void commitEditor() {
        mEditor.apply();
    }
}
