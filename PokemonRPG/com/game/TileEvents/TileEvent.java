package com.game.TileEvents;

import com.game.States.PlayState;

public abstract class TileEvent 
{
  protected PlayState oPlayState = null;
  
  
  public abstract void processEvent();
  
  
  public void setPlayState(PlayState pPlayState)
  {
    oPlayState = pPlayState;
  }
}
