package com.game.TileEvents.events;

import com.game.TileEvents.TileEvent;

public class MoveEvent implements TileEvent
{
  private Integer oRow = null;
  private Integer oCol = null;
  
  
  public MoveEvent(Integer pRow, Integer pCol)
  {
    oRow = pRow;
    oCol = pCol;
  }
  
  
  public void processEvent()
  {
    
  }
}
