import java.awt.*;
import javax.swing.*;

/**
 * Created by poonna on 4/24/15 AD.
 */
public class Minibat extends GameObject {
    private GameScreen screen;
    private Image image;
    private int x, y;
    private int speedX = 3;
    private int speedY = 10;
    private int maxX=0;
    private int maxY=1;
    private int hp=5;
    private int count=-4;
  
    public Minibat(GameScreen screen, int x,int y) {
        ImageIcon loader = new ImageIcon(getClass().getResource("pic/minibat.png"));
        image = loader.getImage();
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean update() {
         x -= speedX;

         if(maxY <=20 && maxY >0){
            y += speedY;
            maxY = maxY+1;
         }
         else if(maxY > 20 && maxY <=40){
            y -= speedY;
            maxY = maxY+1;
         }
         else if(maxY>40){
            maxY = 1;
         }
         //System.out.println("max Y = "+maxY);



        if(hp<=0){
            ImageIcon loader = new ImageIcon(getClass().getResource("pic/effectbat1.png"));
            image = loader.getImage();
            count = count-1;
        }
       
        if(x>-250 ){
            return true;
        }
        else{
            return false;
        }
       
        
    }
    @Override
    public  int getHP(){
        return hp;
    }
     @Override
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
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
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
