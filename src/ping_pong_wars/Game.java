package ping_pong_wars;

import interfaz_proyecto.Sonidos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.image.ImageObserver.ABORT;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        return speed - 1;
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

    private void move() throws IOException {
        ball.move();
        rack1.move();
        rack2.move();
    }

    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
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

    public void gameOver() throws IOException {
        JOptionPane.showMessageDialog(this, "your score is: " + getScore(), "GAME OVER", JOptionPane.YES_NO_OPTION);
        
        int[]nom=new int[1];
        nom[0]=getScore();
            try {
              FileWriter   fichero =new FileWriter("score.txt");
                fichero.write(nom+"\n");
                fichero.close();   
                 for(int event: nom) {
    System.out.println(event);
}
            }catch(FileNotFoundException ex){
                System.out.println("Fallo al guardar en el archivo");
            }
System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame window = new JFrame("World of War in the Ping Pong");
        Game game = new Game();
        window.add(game);
        window.setSize(800, 500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Sonidos.Juego.loop();
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
