package com.influx.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.influx.R;
import com.influx.model.Result;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Result> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_rate,tv_date,tv_desc;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_rate = (TextView) view.findViewById(R.id.tv_rate);
            thumbnail = (ImageView) view.findViewById(R.id.img_poster);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_desc = (TextView) view.findViewById(R.id.tv_desc);
        }
    }


    public AlbumsAdapter(Context mContext, List<Result> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Result album = albumList.get(position);
        holder.tv_title.setText(album.getOriginalTitle());
        holder.tv_date.setText(album.getReleaseDate());
        holder.tv_rate.setText(album.getVoteAverage());
        holder.tv_desc.setText(album.getOverview());

        // loading album cover using Glide library
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500/"+album.getPosterPath()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
