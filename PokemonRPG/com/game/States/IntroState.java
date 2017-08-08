package com.game.States;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.game.FX.Assets;
import com.game.Main.GamePanel;

public class IntroState implements State 
{
  
  private ArrayList<SplashScreen> splashScreens       = null;
  private SplashScreen            currentSplashScreen = null;
  private boolean                 isActive;
  private GamePanel               game                = null;
  
  
  public IntroState(GamePanel pGamePanel)
  {
    splashScreens = new ArrayList<>();
    isActive      = true;
    game          = pGamePanel;
  }
  
  
  public void setUpState()
  {
    splashScreens.add(new SplashScreen(
        "GameFreakScreen",
        2000, 
        false,
        Assets.imgGameFreakLogo, 
        null));
    
    splashScreens.add(new SplashScreen(
        "PkmnIntScreen",
        3000, 
        true, 
        Assets.imgPkmnIntLogo,
        Assets.soundMSIntro));
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
    if(currentSplashScreen != null)
    {
      currentSplashScreen.draw(pGraphics);
    }
    
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
        System.out.println(currentSplashScreen.getName() + " being removed");
        currentSplashScreen = null;
      }
    }
  }
  
  
  public void skipSplashScreen()
  {
    if(currentSplashScreen != null)
    {
      currentSplashScreen.setIsDone();
      game.interrupt();
      
      System.out.println(currentSplashScreen.getName() + " being set to done");
      
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
  
  
  public SplashScreen getCurrentSplashScreen()
  {
    return currentSplashScreen;
  }
  
  
  public void cleanUpState()
  {
    
  }
}
