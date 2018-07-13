import java.awt.*;
import javax.swing.*;

public class Bgmove extends GameObject {
    private GameScreen screen;
    private Image bg;
    private int x;
    private int speedX = 25;
    private int count=0;

    public Bgmove(GameScreen screen,Image bg) {
        this.bg = bg;
        this.screen = screen;
        x=0;
 

    }

    @Override
    public boolean update() {
        if(x<-2000){
            x=1900;
        }
        x -= speedX;
        return true;
    }
    @Override
    public int getCount(){
        return count;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg, x, 0, screen);
    }
    @Override
    public void drawdead(Graphics g){
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, 0, 50, 50);
    }
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return 0;
    }
    @Override
    public  int getHP(){
        return 1;
    }
     @Override
    public void setHP(int hp){
        //
    }
}

