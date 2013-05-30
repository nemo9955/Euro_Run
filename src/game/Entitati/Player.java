package game.Entitati;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entitate {

    public Player(float x, float y) {
        super(x, y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        isMoving = false;

        if( gc.getInput().isKeyDown(Input.KEY_W) ) {
            activ = 3;
            isMoving = true;
            modY(-speed * delta);
            if( colid() ) {
                modY(speed * delta);
            }
        }

        if( gc.getInput().isKeyDown(Input.KEY_S) ) {
            activ = 0;
            isMoving = true;
            modY(speed * delta);
            if( colid() ) {
                modY(-speed * delta);
            }
        }

        if( gc.getInput().isKeyDown(Input.KEY_D) ) {
            activ = 2;
            isMoving = true;
            modX(speed * delta);
            if( colid() ) {
                modX(-speed * delta);
            }
        }

        if( gc.getInput().isKeyDown(Input.KEY_A) ) {
            activ = 1;
            isMoving = true;
            modX(-speed * delta);
            if( colid() ) {
                modX(speed * delta);
            }
        }
        
        

        if( gc.getInput().isKeyPressed(Input.KEY_F1) ) {
            System.out.println(x + " " + y);
        }

        Animatie(delta);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.setColor(Color.white);
        g.draw(poly);
        img[activ][frame].draw(x, y);

    }

    protected void setViata() {
        viata = 150;
    }

    protected String LoadSheet() {
        dimW = 32;
        dimH = 48;
        return "res/entitati/player.png";
    }
}
