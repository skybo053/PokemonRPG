package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import com.game.FX.FadeEffect;

public class EffectsPanel extends JPanel
{
  int displayWidth;
  int displayHeight;
  
  ArrayList<FadeEffect> effectsList  = null;
  
  public EffectsPanel(int pDisplayWidth, int pDisplayHeight)
  {
    displayWidth  = pDisplayWidth;
    displayHeight = pDisplayHeight;
    
    effectsList   = new ArrayList<FadeEffect>();
    
    this.setOpaque(false);
  }
  
  
  public void update()
  {
    Iterator<FadeEffect> vIt         = null;
    FadeEffect           vFadeEffect = null;
    
   if(effectsList.isEmpty() == false)
   {
     vIt = effectsList.iterator();
     
     while(vIt.hasNext())
     {
       vFadeEffect = vIt.next();
       
       if(vFadeEffect.isDone())
       {
         System.out.println("removing fadeEffect");
         vIt.remove();
       }
       else
       {
         vFadeEffect.update();
       }
     }
   }
 }
  
  
  public void drawEffect()
  {
    //this.paintImmediately(0, 0, displayWidth, displayHeight);
    repaint();
  }
  
  
  public void paintComponent(Graphics pGraphics)
  {
    super.paintComponent(pGraphics);
    
    if(effectsList.isEmpty()) return;
    
    effectsList.get(0).draw(pGraphics);
  }
  
  
  public void addEffect(FadeEffect pFadeEffect)
  {
    effectsList.add(pFadeEffect);
  }
}
