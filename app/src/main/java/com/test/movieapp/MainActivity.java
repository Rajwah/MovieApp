package com.test.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
  private static int SplashTimeOut=2000;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    new Handler().postDelayed(new Runnable(){

      @Override
      public void run(){

        Intent HomeIntent=new Intent(MainActivity.this,LatestActivity.class);
        startActivity(HomeIntent);
        finish();
      }
    },SplashTimeOut);
  }

}
