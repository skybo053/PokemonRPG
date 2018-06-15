package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.game.EventHandlers.MenuStateKeyListener;
import com.game.FX.Assets;
import com.game.FX.FadeEffect;
import com.game.FX.JukeBox;
import com.game.Main.GamePanel;


public class MenuState implements State
{
  private final int NUM_MENU_OPTIONS       = 3;
  
  private static final int PLAY_BTN        = 0;
  private static final int OPTIONS_BTN     = 1;
  private static final int EXIT_BTN        = 2;
  
  private int     oCurrentMenuPosition      = 0;
  private boolean oMenuPositionChanged      = false;
  
  private boolean    oIsActive              = false;
  private boolean    oPlayBtnSelected       = false;
  private boolean    oOptionsBtnSelected    = false;
  private boolean    oExitBtnSelected       = false;
  
  private JLabel     oPlayButton            = null;
  private JLabel     oOptionsButton         = null;  
  private JLabel     oExitButton            = null;
  private int        oBtnOffset             = 0;
  
  private GamePanel  oGame                  = null;
  private GameStates oGameStateType         = null;
  
  private JukeBox    oButtonSoundFX         = null;
  private JukeBox    oBgSoundFX             = null;
  private JukeBox    oSelectPlayBtnSoundFX  = null;
  
  private FadeEffect oFadeEffect            = null;
  
  private KeyListener oMenuStateKeyListener = null;
  
  
  public MenuState(GamePanel pGamePanel)
  {
    oIsActive      = false;
    oGame          = pGamePanel;
    oGameStateType = GameStates.MENU_STATE;
    
    oButtonSoundFX        = new JukeBox(Assets.soundMainMenuBtnSelect);
    oBgSoundFX            = new JukeBox(Assets.soundMainMenuBGMusic);
    oSelectPlayBtnSoundFX = new JukeBox(Assets.soundMainMenuPlayBtnEnter);
    
    oPlayButton      = new JLabel(new ImageIcon(Assets.imgMenuSelectedPlayBtn));
    oPlayBtnSelected = true;
    
    oOptionsButton   = new JLabel(new ImageIcon(Assets.imgMenuUnselectedOptionsBtn));
    oExitButton      = new JLabel(new ImageIcon(Assets.imgMenuExitBtn));
    
    oBtnOffset       = Assets.getWidth(Assets.imgMenuPlayBtn) / 2;
    
    oMenuStateKeyListener = new MenuStateKeyListener(this);
  }
  
  
  public void initializeState()
  {
    oIsActive = true;
    
    oPlayButton.setBounds(
        GamePanel.displayWidth /2 - oBtnOffset, 
        calcPercentFromTop(0.35), 
        Assets.getWidth(Assets.imgMenuPlayBtn), 
        Assets.getHeight(Assets.imgMenuPlayBtn));
    
    oOptionsButton.setBounds(
        GamePanel.displayWidth / 2 - oBtnOffset, 
        calcPercentFromTop(.42), 
        Assets.getWidth(Assets.imgMenuUnselectedOptionsBtn), 
        Assets.getHeight(Assets.imgMenuUnselectedOptionsBtn));
    
    oExitButton.setBounds(
        GamePanel.displayWidth /2 - oBtnOffset, 
        calcPercentFromTop(0.49), 
        Assets.getWidth(Assets.imgMenuExitBtn), 
        Assets.getHeight(Assets.imgMenuExitBtn));
   
    
    oGame.add(oPlayButton);
    oGame.add(oOptionsButton);
    oGame.add(oExitButton);
    
    oButtonSoundFX.open();
    oBgSoundFX.open();
    oSelectPlayBtnSoundFX.open();
    
    oBgSoundFX.setLoopContinuous();
    oBgSoundFX.play();
    
    oGame.removeKeyListener();
    oGame.setKeyListener(oMenuStateKeyListener);
  }
  
  
  public void update() 
  {
    if(oMenuPositionChanged)
    {
      oMenuPositionChanged = false;
      resetMenu();
      
      switch(oCurrentMenuPosition)
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
    oExitBtnSelected = true;
  }
  
  
  public void optionsButtonSelected()
  {
    setOptionsButtonIcon(Assets.imgMenuFocusOptionsBtn);
    oOptionsBtnSelected = true;
  }
  
  
  public void playButtonSelected()
  {
    setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
    oPlayBtnSelected = true;
  }
  
  
  public void resetMenu()
  {
    oPlayButton.setIcon(new ImageIcon(Assets.imgMenuPlayBtn));
    oOptionsButton.setIcon(new ImageIcon(Assets.imgMenuUnselectedOptionsBtn));
    oExitButton.setIcon(new ImageIcon(Assets.imgMenuExitBtn));
    
    oPlayBtnSelected    = false;
    oOptionsBtnSelected = false;
    oExitBtnSelected    = false;
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
    return oIsActive;
  }
  
  
  public void setIsActive(boolean pIsActive)
  {
    oIsActive = pIsActive;
  }
  
  
  private int calcPercentFromTop(double pPercent)
  {
    return (int)(GamePanel.displayHeight * pPercent);
  }
  
  
  public void setPlayButtonIcon(BufferedImage pImage)
  {
        oPlayButton.setIcon(new ImageIcon(pImage));
   }
  
  public void setOptionsButtonIcon(BufferedImage pImage)
  {
    oOptionsButton.setIcon(new ImageIcon(pImage));
  }
  
  
  public void setExitButtonIcon(BufferedImage pImage)
  {
        oExitButton.setIcon(new ImageIcon(pImage));
  }
  
  
  public boolean playBtnIsSelected()
  {
    return oPlayBtnSelected;
  }
  
  
  public boolean optionsBtnIsSelected()
  {
    return oOptionsBtnSelected;
  }
  
  
  public boolean exitBtnIsSelected()
  {
    return oExitBtnSelected;
  }
  
  
  
  public void cleanUpState()
  {
    oIsActive = false;
    
    oGame.remove(oPlayButton);
    oGame.remove(oOptionsButton);
    oGame.remove(oExitButton);
    
    oButtonSoundFX.stop();
    oBgSoundFX.stop();
    oSelectPlayBtnSoundFX.stop();
    
    oBgSoundFX.close();
    oButtonSoundFX.close();
    oSelectPlayBtnSoundFX.close();
    
    oGame.validate();
  }
  
  
  public GameStates getStateType() 
  {
    return oGameStateType;
  }
  
  
  public void playBTNSoundFX()
  {
    oButtonSoundFX.play();
  }
  
  
  public void rewindBTNSoundFX()
  {
    oButtonSoundFX.rewind();
  }
  
  
  public void playSelectPlayBtnSoundFX()
  {
    oSelectPlayBtnSoundFX.play();
  }
  
  
  public void rewindSelectPlaySoundFX()
  {
    oSelectPlayBtnSoundFX.rewind();
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
    
   vFadeEffect =  oGame.createFadeEffect(
       pColor, 
       pStartAlpha, 
       pEndAlpha, 
       pDeltaAlpha, 
       pWaitTime, 
       pDuration,
       getStateType().name() + " FadeEffect",
       this);
   
   oGame.addFadeEffect(vFadeEffect);
  }
  
  
  public void disableGamePanel()
  {
    oGame.setFocusable(false);
  }
  
  
  public void moveMenuPositionDown()
  {
    if(oCurrentMenuPosition == NUM_MENU_OPTIONS - 1)
    {
      oCurrentMenuPosition = 0;
    }
    else
    {
      ++oCurrentMenuPosition;
    }
    oMenuPositionChanged = true;
  }
  
  
  public void moveMenuPositionUp()
  {
    if(oCurrentMenuPosition == 0)
    {
      oCurrentMenuPosition = NUM_MENU_OPTIONS - 1;
    }
    else
    {
      --oCurrentMenuPosition;
    }
    
    oMenuPositionChanged = true;
  }
}
