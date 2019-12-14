package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String API_KEY = "0adedf406ed8bb6b066a71df80a94396";
        String url = "https://api.themoviedb.org/3/movie/popular?api_key="+API_KEY+"&language=en-US&page=1";
        final String imageURL = "https://image.tmdb.org/t/p/w500";




        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Use the Gson library to turn the Json into a JAVA Object
                        Gson gson = new Gson();
                        MovieAPIResult result = gson.fromJson(response, MovieAPIResult.class);

                        //Log the results to make sure we are getting correct data
                        for(int i = 0; i < result.getResults().size()/2; i++){
                            Log.d("apiMovie", result.getResults().get(i).getTitle());
                            Log.d("apiMovie", result.getResults().get(i).getOverview());
                            Log.d("apiMovie", result.getResults().get(i).getVote_average() +"");
                            Log.d("apiMovie",imageURL + result.getResults().get(i).getPoster_path());
                        }

                        //Create a RecyclerView
                        RecyclerView view = findViewById(R.id.rv_movies);

                        //Create RecyclerView Adapter
                        MovieAdapter movieAdapter = new MovieAdapter( (ArrayList<Movie>) result.getResults(),MainActivity.this);

                        //Create a layout manager
                        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);

                        //Set adapter and layout manager to the RecyclerView
                        view.setAdapter(movieAdapter);
                        view.setLayoutManager(manager);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

}
