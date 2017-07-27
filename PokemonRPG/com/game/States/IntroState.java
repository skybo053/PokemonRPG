package com.game.States;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.game.FX.Assets;

public class IntroState implements State 
{
  
  private ArrayList<SplashScreen> splashScreens       = null;
  private SplashScreen            currentSplashScreen = null;
  
  private String name = null;
  
  private boolean isActive;
  
  
  public IntroState(String pName, int pDisplayWidth, int pDisplayHeight)
  {
    splashScreens = new ArrayList<>();
    
    splashScreens.add(new SplashScreen(
        "GameFreakScreen", 
        pDisplayWidth, 
        pDisplayHeight, 
        2000, 
        false,
        Assets.imgGameFreakLogo, 
        null));
    
    splashScreens.add(new SplashScreen(
        "PkmnIntScreen", 
        pDisplayWidth, 
        pDisplayHeight, 
        3000, 
        true, 
        Assets.imgPkmnIntLogo,
        Assets.soundMSIntro));
    
    isActive = true;
    name     = pName;
  }
  
  
  public void update()
  {
    removeFinishedSplashScreens();
    
    if(currentSplashScreen == null &&
       splashScreens.size() > 0)
    {
      currentSplashScreen = splashScreens.get(0);
      currentSplashScreen.setStartTime();
    }
    else if(splashScreens.size() == 0)
    {
      isActive = false;
      return;
    }
    
    currentSplashScreen.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    currentSplashScreen.draw(pGraphics);
  }
  
  
  private void removeFinishedSplashScreens()
  {
    Iterator<SplashScreen> vIt           = null;
    SplashScreen           vSplashScreen = null;
    
    vIt = splashScreens.iterator();
    
    while(vIt.hasNext())
    {
      vSplashScreen = vIt.next();
      
      if(vSplashScreen.isDone())
      {
        vIt.remove();
        currentSplashScreen = null;
      }
    }
  }
  
  
  public void skipSplashScreen()
  {
    if(currentSplashScreen != null)
    {
      currentSplashScreen.setIsDone();
      
      if(currentSplashScreen.hasAudio())
      {
        currentSplashScreen.closeAudio();
      }
    }
  }
  
  
  public boolean isActive()
  {
    return isActive;
  }
  
  
  public void setIsActive(boolean pIsActive)
  {
    isActive = pIsActive;
  }

  
  public String getName()
  {
    return name;
  }
  
  
  public SplashScreen getCurrentSplashScreen()
  {
    return currentSplashScreen;
  }
}
