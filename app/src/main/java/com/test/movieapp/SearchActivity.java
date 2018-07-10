package com.test.movieapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity {

  private ArrayList<Movie> ArrayListMovie;
  private JSONArray search_movies_array = null;
  SearchView searchMovie; //The founded Results
  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    searchMovie = (SearchView) findViewById(R.id.search_movie);
    searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        searchForMovie(query);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String s) {
        return false;
      }

    }
  );

    setupBottomNavigationView();
  }


  public void searchForMovie(String movie) {

    Log.d("5555", "55558"+movie);
    final RequestQueue queue = Volley.newRequestQueue(this);
    String url = "https://api.themoviedb.org/3/search/movie?api_key=289f7aa1bc5b11d8ea1af282260e511b&query=";

// Request a string response from the provided URL.
    final StringRequest stringRequest = new StringRequest(Request.Method.GET, url + movie, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        String responseString = response;
        ArrayList movieArrayList = new ArrayList<Movie>();

        try {
          JSONObject jsonObject = new JSONObject(responseString);
          JSONArray arrayOfMovie = jsonObject.getJSONArray("results");

          ArrayListMovie = new ArrayList<Movie>();

          for (int i = 0; i < arrayOfMovie.length(); i++) {
            JSONObject object = arrayOfMovie.getJSONObject(i);
            Log.d("5555", "5555||||" + object);
            Gson gson = new Gson();
            Movie movie = gson.fromJson(String.valueOf(object), Movie.class);
            Log.d("5555", "5555||||222" + movie.toString());
            ArrayListMovie.add(movie);
          }


          CustomListAdapter CustomListView = new CustomListAdapter(getApplicationContext(), ArrayListMovie);
          ListView MoviesListView = (ListView) findViewById(R.id.latest_movies_list);
          MoviesListView.setAdapter(CustomListView);

          MoviesListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

              Bundle bundle = new Bundle();
              bundle.putInt("movieId",  ArrayListMovie.get(position).getId());
              Intent intent = new Intent(SearchActivity.this, SingleMovieActivity.class);
              intent.putExtras(bundle);
              //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

              startActivity(intent);


            }
          });



          return;
        } catch (JSONException e) {
          e.printStackTrace();
        }


        Log.d("5555", "5555" + responseString);
        // queue.stop();


        return;
      }


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


  /*class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Movie> movieArrayList;

    public ListAdapter(Context context, ArrayList<Movie> movieArrayList) {
      this.context=context;
      this.movieArrayList = movieArrayList;
    }

    public int getCount() {
      return movieArrayList.size();
    }

    @Override
    public Object getItem(int i) {
      return null;
    }

    @Override
    public long getItemId(int i) {
      return 0;
    }
  public View getView(int position, @NonNull View covertView, @NonNull ViewGroup parent) {

    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.listview_layout, null, true);
    ImageView movie_poster = (ImageView) view.findViewById(R.id.movieImage);
    TextView movie_name = (TextView) view.findViewById(R.id.movieName);
    TextView movie_description = (TextView) view.findViewById(R.id.movieDescription);
    movie_name.setText(ArrayListMovie.get(position).getOriginal_title());
    movie_description.setText(ArrayListMovie.get(position).getOverview());
    Picasso.get().load("https://image.tmdb.org/t/p/w500" + ArrayListMovie.get(position).getPoster_path()).into(movie_poster);
    return view;
  }*/



  //Navigation Bar
  private void setupBottomNavigationView() {
    BottomNavigationView bottomNavigationView = findViewById(R.id.main_nav);
    BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    BottomNavigationViewHelper.enableNavigation(getApplicationContext(), this,bottomNavigationView);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(1);
    menuItem.setChecked(true);
  }
}
