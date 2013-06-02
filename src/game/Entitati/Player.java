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
    private final short frames[] = { 8, 6, 5, 9, 5 };
    /* indicele fiecaruia e          0  1  2  3  4  5  6  7  8  9
     * 
     * 0 - run
     * 1 - to_jump
     * 2 - roll
     * 3 - slide
     * 4 - to_slide
     * 
     */

    private short activ = 0, frame = 0;
    private int interval = 0;
    private final int intervalTo = 100;

    private boolean isMoving = false;

    private boolean canjump = true;
    private float accel = 1f;
    private short jumpNo = 0;
    private short jumpMax = 2;

    private static short lifes = 3;
    private short imunitate = 0;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        Imagini();
        setPoly(x, y, 10, 10);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        isMoving = true;
        activ = 0;

        if( gc.getInput().isKeyPressed(Input.KEY_W) && jumpNo < jumpMax && canjump && !colid() ) {
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

        /*
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
           */

        if( gc.getInput().isKeyPressed(Input.KEY_F1) ) {
            System.out.println(x + " " + y);
        }

        Animatie(delta);
        poly.setSize(img[activ][frame].getWidth(), img[activ][frame].getHeight());
        
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
        img[activ][frame].setAlpha(1f);
        if( imunitate > 0 )
            img[activ][frame].setAlpha(0.3f + (float) Math.abs(Math.sin(Math.toRadians(imunitate))));
        img[activ][frame].draw(x, y);

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
                    System.out.println(String.format("%s%d", links[i], k));
                }

            }

        }
    }

    private void setPoly(float x, float y, float w, float h) {
        poly = new Rectangle(x, y, w, h);
    }

    @SuppressWarnings("unused")
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
