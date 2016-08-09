package com.example.mgkan.project2mini.model;

/**
 * Created by mgkan on 2016-08-09.
 */
public class Recipe {
  private String name, cook_time, servings, summary, image;
  private int id = -1;

  public Recipe(int id, String name,String summary,String cook_time,String servings,String image) {
    this.id = id;
    this.name = name;
    this.summary = summary;
    this.cook_time = cook_time;
    this.servings = servings;
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCook_time() {
    return cook_time;
  }

  public void setCook_time(String cook_time) {
    this.cook_time = cook_time;
  }

  public String getServings() {
    return servings;
  }

  public void setServings(String servings) {
    this.servings = servings;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
