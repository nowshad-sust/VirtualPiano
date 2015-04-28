package PianoPlain;
//import .*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class NewJFrame extends javax.swing.JFrame{

    
    //ArrayList<AudioInputStream> keyThread;
    
    public NewJFrame() {
        initComponents();
        String[] soundList = {"src//wav//a.wav", "src//wav//b.wav", "src//wav//c.wav"};
        LoadSounds(soundList);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public String DetectKey(java.awt.event.KeyEvent evt){
        String filename = null;
        try{
            filename = evt.getKeyChar()+".wav";
        }catch(Exception e){
            System.out.println("Event could not be detected");
        }
        return(filename);
    }
    public void Play(String filename)
	{
            try{
                playSounds(filename);
            } catch(Exception e){
                System.out.println("exception in play()");
            }            
	}
    
    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    //private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    //private DataLine.Info info;
    //private ArrayList<AudioFormat> audioFormat;
    //private ArrayList<DataLine.Info> info;
    private final AudioFormat[] audioFormat = new AudioFormat[3];
    private final DataLine.Info[]  info= new DataLine.Info[3];
        
    public void LoadSounds(String fileNames[]){
        for (String strFilename : fileNames) {
            try {
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
                    System.out.println(audioFormat[0] + " " + info[0]);
                    break;
                case "src//wav//b.wav":
                    audioFormat[1] = audioStream.getFormat();
                    info[1] = new DataLine.Info(SourceDataLine.class, audioFormat[1]);
                    System.out.println(audioFormat[0] + " " + info[0]);
                    break;
                case "src//wav//c.wav":
                    audioFormat[2] = audioStream.getFormat();
                    info[2] = new DataLine.Info(SourceDataLine.class, audioFormat[1]);
                    System.out.println(audioFormat[0] + " " + info[0]);
                    break;
            }
        }
        System.out.println("Sound Files Loaded");
    }
    public void playSounds(String strFilename){
        try {
                System.out.println("playSounds called with "+strFilename);
                if(null != strFilename)switch (strFilename) {
                case "a.wav":
                    System.out.println("playing a");
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
                case "b.wav":
                    System.out.println("playing b");
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
                case "c.wav":
                    System.out.println("playing c");
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
    /*private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    
    public void playSound(String filename){

        String strFilename = filename;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();
        
        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }*/
    
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
        try{
            
                String keyFileName = DetectKey(evt);
                System.out.println("key pressed "+evt.getKeyChar() + " file name " + keyFileName);
                Play(keyFileName);
       }catch(Exception e){
           System.out.println("Exception in creating Thread");
       
    }//GEN-LAST:event_formKeyPressed
}

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        System.out.println("key releassed "+evt.getKeyChar());
        //String keyFileName = evt.getKeyChar()+".wav";
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
            //NewJFrame ts = new NewJFrame();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
