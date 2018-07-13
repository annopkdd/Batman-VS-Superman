import java.awt.*;
import javax.swing.*;

public class Startscreen extends GameObject {
    private GameScreen screen;
    private Image bg;



    public Startscreen(GameScreen screen,Image bg) {
        this.bg = bg;
        this.screen = screen;
        this.bg = bg;

    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg, 0,0, screen);
    }
    @Override
    public void drawdead(Graphics g){
        
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 50, 50);
    }
    @Override
    public int getX() {
        return 1;
    }
    @Override
    public int getY() {
       return 1;
    }
        @Override
    public  int getHP(){
        return 1;
    }
     @Override
    public void setHP(int hp){
        //
    }
    @Override
    public int getCount(){
        return 1;
    }
}


