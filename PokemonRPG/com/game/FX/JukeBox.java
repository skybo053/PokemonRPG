package com.game.FX;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class JukeBox 
{
  Clip             audio            = null;
  AudioInputStream audioInputStream = null;
  
  public JukeBox(AudioInputStream pAudioInputStream)
  {
    try
    {
      audio            = AudioSystem.getClip();
      audioInputStream = pAudioInputStream;
      
    }
    catch(LineUnavailableException pException)
    {
      System.out.println("Error opening audioStream " + pAudioInputStream);
    }
  }

  
  public void open()
  {
    try
    {
      audio.open(audioInputStream);
    }
    catch(IOException pException)
    {
      System.out.println("Jukebox.open() - " + pException.getMessage());
    }
    catch(LineUnavailableException pException)
    {
      System.out.println("Jukebox.open() " + pException.getMessage());
    }
  }
  
  
  public void rewind()
  {
    audio.setFramePosition(0);
  }
  
  
  public boolean isOpen()
  {
    return audio.isOpen();
  }
  
  
  public void play() 
  {
    audio.start();
  }
  
  
  public void stop()
  {
    audio.stop();
  }
  
  
  public void close()
  {
    audio.close();
  }
  
  
  public boolean isPlaying()
  {
    return audio.isActive();
  }
  
  
  public long getCurrentFrame()
  {
    return audio.getLongFramePosition();
  }
  
  
  public int getTotalFrames()
  {
    return audio.getFrameLength();
  }
  
  
  public void setLoopContinuous()
  {
    audio.loop(Clip.LOOP_CONTINUOUSLY);
  }
}
