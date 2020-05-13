package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.Entities.Player;
import com.game.Exceptions.AssetLoaderException;
import com.game.Exceptions.InitializeStateException;
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
  private long    sleepTime;
  private long    ninetyPercentSleepTime;
  private long    remainingSleepTime;
  private long    startTime;
  private long    endTime;
  private boolean running;
  
  private GameStateManager       gameStateManager  = null;
  private HudPanel               hudPanel          = null;
  private EffectsPanel           effectsPanel      = null;
  private Thread                 mainThread        = null;
  private Thread                 assetLoaderThread = null;
  
  
  public GamePanel(HudPanel pHudPanel, EffectsPanel pEffectsPanel)
  {
    hudPanel     = pHudPanel;
    effectsPanel = pEffectsPanel;
    
    this.setPreferredSize(new Dimension(displayWidth, displayHeight));
    this.setBackground(Color.white);
    this.setLayout(null);
    this.setFocusable(true);
    
    mainThread        = new Thread(this, "GameLoop");
    assetLoaderThread = new Thread(new AssetLoader(), "AssetLoader");
  }
  
  
  public void start()
  {
    if(running)
    {
      return;
    }
    mainThread.start();
  }
  
  
  public boolean assetsLoaded()
  {
    if(assetLoaderThread.getState() == Thread.State.TERMINATED &&
       Assets.IsLoaded == true)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  
  public boolean assetsLoading()
  {
    if(assetLoaderThread.getState() == Thread.State.RUNNABLE)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  
  private void init()
  {
    try
    {
      assetLoaderThread.start();
      
      gameStateManager       = new GameStateManager(this);
      
      running                = true;
      FPS                    = 30;
      frameTicksPerSecond    = 1000/FPS;
      
      startTime              = System.currentTimeMillis();
      endTime                = 0L;
      
      sleepTime              = 0L;
      ninetyPercentSleepTime = 0L;
      remainingSleepTime     = 0L;
    }
    catch(InitializeStateException pInitializeStateException)
    {
      JOptionPane.showMessageDialog(null, pInitializeStateException.getMessage() + "\n" + pInitializeStateException.getCause().getMessage());
      pInitializeStateException.printStackTrace();
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
        
        startTime  += frameTicksPerSecond;
        endTime     = System.currentTimeMillis();
        sleepTime   = startTime - endTime;
        
        if(sleepTime > 0)
        {
          Thread.sleep(sleepTime);
        }
      }
      catch(AssetLoaderException pAssetLoaderException)
      {
        JOptionPane.showMessageDialog(null, pAssetLoaderException.getMessage());
        System.exit(1);
      }
      catch(InitializeStateException pInitializeStateException)
      {
        JOptionPane.showMessageDialog(null, pInitializeStateException.getMessage());
        System.exit(1);
      }
      catch(InterruptedException pInterruptedException)
      {
       continue;
      }
      
    } //end while loop
    
  } //end run()
  
  
  public void paintComponent(Graphics pGraphics)
  {
    try
    {
      super.paintComponent(pGraphics);
      
      gameStateManager.draw(pGraphics);
      //hudPanel.drawHUD();
      //effectsPanel.drawEffect();
    }
    catch(NullPointerException pNullPointerException)
    {
      return;
    }
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
