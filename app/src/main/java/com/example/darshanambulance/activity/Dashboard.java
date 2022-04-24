package com.example.darshanambulance.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Header;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.darshanambulance.MainActivity;
import com.example.darshanambulance.R;
import com.example.darshanambulance.utility.URLs;
import com.example.darshanambulance.utility.VolleySingleton;
import com.example.darshanambulance.utility.preferences;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity {

    private Button logout_btn;
    private CardView find_ambulance,ambulancemap,contactsupport,updateprofile;
    private TextView fullname,email;
    private SliderLayout first_home_slider;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        RelativeLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        logout_btn = (Button) findViewById(R.id.logout_btn);
        find_ambulance = (CardView) findViewById(R.id.find_ambulance);
        ambulancemap = (CardView) findViewById(R.id.ambulancemap);
        contactsupport = (CardView) findViewById(R.id.contactsupport);
        updateprofile =(CardView) findViewById(R.id.updateprofile);
        fullname = (TextView) findViewById(R.id.user_name);
        email = (TextView) findViewById(R.id.user_email);
        first_home_slider = (SliderLayout) findViewById(R.id.first_home_slider);
        getSlider();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("user")) {
                fullname.setText(preferences.getDataFullname(getApplicationContext()));
                email.setText(preferences.getDataEmail(getApplicationContext()));
            }
        }
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                preferences.clearData(Dashboard.this);
                startActivity(new Intent(Dashboard.this, MainActivity.class));
            }
        });

        find_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, FindAmbulance.class));
            }
        });

        ambulancemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, AmbulanceMap.class));
            }
        });

        contactsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, ContactSupport.class));
            }
        });

        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, ProfileUpdate.class));
            }
        });
    }

    private void getSlider() {
        String url = "https://newsapi.org/v2/top-headlines?country=gb&category=health&apiKey=c940dfe177f24f20bf97f3e97a27b957";
        final ArrayList arraylist = new ArrayList<HashMap<String, String>>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Responsecheck",response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray DATA = obj.getJSONArray("articles");
                            JSONObject jsonObject;
                            for(int i=0;i<DATA.length();i++){
                                jsonObject = DATA.getJSONObject(i);
                                String urltoimage = jsonObject.getString("urlToImage");
                                String title = jsonObject.getString("title");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put(title, urltoimage);
                                arraylist.add(map);
                                for(String name : map.keySet()){
                                    DefaultSliderView textSliderView = new DefaultSliderView (Dashboard.this);
                                    textSliderView.description(name);
                                    textSliderView.image(map.get(name));
                                    textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                                    first_home_slider.addSlider(textSliderView);
                                }
                            }
                            first_home_slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                            first_home_slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            first_home_slider.setCustomAnimation(new DescriptionAnimation());
                            first_home_slider.setDuration(3000);
                            first_home_slider.setMinimumWidth(300);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String>  params = new HashMap<String, String>();
                params.put("User-Agent", "Mozilla/5.0");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setShouldCache(false);
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}