import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
   
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipTest extends JFrame{
   
   // Constructor
    /**
     *
     * @param filename
     */
       public void PlaySound(String filename) {
      /*this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Test Sound Clip");
      this.setSize(300, 200);
      this.setVisible(true);
   */
      AudioInputStream audioIn = null;
      URL url;
      Clip clip = null;
      
      try {
         // Open an audio input stream.
         url = this.getClass().getClassLoader().getResource(filename);
         audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
         //audioIn.close();
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      }finally{
        try{
            audioIn.close();
            audioIn = null;
            clip = null;
        }catch(Exception ex){
        System.out.println("not closed");
            }
        }
   }
   
   /*public static void main(String[] args) {
      new SoundClipTest("\\wav\\a.wav");
   }*/
}