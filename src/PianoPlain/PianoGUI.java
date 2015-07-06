/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PianoPlain;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import org.jfugue.player.Player;

/**
 *
 * @author nowshad
 */

public class PianoGUI extends JFrame implements ActionListener, KeyListener{
    private JFrame frame;
    private String[] notes = {"C","D","E","F","G","A","B"};
    private String[] sharps = {"C#","D#","F#","G#","A#"};
    private String[] octave = {"4","5","6"};
    
    private Player player = new Player();
    
    
    public PianoGUI(){
        frame = new JFrame("VirtualPiano");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        
        frame.addKeyListener(this);
        
        
        Container mainPanel = frame.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBackground(Color.BLACK);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        
        
        
        JLayeredPane pianoKeyPanel = makeKeyboard();
        
        mainPanel.add(pianoKeyPanel);
        
        
        
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900,400);
    }
    
    public JLayeredPane makeKeyboard(){
        
        String name = "";
	int x = 55;
        int y = 180;
        
        JLayeredPane keyboard = new JLayeredPane();
        keyboard.setPreferredSize(new Dimension(900,162));
        keyboard.add(Box.createRigidArea(new Dimension(x, 0)));
        
        for(int i=0;i<octave.length;++i){
            for(int j=0;j<notes.length;++j){
                ImageIcon img = new ImageIcon("images/"+notes[j]+".png");
                JButton jb = new JButton(img);

                        name = notes[j]+octave[i];
        		jb.setName(name);
        		jb.setActionCommand(name);
        		jb.addActionListener(this);
        		jb.setBounds(x,y,35,162);
        		keyboard.add(jb,new Integer(1));
        		keyboard.add(Box.createRigidArea(new Dimension(2, 0)));
        		x += 37;
            }
        }
        
        x = 0;
        
        // Add the black keys 
        for(int i=0; i< octave.length; i++){
        	
    		ImageIcon img = new ImageIcon("images/blackKey.png");
    		
    		// Make 5 "keys"
    		
    		JButton jb0 = new JButton(img);
    		name = sharps[0]+octave[i];
    		jb0.setName(name);
    		jb0.setActionCommand(name);
    		jb0.addActionListener(this);
    		
    		JButton jb1 = new JButton(img);
    		name = sharps[1]+octave[i];
    		jb1.setName(name);
    		jb1.setActionCommand(name);
    		jb1.addActionListener(this);
    		
    		JButton jb2 = new JButton(img);
    		name = sharps[2]+octave[i];
    		jb2.setName(name);
    		jb2.setActionCommand(name);
    		jb2.addActionListener(this);
    		
    		JButton jb3 = new JButton(img);
    		name = sharps[3]+octave[i];
    		jb3.setName(name);
    		jb3.setActionCommand(name);
    		jb3.addActionListener(this);
    		
    		JButton jb4 = new JButton(img);
    		name = sharps[4]+octave[i];
    		jb4.setName(name);
    		jb4.setActionCommand(name);
    		jb4.addActionListener(this);
    		
    		// Place the 5 keys 
    		jb0.setBounds(77+(260*i),y,25,95);
    		keyboard.add(jb0,new Integer(2));
    		
    		jb1.setBounds(115+(260*i),y,25,95);
    		keyboard.add(jb1,new Integer(2));
    		
    		jb2.setBounds(188+(260*i),y,25,95);
    		keyboard.add(jb2,new Integer(2));
        	
    		jb3.setBounds(226+(260*i),y,25,95);
    		keyboard.add(jb3,new Integer(2));
   
    		jb4.setBounds(264+(260*i),y,25,95);
    		keyboard.add(jb4,new Integer(2));
        }
        
        return keyboard;
        
        
    }
    
    public static void main(String args[]){
        new PianoGUI();
    }


    @Override
	public void actionPerformed(ActionEvent e) {
		
            Runnable playNotes = new Runnable(){
                         public void run() {
                            
                             // Initialize
                            String command = null;
                            String name = "";
                            Object obj = e.getSource();
                           // Cast the object to a JButton
                            final JButton jb = (JButton)obj;
                            name = jb.getName();
                             System.out.println("name: "+ name);
                            //final String actionCommand;
                            //actionCommand = jb.getActionCommand();
		            //player.play(actionCommand);
                            player.play(name);
                            
                            }
		         };
		 	(new Thread(playNotes)).start();
                    }
        
        public void playSound(String key){
            Runnable playNotes = new Runnable(){
                         public void run() {
                            player.play(key);
                            
                            }
		         };
		 	(new Thread(playNotes)).start();
		            
        }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Pressed: "+e.getKeyChar());
        generateNotes(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public void generateNotes(KeyEvent e){
        char keyPressed = e.getKeyChar();
        System.out.println("Pressed: "+keyPressed);
        
        switch(keyPressed){
            case 'q':
                playSound("C4");
                break;
            case 'w':
                playSound("D4");
                break;
            case 'e':
                playSound("E4");
                break;
            case 'r':
                playSound("F4");
                break;
            case 't':
                playSound("G4");
                break;
            case 'y':
                playSound("A4");
                break;
            case 'u':
                playSound("B4");
                break;
            case 'a':
                playSound("C5");
                break;
            case 's':
                playSound("D5");
                break;
            case 'd':
                playSound("E5");
                break;
            case 'f':
                playSound("F5");
                break;
            case 'g':
                playSound("G5");
                break;
            case 'h':
                playSound("A5");
                break;
            case 'j':
                playSound("B5");
                break;
            case 'z':
                playSound("C6");
                break;
            case 'x':
                playSound("D6");
                break;
            case 'c':
                playSound("E6");
                break;
            case 'v':
                playSound("F6");
                break;
            case 'b':
                playSound("G6");
                break;
            case 'n':
                playSound("A6");
                break;
            case 'm':
                playSound("B6");
                break;
            
        }
        
    }
           
}