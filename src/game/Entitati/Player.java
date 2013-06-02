package game.Entitati;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.PackedSpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Physics {

    private Image img[][];
    private final short frames[] = { 8, 6, 9, 5, 6 };
    /* indicele fiecaruia e          0  1  2  3  4  5  6  7  8  9
     * 
     * 0 - run
     * 1 - to_jump
     * 2 - roll
     * 3 - slide
     * 4 - to_slide
     * 
     */

    private short actiune = 0, frame = 0;
    private boolean rstFrame = false;
    private short interval = 0;
    private final short intervalTo = 80;

    private boolean hasNext = false;
    private boolean isActiv = false;
    private short buff;
    private short next;

    private boolean canjump = true;
    private float accel = 1f;
    private short jumpNo = 0;
    private short jumpMax = 2;

    private static short lifes = 3;
    private short imunitate = 0;

    private float marY;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        Imagini();
        setPoly(x, y, img[0][0].getWidth(), img[0][0].getHeight());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        marY = poly.getHeight();

        if( isActiv )
            actiune = 0;
        isActiv = false;

        if( gc.getInput().isKeyPressed(Input.KEY_W) && jumpNo < jumpMax && canjump && !colid() && !isActiv ) {
            jumpNo++;
            if( jumpNo == 1 ) {
                accel = -1.5f;
            }
            if( jumpNo > 1 ) {
                accel = -1f;
            }
            rstFrame = true;
        }

        jump_gravity(delta);
        //       Move_st_dr(gc, delta);

        if( gc.getInput().isKeyPressed(Input.KEY_S) && !isActiv ) {
            rstFrame = true;
            buff = 4;
            next = 3;
            hasNext = true;
        }

        if( gc.getInput().isKeyDown(Input.KEY_S) ) {
            isActiv = true;
        }

        Animatie(delta);

        if( isActiv ) {
            if( hasNext ) {
                actiune = buff;
            } else {
                actiune = next;
            }
        }
        
        System.out.printf("%d %d %d \n", actiune, frame, frames[actiune]);

        poly.setSize(img[actiune][frame].getWidth(), img[actiune][frame].getHeight());

        modY(marY - poly.getHeight());

        if( gc.getInput().isKeyPressed(Input.KEY_F1) ) {
            System.out.println(x + " " + y);
        }
    }

    @SuppressWarnings("unused")
    private void Move_st_dr(GameContainer gc, int delta) {
        if( gc.getInput().isKeyDown(Input.KEY_D) ) {
            modX(speed * delta);
            if( colid() ) {
                modX(-speed * delta);
            }
        }

        if( gc.getInput().isKeyDown(Input.KEY_A) ) {
            modX(-speed * delta);
            if( colid() ) {
                modX(speed * delta);
            }
        }
    }

    private void jump_gravity(int delta) {
        if( jumpNo > 0 ) {
            isActiv = true;
            buff = 1;
        }

        modY(accel * delta);
        if( colid() ) {
            modY(-accel * delta);
            if( colid() && imunitate == 0 ) {
                addLifes(-1);
                imunitate = 2000;

            }

            jumpNo = 0;
            if( accel >= 0 ) {
                adapt(1);
                canjump = true;
            } else {
                adapt(-1);
                canjump = false;
            }
        }
        imunitate -= delta;
        if( imunitate < 0 )
            imunitate = 0;

        accel += 0.005f * delta;
        if( accel > 1 )
            accel = 1;
    }

    private void adapt(int cantitate) {
        while (!colid()) {
            modY(cantitate);
        }
        modY(-cantitate);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.setColor(Color.red);
        g.setLineWidth(1);
        g.draw(poly);
        //        System.out.println(activ+" "+frame);
        img[actiune][frame].setAlpha(1f);
        if( imunitate > 0 )
            img[actiune][frame].setAlpha(0.3f + (float) Math.abs(Math.sin(Math.toRadians(imunitate))));
        img[actiune][frame].draw(x, y);

    }

    private void Animatie(int delta) {

        interval += delta;
        if( rstFrame ) {
            rstFrame = false;
            frame = 0;
        }

        if( interval > intervalTo ) {
            interval = 0;
            frame++;
        }

        if( frame >= frames[actiune] ) {
            frame = 0;
            if( hasNext )
                hasNext = false;
        }

    }

    private void Imagini() {
        String links[] = { "run", "to_jump", "roll", "slide", "to_slide" };

        img = new Image[frames.length][9];

        PackedSpriteSheet sheet = null;
        try {
            sheet = new PackedSpriteSheet("res/player/sheet_activ.def", Color.white);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        for( int i = 0; i < links.length; i++ ) {
            boolean finish = false;

            frames[i] = 0;

            for( int k = 0; !finish; k++ ) {

                try {
                    img[i][k] = sheet.getSprite(String.format("%s%d.bmp", links[i], k));
                    frames[i]++;
                } catch (Exception e) {
                    finish = true;
                    //System.out.println(String.format("%s%d", links[i], frames[i]));
                }

            }

        }
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

    public static short getLifes() {
        return lifes;
    }

    public static void setLifes(short lifes) {
        Player.lifes = lifes;
    }

    public static void addLifes(int i) {
        if( Player.lifes + i >= 0 )
            Player.lifes += i;
        System.out.println(Player.lifes);
    }

}
