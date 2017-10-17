package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.game.FX.Assets;
import com.game.FX.FadeEffect;
import com.game.Main.GamePanel;

public class IntroState implements State 
{
  
  private ArrayList<SplashScreen> splashScreens       = null;
  private SplashScreen            currentSplashScreen = null;
  private boolean                 isActive;
  private GamePanel               game                = null;
  private GameStates              gameStateType       = null;
  private boolean                 isEffectDone        = false;
  
  public IntroState(GamePanel pGamePanel)
  {
    splashScreens = new ArrayList<>();
    isActive      = false;
    game          = pGamePanel;
    gameStateType = GameStates.INTRO_STATE;
  }
  
  
  public void setUpState()
  {
    FadeEffect vGameFreakFadeEffect = null;
    FadeEffect vPkmnIntFadeEffect   = null;
    isActive                        = true;
    
    vGameFreakFadeEffect = game.createFadeEffect(Color.white, 255, 0, -1, 5000);
    vPkmnIntFadeEffect   = game.createFadeEffect(Color.white, 255, 0, -1, 5000);
    
    splashScreens.add(new SplashScreen(
        "GameFreakScreen",
        2000, 
        false,
        Assets.imgGameFreakLogo, 
        null,
        vGameFreakFadeEffect));
    
    splashScreens.add(new SplashScreen(
        "PkmnIntScreen",
        3000, 
        true, 
        Assets.imgPkmnIntLogo,
        Assets.soundMSIntro,
        vPkmnIntFadeEffect));
  }
  
  
  public void update()
  {
    checkEffectDone();
    removeFinishedSplashScreens();
    
    if(currentSplashScreen == null &&
       splashScreens.size() > 0)
    {
      currentSplashScreen = splashScreens.get(0);
      game.addFadeEffect(currentSplashScreen.getFadeEffect());
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
    }
    if(currentSplashScreen != null && currentSplashScreen.hasAudio())
    {
      currentSplashScreen.closeAudio();
    }
  }
  
  
  public void checkEffectDone()
  {
    if(isEffectDone)
    {
      System.out.println("effect is done");
      currentSplashScreen.setIsDone();
    }
    
    isEffectDone = false;
  }
  
  
  public void setEffectDone()
  {
    isEffectDone = true;
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
    isActive = false;
  }
  
  
  public GameStates getStateType() 
  {
    return gameStateType;
  }
}
