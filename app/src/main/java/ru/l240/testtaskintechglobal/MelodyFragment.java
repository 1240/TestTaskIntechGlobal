package ru.l240.testtaskintechglobal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;
import ru.l240.testtaskintechglobal.models.Melody;
import ru.l240.testtaskintechglobal.realm.RealmHelper;

public class MelodyFragment extends Fragment {
    public static final String TAG = MelodyFragment.class.getSimpleName();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private MyMelodyRecyclerViewAdapter adapter;

    public MelodyFragment() {
    }

    @SuppressWarnings("unused")
    public static MelodyFragment newInstance(int columnCount) {
        MelodyFragment fragment = new MelodyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_melody_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setOnScrollListener(new EndlessOnScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int currentPage) {
                        mListener.onListFragmentInteraction(currentPage);
                    }
                });
            } else {
                GridLayoutManager layoutManager = new GridLayoutManager(context, mColumnCount);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setOnScrollListener(new EndlessOnScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int currentPage) {
                        mListener.onListFragmentInteraction(currentPage);
                    }
                });
            }
            List<Melody> melodies = RealmHelper.getMelodies(Realm.getInstance(getContext()));
            adapter = new MyMelodyRecyclerViewAdapter(melodies, mListener);
            recyclerView.setAdapter(adapter);

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Integer page);
        void onListItemFragmentInteraction(Melody item);
    }

    public void addItems(List<Melody> melodies) {
        adapter.addItems(melodies);
    }
}
