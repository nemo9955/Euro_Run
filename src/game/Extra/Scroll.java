package game.Extra;

import game.GamePlay.GameplayState;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private Image img;
    private short x, y;
    private Rectangle zon;

    private Random zar = new Random();
    private final byte noFacts = 13;

    private String message;

    public Scroll(GameContainer gc) {

        try {
            img = new Image("res/item/scroll.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        x = (short) ((450 - img.getWidth() / 2) - GameplayState.getCamera().getX());
        y = (short) ((300 - img.getHeight() / 2) - GameplayState.getCamera().getY());

        //   zon = new Rectangle(x, y, img.getWidth(), img.getHeight());
        zon = new Rectangle(-50, 300, 50, 50);

        gatFact();
        genText(gc);
    }

    private void gatFact() {
        //     byte rand = (byte) zar.nextInt(noFacts);
        byte rand = 1;
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("res/item/intrebari.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        for( byte i = 0; i < rand; i++ ) {
            try {
                message = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void genText(GameContainer gc) {
        int i = 0;
        int range = 55;
        StringBuilder sb = new StringBuilder(message);
        while (i + range < sb.length() && (i = sb.lastIndexOf(" ", i + range)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        message = sb.toString();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if( gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            if( zon.contains(gc.getInput().getMouseX() - GameplayState.getCamera().getX(), gc.getInput().getMouseY() - GameplayState.getCamera().getY()) ) {
                GameplayState.setTaken(false);
            }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img.draw(x, y);
        g.draw(zon);
        g.drawString(message, x + 55, y + 40);
    }
}
