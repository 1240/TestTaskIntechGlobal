package ru.l240.testtaskintechglobal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import ru.l240.testtaskintechglobal.models.Melody;

public class MyMelodyRecyclerViewAdapter extends RecyclerView.Adapter<MyMelodyRecyclerViewAdapter.ViewHolder> {

    private List<Melody> mValues;
    private MelodyFragment.OnListFragmentInteractionListener mListener;

    public MyMelodyRecyclerViewAdapter(List<Melody> items, MelodyFragment.OnListFragmentInteractionListener listener) {
        mValues = new ArrayList<>(items);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_melody, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getArtist());
        holder.mContentView.setText(mValues.get(position).getTitle());
        Picasso.with(holder.mView.getContext())
                .load(holder.mItem.getPicUrl())
                .placeholder(R.mipmap.song_preview)
                .into(holder.mImage);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListItemFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImage;
        public Melody mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mImage = (ImageView) view.findViewById(R.id.ivMelody);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public void addItems(List<Melody> melodies) {
        mValues.addAll(new ArrayList<>(melodies.subList(0, melodies.size())));
        this.notifyDataSetChanged();
    }

}
