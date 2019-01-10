package com.game.TileEvents.events;

import com.game.Entities.Tile;
import com.game.TileEvents.TileEvent;

public class MoveEvent extends TileEvent
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
    Tile vDestinationTile = null;
    
    vDestinationTile = oPlayState.getTileAtPosition(oRow, oCol);
    
    oPlayState.setPlayerPositionInWorld(vDestinationTile);
  }
}
