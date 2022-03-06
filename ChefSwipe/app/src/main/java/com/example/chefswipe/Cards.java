package com.example.chefswipe;

public class Cards {
    private String recipeId;
    private String recipeName;

    public Cards (String recipeId, String recipeName) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
    }

    //Getters & setters recipeId
    public String getRecipeId() {
      return recipeId;
    }
    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    //Getters & setters recipeName
    public String getRecipeName() {
        return recipeName;
    }

}
