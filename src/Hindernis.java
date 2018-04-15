import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Hindernis{
    private double x, y;
    private BufferedImage bild1, bild2;
    private Rectangle rect1, rect2, rect3;
    public static int count;
    private boolean beruehrt;
    private final int abstand = 150;

    public Hindernis(BufferedImage img1 , BufferedImage img2, double x, double y)    {
        this.x=x;
        this.y=y;
        bild1=img1;
        bild2=img2;
        beruehrt=false;
        rect1 = new Rectangle((int)x,(int)y,64,800);
        rect2 = new Rectangle((int)x,(int)y+800+abstand,64,800);
        rect3 = new Rectangle((int)x,(int)y+800,64,abstand);
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld, int xVerschiebung){
        g.drawImage(bild1,(int)(x-xVerschiebung+200), (int)y, spielfeld);
        g.drawImage(bild2,(int)(x-xVerschiebung+200), (int)y+800+abstand, spielfeld);
    }

    public boolean kollision(Rectangle r){
        if(!beruehrt&&r.intersects(rect3)){
            count++;
            beruehrt=true;
        }
        if(r.intersects(rect1)||r.intersects(rect2)){
            return true;
        }else{
            return false;
        }
    }
}
