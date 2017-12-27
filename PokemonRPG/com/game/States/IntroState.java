package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
  
  public IntroState(GamePanel pGamePanel)
  {
    splashScreens = new ArrayList<>();
    isActive      = false;
    game          = pGamePanel;
    gameStateType = GameStates.INTRO_STATE;
  }
  
  
  public void initializeState()
  {
    FadeEffect   vGameFreakFadeEffect = null;
    FadeEffect   vPkmnIntFadeEffect   = null;
    SplashScreen vSplashScreen        = null;
    
    isActive = true;
    
    //create first splash screen and effect
    
    vSplashScreen = new SplashScreen(
        "GameFreakScreen", 
        6000,
        Assets.imgGameFreakLogo, 
        null);
    
    vGameFreakFadeEffect = game.createFadeEffect(
        Color.white, 
        255, 
        0, 
        -2, 
        1000L,
        vSplashScreen.getSplashScreenDuration(),
        null);
    
    vSplashScreen.setFadeEffect(vGameFreakFadeEffect);
    
    splashScreens.add(vSplashScreen);
    
    
    //create second splash screen and effect
    
    vSplashScreen = new SplashScreen(
        "PkmnIntScreen",
        9000,
        Assets.imgPkmnIntLogo,
        Assets.soundMSIntro);
    
    vPkmnIntFadeEffect = game.createFadeEffect(
        Color.white, 
        255, 
        0, 
        -2, 
        1500L,
        vSplashScreen.getSplashScreenDuration(),
        null);
    
    vSplashScreen.setFadeEffect(vPkmnIntFadeEffect);
    
    splashScreens.add(vSplashScreen);
  }
  
  
  public void update()
  {
    boolean vRemovedScreen;
    
    vRemovedScreen = removedFinishedSplashScreen();
    
    if(splashScreens.size() > 0)
    {
      if(vRemovedScreen)
      {
        game.addFadeEffect(splashScreens.get(0).getFadeEffect());
      }
      else if(currentSplashScreen == null)
      {
        currentSplashScreen = splashScreens.get(0);
        currentSplashScreen.initSplashScreen();
        game.addFadeEffect(splashScreens.get(0).getFadeEffect());
      }
      else
      {
        currentSplashScreen.update();
      }
    }
    else
    {
      isActive = false;
    }
  }
  
  
  public void draw(Graphics pGraphics)
  {
    if(currentSplashScreen != null)
    {
      currentSplashScreen.draw(pGraphics);
    }
  }
  
  
  private boolean removedFinishedSplashScreen()
  {
    if(splashScreens.get(0).isDone())
    {
      splashScreens.remove(0);
      currentSplashScreen = null;
      return true;
    }
    
    return false;
  }
  
  
  public void skipSplashScreen()
  {
    if(currentSplashScreen != null)
    {
      currentSplashScreen.setIsDone();
      currentSplashScreen.getFadeEffect().setIsDone();
      game.interrupt();
    }
    if(currentSplashScreen != null && currentSplashScreen.hasAudio())
    {
      currentSplashScreen.closeAudio();
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
    isActive = false;
  }
  
  
  public GameStates getStateType() 
  {
    return gameStateType;
  }
}
