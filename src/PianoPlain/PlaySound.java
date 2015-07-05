/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PianoPlain;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineEvent.Type;

public class PlaySound{
    
    /*
    public static void main(String args[]) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        try {
            File soundClip = new File(".//src//PianoPlain//wav//b.wav");
            playClip(soundClip);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    
        public static void playClip(File clipFile) throws IOException, 
        UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {
        private boolean done = false;
        @Override public synchronized void update(LineEvent event) {
          Type eventType = event.getType();
          if (eventType == Type.STOP || eventType == Type.CLOSE) {
            done = true;
            notifyAll();
          }
        }
        public synchronized void waitUntilDone() throws InterruptedException {
          while (!done) { wait(); }
        }
      }
      AudioListener listener = new AudioListener();
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
      try {
        Clip clip = AudioSystem.getClip();
        clip.addLineListener(listener);
        clip.open(audioInputStream);
        try {
          clip.start();
          listener.waitUntilDone();
        } finally {
          clip.close();
        }
      } finally {
        audioInputStream.close();
      }
    }
}


