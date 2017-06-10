package com.game.Exceptions;

public class AssetLoaderException extends Exception
{
  public AssetLoaderException()
  {
    super();
  }
  
  public AssetLoaderException(String pMessage)
  {
    super(pMessage);
  }
  
  public AssetLoaderException(String pMessage, Exception pException)
  {
    super(pMessage, pException);
  }

}
