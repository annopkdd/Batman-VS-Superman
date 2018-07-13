import java.awt.*;
import javax.swing.*;

import java.util.*;
/**
 * Created by poonna on 4/24/15 AD.
 */
public class Boss1 extends GameObject {
    private GameScreen screen;
    private Image image;
    private int x, y;
    private int speedX = 10;
    private int speedY = 10;
    private int hp=20;
    private int count=0;
    private Random  random = new Random();
    public Boss1(GameScreen screen, int x,int y) {
        ImageIcon loader = new ImageIcon(getClass().getResource("pic/boss1-2.png"));
        image = loader.getImage();
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean update() {     
        if(hp<=0){
            if(y>=950){
                return false;
            }
            ImageIcon loader = new ImageIcon(getClass().getResource("pic/boss1die.png"));
            image = loader.getImage();
            speedX = 0;
            speedY = 5;
        }
        else{
            if (x >2200){
               
                    ImageIcon loader = new ImageIcon(getClass().getResource("pic/boss1-2.png"));
                    image = loader.getImage();

                    x=2200;
                    y = random.nextInt(700)+1;
                    speedX = -10;
                    speedY = 0;
             }
            if( x< -300){
                 
                    ImageIcon loader = new ImageIcon(getClass().getResource("pic/boss1.png"));
                    image = loader.getImage();
                    x=-300;
                    y = random.nextInt(700)+1;
                    speedX = 10;
                    speedY = 0;
             }   

        }
     

        
        x += speedX;
        y += speedY;
        return true;
        
    }
    @Override
    public int getCount(){
        return count;
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
       return new Rectangle(x, y, 250, 250);
    }
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
   

}
