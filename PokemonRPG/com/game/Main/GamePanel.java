package com.game.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.EventHandlers.KeyHandler;
import com.game.Exceptions.AssetLoaderException;
import com.game.FX.Assets;
import com.game.States.GameStateManager;


public class GamePanel extends JPanel implements Runnable
{
  //Screen and panel width and height
  public static final int displayWidth;
  public static final int displayHeight;
  
  static
  {
    displayWidth  = Launcher.screenWidth;
    displayHeight = Launcher.screenHeight;
  }
  
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
  
  private HudPanel hudPanel    = null;
  private Thread   mainThread  = null;
 
  
  public GamePanel()
  {
    hudPanel = new HudPanel(displayWidth, displayHeight);
    hudPanel.setVisible(false);
    
    //this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    this.setLayout(null);
    this.setBackground(Color.white);
    this.add(hudPanel);
    this.setFocusable(true);
    
    mainThread = new Thread(this);
    mainThread.start();
  }
  
  
  private void addListeners()
  {
    this.addKeyListener(new KeyHandler(gameStateManager));
  }
  
  
  private void init()
  {
    try
    {
      Assets.load();
      gameStateManager = new GameStateManager(this);
      addListeners();
      
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
  
  
  public void addComponent(JComponent pComponent, GridBagConstraints pGBC)
  {
    this.add(pComponent, pGBC);
    this.validate();
  }
  
  
  public void interrupt()
  {
    mainThread.interrupt();
  }
  
 
  
  
} // end GamePanel class
