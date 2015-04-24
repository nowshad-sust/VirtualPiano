import java.awt.*; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

class ButtonFrame extends JFrame implements KeyListener{
  JButton bChange ; // reference to the button object

  // constructor for ButtonFrame
  ButtonFrame(String title) 
  {
    super( title );                     // invoke the JFrame constructor
    setLayout( new FlowLayout() );      // set the layout manager
    this.addKeyListener(this);
    bChange = new JButton("Click Me!"); // construct a JButton
    add( bChange );                     // add the button to the JFrame
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
  }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void init(String filename)
	{
            Audioapp.Sound testsong = new Audioapp.Sound("background.mid");
            testsong.playSoundOnce();
	}
}

public class ButtonDemo
{
  public static void main ( String[] args )
  {
    ButtonFrame frm = new ButtonFrame("Button Demo");

    frm.setSize( 150, 75 );     
    frm.setVisible( true );   
  }
}