import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    // update() returns true in most cases, and false when it needs to destroy itself
    public abstract boolean update();

    public abstract void draw(Graphics g);
    public abstract void drawdead(Graphics g);
    public abstract Rectangle getBounds();

    public boolean collidedWith(GameObject other) {
        return getBounds().intersects(other.getBounds());
    }
    public abstract int getCount();
    public abstract int getX();
    public abstract int getY();
    public abstract int getHP();
    public abstract void setHP(int hp);
}

