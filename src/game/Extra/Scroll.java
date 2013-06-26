package game.Extra;

import game.Start;
import game.GamePlay.GameplayState;
import game.GamePlay.GameplayState.STATES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Scroll {

    private Image      img;
    private short      x, y;
    private Rectangle  zon;
    private Random     zar     = new Random();
    private final byte noFacts = 13;
    private String     message = "avbftw 3gv8 c732gc7 6t 2c5t 4524 5y 54yc2 5y45vcy25y 26uy 2547uy 2gv2356 f7u26 g7";

    public Scroll() {

        try {
            img = new Image("res/item/scroll.png");
        }
        catch (SlickException e) {
            e.printStackTrace();
        }

        x = (short) ((Start.getWIDTH() / 2 - img.getWidth() / 2) - GameplayState.getCamera().getX());
        y = (short) ((Start.getHEIGHT() / 2 - img.getHeight() / 2) - GameplayState.getCamera().getY());
        zon = new Rectangle(x, y, img.getWidth(), img.getHeight());
        // zon = new Rectangle(-50, 300, 50, 50);

        gatFact();
        genText();
    }

    private void gatFact() {

        byte rand = (byte) (1 + zar.nextInt(noFacts));

        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("intrebari.txt")));

        for (byte i = 0; i < rand; i++) {
            try {
                message = br.readLine();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void genText() {
        int i = 0;
        int range = 55;
        StringBuilder sb = new StringBuilder(message);
        i = 0;

        while (i + range < sb.length()) {
            if (sb.indexOf("*") != -1) {
                i = sb.indexOf("*");
                sb.replace(i, i + 1, "\n");
            }
            if (i + range >= sb.length()) {
                break;
            }
            i = sb.lastIndexOf(" ", i + range);
            if (i == -1) {
                break;
            }
            sb.replace(i, i + 1, "\n");
        }

        while (sb.indexOf("*") != -1) {
            i = sb.indexOf("*");
            sb.replace(i, i + 1, "\n");
        }

        message = sb.toString();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if ((gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && zon.contains(gc.getInput().getMouseX() - GameplayState.getCamera().getX(), gc.getInput().getMouseY() - GameplayState.getCamera().getY())) || gc.getInput().isKeyPressed(Res.jump)) {
            GameplayState.setToUpd(STATES.PLAY);
            message = null;
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        img.draw(x, y);
        g.drawString(message, x + 55, y + 40);

    }

}
