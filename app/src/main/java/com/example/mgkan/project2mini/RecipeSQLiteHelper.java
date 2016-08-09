package com.example.mgkan.project2mini;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mgkan.project2mini.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgkan on 2016-08-09.
 */
public class RecipeSQLiteHelper extends SQLiteOpenHelper{
  private static final String TAG = RecipeSQLiteHelper.class.getCanonicalName();

  private static final int DATABASE_VERSION = 7;
  public static final String DATABASE_NAME = "SHOPPING_DB";
  public static final String RECIPE_TABLE_NAME = "RECIPE_LIST";

  public static final String COL_ID = "_id";
  public static final String COL_RECIPE_NAME = "RECIPE_NAME";
  public static final String COL_SUMMARY = "SUMMARY";
  public static final String COL_COOKING_TIME = "COOKING_TIME";
  public static final String COL_SERVINGS = "SERVINGS";
  public static final String COL_IMAGE = "IMAGE";

  private static RecipeSQLiteHelper DB;

  public static final String[] RECIPE_COLUMNS = {COL_ID,COL_RECIPE_NAME,COL_SUMMARY,COL_COOKING_TIME,COL_SERVINGS,COL_IMAGE};

  private static final String CREATE_RECIPE_TABLE =
    "CREATE TABLE " + RECIPE_TABLE_NAME +
      "(" +
      COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
      COL_RECIPE_NAME + " TEXT, " +
      COL_SUMMARY + " TEXT, " +
      COL_COOKING_TIME + " TEXT, " +
      COL_SERVINGS + " TEXT, " +
      COL_IMAGE + " TEXT)";

  public RecipeSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public static RecipeSQLiteHelper getInstance(Context context) {
    if (DB == null) {
      Log.i("SINGLETON", "has been created!!!");
      DB = new RecipeSQLiteHelper(context);
    } else {
      Log.i("SINGLETON", "has been accessed.....");
    }
    return DB;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_RECIPE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);
    this.onCreate(db);
  }

  public long addItem(String name, String summary, String cooking_time, String servings, String image){
    ContentValues values = new ContentValues();
    values.put(COL_RECIPE_NAME, name);
    values.put(COL_SUMMARY, summary);
    values.put(COL_COOKING_TIME, cooking_time);
    values.put(COL_SERVINGS, servings);
    values.put(COL_IMAGE, image);

    SQLiteDatabase db = this.getWritableDatabase();
    long returnId = db.insert(RECIPE_TABLE_NAME, null, values);
    db.close();
    return returnId;
  }

//  public Cursor getRecipeList(){
//
//    SQLiteDatabase db = this.getReadableDatabase();
//
//    Cursor cursor = db.query(RECIPE_TABLE_NAME, // a. table
//      RECIPE_COLUMNS, // b. column names
//      null, // c. selections
//      null, // d. selections args
//      null, // e. group by
//      null, // f. having
//      null, // g. order by
//      null); // h. limit
//
//    return cursor;
//  }

  public int deleteItem(int id){
    SQLiteDatabase db = getWritableDatabase();
    int deleteNum = db.delete(RECIPE_TABLE_NAME,
      COL_ID + " = ?",
      new String[]{String.valueOf(id)});
    db.close();
    return deleteNum;
  }

  public Cursor searchShoppingList(String query){
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(RECIPE_TABLE_NAME, // a. table
      RECIPE_COLUMNS, // b. column names
      COL_RECIPE_NAME + " LIKE ?", // c. selections
      new String[]{"%" + query + "%"}, // d. selections args
      null, // e. group by
      null, // f. having
      null, // g. order by
      null); // h. limit

    return cursor;
  }

  public List<Recipe> getRecipeList(RecipeSQLiteHelper self) {
    String sql = "SELECT * FROM "+ RECIPE_TABLE_NAME+";";
    //SQLiteDatabase db = this.getWritableDatabase();
    //Cursor cursor = db.query(RECIPE_TABLE_NAME,new String[] {COL_ID},null,null,null,null,null);
    Cursor cursor = self.getWritableDatabase().rawQuery(sql, null);

    Log.d("DB","Count: " + cursor.getCount());

    cursor.moveToFirst();

    List<Recipe> recipes = new ArrayList<>();
    while (!cursor.isAfterLast()) {
      int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
      String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_RECIPE_NAME));
      String summary = cursor.getString(cursor.getColumnIndexOrThrow(COL_SUMMARY));
      String cooking_Time = cursor.getString(cursor.getColumnIndexOrThrow(COL_COOKING_TIME));
      String servings = cursor.getString(cursor.getColumnIndexOrThrow(COL_SERVINGS));
      String image = cursor.getString(cursor.getColumnIndexOrThrow(COL_IMAGE));

      recipes.add(new Recipe(id, name, summary, cooking_Time, servings, image));
      cursor.moveToNext();
    }
    cursor.close();
    return recipes;
  }
}
