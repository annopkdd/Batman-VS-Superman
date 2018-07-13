import java.awt.*;
import javax.swing.JFrame;

class Mygame {
	public static void main(String[] args) {
		JFrame game = new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(2000, 1080);
        game.add(new GameScreen(), BorderLayout.CENTER);
        game.setVisible(true);
	}
}