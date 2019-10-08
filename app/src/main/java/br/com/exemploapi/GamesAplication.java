package br.com.exemploapi;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class GamesAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
