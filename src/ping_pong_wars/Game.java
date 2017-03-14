

package ping_pong_wars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel { 
    private URL url = getClass().getResource("/imagenes/Cielo estrellado.png");
    Image image = new ImageIcon(url).getImage();

    Ball ball = new Ball(this);
    Racquet1 rack1 = new Racquet1(this);
    Racquet2 rack2 = new Racquet2(this);
    int speed = 1;

    private int getScore() {
        return speed -1;
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                rack1.keyReleased(e);
                rack2.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                rack1.keyPressed(e);
                rack2.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    public void move() {
        ball.move();
        rack1.move();
        rack2.move();
    }

    @Override
    public void paint(Graphics g) { 
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        rack1.paint(g2d);
        rack2.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Kongtext", Font.BOLD, 20));
        g2d.drawString(String.valueOf(getScore()), 390, 50);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "your score is: " + getScore(), "GAME OVER", JOptionPane.YES_NO_OPTION);
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame window = new JFrame("War Table Hana");
        Game game = new Game();
        window.add(game);
        window.pack();
        window.setSize(800, 500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
