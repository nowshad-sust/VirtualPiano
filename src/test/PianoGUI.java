/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
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
    //octaves : 3vhere
    private String[] octave = {"4","5","6"};
    
    //creating a player object to play notes
    private Player player = new Player();
    
    private JLabel currentlyPlayingLabel = new JLabel();
    
    
    //constructor
    public PianoGUI() throws BadLocationException{
        //Main JFrame
        frame = new JFrame("VirtualPiano");
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
        goWebsite(linkLabel,"http://nowshad.scdnlab.com","Click here");
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
        
        
        //rightPanel.add(rightLabel);
        
        
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
    
    
    private void goWebsite(JLabel website, final String url, String text) {
        website.setText("<html> : <a href=\"\">"+text+"</a></html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                    try {
                            Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {
                            //It looks like there's a problem
                    }
            }
        });
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
    
    public static void main(String args[]) throws BadLocationException{
        new PianoGUI();
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
        
        
        //function to call for keyboard events
        public void playSound(String key){
            Runnable playNotes = new Runnable(){
                         public void run() {
                            try{
                                //print the currently playing note
                                currentlyPlayingLabel.setText("<html><h1 style=\"margin-left:200;\">"+key+"</h1></html>");
                                //trying to play note
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
        generateNotes(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    //method to determine which note to play
    //with corresponding key event
    //incomplete: black keys still left
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