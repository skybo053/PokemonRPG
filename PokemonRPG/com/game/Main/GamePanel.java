package com.game.Main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.Exceptions.AssetLoaderException;
import com.game.FX.Assets;
import com.game.States.GameStateManager;
import com.game.States.GameStates;


public class GamePanel extends JPanel implements Runnable, KeyListener
{
  
  //Screen and panel width and height
  private int  displayWidth;
  private int  displayHeight;
  
  //Game loop variables
  private int  FPS;
  private int  frameTicksPerSecond;
  private int  frames;
  
  private long overallSleepTime;
  private long ninetyPercentSleepTime;
  private long remainingSleepTime;
  
  private long startTime;
  private long frameCountStartTime;
  private long endTime;
  
  boolean  run;
  
  //GameStateManager
  private GameStateManager gameStateManager;
  
  
  HudPanel hudPanel        = null;
  Thread   mainThread      = null;
 
  int   x = 0;
  
  
  public GamePanel(int pWidth, int pHeight)
  {
    displayWidth     = pWidth;
    displayHeight    = pHeight;
    
    hudPanel = new HudPanel(pWidth, pHeight);
    hudPanel.setVisible(false);
    
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    this.setBackground(Color.white);
    this.add(hudPanel);
    
    this.addKeyListener(this);
    this.setFocusable(true);
    
    mainThread = new Thread(this);
    mainThread.start();
  }
  
  
  private void init()
  {
    try
    {
      Assets.load();
      gameStateManager = new GameStateManager(displayWidth, displayHeight);
      
      run                    = true;
      FPS                    = 30;
      frameTicksPerSecond    = 1000/FPS;
      frames                 = 0;
      
      startTime              = System.currentTimeMillis();
      frameCountStartTime    = System.currentTimeMillis();
      endTime                = 0L;
      
      overallSleepTime       = 0L;
      ninetyPercentSleepTime = 0L;
      remainingSleepTime     = 0L;
    }
    catch(AssetLoaderException pLoaderException)
    {
      JOptionPane.showMessageDialog(null, pLoaderException.getMessage());
      System.exit(1);
   }
  }
  
  
  public void run()
  {
    init();
    
    while(run)
    {
      try
      {
        ++frames;
        
        gameStateManager.update();                            //updates game
        paintImmediately(0, 0, displayWidth, displayHeight); // draws game
        
        
        startTime             += frameTicksPerSecond;
        endTime                = System.currentTimeMillis();
        overallSleepTime       = startTime - endTime;
        ninetyPercentSleepTime = (long)(overallSleepTime * 0.9);
        
        if(ninetyPercentSleepTime > 0)
        {
          Thread.sleep(ninetyPercentSleepTime);
        }
        
        //after wake up recalculate
        endTime            = System.currentTimeMillis();
        remainingSleepTime = startTime - endTime;
        
        if(remainingSleepTime > 0)
        {
          Thread.sleep(remainingSleepTime);
        }
      }
      catch(InterruptedException e)
      {
       
      }
      
      //frame per second calculations
      endTime = System.currentTimeMillis();
      if(endTime - frameCountStartTime > 1000)
      {
        //System.out.println("FPS: " + frames);
        frameCountStartTime = System.currentTimeMillis();
        frames = 0;
      }
      
    } //end while loop
    
  } //end run()
  
  
  public void paintComponent(Graphics pGraphics)
  {
    if(gameStateManager == null) return;
    
    super.paintComponent(pGraphics);
    
    gameStateManager.draw(pGraphics);
    
    //drawGamePanel(pGraphics);
    //hudPanel.drawHUD();
  }
  
  
  public void drawGamePanel(Graphics pGraphics)
  {
    //clear panel
    pGraphics.setColor(Color.cyan);
    pGraphics.fillRect(0, 0, displayWidth, displayHeight);
    
    pGraphics.drawImage(Assets.imgAshThrow, 0, 0, null);
    
    //draw animation
    pGraphics.setColor(Color.black);
    pGraphics.fillRect(x, 15, 45, 45);
    
    
  }
  
  
  public void keyPressed(KeyEvent pKeyEvent)
  {
    if(pKeyEvent.getKeyCode() == KeyEvent.VK_ESCAPE)
    {
      if(gameStateManager.getCurrentState() == GameStates.INTRO_STATE)
      {
        gameStateManager.getIntroState().skipSplashScreen();
      }
    }
  }
  
  
  public void keyReleased(KeyEvent pKeyEvent)
  {
    
  }
  
  
  public void keyTyped(KeyEvent pKeyEvent) {
    
    
  }
  
}



