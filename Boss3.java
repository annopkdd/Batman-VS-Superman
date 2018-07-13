import java.awt.*;
import javax.swing.*;

/**
 * Created by poonna on 4/24/15 AD.
 */
public class Boss3 extends GameObject {
    private GameScreen screen;
    private Image image;
    private int x, y;
    private int speedX = 1;
    private int speedY = 15;
    private int hp=320;
    private int count=5;

    public Boss3(GameScreen screen, int x,int y) {
        ImageIcon loader = new ImageIcon(getClass().getResource("pic/batman.png"));
        image = loader.getImage();
        this.screen = screen;
        this.x = x;
        this.y = y;
    }   

    @Override
    public boolean update() {
         if(hp<=0){
            if(count<=-120){
                return false;
            }
            ImageIcon loader = new ImageIcon(getClass().getResource("pic/boss3die.png"));
            image = loader.getImage();
            speedX = 0;
            speedY = 0;
            count -= 1;
            
        }       
        else{
                
                x -= speedX;
            
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
       return new Rectangle(x, y, 700, image.getHeight(null));
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
