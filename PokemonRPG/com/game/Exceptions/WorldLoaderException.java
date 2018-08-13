package com.game.Exceptions;

public class WorldLoaderException extends Exception
{

  public WorldLoaderException()
  {
    super();
  }
  
  
  public WorldLoaderException(String pMessage)
  {
    super(pMessage);
  }
  
  
  public WorldLoaderException(String pMessage, Exception pException)
  {
    super(pMessage, pException);
  }
}
