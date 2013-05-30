package game.Entitati;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entitate {

    private float grav = 1;
    private final float gravMod = 0.01f;
    private final float gravMax = 7f;

    private float jump;
    private final float jumpMod = 0.01f;
    private final float FjumpFO = 5f;
    private final float DjumpFO = 4f;
    private boolean jumping = false;
    private boolean Fjump = false;
    private boolean Djump = false;

    public Player(float x, float y) {
        super(x, y);
    }

    private void Jump(GameContainer gc, int delta) {

        if( gc.getInput().isKeyPressed(Input.KEY_W) ) {
            jumping = true;
            if( Fjump && !Djump ) {
                jump += DjumpFO;
                Djump = true;
            }
            if( !Fjump ) {
                jump += FjumpFO;
                Fjump = true;
            }
        }

        if( jumping ) {
            modY(-jump * delta);
        }

        jump -= jumpMod * delta;
        if(jump<=0){
            jumping=false;
            jump=0;
        }
    }

    private void Gravitatie(int delta) {

        modY(grav * delta);

        if( colid() ) {

            modY(-grav * delta);
            grav = 1;
            jumping = false;

        } else {
            grav += gravMod * delta;
            if( grav > gravMax )
                grav = gravMax;
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if( !jumping ) {
            Fjump = false;
            Djump = false;
            jump = 0;
        }
        isMoving = false;
        Jump(gc, delta);
        Gravitatie(delta);

        /*       if( gc.getInput().isKeyDown(Input.KEY_W) ) {
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
                }*/

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
