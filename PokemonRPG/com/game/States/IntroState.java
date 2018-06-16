package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.game.EventHandlers.IntroStateKeyListener;
import com.game.FX.Assets;
import com.game.FX.FadeEffect;
import com.game.Main.GamePanel;

public class IntroState implements State 
{
  
  private ArrayList<SplashScreen> oSplashScreens         = null;
  private SplashScreen            oCurrentSplashScreen   = null;
  private boolean                 oIsActive;
  private GamePanel               oGame                  = null;
  private GameStates              oGameStateType         = null;
  private IntroStateKeyListener   oIntroStateKeyListener = null;
  
  public IntroState(GamePanel pGame)
  {
    oSplashScreens         = new ArrayList<>();
    oIsActive              = false;
    oGame                  = pGame;
    oGameStateType         = GameStates.INTRO_STATE;
    oIntroStateKeyListener = new IntroStateKeyListener(this);
  }
  
  
  public void initializeState()
  {
    FadeEffect   vGameFreakFadeEffect = null;
    FadeEffect   vPkmnIntFadeEffect   = null;
    SplashScreen vSplashScreen        = null;
    
    oIsActive = true;
    
    //create first splash screen and effect
    
    vSplashScreen = new SplashScreen(
        "GameFreakScreen", 
        6000,
        Assets.imgGameFreakLogo, 
        null);
    
    vGameFreakFadeEffect = oGame.createFadeEffect(
        Color.white, 
        255, 
        0, 
        -2, 
        1000L,
        vSplashScreen.getSplashScreenDuration(),
        vSplashScreen.getName() + " FadeEffect",
        null);
    
    vSplashScreen.setFadeEffect(vGameFreakFadeEffect);
    
    oSplashScreens.add(vSplashScreen);
    
    
    //create second splash screen and effect
    
    vSplashScreen = new SplashScreen(
        "PkmnIntScreen",
        9000,
        Assets.imgPkmnIntLogo,
        Assets.soundMSIntro);
    
    vPkmnIntFadeEffect = oGame.createFadeEffect(
        Color.white, 
        255, 
        0, 
        -2, 
        1500L,
        vSplashScreen.getSplashScreenDuration(),
        vSplashScreen.getName() + " FadeEffect",
        null);
    
    vSplashScreen.setFadeEffect(vPkmnIntFadeEffect);
    
    oSplashScreens.add(vSplashScreen);
  }
  
  
  public void update()
  {
    boolean vRemovedScreen;
    
    vRemovedScreen = removedFinishedSplashScreen();
    
    if(oSplashScreens.size() > 0)
    {
      if(vRemovedScreen)
      {
        oGame.addFadeEffect(oSplashScreens.get(0).getFadeEffect());
      }
      else if(oCurrentSplashScreen == null)
      {
        oCurrentSplashScreen = oSplashScreens.get(0);
        oCurrentSplashScreen.initSplashScreen();
        oGame.addFadeEffect(oSplashScreens.get(0).getFadeEffect());
      }
      else
      {
        oCurrentSplashScreen.update();
      }
    }
    else
    {
      oIsActive = false;
    }
  }
  
  
  public void draw(Graphics pGraphics)
  {
    if(oCurrentSplashScreen != null)
    {
      oCurrentSplashScreen.draw(pGraphics);
    }
  }
  
  
  private boolean removedFinishedSplashScreen()
  {
    if(oSplashScreens.get(0).isDone())
    {
      oSplashScreens.remove(0);
      oCurrentSplashScreen = null;
      return true;
    }
    
    return false;
  }
  
  
  public void skipSplashScreen()
  {
    if(oCurrentSplashScreen != null)
    {
      oCurrentSplashScreen.setIsDone();
      oCurrentSplashScreen.getFadeEffect().setIsDone();
      oCurrentSplashScreen.closeAudio();
      oGame.interrupt();
    }
  }
  
  
  public boolean isActive()
  {
    return oIsActive;
  }
  
  
  public void setIsActive(boolean pIsActive)
  {
    oIsActive = pIsActive;
  }
  
  
  public SplashScreen getCurrentSplashScreen()
  {
    return oCurrentSplashScreen;
  }
  
  
  public void cleanUpState()
  {
    oIsActive = false;
  }
  
  
  public GameStates getStateType() 
  {
    return oGameStateType;
  }
  
  
  public KeyListener getKeyListener()
  {
    return oIntroStateKeyListener;
  }
}
