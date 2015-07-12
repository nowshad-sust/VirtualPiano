/*
 * Copyright 2015 nowshad.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.BadLocationException;
import org.jfugue.player.Player;

/**
 *
 * @author nowshad
 */

public class PianoGUI extends JFrame implements ActionListener, KeyListener{
    //main JFrame
    private JFrame frame;
    //key notes to repeat
    private String[] notes = {"C","D","E","F","G","A","B"};
    //dhsrp notes
    private String[] sharps = {"C#","D#","F#","G#","A#"};
    //octaves : primarily 3 octaves used
    private String[] octave = {"4","5","6"};
    
    //creating a player object to play notes
    public static Player player = new Player();
    
    private static JLabel currentlyPlayingLabel = new JLabel();
    

    
    
    
    //constructor
    public PianoGUI() throws BadLocationException{
        //Main JFrame
        frame = new JFrame("VirtualPiano");
        
        /*
        look & feel doesn't work properly
        makes piano playing jammed
        & all static labels vanished
        
        try {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        */    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setFocusable(true);
        
        frame.addKeyListener(this);
        
        //main Conatainer MainPanel
        
        Container mainPanel = frame.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setForeground(Color.lightGray);
		mainPanel.setBackground(Color.BLACK);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        
        //top panel
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(600,50));
      
        //logo area/ "Virtual Piano" - title area
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLACK);
        JLabel leftLabel = new JLabel();
        leftLabel.setText("<html><h1>Virtual<br>Piano</h1></html>");
        leftPanel.add(leftLabel);
        
        
        topPanel.add(leftPanel);
        
        
        //tabbed menu starts
        
        JTabbedPane tabbedMenuPanel = new JTabbedPane();
        tabbedMenuPanel.setBackground(Color.LIGHT_GRAY);
        tabbedMenuPanel.setBorder(null);
        //1st tab -> CurrentlyPlayingPanel
        
        JPanel CurrentlyPlayingPanel = new JPanel();
        
        CurrentlyPlayingPanel.setLayout(new BoxLayout(CurrentlyPlayingPanel, BoxLayout.Y_AXIS));
        CurrentlyPlayingPanel.setBackground(Color.BLACK);
        
        JLabel label1 = new JLabel();
        label1.setText("<html><h2 style=\"margin-left:100;\">Currently Playing Note</h2></html>");
        currentlyPlayingLabel.setText("<html><h2 style=\"margin-left:200;\">Nothing</h2></html>");
        
        CurrentlyPlayingPanel.add(label1);
        CurrentlyPlayingPanel.add(currentlyPlayingLabel);
        
        //2nd tab -> CreditsPanel
        JPanel creditsPanel = new JPanel();
        creditsPanel.setBackground(Color.BLACK);
        JLabel label2 = new JLabel();
        //credits text
        label2.setText("<html>Md. Al-amin Nowshad"
                + "<br>Batch: 2012,"
                + "<br>Dept. of Computer Science & Engineering,"
                + "<br>ShahJalal University of Science & Technology"
                + "<br>email: iamnowshad@gmail.com</html>");
        
        creditsPanel.add(label2);
        
        //3rd tab -> helpPanel
        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(Color.BLACK);
        JLabel helpLabel = new JLabel("<html><p>Watch the keymap to play</p></html>");
        JLabel linkLabel = new JLabel();
        PianoFunctionality.goWebsite(linkLabel,"http://nowshad.scdnlab.com","Click here");
        helpPanel.add(helpLabel);
        helpPanel.add(linkLabel);
        
        //adding tabs to main JTabbedPane
        tabbedMenuPanel.addTab("Playing", CurrentlyPlayingPanel);
        tabbedMenuPanel.addTab("Credits", creditsPanel);
        tabbedMenuPanel.addTab("Help", helpPanel);
        
        
        
        //tabbed menu ends
     
        //adding JTabbedPane to topPanel
        topPanel.add(tabbedMenuPanel);
        
        
        
        //right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        JLabel rightLabel = new JLabel();
        rightLabel.setText("<html><h1>Right<br>Panel</h1></html>");
        
        
        rightPanel.add(rightLabel);
        
        
        topPanel.add(rightPanel);
        
        
        //top panel finished
        
        mainPanel.add(topPanel);
        
        //making & adding the keyboard
        JLayeredPane pianoKeyPanel = makeKeyboard();
        
        mainPanel.add(pianoKeyPanel);
        
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900,400);
    }
    
    
    
    
    //function to generate the keyboard
    public JLayeredPane makeKeyboard(){
        
        String name = "";
	int x = 55;
        int y = 20;
        
        JLayeredPane keyboard = new JLayeredPane();
        keyboard.setPreferredSize(new Dimension(900,162));
        keyboard.add(Box.createRigidArea(new Dimension(x, 0)));
        keyboard.setFocusable(false);
        //adding the white keys
        for(int i=0;i<octave.length;++i){
            for(int j=0;j<notes.length;++j){
                ImageIcon img = new ImageIcon("images/"+notes[j]+".png");
                JButton jb = new JButton(img);

                        name = notes[j]+octave[i];
        		jb.setName(name);
                        jb.setBorderPainted(false);
                        jb.setBorder(null);
                        //button.setFocusable(false);
                        jb.setMargin(new Insets(0, 0, 0, 0));
                        jb.setContentAreaFilled(false);
        		jb.setActionCommand(name);
        		jb.addActionListener(this);
        		jb.setBounds(x,y,35,207);
        		jb.setFocusable(false);
                        /* used for pressed & released effect
                        button.setIcon(myIcon1);
                        button.setRolloverIcon(myIcon2);
                        button.setPressedIcon(myIcon3);
                        button.setDisabledIcon(myIcon4);
                        */
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
                jb0.setFocusable(false);

    		
    		JButton jb1 = new JButton(img);
    		name = sharps[1]+octave[i];
    		jb1.setName(name);
    		jb1.setActionCommand(name);
    		jb1.addActionListener(this);
    		jb1.setFocusable(false);
                
    		JButton jb2 = new JButton(img);
    		name = sharps[2]+octave[i];
    		jb2.setName(name);
    		jb2.setActionCommand(name);
    		jb2.addActionListener(this);
    		jb2.setFocusable(false);
                
    		JButton jb3 = new JButton(img);
    		name = sharps[3]+octave[i];
    		jb3.setName(name);
    		jb3.setActionCommand(name);
    		jb3.addActionListener(this);
    		jb3.setFocusable(false);
                
    		JButton jb4 = new JButton(img);
    		name = sharps[4]+octave[i];
    		jb4.setName(name);
    		jb4.setActionCommand(name);
    		jb4.addActionListener(this);
    		jb4.setFocusable(false);
                
    		// Place the 5 keys 
    		jb0.setBounds(77+(260*i),y-1,25,115);
    		keyboard.add(jb0,new Integer(2));
    		
    		jb1.setBounds(115+(260*i),y-1,25,115);
    		keyboard.add(jb1,new Integer(2));
    		
    		jb2.setBounds(188+(260*i),y-1,25,115);
    		keyboard.add(jb2,new Integer(2));
        	
    		jb3.setBounds(226+(260*i),y-1,25,115);
    		keyboard.add(jb3,new Integer(2));
   
    		jb4.setBounds(264+(260*i),y-1,25,115);
    		keyboard.add(jb4,new Integer(2));
        }
        
        return keyboard;
        
        
    }
    
   

    //mouse event handler
    @Override
	public void actionPerformed(ActionEvent e) {
		
            Runnable playNotes = new Runnable(){
                         public void run() {
                            
                            String key = "";
                            Object obj = e.getSource();
                            // Cast the object to a JButton
                            final JButton jb = (JButton)obj;
                            key = jb.getName();
                            try{
                                //print the currently playing note
                                currentlyPlayingLabel.setText("<html><h1 style=\"margin-left:200;\">"+key+"</h1></html>");
                                //trying to play the note
                                player.play(key);
                            }catch(Exception e){
                                System.out.println("Exception with "+ key);
                                //retrying to play
                                player.play(key);
                            }
                            
                            }
		         };
		 	(new Thread(playNotes)).start();
                    }
        
        
        

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //calling keyboard key response function
        PianoFunctionality.generateNotes(e);
    }
    
    public static void setCurrentlyPlayingLabel(String labelText){
        currentlyPlayingLabel.setText(labelText);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
   /*
     public static void main(String args[]) throws BadLocationException{
        new PianoGUI();
    }
    */ 
           
}
