package com.u1.group2.unirideshare.components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.u1.group2.unirideshare.R;
import com.u1.group2.unirideshare.datamodels.Trip;

import java.util.ArrayList;
import java.util.Collections;

public class TripViewAdapter extends RecyclerView.Adapter<TripViewAdapter.ViewHolder> {
    private ArrayList<Trip> trips;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tripTitle;

        public ViewHolder(TextView v) {
            super(v);
            this.tripTitle = v;
        }
    }

    public TripViewAdapter(ArrayList<Trip> trips) {
        this.trips = trips;
        Collections.sort(trips);
    }

    @Override
    public TripViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        TextView v = new TextView(parent.getContext());
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trip trip = trips.get(position);
        if (trip.getRepeating()) {
            holder.tripTitle.setText(String.format("%s Recurring Trip", trip.getDay()));
        } else {
            holder.tripTitle.setText(String.format("%s One Off Trip", trip.getDay()));
        }
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


}
