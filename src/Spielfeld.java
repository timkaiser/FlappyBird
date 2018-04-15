import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spielfeld extends JPanel implements KeyListener, MouseListener{

    private Spielschleife spielschleife;
    private BufferedImage hintergrund;

    public Spielfeld()    {
        try {       hintergrund = ImageIO.read(getClass().getResource("Hintergrund.png"));
        } catch (IOException e) {}

        spielschleife = new Spielschleife();
        addKeyListener(this);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        spielschleife.sprung();
    }

    public void mouseReleased(MouseEvent e) {}

    public void keyPressed(KeyEvent k) {
        spielschleife.sprung();
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );
        g.drawImage(hintergrund,0,0,this);
        spielschleife.zeichnen(g,this);

        requestFocus();
    }

}
