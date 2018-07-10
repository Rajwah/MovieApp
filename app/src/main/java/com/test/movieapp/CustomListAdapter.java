package com.test.movieapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {

 private ArrayList<Movie> movieList;
 private Context context;


  public CustomListAdapter(Context context, ArrayList<Movie> movieArrayList) {
    this.context=context;
    this.movieList = movieArrayList;

  }


  @Override
  public int getCount() {
    return movieList.size();
  }

  @Override
  public Object getItem(int i) {
    return null;
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  public View getView (int position, @NonNull View covertView, @NonNull ViewGroup parent){

    LayoutInflater inflater = LayoutInflater.from(context);
    View view =inflater.inflate(R.layout.listview_layout,null,true);
    ImageView movie_poster = (ImageView) view.findViewById(R.id.movieImage);
    TextView movie_name = (TextView) view.findViewById(R.id.movieName);
    TextView movie_description = (TextView) view.findViewById(R.id.movieDescription);
    movie_name.setText(movieList.get(position).getOriginal_title());
    movie_description.setText(movieList.get(position).getOverview());

    Picasso.get().load("https://image.tmdb.org/t/p/w500"+movieList.get(position).getPoster_path()).into(movie_poster);
    return view;
  }
}
