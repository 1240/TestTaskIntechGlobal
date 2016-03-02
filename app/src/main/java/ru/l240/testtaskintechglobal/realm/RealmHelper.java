package ru.l240.testtaskintechglobal.realm;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import ru.l240.testtaskintechglobal.models.Melody;

/**
 * @author Alexander Popov created on 02.03.2016.
 */
public class RealmHelper {

    public static void clear(@NonNull Realm realm) {
        realm.beginTransaction();
        realm.clear(Melody.class);
        realm.commitTransaction();
    }

    public static void save(@NonNull Realm realm, List<Melody> melodyList) {
        realm.beginTransaction();
//        realm.clear(Melody.class);
        realm.copyToRealm(melodyList);
        realm.commitTransaction();
    }

    @NonNull
    public static List<Melody> getMelodies(@NonNull Realm realm) {
        return realm.allObjects(Melody.class);
    }

    @NonNull
    public static Melody getMelody(@NonNull Realm realm, @NonNull Integer id) {
        return realm.where(Melody.class)
                .equalTo("id", id)
                .findAll()
                .first();
    }

}
