package ru.l240.testtaskintechglobal.retrofit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.l240.testtaskintechglobal.models.Melody;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public interface IntechService {

    /*@GET("/places/coords_to_places_ru.json")
    Call<List<Melody>> melodies(@Query("coords") String gps);*/
    @GET("/public/marketplaces/1/tags/4/melodies")
    Call<Melodies> melodies(@Query("limit") String limit, @Query("from") String from);

}
