package ru.l240.testtaskintechglobal;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ru.l240.testtaskintechglobal.models.Melody;
import ru.l240.testtaskintechglobal.retrofit.response.Response;
import ru.l240.testtaskintechglobal.retrofit.loaders.IntechLoader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Response> {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(R.id.intech_loader, Bundle.EMPTY, this);
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.intech_loader:
                return new IntechLoader(this, 0);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        int id = loader.getId();
        if (id == R.id.intech_loader) {
            List<Melody> melodies = data.getTypedAnswer();
            //do something here
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }
}
