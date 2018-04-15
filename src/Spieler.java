import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;

public class Spieler{

    private double x, y, xGeschwindigkeit, yGeschwindigkeit, gravitation;    
    private BufferedImage bild;
    private Rectangle rect;

    public Spieler(double x, double y)    {
        this.x = x;
        this.y = y;
        xGeschwindigkeit = 1;
        yGeschwindigkeit = 0;
        gravitation = 0.04;

        rect = new Rectangle((int)x,(int)y,28,28);

        try {       bild = ImageIO.read(getClass().getResource("Vogel.png"));
        } catch (IOException e) {}
    }

    public void sprung(){      
        if(y>-100){
            yGeschwindigkeit = -3;
        }
    }

    public void bewegen(){
        x += xGeschwindigkeit;
        yGeschwindigkeit += gravitation;
        y += yGeschwindigkeit;

        rect = new Rectangle((int)x,(int)y,32,32);
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld){
        g.drawImage(bild, 202, (int)y+2, spielfeld);
    }

    public int getX(){
        return (int) x;
    }

    public int getY(){
        return (int) y;
    }

    public Rectangle getRect(){
        return rect;
    }
}
