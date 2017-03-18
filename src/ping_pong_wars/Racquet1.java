package ping_pong_wars;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet1 {

    private static final int X = 20;
    private static final int HEIGHT = 80;
    private static final int WIDTH = 20;
    int y = 200;
    int ya = 0;
    private Game game;

    public Racquet1(Game game) {
        this.game = game;
    }

    public void move() {
        if (y + ya > 0 && y + ya < game.getHeight() - HEIGHT ) {
            y = y + ya;
        }
    }

    public void paint(Graphics2D g) {
        g.fillRect(X, y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        ya = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            ya = -game.speed-1;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            ya = game.speed+1;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(X, y, WIDTH, HEIGHT);
    }

    public int getTopX() {
        return X + WIDTH;
    }
}
