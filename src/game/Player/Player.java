package game.Player;

import game.Extra.Res;

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

    private Image       img[][];
    private final byte  frames[]   = { 8, 6, 9, 5, 6 };
    /*
     * indicele fiecaruia e 0 1 2 3 4 5 6 7 8 9
     * 0 - run 1 - to_jump 2 - roll 3 - slide 4 - to_slide
     */
    private Image       scut;
    private short       rezist     = 32000;
    private byte        actiune    = 0, frame = 0;
    private byte        interval   = 0;
    private final byte  intervalTo = 80;
    private boolean     hasNext    = false;
    private byte        buff;
    private byte        next;
    private byte        isActiv    = 0;
    private boolean     canjump    = true;
    private float       accel      = 1f;
    private byte        jumpNo     = 0;
    private final byte  jumpMax    = 2;
    private static byte lifes      = 3;
    private short       imunitate  = 0;
    private float       marY;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        lifes = 3;
        Imagini();
        setPoly(x, y, img[0][0].getWidth(), img[0][0].getHeight());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        marY = poly.getHeight();
        if (rezist - delta > 0)
            rezist -= delta;
        else
            rezist = 0;
        if (gc.getInput().isKeyDown(Res.jump)) {
            if (jumpNo < jumpMax && canjump && !colid() && isActiv <= 1) {
                if (gc.getInput().isKeyPressed(Res.jump)) {
                    jumpNo++;
                    if (jumpNo == 1) {
                        accel = -1.5f;
                    }
                    if (jumpNo > 1) {
                        accel = -1.2f;
                    }
                }
                if (jumpNo > 0) {
                    accel -= 0.002f * delta;
                }
            }
        }

        jump_gravity(delta);

        // Move_st_dr(gc, delta);

        if (gc.getInput().isKeyDown(Res.roll) && isActiv <= 2) {
            next = 2;
            hasNext = false;
            isActiv = 2;
        }

        if (gc.getInput().isKeyDown(Res.slide) && (isActiv == 0 || isActiv == 3)) {
            if (gc.getInput().isKeyPressed(Res.slide)) {
                buff = 4;
                next = 3;
                hasNext = true;
            }
            isActiv = 3;
        }

        Animatie(delta);

        if (isActiv != 0) {
            if (hasNext) {
                schAct(buff);
            }
            else {
                schAct(next);
            }
        }
        // System.out.printf("%d %d %d %d \n", actiune, buff, next, frame);
        // System.out.println(isActiv);
        if (!gc.getInput().isKeyDown(Res.slide) && !gc.getInput().isKeyDown(Res.roll) && accel >= 0) {
            isActiv = 0;
            schAct((byte) 0);
        }
        poly.setSize(img[actiune][frame].getWidth(), img[actiune][frame].getHeight());
        modY(marY - poly.getHeight());
        if (gc.getInput().isKeyPressed(Input.KEY_F1)) {
            System.out.println(x + " " + y);
        }
        if (y > 700 || y < -600) {
            System.out.println("teleport");
            setY(300);
        }
    }

    private void schAct(byte act) {
        if (act != actiune)
            frame = 0;
        actiune = act;
    }

    @SuppressWarnings("unused")
    private void Move_st_dr(GameContainer gc, int delta) {
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            modX(speed * delta);
            if (colid()) {
                modX(-speed * delta);
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            modX(-speed * delta);
            if (colid()) {
                modX(speed * delta);
            }
        }
    }

    private void jump_gravity(int delta) {
        if (jumpNo >= 1) {
            if (accel < 0) {
                next = 1;
                hasNext = false;
                isActiv = 1;
            }
        }
        modY(accel * delta);
        if (colid()) {
            modY(-accel * delta);
            if (colid() && imunitate == 0 && rezist == 0 && !canMakeStep()) {
                addLifes(-1);
                imunitate = 3000;
            }
            jumpNo = 0;
            if (accel >= 0) {
                adapt(1);
                canjump = true;
            }
            else {
                adapt(-1);
                canjump = false;
            }
            accel = 0;
        }
        imunitate -= delta;
        if (imunitate < 0)
            imunitate = 0;
        accel += 0.006f * delta;
        if (accel > 1)
            accel = 1;
    }

    private boolean canMakeStep() {
        modY(-15);
        if (!colid()) {
            modY(15);
            while (!colid()) {
                modY(-2);
            }
            return true;
        }
        modY(15);
        return false;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        // g.setColor(Color.red);
        // g.setLineWidth(1);
        // g.draw(poly);
        // System.out.println(activ+" "+frame);
        img[actiune][frame].setAlpha(1f);
        if (imunitate > 0)
            img[actiune][frame].setAlpha(0.1f + (float) Math.abs(Math.sin(Math.toRadians(imunitate))));
        img[actiune][frame].draw(x, y);
        if (rezist > 0)
            scut.draw(poly.getCenterX() - (poly.getWidth() * 1.6f / 2), poly.getCenterY() - (poly.getHeight() * 1.4f / 2), poly.getWidth() * 1.6f, poly.getHeight() * 1.4f);

    }

    private void Animatie(int delta) {
        interval += delta;
        if (interval > intervalTo) {
            interval = 0;
            frame++;
        }
        if (frame >= frames[actiune]) {
            frame = 0;
            if (hasNext) {
                hasNext = false;
            }
        }
    }

    private void Imagini() {
        String links[] = { "run", "to_jump", "roll", "slide", "to_slide" };
        img = new Image[frames.length][9];
        PackedSpriteSheet sheet = null;
        try {
            sheet = new PackedSpriteSheet("res/player/sheet_activ.def", Color.white);
            scut = new Image("res/player/scut.png");
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        scut.setAlpha(0.8f);
        for (int i = 0; i < links.length; i++) {
            boolean finish = false;
            frames[i] = 0;
            for (int k = 0; !finish; k++) {
                try {
                    img[i][k] = sheet.getSprite(String.format("%s%d.bmp", links[i], k));
                    frames[i]++;
                }
                catch (Exception e) {
                    finish = true;
                    // System.out.println(String.format("%s%d", links[i],
                    // frames[i]));
                }
            }
        }
    }

    private void setPoly(float x, float y, float w, float h) {
        poly = new Rectangle(x, y, w, h);
    }

    // getters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        poly.setX(x);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        poly.setY(y);
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

    public static void setLifes(byte lifes) {
        Player.lifes = lifes;
    }

    public static void addLifes(int i) {
        if (Player.lifes > 0)
            if (Player.lifes + i >= 0 && Player.lifes + i <= 5) {
                Player.lifes += i;
                System.out.println(Player.lifes);
            }
    }

    public short getRezist() {
        return rezist;
    }

    public void setRezist(short rezist) {
        this.rezist = rezist;
    }

    public void modRezist(short rezist) {
        this.rezist += rezist;
    }
}
