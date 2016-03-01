package ru.l240.testtaskintechglobal.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public class Melodies extends RealmObject {

    private RealmList<Melody> melodies;

    public RealmList<Melody> getMelodies() {
        return melodies;
    }

    public void setMelodies(RealmList<Melody> melodies) {
        this.melodies = melodies;
    }
}
