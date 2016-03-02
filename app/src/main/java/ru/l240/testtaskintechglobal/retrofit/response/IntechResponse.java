package ru.l240.testtaskintechglobal.retrofit.response;

import android.content.Context;

import io.realm.Realm;
import ru.l240.testtaskintechglobal.models.Melodies;
import ru.l240.testtaskintechglobal.realm.RealmHelper;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public class IntechResponse extends Response {

    @Override
    public void save(Context context) {
        Melodies melodies = getTypedAnswer();
        if (melodies != null) {
//            RealmHelper.save(Realm.getInstance(context), melodies.getMelodies());
        }
    }
}
