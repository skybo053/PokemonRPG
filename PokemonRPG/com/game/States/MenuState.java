package com.game.States;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.game.FX.Assets;
import com.game.Main.GamePanel;

public class MenuState implements State
{
  private boolean   isActive        = false;
  private boolean   menuInitialized = false;
  
  private JLabel    playButton     = null;
  private JLabel    exitButton     = null;
  private GamePanel game           = null;
  
  
  public MenuState(GamePanel pGamePanel)
  {
    isActive   = false;
    playButton = new JLabel(new ImageIcon(Assets.imgMenuPlayBtn)); 
    exitButton = new JLabel(new ImageIcon(Assets.imgMenuExitBtn));
    
    game = pGamePanel;
  }
  
  
  public void update() 
  {
    if(menuInitialized == false)
    {
      setupMenu();
    }
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
  
  
  private void setupMenu()
  {
    GridBagConstraints vGBC          = null;
    int                vTopMargin    = 0;
    int                vBottomMargin = 0;
    
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
    
    
    menuInitialized = true;
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
  
  
  public void setPlayButtonIcon(ImageIcon pIcon)
  {
    playButton.setIcon(pIcon);
  }
}
