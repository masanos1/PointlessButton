package com.rodo.pointlessbutton;

import android.content.Context;
import android.content.SharedPreferences;

public class SoundPref {
    SharedPreferences soundPref;

    public SoundPref(Context context) {

        soundPref = context.getSharedPreferences("iamsupposedtogiveanamebutinsteadimdoingthis", Context.MODE_PRIVATE);

    }

    // this will save the pref: true or false
    public void setButtonSound(Boolean state) {

        SharedPreferences.Editor editor = soundPref.edit();
        editor.putBoolean("SoundMode", state);
        editor.apply();
    }

    //this method will load the pref
    public boolean ButtonSound() {
        return soundPref.getBoolean("SoundMode", true);
    }
}
