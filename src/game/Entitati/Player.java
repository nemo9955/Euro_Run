package game.Entitati;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Physics {

    private Image img[][];
    private final short frames[] = { 8, 6, 5, 9 };
    /* indicele fiecaruia e          0  1  2  3
     * 
     * 0 - run
     * 1 - jump
     * 2 - roll
     * 3 - slide
     * 
     */

    private short activ = 0, frame = 1;
    private int interval = 0;
    private final int intervalTo = 100;

    private boolean isMoving = false;

    private int team;
    private int viata;

    private boolean canjump = true;
    private float accel = 1f;
    private short jumpNo = 0;
    private short jumpMax = 2;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        Imagini();
        setViata();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        isMoving = false;
        activ = 0;

        if( gc.getInput().isKeyPressed(Input.KEY_W) && jumpNo < jumpMax && canjump ) {
            jumpNo++;
            if( jumpNo == 1 ) {
                accel = -1.5f;
            }
            if( jumpNo > 1 ) {
                accel = -1f;
            }
        }
        if( jumpNo > 0 ) {
            activ = 1;
            isMoving = true;
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
            isMoving = true;
            modX(speed * delta);
            if( colid() ) {
                modX(-speed * delta);
            }
        }

        if( gc.getInput().isKeyDown(Input.KEY_A) ) {
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
        g.setColor(Color.red);
        img[activ][frame].draw(x, y);
        g.draw(poly);

    }

    protected void Animatie(int delta) {

        if( isMoving == true )
            interval += delta;
        else {
            frame = 0;
            interval = intervalTo - 1;
        }

        if( interval > intervalTo ) {
            interval = 0;
            frame++;
        }

        if( frame >= frames[activ] )
            frame = 0;

    }

    private void Imagini() {
        String links[] = { "res/player/run.png", "res/player/jump.png", "res/player/roll.png", "res/player/slide.png" };

        img = new Image[frames.length][9];

        for( int i = 0; i < frames.length; i++ ) {
            Image temp = null;
            SpriteSheet sheet = null;

            try {
                temp = new Image(links[i]);
                sheet = new SpriteSheet(links[i], temp.getWidth() / frames[i], temp.getHeight());

            } catch (Exception e) {
                e.printStackTrace();
            }

            if( i == 0 )
                setPoly(x, y, temp.getWidth() / frames[i], temp.getHeight());

            for( int k = 0; k < sheet.getHorizontalCount(); k++ ) {
                img[i][k] = sheet.getSprite(k, 0);
            }
        }

    }

    private void setViata() {
        viata = 100;
    }

    private void setPoly(float x, float y, float w, float h) {
        poly = new Rectangle(x, y, w, h);
    }

    private void modX(float amont) {
        x += amont;
        poly.setX(x);
    }

    private void modY(float amont) {
        y += amont;
        poly.setY(y);
    }

    //                 getters

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Rectangle getPoly() {
        return poly;
    }

    public void setPoly(Rectangle poly) {
        this.poly = poly;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getViata() {
        return viata;
    }

    public void setViata(int viata) {
        this.viata = viata;
    }

}
