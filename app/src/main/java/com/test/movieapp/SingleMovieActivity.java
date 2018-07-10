package com.test.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SingleMovieActivity extends AppCompatActivity {

  ListView latestList;
  ArrayList<Movie> movieArrayList;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_movie);
    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    int movieId= bundle.getInt("movieId");
    getMovieInformation(movieId);
    ImageButton backButton = (ImageButton) findViewById(R.id.back_button);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });


  }

  public void getMovieInformation(int movieId) {

    final RequestQueue queue = Volley.newRequestQueue(this);
    String url = "https://api.themoviedb.org/3/movie/%20"+movieId+"?api_key=289f7aa1bc5b11d8ea1af282260e511b&language=en-US";

// Request a string response from the provided URL.
    final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            String responseString = response;

            try {
              JSONObject jsonObject= new JSONObject(responseString);
              Gson gson = new Gson();
              Movie movie = gson.fromJson(String.valueOf(jsonObject), Movie.class);
              //call element
              ImageView poster = (ImageView) findViewById(R.id.movieImage);
              TextView movie_name = (TextView) findViewById(R.id.movieName);
              TextView movie_description = (TextView) findViewById(R.id.movieDescription);
              TextView movie_Genre = (TextView) findViewById(R.id.movieGenre);
              movie_name.setText(movie.getOriginal_title());
              movie_description.setText(movie.getOverview());
              String genres_str="Genres:  ";
              for(int i=0; i<movie.getGenres().size();i++){
                genres_str+=movie.getGenres().get(i).getName();
                    if(i!=movie.getGenres().size()-1)
                      genres_str+= ", ";

              }
              movie_Genre.setText(genres_str);

              Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path()).into(poster);


              Log.d("4444","444"+genres_str);

            } catch (JSONException e) {
              e.printStackTrace();
            }

            return;}


        }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        String errorMessage = "Error: Somthing is wrong";
        error.printStackTrace();
        queue.stop();
      }
    });
    queue.add(stringRequest);
  }
}
