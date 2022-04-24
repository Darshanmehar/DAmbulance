package com.example.darshanambulance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.darshanambulance.R;
import com.example.darshanambulance.activity.ViewLocation;
import com.example.darshanambulance.model.Ambulance;

import java.util.List;

public class AmbulanceAdapter extends RecyclerView.Adapter<AmbulanceAdapter.MyViewHolder> {
    private List<Ambulance> ambulanceList;
    private Context mContext;
    private View parent_view;
    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView ambulance_name,ambulance_lat,ambulance_lng;
        Button viewmap;
        MyViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            parent_view = view.findViewById(android.R.id.content);
            ambulance_name = (TextView) view.findViewById(R.id.ambulance_name);
            ambulance_lat = (TextView) view.findViewById(R.id.ambulance_lat);
            ambulance_lng = (TextView) view.findViewById(R.id.ambulance_lng);
            viewmap = (Button) view.findViewById(R.id.viewmap);
        }
    }

    public AmbulanceAdapter(List<Ambulance> ambulanceList) {
        this.ambulanceList = ambulanceList;
    }

    @NonNull
    @Override
    public AmbulanceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ambulance_single_entry, parent, false);
        return new AmbulanceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AmbulanceAdapter.MyViewHolder holder, int position) {
        final Ambulance ambulance = ambulanceList.get(position);
        final String ambulanceName = ambulance.getAmbulance_name();
        final Double ambulanceLat = ambulance.getLat();
        final Double ambulanceLng = ambulance.getLng();
        holder.ambulance_name.setText(ambulanceName);
        holder.ambulance_lat.setText(String.valueOf(ambulanceLat));
        holder.ambulance_lng.setText(String.valueOf(ambulanceLng));
        holder.viewmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewLocation.class);
                intent.putExtra("ambulance_name",ambulanceName);
                intent.putExtra("ambulance_lat",ambulanceLat);
                intent.putExtra("ambulance_lng",ambulanceLng);
                view.getContext().startActivity(intent);
                //Toast.makeText(mContext, "Clicked Laugh Vote", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ambulanceList.size();
    }
}
