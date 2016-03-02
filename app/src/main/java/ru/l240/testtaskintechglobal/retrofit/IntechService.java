package ru.l240.testtaskintechglobal.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.l240.testtaskintechglobal.models.Melodies;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public interface IntechService {

    /*@GET("/places/coords_to_places_ru.json")
    Call<List<Melody>> melodies(@Query("coords") String gps);*/
    @GET("/public/marketplaces/1/tags/4/melodies")
    Call<Melodies> melodies(@Query("limit") Integer limit, @Query("from") Integer from);

}
