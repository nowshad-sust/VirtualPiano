/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PianoPlain;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 *
 * @author nowshad
 */
public class Piano implements ActionListener{
    /** GUI frame.*/
	private JFrame frame;
	/** Allows user to type in notes to play.*/
	private JTextArea entryBox;
	/** Allows entry of rhythm pattern.*/
	private JTextArea rhythm1;
	/** Allows entry of another rhythm pattern.*/
	private JTextArea rhythm2;
	/** Allows user to alter how many times the song repeats.*/
	private JTextArea repeatNumber;
	/** Allows selection from various instruments.*/
	private JComboBox instrument;
	/** Allows user to enter desired tempo.*/
	private JTextArea tempo;
	/** Button that launches the help window.*/
	private JButton help;
	/** The total number of notes.*/
	public static final int NUM_KEYS = 7;
	/** How many octaves should be created.*/
	public static final int NUM_OCTAVES = 3;
	/** Holds the possible notes.*/
	private String[] notes = {"C","D","E","F","G","A","B"};
	/** Holds the possible sharps.*/
	private String[] sharps = {"C#","D#","F#","G#","A#"};
	/** Holds the octave numbers.*/
	private String[] octave = {"4","5","6"};
	/** Holds the possible instruments*/
	private String[] instruments = {"Piano", "Guitar", "Vibraphone","Violin","SCI-FI"};
	/** Creates a new player.*/
	private Player player = new Player(); 
	/** Holds which instrument is currently selected.*/
	private String instrumentType = "I[Piano]";
	/** Custom color for GUI entry fields*/
	private Color customColor = new Color(170,180,254);
	/** Border for entry fields*/
        private Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        
        /** Constructs the GUI */
	public Piano(){
		
		// ------------ Create GUI -----------
		frame = new JFrame("Piano GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        // Create the mainPanel
        Container mainPanel = frame.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBackground(Color.BLACK);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        
        // ---- Instrument and tempo panel ----
        JPanel iTpanel = new JPanel();
        iTpanel.setLayout(new BoxLayout(iTpanel,BoxLayout.X_AXIS));
		iTpanel.setForeground(Color.WHITE);
		iTpanel.setBackground(Color.BLACK);
        iTpanel.add(Box.createRigidArea(new Dimension(180,0)));
        
        // Title label
        JLabel titleLabel = new JLabel(" CSC 281 MIDI Piano");
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(Color.BLACK);
		iTpanel.add(titleLabel);
        iTpanel.add(Box.createRigidArea(new Dimension(150,0)));
        
        // Instrument label
        JLabel instrumentLabel = new JLabel("Instrument:");
		instrumentLabel.setForeground(Color.WHITE);
		instrumentLabel.setBackground(Color.BLACK);
        iTpanel.add(instrumentLabel);
        iTpanel.add(Box.createRigidArea(new Dimension(20,0)));

        // Instrument combo box
        instrument = new JComboBox(instruments);
        instrument.setName("instrument");
        instrument.addActionListener(this);
		instrument.setForeground(Color.WHITE);
		instrument.setBackground(Color.BLACK);
        iTpanel.add(instrument);
        iTpanel.add(Box.createRigidArea(new Dimension(20,0)));
        
        // Tempo label
        JLabel tempoLabel = new JLabel("Tempo:");
		tempoLabel.setForeground(Color.WHITE);
		tempoLabel.setBackground(Color.BLACK);
        iTpanel.add(tempoLabel);
        iTpanel.add(Box.createRigidArea(new Dimension(20,0)));

        // Tempo text area
        tempo = new JTextArea();
        tempo.setName("tempo");
        tempo.setText("120");
		tempo.setFont(new Font("Ariel", Font.BOLD, 14));
        tempo.setBorder(border);
		tempo.setForeground(Color.BLACK);
		tempo.setBackground(customColor);
        iTpanel.add(tempo);
        iTpanel.add(Box.createRigidArea(new Dimension(20,0)));
        
        // Help button
        help = new JButton("Help");
        help.setForeground(Color.WHITE);
        help.setBackground(Color.BLACK);
        help.addActionListener(this);
        help.setName("help");
        iTpanel.add(help);
        iTpanel.add(Box.createRigidArea(new Dimension(20,0)));

        // Add iTpanel to mainPanel
        mainPanel.add(iTpanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));

        // -------- piano keys panel --------
        
        // Call the make keys method
        JLayeredPane pianoKeyPanel = makeKeys();
        // Add to main panel
        mainPanel.add(pianoKeyPanel);
        
        // ---------- Notes Panel -----------
        
        // Create the notes panel
        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BoxLayout(notesPanel,BoxLayout.X_AXIS));
		notesPanel.setForeground(Color.WHITE);
		notesPanel.setBackground(Color.BLACK);
        notesPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        
        // Make notes label
        JLabel notesLabel = new JLabel("Notes:");
		notesLabel.setForeground(Color.WHITE);
		notesLabel.setBackground(Color.BLACK);
        notesPanel.add(notesLabel);
        notesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        // Create entry box
        entryBox = new JTextArea();
        entryBox.setBorder(border);
		entryBox.setFont(new Font("Ariel", Font.BOLD, 14));
		entryBox.setForeground(Color.BLACK);
		entryBox.setBackground(customColor);
        notesPanel.add(entryBox);
        notesPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        
        // Add the top panel to the main panel
        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));
        mainPanel.add(notesPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // ------- Rhythm panel 1 -------
        
        // Create panel1
        JPanel rhythmPanel1 = new JPanel();
        rhythmPanel1.setLayout(new BoxLayout(rhythmPanel1,BoxLayout.X_AXIS));
		rhythmPanel1.setForeground(Color.WHITE);
		rhythmPanel1.setBackground(Color.BLACK);
        rhythmPanel1.add(Box.createRigidArea(new Dimension(150,0)));
        
        // Rhythm 1 label
        JLabel r1 = new JLabel("Rhythm 1: ");
		r1.setForeground(Color.WHITE);
		r1.setBackground(Color.BLACK);
        rhythmPanel1.add(r1);
        rhythmPanel1.add(Box.createRigidArea(new Dimension(10,0)));
        
        // Text area
        rhythm1 = new JTextArea();
        rhythm1.setBorder(border);
        rhythm1.setFont(new Font("Ariel", Font.BOLD, 14));
		rhythm1.setForeground(Color.BLACK);
		rhythm1.setBackground(customColor);
        rhythmPanel1.add(rhythm1);
        rhythmPanel1.add(Box.createRigidArea(new Dimension(150,0)));

        // Add to main panel
        mainPanel.add(rhythmPanel1);
        mainPanel.add(Box.createRigidArea(new Dimension(20,20)));

        // ------- Rhythm panel 2 -------
        
        // Create panel 2
        JPanel rhythmPanel2 = new JPanel();
        rhythmPanel2.setLayout(new BoxLayout(rhythmPanel2,BoxLayout.X_AXIS));
		rhythmPanel2.setForeground(Color.WHITE);
		rhythmPanel2.setBackground(Color.BLACK);
        rhythmPanel2.add(Box.createRigidArea(new Dimension(150,0)));
        
        // Rhythm 2 label
        JLabel r2 = new JLabel("Rhythm 2: ");
		r2.setForeground(Color.WHITE);
		r2.setBackground(Color.BLACK);
        rhythmPanel2.add(r2);
        rhythmPanel2.add(Box.createRigidArea(new Dimension(10,0)));
        
        // Text area
        rhythm2 = new JTextArea();
        rhythm2.setBorder(border);
		rhythm2.setFont(new Font("Ariel", Font.BOLD, 14));
		rhythm2.setForeground(Color.BLACK);
		rhythm2.setBackground(customColor);
        rhythmPanel2.add(rhythm2);
        rhythmPanel2.add(Box.createRigidArea(new Dimension(150,0)));

        // Add to main panel
        mainPanel.add(rhythmPanel2);
        mainPanel.add(Box.createRigidArea(new Dimension(20,20)));
        
        // ---- Repeat rhythm panel ---
        
        // Create repeat rhythm panel
        JPanel repeatPanel = new JPanel();
        repeatPanel.setLayout(new BoxLayout(repeatPanel,BoxLayout.X_AXIS));
		repeatPanel.setForeground(Color.WHITE);
		repeatPanel.setBackground(Color.BLACK);
        repeatPanel.add(Box.createRigidArea(new Dimension(380,0)));
        
        // Repeat rhythm label
        JLabel repeatRhythmLabel = new JLabel("Repeat Rhythm: ");
		repeatRhythmLabel.setForeground(Color.WHITE);
		repeatRhythmLabel.setBackground(Color.BLACK);
        repeatPanel.add(repeatRhythmLabel);
        repeatPanel.add(Box.createRigidArea(new Dimension(10,0)));
        
        // Text area
        repeatNumber = new JTextArea();
        repeatNumber.setText("1");
        repeatNumber.setFont(new Font("Ariel", Font.BOLD, 14));
        repeatNumber.setBorder(border);
		repeatNumber.setForeground(Color.BLACK);
		repeatNumber.setBackground(customColor);
        repeatPanel.add(repeatNumber);
        repeatPanel.add(Box.createRigidArea(new Dimension(380,0)));
        
        // Add to main panel
        mainPanel.add(repeatPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(20,20)));

        
        // Create the play notes button
        JButton playButton = new JButton("Play Notes");
        playButton.setName("play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.setForeground(Color.WHITE);
		playButton.setBackground(Color.BLACK);
        
        // Add the action listener
        playButton.addActionListener(this);
        
        // Add Play notes button to the mainPanel
        mainPanel.add(playButton);
        mainPanel.add(Box.createRigidArea(new Dimension(50, 20)));
        
        // Show the window
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900,520);
        
	}
    
   
    
    /** Creates the panel containing all of the piano keys.
	 * @return the panel containing the keys. */
	public JLayeredPane makeKeys(){
		// Initialize
		String name = "";
		int x = 55;
		int y = 0;
		
		// Create layerPane
		JLayeredPane keyBoard = new JLayeredPane();
		keyBoard.setPreferredSize(new Dimension(900,162));
		keyBoard.add(Box.createRigidArea(new Dimension(x, 0)));

        // Add the white key buttons
        for(int i=0; i< NUM_OCTAVES; i++){
        	for(int j=0; j<NUM_KEYS; j++){
        		ImageIcon img = new ImageIcon("images/"+notes[j]+".png");
        		JButton jb = new JButton(img);
        		name = notes[j]+octave[i];
        		jb.setName(name);
        		jb.setActionCommand(name);
        		jb.addActionListener(this);
        		jb.setBounds(x,y,35,162);
        		keyBoard.add(jb,new Integer(1));
        		keyBoard.add(Box.createRigidArea(new Dimension(2, 0)));
        		x += 37;
        	}
        }
        
        // Reinitialize
        x = 0;
        
        // Add the black keys 
        for(int i=0; i< NUM_OCTAVES; i++){
        	
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
    		keyBoard.add(jb0,new Integer(2));
    		
    		jb1.setBounds(115+(260*i),y,25,95);
    		keyBoard.add(jb1,new Integer(2));
    		
    		jb2.setBounds(188+(260*i),y,25,95);
    		keyBoard.add(jb2,new Integer(2));
        	
    		jb3.setBounds(226+(260*i),y,25,95);
    		keyBoard.add(jb3,new Integer(2));
   
    		jb4.setBounds(264+(260*i),y,25,95);
    		keyBoard.add(jb4,new Integer(2));
        }
		// Return the keyboard
        return keyBoard;
	}
        
        /** Creates a piano object. */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Piano();
		

	}

    @Override
	public void actionPerformed(ActionEvent e) {
		// Initialize
		String command = "";
		JButton jb = null;
		String name = "";
		
		// Get which object was clicked
		Object obj = e.getSource();
		
		// If the object was a JComboBox
		if (obj instanceof JComboBox){
			// Get which instrument the user selected
			Object instrumentObj = instrument.getSelectedItem();
			// Add formatting so the instrument can be used in playback
			instrumentType = "I["+(String)instrumentObj+"]";
		}
		// Else the object is a JButton
		else{
			// Cast the object to a JButton
			jb = (JButton)obj;
			// Get the name of the JButton
			name = jb.getName();
		}

		
		// If the JButton is the play notes button:
		if (name.equals("play")){
			 // Create a new Runnable object
		     Runnable playNotes = new Runnable(){
		    	 // Create a subclass
		         public void run() {
		        	 // Call the playSong method to play the song
		        	 //playSong();
		         }
		         };
		         // Tell the new thread to start
		 		(new Thread(playNotes)).start();

		}
		// If the JButton is the help button
		else if (name.equals("help")){
			// Call the helpWindow method to open the help window
			//helpWindow();
		}
		// If the JComboBox was what the user clicked
		else if(obj instanceof JComboBox){
			// Do nothing
		}
		// Else a key was clicked
		else{
			// Get the action command
			command = jb.getActionCommand();
			// Add that string to the text field
			entryBox.append(command+" ");
			// Play that note using the player
			player.play(instrumentType+" "+command);
		}
		
	}
    
}
