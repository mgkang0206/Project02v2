package com.example.mgkan.project2mini.setup;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by mgkan on 2016-08-09.
 */
public class DBAssetHelper extends SQLiteAssetHelper {

  private static final String DATABASE_NAME = "SHOPPING_DB";
  private static final int DATABASE_VERSION = 7;

  public DBAssetHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
}
