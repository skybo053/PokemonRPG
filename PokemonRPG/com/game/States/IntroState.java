package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.game.EventHandlers.IntroStateKeyListener;
import com.game.Exceptions.InitializeStateException;
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
  
  public static final String GameFreakImgFile = "Resources/Images/GameFreakLogo.png";
  public static final String PkmnIntLogoFile  = "Resources/Images/PkmnIntLogo.png";
  public static final String MSIntroWavFile   = "Resources/Sounds/MSIntroWAV.wav";
  
  
  public IntroState(GamePanel pGame)
  {
    oSplashScreens         = new ArrayList<>();
    oIsActive              = false;
    oGame                  = pGame;
    oGameStateType         = GameStates.INTRO_STATE;
    oIntroStateKeyListener = new IntroStateKeyListener(this);
  }
  
  
  public void initializeState() throws InitializeStateException
  {
    FadeEffect       vGameFreakFadeEffect = null;
    FadeEffect       vPkmnIntFadeEffect   = null;
    SplashScreen     vSplashScreen        = null;
    BufferedImage    vImgGameFreakLogo    = null;
    BufferedImage    vPkmnIntLogo         = null;
    AudioInputStream vSoundMSIntro        = null;
    
    try
    {
      oIsActive = true;
      
      vImgGameFreakLogo = ImageIO.read(new File(GameFreakImgFile));
      vPkmnIntLogo      = ImageIO.read(new File(PkmnIntLogoFile));
      vSoundMSIntro     = AudioSystem.getAudioInputStream(new File(MSIntroWavFile));
      
      
      vSplashScreen = new SplashScreen(
          "GameFreakScreen", 
          6000,
          vImgGameFreakLogo, 
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
          vPkmnIntLogo,
          vSoundMSIntro);
      
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
    catch(IOException pIOException)
    {
      throw new InitializeStateException("IntroState.initializeState - " +
                                          pIOException.getMessage());
    }
    catch(UnsupportedAudioFileException pUnsupportedAudioFileException)
    {
      throw new InitializeStateException("IntroState.initializeState - " +
                                          pUnsupportedAudioFileException.getMessage());
    }
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
