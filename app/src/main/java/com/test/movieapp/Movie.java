package com.test.movieapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by razan on 7/6/18.
 */

public class Movie {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("original_title")
  @Expose
  private String original_title;
  @SerializedName("poster_path")
  @Expose
  private String poster_path;
  @SerializedName("release_date")
  @Expose
  private String release_date;
  @SerializedName("overview")
  @Expose
  private String overview;
  @SerializedName("genres")
  @Expose
  private List<Genre> genres = new ArrayList<Genre>();


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getOriginal_title() {
    return original_title;
  }

  public void setOriginal_title(String original_title) {
    this.original_title = original_title;
  }

  public String getPoster_path() {
    return poster_path;
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
  }

  public String getRelease_date() {
    return release_date;
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "id=" + id +
        ", original_title='" + original_title + '\'' +
        ", poster_path='" + poster_path + '\'' +
        ", release_date='" + release_date + '\'' +
        ", overview='" + overview + '\'' +
        ", genres=" + genres +
        '}';
  }
}