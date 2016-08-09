package com.example.mgkan.project2mini;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.mgkan.project2mini.model.Recipe;

public class ItemDetailActivity extends AppCompatActivity {
  Activity mActivity;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_detail2);

    mActivity = this;
    int pos = getIntent().getIntExtra("POS", 0);
    Recipe recipe = MainActivity.recipes.get(pos);

    AppBarLayout toppic = (AppBarLayout) findViewById(R.id.app_bar);
    TextView summary = (TextView) findViewById(R.id.summary);

    Resources res = mActivity.getResources();
    String mDrawableName = recipe.getImage();
    int resID = res.getIdentifier(mDrawableName , "drawable", mActivity.getPackageName());

    toppic.setBackgroundResource(resID);
    summary.setText(recipe.getSummary());
    mActivity.setTitle(recipe.getName());

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Animation moveLeft = AnimationUtils.loadAnimation(mActivity, R.anim.move_left);
        fab.startAnimation(moveLeft);

        Snackbar.make(view, "STOP PRESSING IT!!", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      // This ID represents the Home or Up button. In the case of this
      // activity, the Up button is shown. For
      // more details, see the Navigation pattern on Android Design:
      //
      // http://developer.android.com/design/patterns/navigation.html#up-vs-back
      //
      navigateUpTo(new Intent(this, MainActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
