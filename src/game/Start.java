package game;

import game.States.CreditState;
import game.States.DeathState;
import game.States.GameplayState;
import game.States.InstructiuniState;
import game.States.MenuState;
import game.States.OptionState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Start extends StateBasedGame {

    /**
     * @author Nemo9955
     */

    private final static String titlu             = "Euro Run";
    private static int          WIDTH             = 800;
    private static int          HEIGHT            = 600;

    public static final byte    MENUSTATE         = 0;
    public static final byte    GAMEPLAYSTATE     = 1;
    public static final byte    OPTIONSTATE       = 2;
    public static final byte    DEATHSTATE        = 3;
    public static final byte    INSTRUCTIUNISTATE = 4;
    public static final byte    CREDITSTATE       = 5;

    public Start() throws SlickException {
        super( titlu );
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState( new MenuState() );
        addState( new OptionState() );
        addState( new DeathState() );
        addState( new GameplayState() );
        addState( new InstructiuniState() );
        addState( new CreditState() );
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer( new Start() );
        app.setDisplayMode( WIDTH, HEIGHT, false );
        app.setShowFPS( false );
        app.setVSync( true );
        app.setMusicVolume( 0.3f );
        app.start();
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int wIDTH) {
        WIDTH = wIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int hEIGHT) {
        HEIGHT = hEIGHT;
    }
}