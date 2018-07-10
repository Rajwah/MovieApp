package com.test.movieapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;

import java.lang.reflect.Field;


public class BottomNavigationViewHelper {
  @SuppressLint("RestrictedApi")
  public static void disableShiftMode(BottomNavigationView view) {
    BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
    try {
      Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
      shiftingMode.setAccessible(true);
      shiftingMode.setBoolean(menuView, false);
      shiftingMode.setAccessible(false);
      for (int i = 0; i < menuView.getChildCount(); i++) {
        BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
        //noinspection RestrictedApi
        item.setShiftingMode(false);
        // set once again checked value, so view will be updated
        //noinspection RestrictedApi
        item.setChecked(item.getItemData().isChecked());
        // item.setTextColor(ColorStateList.valueOf(R.color.colorAccent));
      }
    } catch (NoSuchFieldException e) {
      Log.e("BNVHelper", "Unable to get shift mode field", e);
    } catch (IllegalAccessException e) {
      Log.e("BNVHelper", "Unable to change value of shift mode", e);
    }
  }


  public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationView view){
    view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){

          case R.id.nav_latest:
            intent = new Intent(context, LatestActivity.class);//ACTIVITY_NUM = 0
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            break;

          case R.id.nav_search:
            intent  = new Intent(context, SearchActivity.class);//ACTIVITY_NUM = 1
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


            break;

          case R.id.nav_favourite:

            intent = new Intent(context,FavoriteActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            break;

        }
        return false;
      }
    });
  }

}

