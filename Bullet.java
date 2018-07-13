import java.awt.*;


public class Bullet extends GameObject {
    private GameScreen screen;
    private int x, y;
    private int speed = 20;
    private int count=0;


    public Bullet(GameScreen screen, int x, int y) {
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean update() {


        x -= speed;
        if (x < 0) {
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
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, 25, 12);
    }
    @Override
    public void drawdead(Graphics g){
        
    }
    

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 25, 12);
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
    public  int getHP(){
        return 1;
    }
     @Override
    public void setHP(int hp){
        //
    }
}
