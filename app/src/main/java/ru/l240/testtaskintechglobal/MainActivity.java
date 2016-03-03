package ru.l240.testtaskintechglobal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmList;
import ru.l240.testtaskintechglobal.models.Melodies;
import ru.l240.testtaskintechglobal.models.Melody;
import ru.l240.testtaskintechglobal.realm.RealmHelper;
import ru.l240.testtaskintechglobal.retrofit.loaders.IntechLoader;
import ru.l240.testtaskintechglobal.retrofit.response.Response;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Response>, MelodyFragment.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private int from = 1;
    private boolean isList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            isList = savedInstanceState.getBoolean("isList");
            from = savedInstanceState.getInt("from");
        }
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(R.id.intech_loader, Bundle.EMPTY, this);
        if (isList) {
            MelodyFragment melodyFragment = MelodyFragment.newInstance(1);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, melodyFragment, MelodyFragment.TAG);
            transaction.commit();
        } else {
            MelodyFragment melodyFragment = MelodyFragment.newInstance(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, melodyFragment, MelodyFragment.TAG);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isList", isList);
        outState.putInt("from", from);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_list:
                if (isList)
                    break;
                isList = true;
                MelodyFragment melodyFragment = MelodyFragment.newInstance(1);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, melodyFragment, MelodyFragment.TAG);
                transaction.commit();
                break;
            case R.id.action_table:
                if (!isList)
                    break;
                isList = false;
                MelodyFragment melodyFragmentT = MelodyFragment.newInstance(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2);
                FragmentTransaction transactionT = getSupportFragmentManager().beginTransaction();
                transactionT.replace(R.id.fragment_container, melodyFragmentT, MelodyFragment.TAG);
                transactionT.commit();
                break;
        }
        getSupportLoaderManager().restartLoader(R.id.intech_loader, Bundle.EMPTY, this);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.intech_loader:
                return new IntechLoader(this, from - 1);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        int id = loader.getId();
        if (id == R.id.intech_loader) {
            MelodyFragment fragment = (MelodyFragment)getSupportFragmentManager().findFragmentByTag(MelodyFragment.TAG);
            RealmList<Melody> melodies = ((Melodies) data.getTypedAnswer()).getMelodies();
            RealmHelper.save(Realm.getInstance(this), melodies);
            fragment.addItems(melodies);
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }

    @Override
    public void onListFragmentInteraction(Integer page) {
        from = page;
        getSupportLoaderManager().restartLoader(R.id.intech_loader, Bundle.EMPTY, this);
    }

    @Override
    public void onListItemFragmentInteraction(Melody item) {
        Intent intent = new Intent(this, AudioPlayerActivity.class);
        intent.putExtra("id", item.getId());
        startActivity(intent);

    }
}
