package com.game.States;

public enum GameStates 
{
  INTRO_STATE("Splash Screen for background loading"),
  MENU_STATE("Display game menu options"),
  PLAY_STATE("Game has been initiated");
  
  private String description = null;
  
  private GameStates(String pDescription)
  {
    description = pDescription;
  }
  
  public void setDescription(String pDescription)
  {
    description = pDescription;
  }
  
  public String getDescription()
  {
    return description;
  }
}
