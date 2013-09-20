package game.States;

import game.Start;
import game.Extra.Button;
import game.Extra.Camera;
import game.Extra.Res;
import game.Extra.Scroll;
import game.Extra.ScrollTara;
import game.Player.Player;
import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {

    private final byte      ID;
    private static Camera   camera;
    private static Player   player;
    private static WorldMap world;
    private byte            tick    = 0;
    private final byte      tickMax = 58;
    private boolean         mort;
    private static Scroll   scroll  = null;

    public static long      distanta;
    public static int       iteme;
    public static short     tariCunosti;

    private Rectangle       bkg;
    private Button          resume;
    private Button          meniu;

    private Music           dal;

    public enum STATES {
        PLAY, SCROL, PAUSE;
    }

    private static STATES toUpd = STATES.PLAY;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player( 0, -200 );
        world = new WorldMap();
        camera = new Camera();
        bkg = new Rectangle( 0, 0, 600, 400 );
        resume = new Button( 0, 0, "resume.png" );
        meniu = new Button( 0, 0, "mainMenu.png" );
        dal = new Music( "res/sunet/run.wav" );
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        camera.reset();
        dal.stop();
        MenuState.imn.loop();
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {

        MenuState.imn.stop();

        dal.loop();
        player.reset( 0, -200 );
        world.reset();
        tick = 0;
        distanta = 0;
        iteme = 0;
        mort = false;
        toUpd = STATES.PLAY;
        tariCunosti = 0;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if ( gc.getInput().isKeyPressed( Res.pause ) ) {
            if ( toUpd ==STATES.PAUSE )
                toUpd = STATES.PLAY;
            else
                toUpd = STATES.PAUSE;
        }

        switch (toUpd) {
            case PLAY:
                updateGame( gc, sbg, delta );
                break;
            case SCROL:
                scroll.update( gc, sbg, delta );
                break;
            case PAUSE:
                updateMenu( gc, sbg, delta );
                break;
        }
    }

    private void updateMenu(GameContainer gc, StateBasedGame sbg, int delta) {

        bkg.setLocation( -camera.getX() +gc.getWidth() /2 -bkg.getWidth() /2, -camera.getY() +gc.getHeight() /2 -bkg.getHeight() /2 );
        resume.setCenterLocation( (int) bkg.getCenterX(), (int) bkg.getCenterY() -50 );
        meniu.setCenterLocation( (int) bkg.getCenterX(), (int) bkg.getCenterY() +50 );

        if ( resume.clikOn( gc ) )
            toUpd = STATES.PLAY;
        if ( meniu.clikOn( gc ) ) {
            // toUpd = STATES.PLAY ;
            sbg.enterState( Start.MENUSTATE );
        }
    }

    private void updateGame(GameContainer gc, StateBasedGame sbg, int delta) {
        player.update( gc, sbg, delta );
        tick += delta;

        if ( tick >tickMax ) {
            world.update( gc, sbg, tick );
            tick = 0;
            if ( Player.getLifes() >0 ) {
                distanta += WorldMap.getMove();
            }
        }

        if ( Player.getLifes() <=0 &&!mort ) {
            mort = true;
            sbg.enterState( Start.DEATHSTATE );
        }
    }


    private final int draw = 30;

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground( Color.cyan );
        camera.translate( g, player );

        world.render( gc, sbg, g );
        player.render( gc, sbg, g );

        g.setColor( Color.red );
        g.drawString( "Vieti : ", 0, draw );

        for (int i = 0 ; i <Player.getLifes() ; i ++ ) {
            g.fillOval( 73 + ( i *30 ), draw, 20, 20 );
        }

        g.setColor( Color.black );
        g.drawString( String.format( "Distanta : %d", distanta ), 0, draw +30 );
        g.drawString( String.format( "Cunostinte dobandite : %d", iteme ), 0, draw +50 );

        switch (toUpd) {
            case SCROL:
                scroll.render( gc, sbg, g );
                break;
            case PAUSE:
                g.setColor( new Color( 0, 0, 0, 0.7f ) );
                g.fill( bkg );
                resume.render( gc, sbg, g );
                meniu.render( gc, sbg, g );
                break;
            default:
                break;
        }
    }

    public GameplayState() {
        this.ID = Start.GAMEPLAYSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }

    public static Camera getCamera() {
        return camera;
    }

    public static WorldMap getWorldMap() {
        return world;
    }

    public static Player getPlayer() {
        return player;
    }

    public boolean isMort() {
        return mort;
    }

    public void setMort(boolean mort) {
        this.mort = mort;
    }

    public static STATES getToUpd() {
        return toUpd;
    }

    public static void setToUpd(STATES toUpd) {
        GameplayState.toUpd = toUpd;
    }

    public static Scroll getScroll() {
        return scroll;
    }

    public static void makeFactScroll() {
        GameplayState.scroll = new Scroll();
    }

    public static void makeTaraScroll(int tara) {
        GameplayState.scroll = new ScrollTara( tara );
    }

    public static void modIteme(int iteme) {
        GameplayState.iteme += iteme;
        if ( GameplayState.iteme %10 ==0 )
            Player.addLifes( 1 );

    }

    public static void modTariCunoscute(byte i) {
        tariCunosti ++;
        if ( tariCunosti %5 ==0 )
            Player.addLifes( 1 );

    }
}
