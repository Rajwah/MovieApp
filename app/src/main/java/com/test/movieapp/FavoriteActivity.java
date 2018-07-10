package com.test.movieapp;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FavoriteActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_favorite);
    setupBottomNavigationView();
  }
  private void setupBottomNavigationView() {
    BottomNavigationView bottomNavigationView = findViewById(R.id.main_nav);
    BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    BottomNavigationViewHelper.enableNavigation(getApplicationContext(), this,bottomNavigationView);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(2);
    menuItem.setChecked(true);
  }
}
