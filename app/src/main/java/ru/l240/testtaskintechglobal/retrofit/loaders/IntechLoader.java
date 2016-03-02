package ru.l240.testtaskintechglobal.retrofit.loaders;

import android.content.Context;

import java.io.IOException;

import retrofit2.Call;
import ru.l240.testtaskintechglobal.models.Melodies;
import ru.l240.testtaskintechglobal.retrofit.ApiFac;
import ru.l240.testtaskintechglobal.retrofit.IntechService;
import ru.l240.testtaskintechglobal.retrofit.response.IntechResponse;
import ru.l240.testtaskintechglobal.retrofit.response.RequestResult;
import ru.l240.testtaskintechglobal.retrofit.response.Response;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public class IntechLoader extends BaseLoader {

    private final Integer from;

    public IntechLoader(Context context, Integer from) {
        super(context);
        this.from = from;
    }

    @Override
    protected Response apiCall() throws IOException {
        IntechService service = ApiFac.getMelodyService();
        Call<Melodies> call = service.melodies(20, from * 20);
        Melodies melodies = call.execute().body();
        return new IntechResponse()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(melodies);
    }
}