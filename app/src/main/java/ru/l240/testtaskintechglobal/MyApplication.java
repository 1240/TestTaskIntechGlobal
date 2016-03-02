package ru.l240.testtaskintechglobal;

import android.app.Application;

import io.realm.Realm;
import ru.l240.testtaskintechglobal.realm.RealmHelper;

/**
 * Created by 1240 on 02.03.2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmHelper.clear(Realm.getInstance(this));

    }
}
