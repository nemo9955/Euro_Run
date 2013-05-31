package game.Entitati;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entitate {

    private boolean canjump = true;
    private float accel = 1f;
    private short jumpNo = 0;
    private short jumpMax = 2;

    public Player(float x, float y) {
        super(x, y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        isMoving = false;

        if( gc.getInput().isKeyPressed(Input.KEY_W) && jumpNo < jumpMax && canjump ) {
            jumpNo++;
            if(jumpNo==1){
                accel = -1.5f;
            }
            if( jumpNo > 1 ) {
                accel = -1f ;
            }
        }

        modY(accel * delta);
        if( colid() ) {
            modY(-accel * delta);
            jumpNo = 0;
            if( accel >= 0 ) {
                adapt(1);
                canjump = true;
            } else {
                adapt(-1);
                canjump = false;
            }
        }

        accel += 0.005f * delta;

        if( accel > 1 )
            accel = 1;

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

    private void adapt(int cantitate) {
        while (!colid()) {
            modY(cantitate);
        }
        modY(-cantitate);
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
