package ru.l240.testtaskintechglobal.retrofit.response;

import android.content.Context;

import java.util.List;

import ru.l240.testtaskintechglobal.models.Melody;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public class IntechResponse extends Response {

    @Override
    public void save(Context context) {
        List<Melody> melodies = getTypedAnswer();
//        List<Airport> airports = getTypedAnswer();
//        if (airports != null) {
//            AirportsTable.save(context, airports);
//        }
    }
}
