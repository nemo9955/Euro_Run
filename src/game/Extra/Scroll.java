package game.Extra;

import game.Start;
import game.States.GameplayState;
import game.States.GameplayState.STATES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;


public class Scroll {

    protected Image      img;
    protected short      x, y;
    protected Rectangle  zon;
    protected Random     zar     = new Random();
    protected final byte noFacts = 17;
    protected String     message = "Asta e mesajul default , mai mult ca sigur fisierul cu intrebari nu e in pachetul \"Extra\" ........ chiar asa e de greu sa dai copy / paste ? ";

    protected Sound      sunet;

    public Scroll() {

        try {
            img = new Image( "res/item/scroll.png" );
            sunet = new Sound( "res/sunet/ding1.wav" );
        }
        catch (SlickException e) {
            e.printStackTrace();
        }

        x = (short) ( ( Start.getWIDTH() /2 -img.getWidth() /2 ) -GameplayState.getCamera().getX() );
        y = (short) ( ( Start.getHEIGHT() /2 -img.getHeight() /2 ) -GameplayState.getCamera().getY() );
        zon = new Rectangle( x, y, img.getWidth(), img.getHeight() );
        // zon = new Rectangle(-50, 300, 50, 50);
        if ( ! ( this instanceof ScrollTara ) ) {
            gatFact();
            genText();
        }
    }

    protected void gatFact() {

        byte rand = (byte) ( 1 +zar.nextInt( noFacts ) );
        // rand = 12;

        BufferedReader br = null;

        try {

            br = new BufferedReader( new InputStreamReader( ResourceLoader.getResourceAsStream( "res/item/facts.txt" ) ) );

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

    protected void genText() {
        int i = 0;
        int range = 52;
        StringBuilder sb = new StringBuilder( message );
        i = 0;

        while ( i +range <sb.length() ) {
            if ( sb.lastIndexOf( "*", i +range ) ==-1 ) {
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
        sunet.play();
        message = sb.toString();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if ( ( gc.getInput().isMousePressed( Input.MOUSE_LEFT_BUTTON ) &&zon.contains( gc.getInput().getMouseX() -GameplayState.getCamera().getX(), gc.getInput().getMouseY() -GameplayState.getCamera().getY() ) ) ||gc.getInput().isKeyPressed( Res.jump ) ) {
            GameplayState.setToUpd( STATES.PLAY );
            message = null;
        }

        if ( gc.getInput().isKeyPressed( Input.KEY_F8 ) ) {
            gatFact();
            genText();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        img.draw( x, y );
        g.drawString( message, x +85, y +90 );

    }

}
