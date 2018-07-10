package com.test.movieapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by razan on 7/6/18.
 */

public class Genre {
@SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("name")
  @Expose
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  //{"id":28,"name":"Action"}

  @Override
  public String toString() {
    return "{id=" + id + ", name="+name+"}";
  }
}