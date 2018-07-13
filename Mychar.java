import javax.swing.*;
import java.awt.*;

public class Mychar extends GameObject {
    private GameScreen screen;
    private Image image;
    private int x;
    private int y;
    private int speed = 12;
    private int hp=20;
    private int count=0;

    public Mychar(GameScreen screen,int x,int y) {
        this.screen = screen;

        ImageIcon loader = new ImageIcon(getClass().getResource("pic/superman.png"));
        image = loader.getImage();

        this.x = x - image.getWidth(null) / 2;
        this.y = y - image.getHeight(null) / 2;
    }

    @Override
    public boolean update() {
        if (screen.getKeyState(GameScreen.KEY_UP)) {
            y -= speed;
        }
        if (screen.getKeyState(GameScreen.KEY_DOWN)) {
            y += speed;
        }
        if (screen.getKeyState(GameScreen.KEY_LEFT)) {
            x -= speed;
        }
        if (screen.getKeyState(GameScreen.KEY_RIGHT)) {
            x += speed;
        }
        if( x<=0){
            x=0;
        }
        if( x>=1920){
            x=1920;
        }
        if(y<=0){
            y=0;
        }
        if(y>=950){
            y=950;
        }

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
}
