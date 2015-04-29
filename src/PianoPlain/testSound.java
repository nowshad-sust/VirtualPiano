package PianoPlain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sun.applet.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Al-amin
 */
public class testSound {
    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private File soundFile1;
    private AudioInputStream audioStream;
    private AudioInputStream audioStream1;
    //private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private SourceDataLine sourceLine1;
    //private DataLine.Info info;
    //private ArrayList<AudioFormat> audioFormat;
    //private ArrayList<DataLine.Info> info;
    private final AudioFormat[] audioFormat = new AudioFormat[3];
    private final DataLine.Info[]  info= new DataLine.Info[3];
    // now Load & play in different functions
    
    // loadSounds()
        //files names are passed
        //create an array of sourceLine naming each same as the filename
        //details
            //an array of filenames are passed as parameter
            //load all files differently
    public void LoadSound(){
        try {
                System.out.println("Loading: a");
                soundFile = new File("src//wav//a.wav");
                System.out.println("Loading: b");
                soundFile1 = new File("src//wav//b.wav");
            } catch (Exception e) {
                System.exit(1);
            }

            try {
                audioStream = AudioSystem.getAudioInputStream(soundFile);
                audioStream1 = AudioSystem.getAudioInputStream(soundFile1);
            } catch (UnsupportedAudioFileException | IOException e){
                System.exit(1);
            }
                    audioFormat[0] = audioStream.getFormat();
                    info[0] = new DataLine.Info(SourceDataLine.class, audioFormat[1]);
                    audioFormat[1] = audioStream1.getFormat();
                    info[1] = new DataLine.Info(SourceDataLine.class, audioFormat[1]);   
            }
    
    /*public void LoadSounds(String fileNames[]){
        for (String strFilename : fileNames) {
            try {
                System.out.println("Loading: " + strFilename );
                soundFile = new File(strFilename);
            } catch (Exception e) {
                System.exit(1);
            }

            try {
                audioStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (UnsupportedAudioFileException | IOException e){
                System.exit(1);
            }
            if(null != strFilename)switch (strFilename) {
                case "src//wav//a.wav":
                    audioFormat[0] = audioStream.getFormat();
                    info[0] = new DataLine.Info(SourceDataLine.class, audioFormat[1]);
                    //System.out.println(audioFormat[0] + " " + info[0]);
                    break;
                case "src//wav//b.wav":
                    audioFormat[1] = audioStream.getFormat();
                    info[1] = new DataLine.Info(SourceDataLine.class, audioFormat[1]);
                    //System.out.println(audioFormat[0] + " " + info[0]);
                    break;
                case "src//wav//c.wav":
                    audioFormat[2] = audioStream.getFormat();
                    info[2] = new DataLine.Info(SourceDataLine.class, audioFormat[1]);
                    //System.out.println(audioFormat[0] + " " + info[0]);
                    break;
                
            }
            System.out.println("Loaded " + strFilename );
        }
        System.out.println("Sound Files Loaded");
        for (int i=0;i<3;i++) {
            System.out.println(audioFormat[i] + " " + info[i]);
        }
    }*/
    // playSounds()
        //a single filename is received
        //finds the named file from the array of sourceLine
        // & play it
        // then release the memory
    public void playSounds(String strFilename){
        try {
                if(null != strFilename)switch (strFilename) {
                case "src//wav//a.wav":
                    sourceLine = (SourceDataLine) AudioSystem.getLine(info[0]);
                    sourceLine.open(audioFormat[0]);
                    sourceLine.start();
                    int nBytesRead1 = 0;
                    byte[] abData1 = new byte[BUFFER_SIZE];
                    while (nBytesRead1 != -1) {
                        try {
                            nBytesRead1= audioStream.read(abData1, 0, abData1.length);
                        } catch (IOException e) {
                        }
                        if (nBytesRead1 >= 0) {
                            @SuppressWarnings("unused")
                            int nBytesWritten = sourceLine.write(abData1, 0, nBytesRead1);
                        }
                    }

                    sourceLine.drain();
                    sourceLine.close();
                    break;
                case "src//wav//b.wav":
                    sourceLine = (SourceDataLine) AudioSystem.getLine(info[1]);
                    sourceLine.open(audioFormat[1]);
                    sourceLine.start();
                    int nBytesRead2 = 0;
                    byte[] abData2 = new byte[BUFFER_SIZE];
                    while (nBytesRead2 != -1) {
                        try {
                            nBytesRead2 = audioStream.read(abData2, 0, abData2.length);
                        } catch (IOException e) {
                        }
                        if (nBytesRead2 >= 0) {
                            @SuppressWarnings("unused")
                            int nBytesWritten = sourceLine.write(abData2, 0, nBytesRead2);
                        }
                    }

                    sourceLine.drain();
                    sourceLine.close();
                    break;
                case "src//wav//c.wav":
                    System.out.println("Playing c");
                    sourceLine = (SourceDataLine) AudioSystem.getLine(info[2]);
                    sourceLine.open(audioFormat[2]);
                    sourceLine.start();
                    int nBytesRead3 = 0;
                    byte[] abData3 = new byte[BUFFER_SIZE];
                    while (nBytesRead3 != -1) {
                        try {
                            nBytesRead3 = audioStream.read(abData3, 0, abData3.length);
                        } catch (IOException e) {
                        }
                        if (nBytesRead3 >= 0) {
                            @SuppressWarnings("unused")
                            int nBytesWritten = sourceLine.write(abData3, 0, nBytesRead3);
                        }
                    }

                    sourceLine.drain();
                    sourceLine.close();
                    break;
            }
            
                
            } catch (LineUnavailableException e) {
                System.exit(1);
            } catch (Exception e) {
                System.exit(1);
            }
    }
    
    public static void main(String args[]) throws Exception{
        testSound ts = new testSound();
        String[] soundList = {"src//wav//a.wav", "src//wav//b.wav", "src//wav//c.wav"};
        ts.LoadSound();
        ts.playSounds("src//wav//a.wav");
        ts.playSounds("src//wav//b.wav");
        //ts.playSounds("src//wav//b.wav");
    }
    
}
