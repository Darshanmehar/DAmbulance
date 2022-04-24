package com.example.darshanambulance.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.darshanambulance.model.Ambulance;
import com.example.darshanambulance.adapter.AmbulanceAdapter;
import com.example.darshanambulance.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FindAmbulance extends AppCompatActivity{
    private GoogleMap mMap;
    private ArrayList<LatLng> locationArrayList;
    private FirebaseAuth mAuth;
    RecyclerView ambulance_rv;
    private List<Ambulance> ambulanceList = new ArrayList<>();
    String TAG = "tag";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ambulance);
        ambulance_rv = (RecyclerView) findViewById(R.id.ambulance_rv);
        getSupportActionBar().setTitle("All Ambulance List");
        getAmbulance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        ambulance_rv.setLayoutManager(linearLayoutManager);
    }

    private void getAmbulance() {
        DatabaseReference familyListReference = FirebaseDatabase.getInstance().getReference().child("AmbulanceList");
        familyListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = (String) ds.getKey();
                    DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("AmbulanceList").child(key);
                    keyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String ambulance_name = dataSnapshot.child("ambulance_name").getValue(String.class);
                            Double lat = (Double) dataSnapshot.child("lat").getValue();
                            Double lng = (Double) dataSnapshot.child("lng").getValue();
                            Ambulance ambulance = new Ambulance(ambulance_name, lat, lng);
                            ambulanceList.add(ambulance);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Read failed");
                        }
                    });
                }
                AmbulanceAdapter adapter = new AmbulanceAdapter(ambulanceList);
                ambulance_rv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Read failed");
            }
        });
    }

}