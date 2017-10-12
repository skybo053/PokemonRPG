package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.game.FX.Assets;
import com.game.FX.Effects;
import com.game.FX.JukeBox;
import com.game.Main.GamePanel;


public class MenuState implements State
{
  private boolean    isActive          = false;
  private boolean    playBtnSelected   = false;
  private boolean    exitBtnSelected   = false;
  
  private JLabel     playButton        = null;
  private int        playButtonOffset  = 0;
  private JLabel     exitButton        = null;
  private int        exitButtonOffset  = 0;
  private GamePanel  game              = null;
  
  private GameStates gameStateType     = null;
  
  private JukeBox    buttonSoundFX     = null;
  private JukeBox    bgSoundFX         = null;
  private JukeBox    selectPlaySoundFX = null;
  
  private boolean transition = false;
  private boolean sleepEDT   = false;
  private int     alphaValue;
  private int     deltaAlpha;
  
  private long    transitionStartTime;
  private long    transitionLengthTime;
  
  
  public MenuState(GamePanel pGamePanel)
  {
    isActive      = false;
    game          = pGamePanel;
    gameStateType = GameStates.MENU_STATE;
    
    buttonSoundFX    = new JukeBox(Assets.soundMainMenuBtnSelect);
    bgSoundFX         = new JukeBox(Assets.soundMainMenuBGMusic);
    selectPlaySoundFX = new JukeBox(Assets.soundMainMenuPlayBtnEnter);
    
    playButtonOffset = Assets.getWidth(Assets.imgMenuPlayBtn) / 2;
    exitButtonOffset = Assets.getWidth(Assets.imgMenuExitBtn) / 2;
  
    alphaValue           = 0;
    deltaAlpha           = 3;
    transitionLengthTime = 4000L;
  }
  
  
  public void setUpState()
  {
    isActive = true;
    
    playButton = new JLabel(new ImageIcon(Assets.imgMenuPlayBtn)); 
    exitButton = new JLabel(new ImageIcon(Assets.imgMenuExitBtn));
    
    game.setLayout(null);
    game.add(playButton);
    game.add(exitButton);
    
    playButton.setBounds(
        GamePanel.displayWidth /2 - playButtonOffset, 
        calcPercentFromTop(0.35), 
        Assets.getWidth(Assets.imgMenuPlayBtn), 
        Assets.getHeight(Assets.imgMenuPlayBtn));
    
    exitButton.setBounds(
        GamePanel.displayWidth /2 - exitButtonOffset, 
        calcPercentFromTop(0.42), 
        Assets.getWidth(Assets.imgMenuExitBtn), 
        Assets.getHeight(Assets.imgMenuExitBtn));
    
    bgSoundFX.setLoopContinuous();
    bgSoundFX.play();
  }
  
  
  public void update() 
  {
    if(transition)
    {
      transition = false;
      System.out.println("MenuState.update - calling Effects.fade");
      Effects.fade(game, 0, 1, Color.black, 7000);
      isActive = false;
    }
  /* if(transition)
    {
      long vNow     = System.currentTimeMillis();
      long vElapsed = vNow - transitionStartTime;
      
      if(vElapsed <= transitionLengthTime)
      {
        if(alphaValue + deltaAlpha <= 255)
        {
          alphaValue += deltaAlpha;
        }
        else 
        {
          alphaValue = 255;
        }
      }
      else
      {
        isActive = false;
      }
    }*/
  }
  
  
  public void draw(Graphics pGraphics) 
  {
    pGraphics.drawImage(
        Assets.imgPkmnMenuBg, 
        0, 
        0, 
        GamePanel.displayWidth, 
        GamePanel.displayHeight, 
        null, 
        null);
     
    /* pGraphics.setColor(new Color(0, 0, 0, alphaValue));
     pGraphics.fillRect(0, 0, GamePanel.displayWidth, GamePanel.displayHeight);*/
   }
  
  
  
  public boolean isActive()
  {
    return isActive;
  }
  
  
  public void setIsActive(boolean pIsActive)
  {
    isActive = pIsActive;
  }
  
  
  private int calcPercentFromTop(double pPercent)
  {
    return (int)(GamePanel.displayHeight * pPercent);
  }
  
  
  public void setPlayButtonIcon(BufferedImage pImage)
  {
        playButton.setIcon(new ImageIcon(pImage));
   }
  
  
  public void setExitButtonIcon(BufferedImage pImage)
  {
        exitButton.setIcon(new ImageIcon(pImage));
  }
  
  
  public boolean playBtnIsSelected()
  {
    return playBtnSelected;
  }
  
  
  public boolean exitBtnIsSelected()
  {
    return exitBtnSelected;
  }
  
  
  public void setPlayBtnSelected(boolean pPlayBtnSelected)
  {
    playBtnSelected = pPlayBtnSelected;
  }
  
  
  public void setExitBtnSelected(boolean pExitBtnSelected)
  {
    exitBtnSelected = pExitBtnSelected;
  }
  
  
  public void cleanUpState()
  {
    isActive = false;
    
    game.remove(playButton);
    game.remove(exitButton);
    game.validate();
    
    if(buttonSoundFX.isPlaying())
    {
      buttonSoundFX.stop();
    }
    
    if(bgSoundFX.isPlaying())
    {
      bgSoundFX.stop();
    }
    
    bgSoundFX.close();
    buttonSoundFX.close();
  }
  
  
  public GameStates getStateType() 
  {
    return gameStateType;
  }
  
  
  public void playBTNSoundFX()
  {
    buttonSoundFX.play();
  }
  
  
  public void rewindBTNSoundFX()
  {
    buttonSoundFX.rewind();
  }
  
  
  public void playSelectPlaySoundFX()
  {
    selectPlaySoundFX.play();
  }
  
  
  public void rewindSelectPlaySoundFX()
  {
    selectPlaySoundFX.rewind();
  }
  
  
  public void startTransition()
  {
    System.out.println("menuState. startTransition");
    transition          = true;
    transitionStartTime = System.currentTimeMillis();
  }
  
  
  public void removeMenuButtons()
  {
    game.remove(playButton);
    game.remove(exitButton);
    game.validate();
  }
  
  
      
}
