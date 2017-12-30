package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.game.FX.Assets;
import com.game.FX.FadeEffect;
import com.game.FX.JukeBox;
import com.game.Main.GamePanel;


public class MenuState implements State
{
  private final int NUM_MENU_OPTIONS = 3;
  
  private static final int PLAY_BTN    = 0;
  private static final int OPTIONS_BTN = 1;
  private static final int EXIT_BTN    = 2;
  
  private int     currentMenuPosition = 0;
  private boolean menuPositionChanged = false;
  
  private boolean    isActive           = false;
  private boolean    playBtnSelected    = false;
  private boolean    optionsBtnSelected = false;
  private boolean    exitBtnSelected    = false;
  
  private JLabel     playButton        = null;
  private JLabel     optionsButton     = null;  
  private JLabel     exitButton        = null;
  private int        btnOffset         = 0;
  
  private GamePanel  game              = null;
  private GameStates gameStateType     = null;
  
  private JukeBox    buttonSoundFX        = null;
  private JukeBox    bgSoundFX            = null;
  private JukeBox    selectPlayBtnSoundFX = null;
  
  private FadeEffect fadeEffect           = null;
  
  public MenuState(GamePanel pGamePanel)
  {
    isActive      = false;
    game          = pGamePanel;
    gameStateType = GameStates.MENU_STATE;
    
    buttonSoundFX        = new JukeBox(Assets.soundMainMenuBtnSelect);
    bgSoundFX            = new JukeBox(Assets.soundMainMenuBGMusic);
    selectPlayBtnSoundFX = new JukeBox(Assets.soundMainMenuPlayBtnEnter);
    
    playButton      = new JLabel(new ImageIcon(Assets.imgMenuSelectedPlayBtn));
    playBtnSelected = true;
    
    optionsButton   = new JLabel(new ImageIcon(Assets.imgMenuUnselectedOptionsBtn));
    exitButton      = new JLabel(new ImageIcon(Assets.imgMenuExitBtn));
    
    btnOffset = Assets.getWidth(Assets.imgMenuPlayBtn) / 2;
  }
  
  
  public void initializeState()
  {
    isActive = true;
    
    playButton.setBounds(
        GamePanel.displayWidth /2 - btnOffset, 
        calcPercentFromTop(0.35), 
        Assets.getWidth(Assets.imgMenuPlayBtn), 
        Assets.getHeight(Assets.imgMenuPlayBtn));
    
    optionsButton.setBounds(
        GamePanel.displayWidth / 2 - btnOffset, 
        calcPercentFromTop(.42), 
        Assets.getWidth(Assets.imgMenuUnselectedOptionsBtn), 
        Assets.getHeight(Assets.imgMenuUnselectedOptionsBtn));
    
    exitButton.setBounds(
        GamePanel.displayWidth /2 - btnOffset, 
        calcPercentFromTop(0.49), 
        Assets.getWidth(Assets.imgMenuExitBtn), 
        Assets.getHeight(Assets.imgMenuExitBtn));
   
    
    game.add(playButton);
    game.add(optionsButton);
    game.add(exitButton);
    
    buttonSoundFX.open();
    bgSoundFX.open();
    selectPlayBtnSoundFX.open();
    
    bgSoundFX.setLoopContinuous();
    bgSoundFX.play();
  }
  
  
  public void update() 
  {
    if(menuPositionChanged)
    {
      menuPositionChanged = false;
      resetMenu();
      
      switch(currentMenuPosition)
      {
      case PLAY_BTN:
        playButtonSelected();
        break;
      case OPTIONS_BTN:
        optionsButtonSelected();
        break;
      case EXIT_BTN:
        exitButtonSelected();
        break;
      }
      playBTNSoundFX();
      rewindBTNSoundFX();
    }
  }
  
  
  public void exitButtonSelected()
  {
    setExitButtonIcon(Assets.imgMenuSelectedExitBtn);
    exitBtnSelected = true;
  }
  
  
  public void optionsButtonSelected()
  {
    setOptionsButtonIcon(Assets.imgMenuFocusOptionsBtn);
    optionsBtnSelected = true;
  }
  
  
  public void playButtonSelected()
  {
    setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
    playBtnSelected = true;
  }
  
  
  public void resetMenu()
  {
    playButton.setIcon(new ImageIcon(Assets.imgMenuPlayBtn));
    optionsButton.setIcon(new ImageIcon(Assets.imgMenuUnselectedOptionsBtn));
    exitButton.setIcon(new ImageIcon(Assets.imgMenuExitBtn));
    
    playBtnSelected    = false;
    optionsBtnSelected = false;
    exitBtnSelected    = false;
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
  
  public void setOptionsButtonIcon(BufferedImage pImage)
  {
    optionsButton.setIcon(new ImageIcon(pImage));
  }
  
  
  public void setExitButtonIcon(BufferedImage pImage)
  {
        exitButton.setIcon(new ImageIcon(pImage));
  }
  
  
  public boolean playBtnIsSelected()
  {
    return playBtnSelected;
  }
  
  
  public boolean optionsBtnIsSelected()
  {
    return optionsBtnSelected;
  }
  
  
  public boolean exitBtnIsSelected()
  {
    return exitBtnSelected;
  }
  
  
  
  public void cleanUpState()
  {
    isActive = false;
    
    game.remove(playButton);
    game.remove(optionsButton);
    game.remove(exitButton);
    
    buttonSoundFX.stop();
    bgSoundFX.stop();
    selectPlayBtnSoundFX.stop();
    
    bgSoundFX.close();
    buttonSoundFX.close();
    selectPlayBtnSoundFX.close();
    
    game.validate();
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
  
  
  public void playSelectPlayBtnSoundFX()
  {
    selectPlayBtnSoundFX.play();
  }
  
  
  public void rewindSelectPlaySoundFX()
  {
    selectPlayBtnSoundFX.rewind();
  }
  
  
  public void addFadeEffect(
      Color pColor, 
      int   pStartAlpha, 
      int   pEndAlpha, 
      int   pDeltaAlpha,
      long  pWaitTime,
      long  pDuration)
  {
    FadeEffect vFadeEffect = null;
    
   vFadeEffect =  game.createFadeEffect(
       pColor, 
       pStartAlpha, 
       pEndAlpha, 
       pDeltaAlpha, 
       pWaitTime, 
       pDuration,
       getStateType().name() + " FadeEffect",
       this);
   
   game.addFadeEffect(vFadeEffect);
  }
  
  
  public void disableGamePanel()
  {
    game.setFocusable(false);
  }
  
  
  public void moveMenuPositionDown()
  {
    if(currentMenuPosition == NUM_MENU_OPTIONS - 1)
    {
      currentMenuPosition = 0;
    }
    else
    {
      ++currentMenuPosition;
    }
    menuPositionChanged = true;
  }
  
  
  public void moveMenuPositionUp()
  {
    if(currentMenuPosition == 0)
    {
      currentMenuPosition = NUM_MENU_OPTIONS - 1;
    }
    else
    {
      --currentMenuPosition;
    }
    
    menuPositionChanged = true;
  }
}
