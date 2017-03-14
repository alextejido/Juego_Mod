package ping_pong_wars;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

    private static final int DIAMETER = 30;
    int x = 400;
    int xa = 3;
    int y = 200;
    int ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    }

    void move() {
        //game over de la pared de la izquierda
        if (x + xa < 0) {
            game.gameOver();
        }
        //game over de la pared de la derecha
        if (x + xa > game.getWidth() - DIAMETER) {
            game.gameOver();
        }
         if (y + ya < 0) {
            ya = 1;
        }
        //choque con la pared de arriba
        if (y + ya > game.getHeight() - DIAMETER) {
            ya = -1;
        }
        //colision raqueta de la izquierda
        if (collision1 ()){
            xa = game.speed;
            x = game.rack1.getTopX() + DIAMETER;
            game.speed++;
        }
        //colison de la raqueta derecha
        if (collision2()){
            xa = -game.speed;
            x = game.rack2.getTopX() - DIAMETER;
            game.speed++;
        }
        //movimiento continuo
        x = x + xa;
        y = y + ya;
    }
    
    private boolean collision1() {
        return game.rack1.getBounds().intersects(getBounds());

    }
    
    private boolean collision2(){
        return game.rack2.getBounds().intersects(getBounds());        
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, 30, 30);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
