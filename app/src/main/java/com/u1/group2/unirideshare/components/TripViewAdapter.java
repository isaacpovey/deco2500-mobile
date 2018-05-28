package com.u1.group2.unirideshare.components;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.u1.group2.unirideshare.R;
import com.u1.group2.unirideshare.datamodels.DriverTrip;
import com.u1.group2.unirideshare.datamodels.RiderTrip;
import com.u1.group2.unirideshare.datamodels.Trip;

import java.util.ArrayList;
import java.util.Collections;

public class TripViewAdapter extends RecyclerView.Adapter<TripViewAdapter.ViewHolder> {
    private ArrayList<Trip> trips;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView tripTitle;
        private TextView tripDetails;
        private TextView tripInfo;
        private TextView tripPeople;
        private Button organise;
        private Button startTrip;

        public ViewHolder(View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.cv);
            tripTitle = (TextView) v.findViewById(R.id.tripTitle);
            tripDetails = (TextView) v.findViewById(R.id.tripDetails);
            tripInfo = (TextView) v.findViewById(R.id.tripInfo);
            tripPeople = (TextView) v.findViewById(R.id.tripPeople);
            organise = (Button) v.findViewById(R.id.organise);
            startTrip = (Button) v.findViewById(R.id.startTrip);
        }
    }

    public TripViewAdapter(ArrayList<Trip> trips) {
        this.trips = trips;
        Collections.sort(trips);
    }

    @Override
    public TripViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                   int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_recycleview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trip trip = trips.get(position);
        String recuringTrip = "One Off Trip";
        String toUni = "from Uni";
        String isDriver = "as Passenger";
        String people = "Passengers";
        String tripInfo = "Picked up by";
        if (trip.getRepeating()) {
            recuringTrip = "Recurring Trip";
        }
        if (trip.getToUni()) {
            toUni = "to Uni";
        }
        try {
            DriverTrip driverTrip = (DriverTrip) trip;
            isDriver = "as Driver";
            holder.startTrip.setVisibility(View.VISIBLE);
            if (driverTrip.getRiders().size() > 0) {
                tripInfo = "Picking Up";
                people = "";
                for ( int i = 0;  i < driverTrip.getRiders().size(); i++) {
                    people += driverTrip.getRiders().get(i).getFirstName() + '\n';
                }
            } else {
                tripInfo = "Waiting for";
            }
        } catch (Exception e) {
            RiderTrip riderTrip = (RiderTrip) trip;
            people = riderTrip.getDriver().getFirstName();
        }
        holder.tripTitle.setText(String.format("%s %s", trip.getDay(), recuringTrip));
        holder.tripDetails.setText(String.format("%s %s %s", trip.getTime(), toUni, isDriver));
        holder.tripInfo.setText(tripInfo);
        holder.tripPeople.setText(people);
    }


    @Override
    public int getItemCount() {
        return trips.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private View.OnClickListener generateOnclickOrganise() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
            }
        };
    }

}
