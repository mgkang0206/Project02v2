package com.example.mgkan.project2mini;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mgkan.project2mini.adapter.RecipeAdapter;
import com.example.mgkan.project2mini.model.Recipe;
import com.example.mgkan.project2mini.setup.DBAssetHelper;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

public class MainActivity extends AppCompatActivity {

  private RecipeSQLiteHelper mHelper;
  public static List<Recipe> recipes;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_list);

    DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
    dbSetup.getReadableDatabase();

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());
    View recyclerView = findViewById(R.id.item_list);
    assert recyclerView != null;
    setupRecyclerView((RecyclerView) recyclerView);

  }
  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

    mHelper = RecipeSQLiteHelper.getInstance(this);
    recipes = mHelper.getRecipeList(mHelper);
    recyclerView.setAdapter(new AlphaInAnimationAdapter(new RecipeAdapter(this, recipes)));

  }


}
