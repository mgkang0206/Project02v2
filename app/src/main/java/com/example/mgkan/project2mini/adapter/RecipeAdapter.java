package com.example.mgkan.project2mini.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mgkan.project2mini.ItemDetailActivity;
import com.example.mgkan.project2mini.R;
import com.example.mgkan.project2mini.model.Recipe;

import java.util.List;

/**
 * Created by mgkan on 2016-08-09.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
  private List<Recipe> mRecipe;
  private Context mContext;

  public RecipeAdapter(Context context, List<Recipe> recipes) {
    mRecipe = recipes;
    mContext = context;
    for(Recipe recipe : recipes){
      Log.d("MIKETEST: ", recipe.getName());
    }
    if(recipes.size() == 0){
      Log.d("MIKETEST: ", "EMPTY");
    }
  }


  public static class ViewHolder extends RecyclerView.ViewHolder {

    public CardView card;
    public ImageView photo;
    public TextView title;
    public TextView cooking_time;
    public TextView serving;
    public View view;


    public ViewHolder(View itemView) {
      super(itemView);
      card = (CardView) itemView.findViewById(R.id.list_item_card);
      photo = (ImageView) itemView.findViewById(R.id.list_item_image);
      title = (TextView) itemView.findViewById(R.id.name);
      cooking_time = (TextView) itemView.findViewById(R.id.time);
      serving = (TextView) itemView.findViewById(R.id.serving);
      view = itemView;
    }
  }

  private Context getContext() {
    return mContext;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View contactView = inflater.inflate(R.layout.item_list_content, parent, false);
    ViewHolder viewHolder = new ViewHolder(contactView);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {
    Recipe recipe = mRecipe.get(position);
    Resources res = mContext.getResources();
    String mDrawableName = recipe.getImage();
    int resID = res.getIdentifier(mDrawableName , "drawable", mContext.getPackageName());

//        if (pet.getName() != null) {

//              String[] splitSteps = recipe.getSteps().split("-");

    holder.photo.setImageResource(resID);
    holder.title.setText(recipe.getName());
    holder.cooking_time.setText(recipe.getCook_time());
    holder.serving.setText(recipe.getServings());


    holder.view.setOnClickListener(new View.OnClickListener()

                                   {
                                     @Override
                                     public void onClick (View view){
                                       Intent i = new Intent(getContext(), ItemDetailActivity.class);

                                       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                       i.putExtra("POS", position);
                                       mContext.startActivity(i);
                                     }
                                   }

    );


  }

  // Returns the total count of items in the list
  @Override
  public int getItemCount() {
    return mRecipe.size();
  }
}
