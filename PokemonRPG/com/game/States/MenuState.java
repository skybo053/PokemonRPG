package com.game.States;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.game.FX.Assets;
import com.game.FX.JukeBox;
import com.game.Main.GamePanel;


public class MenuState implements State
{
  private boolean    isActive        = false;
  private boolean    playBtnSelected = false;
  private boolean    exitBtnSelected = false;
  
  private JLabel     playButton      = null;
  private JLabel     exitButton      = null;
  private GamePanel  game            = null;
  
  private GameStates gameStateType   = null;
  
  private JukeBox    buttonSoundFX   = null;
  private JukeBox    bgSoundFX       = null;
  
  
  public MenuState(GamePanel pGamePanel)
  {
    isActive      = false;
    game          = pGamePanel;
    gameStateType = GameStates.MENU_STATE;
    
    buttonSoundFX = new JukeBox(Assets.soundMainMenuBtnSelect);
    bgSoundFX     = new JukeBox(Assets.soundMainMenuBGMusic);
  }
  
  
  public void setUpState()
  {
    GridBagConstraints vGBC          = null;
    int                vTopMargin    = 0;
    int                vBottomMargin = 0;
    
    isActive = true;
    
    playButton = new JLabel(new ImageIcon(Assets.imgMenuPlayBtn)); 
    exitButton = new JLabel(new ImageIcon(Assets.imgMenuExitBtn));
    
    vGBC          = new GridBagConstraints();
    vTopMargin    = calcPercentFromTop();
    vBottomMargin = calcPercentFromBottom();
    
    game.setLayout(new GridBagLayout());
    
    //setting constraints for play button
    vGBC.weighty = 1.0;
    vGBC.gridy   = 0;
    vGBC.anchor  = GridBagConstraints.NORTH;
    vGBC.insets  = new Insets(vTopMargin,0,0,0);
    game.addComponent(playButton, vGBC);
    
    //setting constraints for Exit button
    vGBC.gridy  = 1;
    vGBC.insets = new Insets(0,0,vBottomMargin,0);
    game.addComponent(exitButton, vGBC);
    
    bgSoundFX.setLoop(JukeBox.LOOP_CONTINUOUSLY);
    bgSoundFX.play();
    
  }
  
  
  public void update() 
  {
    
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
  
  
  private int calcPercentFromTop()
  {
    double percentFromTop = .30;
    
    return (int)(GamePanel.displayHeight * percentFromTop);
  }
  
  
  private int calcPercentFromBottom()
  {
    double percentFromBottom = .50;
    
    return (int)(GamePanel.displayHeight * percentFromBottom);
  }
  
  
  public void setPlayButtonIcon(BufferedImage pImage)
  {
        playButton.setIcon(new ImageIcon(pImage));
   }
  
  
  public void setExitButtonIcon(BufferedImage pImage)
  {
        exitButton.setIcon(new ImageIcon(pImage));
  }
  
  public boolean playSelected()
  {
    return playBtnSelected;
  }
  
  
  public boolean exitSelected()
  {
    return exitBtnSelected;
  }
  
  
  public void setPlaySelected(boolean pSelected)
  {
    playBtnSelected = pSelected;
  }
  
  
  public void setExitSelected(boolean pSelected)
  {
    exitBtnSelected = pSelected;
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
      bgSoundFX.close();
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
  
  
  
  
      
}
