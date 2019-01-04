package com.game.Exceptions;

public class InitializeStateException extends Exception
{

  public InitializeStateException()
  {
    super();
  }
  
  
  public InitializeStateException(String pMessage)
  {
    super(pMessage);
  }
  
  
  public InitializeStateException(String pMessage, Exception pException)
  {
    super(pMessage, pException);
  }
}
