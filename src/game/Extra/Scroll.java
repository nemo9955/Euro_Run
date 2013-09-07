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
    private String     message = "Asta e mesajul default , mai mult ca sigur fisierul cu intrebari nu e in pachetul \"Extra\" ........ chiar asa e de greu sa dai copy / paste ? ";

    public Scroll() {

        try {
            img = new Image( "res/item/scroll.png" );
        }
        catch (SlickException e) {
            e.printStackTrace();
        }

        x = (short) ( ( Start.getWIDTH() /2 -img.getWidth() /2 ) -GameplayState.getCamera().getX() );
        y = (short) ( ( Start.getHEIGHT() /2 -img.getHeight() /2 ) -GameplayState.getCamera().getY() );
        zon = new Rectangle( x, y, img.getWidth(), img.getHeight() );
        // zon = new Rectangle(-50, 300, 50, 50);

        gatFact();
        genText();
    }

    private void gatFact() {

        byte rand = (byte) ( 1 +zar.nextInt( noFacts ) );
        rand = 12;

        BufferedReader br = null;

        try {

            br = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream( "intrebari.txt" ) ) );

            for (byte i = 0 ; i <rand ; i ++ ) {

                try {
                    message = br.readLine();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            System.out.println( "fisierul intrebari.txt nu e in pachetul Extra" );
        }
        br = null;

    }

    private void genText() {
        int i = 0;
        int range = 45;
        StringBuilder sb = new StringBuilder( message );
        i = 0;

        while ( i +range <sb.length() ) {
            if ( doSpace( range, sb, i ) ) {
                if ( ( i = sb.lastIndexOf( " ", i +range ) ) ==-1 )
                    break;
                sb.replace( i, i +1, "\n" );
            }
            else {
                i = sb.indexOf( "*" );
                sb.replace( i, i +1, "\n" );
            }
            if ( i +range >=sb.length() )
                break;
        }

        while ( sb.indexOf( "*" ) !=-1 ) {
            i = sb.indexOf( "*" );
            sb.replace( i, i +1, "\n" );
        }

        message = sb.toString();
    }

    private boolean doSpace(int range, StringBuilder sb, int at) {
        if ( sb.lastIndexOf( "*", at +range ) !=-1 )
            return false;
        else
            return true;
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if ( ( gc.getInput().isMousePressed( Input.MOUSE_LEFT_BUTTON ) &&zon.contains( gc.getInput().getMouseX() -GameplayState.getCamera().getX(), gc.getInput().getMouseY() -GameplayState.getCamera().getY() ) ) ||gc.getInput().isKeyPressed( Res.jump ) ) {
            GameplayState.setToUpd( STATES.PLAY );
            message = null;
        }

        if ( gc.getInput().isKeyPressed( Input.KEY_R ) ) {
            gatFact();
            genText();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        img.draw( x, y );
        g.drawString( message, x +80, y +125 );

    }

}
