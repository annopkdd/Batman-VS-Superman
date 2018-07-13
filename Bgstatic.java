import java.awt.*;
import javax.swing.*;

public class Bgstatic extends GameObject {
    private GameScreen screen;
    private Image bg;
    private int count=0;


    public Bgstatic(GameScreen screen,Image bg) {
        this.bg = bg;
        this.screen = screen;
        ImageIcon loader = new ImageIcon(getClass().getResource("pic/skycon.png"));
        bg = loader.getImage();

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
        return 0;
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
    @Override
    public int getCount(){
        return count;
    }
}


