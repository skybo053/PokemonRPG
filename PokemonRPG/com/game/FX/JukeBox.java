package com.game.FX;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class JukeBox 
{
  Clip audio = null;
  
  public static final int LOOP_CONTINUOUSLY = Clip.LOOP_CONTINUOUSLY;
  
  public JukeBox(AudioInputStream pAudioInputStream)
  {
    try
    {
      audio = AudioSystem.getClip();
      audio.open(pAudioInputStream);
    }
    catch(LineUnavailableException pException)
    {
      System.out.println("Error opening audioStream " + pAudioInputStream);
    }
    catch(IOException pIOException)
    {
      System.out.println("Error opening audioStream " + pAudioInputStream);
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
  
  
  public void setLoop(int pLoopSetting)
  {
    audio.loop(pLoopSetting);
  }
}
