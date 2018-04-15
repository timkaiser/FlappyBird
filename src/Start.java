import javax.swing.*;
import java.awt.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.DisplayMode;

public class Start {
    public static JFrame fenster;    
    static Spielfeld spielfeld;

    public Start()    {
        fenster = new JFrame();
        fenster.setSize(806,633);
        fenster.setLocationRelativeTo(null);
        fenster.setResizable(false);
        fenster.setTitle("Flappy Bird");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fenster.setUndecorated(true);
        fenster.setVisible(true);        
        //DisplayMode displayMode;
        //GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //GraphicsDevice device = env.getDefaultScreenDevice();
        //displayMode = new DisplayMode(800,600,16,60);

        //device.setFullScreenWindow(fenster);
        //device.setDisplayMode(displayMode);
 
        
        
        fenster.add(spielfeld = new Spielfeld());
        spielfeld.setBounds(0,0,800,600); 



        fenster.setVisible(true);
        fenster.revalidate();
    }

    public static void main(String[] args){
        new Start();
    }
}
