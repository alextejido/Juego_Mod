package ping_pong_wars;

import interfaz_proyecto.Sonidos;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

public class Ball {

    private static final int DIAMETER = 30;
    int x = 100;
    int xa = 1;
    int y = 200;
    int ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    }

    void move() throws IOException {
        //game over de la pared de la izquierda
        if (x + xa < 0) {
            Sonidos.Gameover.play();
            game.gameOver();
        }
        //game over de la pared de la derecha
        if (x + xa > game.getWidth() - DIAMETER) {
           Sonidos.Gameover.play();
            game.gameOver();
        }
        //choque con la pared de abajo
        if (y + ya < 0) {
            ya = game.speed;
        }
        //choque con la pared de arriba
        if (y + ya > game.getHeight() - DIAMETER) {
            ya = -game.speed;
        }
        //colision raqueta de la izquierda
        if (collision1 ()){
            xa = game.speed;
            x = game.rack1.getTopX() + DIAMETER;
            Sonidos.Rebote.play();
            game.speed++;
        }
        //colison de la raqueta derecha
        if (collision2()){
            xa = -game.speed;
            x = game.rack2.getTopX() - DIAMETER;
             Sonidos.Rebote.play();
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
