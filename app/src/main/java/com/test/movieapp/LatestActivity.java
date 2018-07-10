package com.test.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class LatestActivity extends Activity {
  ListView latestList;
  ArrayList<Movie> movieArrayList;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_latest);
    setupBottomNavigationView();
    getLatestVideos();

    latestList = (ListView) findViewById(R.id.latest_movies_list);

  }

  private void setupBottomNavigationView() {
    BottomNavigationView bottomNavigationView = findViewById(R.id.main_nav);
    BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    BottomNavigationViewHelper.enableNavigation(getApplicationContext(), this, bottomNavigationView);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(0);
    menuItem.setChecked(true);
  }

  public void getLatestVideos() {

    final RequestQueue queue = Volley.newRequestQueue(this);
    String url = "http://api.themoviedb.org/3/movie/popular?api_key=289f7aa1bc5b11d8ea1af282260e511b";

// Request a string response from the provided URL.
    final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            String responseString = response;
            movieArrayList = new ArrayList<Movie>();

            try {
              JSONObject jsonObject= new JSONObject(responseString);
              JSONArray array= jsonObject.getJSONArray("results");

              for (int i=0;i<array.length();i++) {
                JSONObject object = array.getJSONObject(i);
                Gson gson = new Gson();
                Movie movie = gson.fromJson(String.valueOf(object), Movie.class);
                movieArrayList.add(movie);
                Log.d("4444","444"+movie.toString());
              }
              CustomListAdapter CustomListView = new CustomListAdapter(getApplicationContext(), movieArrayList);
              latestList.setAdapter(CustomListView);
              latestList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                  Bundle bundle = new Bundle();
                  bundle.putInt("movieId",  movieArrayList.get(position).getId());
                  Intent intent = new Intent(LatestActivity.this, SingleMovieActivity.class);
                  intent.putExtras(bundle);
                  //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                  startActivity(intent);


                }
              });



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
