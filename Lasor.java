import javax.swing.*;
import java.awt.*;

/**
 * Created by poonna on 4/24/15 AD.
 */
public class Lasor extends GameObject {
    private GameScreen screen;
    private int x, y;
    private int speed = 100;
    private int count=0;

    public Lasor(GameScreen screen, int x, int y) {
        this.screen = screen;
        this.x = x;
        this.y = y;

    }

    @Override
    public boolean update() {
        x += speed;
        if (x > 1850) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public int getCount(){
        return count;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        //g.fillRect(x, y, 5, 10);
        g.drawLine ( x-speed, y, x+speed, y );
        g.drawLine ( x-speed, y-1, x+speed, y-1 );
        g.drawLine ( x-speed, y-2, x+speed, y-2 );
        g.drawLine ( x-speed, y-2, x+speed, y-3 );
        g.drawLine ( x-speed, y-2, x+speed, y-4 );
        g.drawLine ( x-speed, y-2, x+speed, y-5 );



        g.drawLine ( x-speed, y+1, x+speed, y+1 );
        g.drawLine ( x-speed, y+2, x+speed, y+2 );
        g.drawLine ( x-speed, y+2, x+speed, y+3 );
        g.drawLine ( x-speed, y+2, x+speed, y+4 );
        g.drawLine ( x-speed, y+2, x+speed, y+5 );

      
    }
    @Override
    public void drawdead(Graphics g){
    }

    @Override
    public Rectangle getBounds() {
       return new Rectangle(x, y, 10, 10);

    }

    @Override	
    public  int getX(){
    	return x;
    } 
    @Override	
    public  int getY(){
    	return y;
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
