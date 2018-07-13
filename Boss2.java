import java.awt.*;
import javax.swing.*;

/**
 * Created by poonna on 4/24/15 AD.
 */
public class Boss2 extends GameObject {
    private GameScreen screen;
    private Image image;
    private int x, y;
    private int speedX = 10;
    private int speedY = 10;
    private int hp=50;
    private int count=0;

    public Boss2(GameScreen screen, int x,int y) {
        ImageIcon loader = new ImageIcon(getClass().getResource("pic/boss2alive.png"));
        image = loader.getImage();
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean update() {
        if(hp<=0){
            if(x>=2000){
                return false;
            }
            ImageIcon loader = new ImageIcon(getClass().getResource("pic/boss2.png"));
            image = loader.getImage();
            speedX = 5;
            speedY = 0;
            x += speedX;
        }
        else{
             if (x >1500){
                        x -= speedX;
                    }
                    else {
                        y += speedY;
                        if(y<=0 ){
                    
                            speedY = 10;
                        }
                        else if (y>=950){
                   
                            speedY = -10;
                        } 
                        
                  
                        x=1500;
                    }

        }
       
        return true;
        
    }
    public  int getHP(){
        return hp;
    }
    public void setHP(int hp){
        this.hp = hp;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, screen);
    }
    @Override
    public void drawdead(Graphics g){
        
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 400, 329);
    }
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
    @Override
    public int getCount(){
        return count;
    }
}
