import java.util.*;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Spielschleife implements Runnable{

    private Spieler spieler;
    private List<Hindernis> hindernisse;
    private int zeitBisZuNaechstenRoehre;
    private BufferedImage roehre1, roehre2;
    private boolean laeuft=true;
    private int highscore;
    public Spielschleife()    {
        spieler = new Spieler(0.0, 250.0);
        spieler.sprung();

        hindernisse = new ArrayList<Hindernis>();
        zeitBisZuNaechstenRoehre = 0;

        try {       roehre1 = ImageIO.read(getClass().getResource("Roehre1.png"));
            roehre2 = ImageIO.read(getClass().getResource("Roehre2.png"));
        } catch (IOException e) {}

        try {
            FileInputStream iostream = new FileInputStream("highscore.score");
            DataInputStream diostrem = new DataInputStream(iostream);
            try {
                highscore=diostrem.readInt();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {}

        Thread gameloop = new Thread (this);
        gameloop.start();
    }

    public void zeichnen(Graphics g, Spielfeld spielfeld){
        spieler.zeichnen(g, spielfeld);
        for(int i=0;i<hindernisse.size();i++){
            hindernisse.get(i).zeichnen(g, spielfeld,spieler.getX());
        }

        Font score = new Font("Arial",Font.BOLD,40);
        g.setFont(score);
        g.drawString(Hindernis.count+"",30,30);
        Font highscoreFont = new Font("Arial",Font.PLAIN,10);
        g.setFont(highscoreFont);
        g.drawString(highscore+"",40,40);
    }

    public void sprung(){
        spieler.sprung();
        laeuft=true;
    }

    public void run(){
        while(true){          

            spieler.bewegen();

            Start.spielfeld.repaint();
            boolean b = false;
            for(int i=0;i<hindernisse.size();i++){
                if(hindernisse.get(i).kollision(spieler.getRect())){
                    b=true;
                }
            }

            if(spieler.getY()>=563){
                b=true;
            }

            if(b==true){  
                if(Hindernis.count>highscore){
                    highscore = Hindernis.count;
                }
                scoreSpeichern();
                laeuft=false;
                while(!laeuft){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}                   
                }
                restart();
                spieler.sprung();
            }

            zeitBisZuNaechstenRoehre--;
            if(zeitBisZuNaechstenRoehre<=0){
                hindernisse.add(new Hindernis(roehre1, roehre2, (int)spieler.getX()+700,(int)(Math.random()*250-750)));
                zeitBisZuNaechstenRoehre = 300;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {}
        }
    }

    private void restart(){      
        spieler = new Spieler(0.0, 250.0);

        hindernisse = new ArrayList<Hindernis>();
        zeitBisZuNaechstenRoehre = 60;
        Hindernis.count=0;

        laeuft=true;
    }

    public void scoreSpeichern(){
        try {   
            FileOutputStream output = new FileOutputStream("highscore.score");
            DataOutputStream  datop  = new DataOutputStream(output);

            try {
                datop.writeInt(highscore);
            } catch (IOException e) {e.printStackTrace();}

            try {
                output.close();
            } catch (IOException e1) {}
        } catch (IOException e) {e.printStackTrace();}
    }
}
