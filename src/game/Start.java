package game;

import game.Extra.MenuState;
import game.Extra.OptionState;
import game.GamePlay.GameplayState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Start extends StateBasedGame {

    /**
     * @author Nemo9955
     */

    private final static String titlu         = "Euro Run";
    private static int          WIDTH         = 800;
    private static int          HEIGHT        = 600;


    // aici o sa initializam stagiile ;

    public static final byte    MENUSTATE     = 0;
    public static final byte    GAMEPLAYSTATE = 1;
    public static final byte    OPTIONSTATE = 2;

    /*
     * public Start(String titlu) {
     * super(titlu);
     * }
     */

    public Start() throws SlickException {
        super(titlu);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        // aici o sa le adaugam in joc
        addState(new MenuState());
        addState(new OptionState());
        addState(new GameplayState());
    }

    public static void main(String[] args) throws SlickException {
        // AppGameContainer app = new AppGameContainer(new Start(titlu));
        AppGameContainer app = new AppGameContainer(new Start());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setResizable(false);
        app.setShowFPS(true);
        app.setVSync(true);
        app.setTargetFrameRate(60);
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