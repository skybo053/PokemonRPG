package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.Entities.Player;
import com.game.Exceptions.AssetLoaderException;
import com.game.Exceptions.WorldLoaderException;
import com.game.FX.Assets;
import com.game.FX.FadeEffect;
import com.game.States.GameStateManager;
import com.game.States.GameStates;
import com.game.States.State;


public class GamePanel extends JPanel implements Runnable
{
  public static final int displayWidth;
  public static final int displayHeight;
  
 static
  {
    displayWidth  = Launcher.screenWidth;
    displayHeight = Launcher.screenHeight;
  }
 
  private int     FPS;
  private int     frameTicksPerSecond;
  private long    overallSleepTime;
  private long    ninetyPercentSleepTime;
  private long    remainingSleepTime;
  private long    startTime;
  private long    endTime;
  private boolean running;
  
  private GameStateManager       gameStateManager = null;
  private HudPanel               hudPanel         = null;
  private EffectsPanel           effectsPanel     = null;
  private Thread                 mainThread       = null;
  
  
  public GamePanel(HudPanel pHudPanel, EffectsPanel pEffectsPanel)
  {
    hudPanel     = pHudPanel;
    effectsPanel = pEffectsPanel;
    
    this.setPreferredSize(new Dimension(displayWidth, displayHeight));
    this.setBackground(Color.white);
    this.setLayout(null);
    this.setFocusable(true);
    
    mainThread = new Thread(this, "GameLoop");
    
  }
  
  
  public void start()
  {
    if(running)
    {
      return;
    }
    mainThread.start();
  }
  
  
  private void init()
  {
    try
    {
      Assets.load();
      
      gameStateManager       = new GameStateManager(this);
      
      running                = true;
      FPS                    = 30;
      frameTicksPerSecond    = 1000/FPS;
      
      startTime              = System.currentTimeMillis();
      endTime                = 0L;
      
      overallSleepTime       = 0L;
      ninetyPercentSleepTime = 0L;
      remainingSleepTime     = 0L;
    }
    catch(AssetLoaderException pAssetLoaderException)
    {
      JOptionPane.showMessageDialog(null, pAssetLoaderException.getMessage());
      System.exit(1);
     }
    catch(WorldLoaderException pWorldLoaderException)
    {
      JOptionPane.showMessageDialog(null, pWorldLoaderException.getMessage());
      System.exit(1);
    }
  }
  
  
  public void run()
  {
    init();
    
    while(running)
    {
      try
      {
        
        gameStateManager.update(); 
        hudPanel.update();
        effectsPanel.update();
        
        repaint();
        
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
       continue;
      }
      
    } //end while loop
    
  } //end run()
  
  
  public void paintComponent(Graphics pGraphics)
  {
    super.paintComponent(pGraphics);
    
    if(gameStateManager == null) return;
    
    
    gameStateManager.draw(pGraphics);
    effectsPanel.drawEffect();
    hudPanel.drawHUD();
    
    
  }
  
  
  public FadeEffect createFadeEffect(
      Color  pColor, 
      int    pStartAlpha, 
      int    pEndAlpha,
      int    pDeltaAlpha,
      long   pWaitTime,
      long   pDuration,
      String pName,
      State  pCurrentState)
  {
    FadeEffect vFadeEffect   = null;
    
    vFadeEffect = new FadeEffect(
        pColor, 
        pStartAlpha, 
        pEndAlpha,
        pDeltaAlpha,
        pWaitTime,
        pDuration,
        pName,
        pCurrentState);
    
    return vFadeEffect;
  }
  
  
  public void addFadeEffect(FadeEffect pFadeEffect)
  {
    effectsPanel.addEffect(pFadeEffect);
  }
  
  
  public void interrupt()
  {
    mainThread.interrupt();
  }
  
  
  public void hudShowHUD()
  {
    hudPanel.setVisible(true);
  }

  
  public void hudSetHealthBar(int pHealth)
  {
    hudPanel.setHealthBar(pHealth);
  }
  
  
  public void hudSetPlayer(Player pPlayer)
  {
    hudPanel.setPlayer(pPlayer);
  }
  
  
  public GameStates getCurrentGameStateType()
  {
    return gameStateManager.getCurrentGameStateType();
  }
  
  
  public State getCurrentState()
  {
    return gameStateManager.getCurrentGameState();
  }
  
  
  public void removeKeyListener()
  {
    for(KeyListener vListener : getKeyListeners())
    {
      removeKeyListener(vListener);
    }
  }
  
  
  public void setKeyListener(KeyListener pKeyListener)
  {
    addKeyListener(pKeyListener);
  }
  
} // end GamePanel class
