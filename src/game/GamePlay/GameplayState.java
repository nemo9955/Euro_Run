package game.GamePlay;

import game.Start;
import game.Extra.Button;
import game.Extra.Camera;
import game.Extra.Res;
import game.Extra.Scroll;
import game.Player.Player;
import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {

    private final byte      ID;
    private static Camera   camera;
    private static Player   player;
    private static WorldMap world;
    private byte            tick     = 0;
    private final byte      tickMax  = 58;
    private static long     distanta = 0;
    private static int      iteme    = 0;
    private boolean         mort     = false;
    private static Scroll   scroll   = null;

    private Rectangle       bkg;
    private Button          resume;
    private Button          meniu;

    public enum STATES {
        PLAY, SCROL, PAUSE;
    }

    private static STATES toUpd = STATES.PLAY;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player(0, 300);
        camera = new Camera(Start.getWIDTH(), Start.getHEIGHT());
        world = new WorldMap();
        bkg = new Rectangle(0, 0, 600, 400);
        resume = new Button(0, 0, "resume.png");
        meniu = new Button(0, 0, "mainMenu.png");
    }

    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player(0, 300);
        camera = new Camera(Start.getWIDTH(), Start.getHEIGHT());
        world = new WorldMap();
        tick = 0;
        distanta = 0;
        iteme = 0;
        mort = false;
        toUpd=STATES.PLAY;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (gc.getInput().isKeyPressed(Res.pause)) {
            if (toUpd == STATES.PAUSE)
                toUpd = STATES.PLAY;
            else
                toUpd = STATES.PAUSE;
        }

        switch (toUpd) {
            case PLAY:
                updateGame(gc, sbg, delta);
                break;
            case SCROL:
                scroll.update(gc, sbg, delta);
                break;
            case PAUSE:
                updateMenu(gc, sbg, delta);
                break;
        }
    }

    private void updateMenu(GameContainer gc, StateBasedGame sbg, int delta) {

        bkg.setLocation(-camera.getX() + gc.getWidth() / 2 - bkg.getWidth() / 2, -camera.getY() + gc.getHeight() / 2 - bkg.getHeight() / 2);
        resume.setCenterLocation((int) bkg.getCenterX(), (int) bkg.getCenterY() - 50);
        meniu.setCenterLocation((int) bkg.getCenterX(), (int) bkg.getCenterY() + 50);

        if (resume.clikOn(gc))
            toUpd = STATES.PLAY;
        if (meniu.clikOn(gc))
            sbg.enterState(Start.MENUSTATE);
        if (gc.getInput().isKeyPressed(Input.KEY_F5))
            System.out.println(bkg.getX() + " " + bkg.getY());
    }

    private void updateGame(GameContainer gc, StateBasedGame sbg, int delta) {
        player.update(gc, sbg, delta);
        tick += delta;

        if (tick > tickMax) {
            world.update(gc, sbg);
            tick = 0;
            if (Player.getLifes() > 0) {
                distanta += WorldMap.getMove();
            }
        }

        if (Player.getLifes() <= 0 && !mort) {
            mort = true;
            System.out.println("esti mort , distanta parcursa este de :" + distanta);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.cyan);
        camera.translate(g, player);

        for (int i = 0; i <= 11; i++) {
            g.setColor(new Color(100, 200, 255, 0.2f + i * 0.06f));
            g.fillRect(-200, -300 + (i * 100), gc.getWidth() + 100, 100);
        }

        world.render(gc, sbg, g);
        player.render(gc, sbg, g);
        g.setColor(Color.red);
        g.drawString("Vieti : ", 0, 650);

        for (int i = 0; i < Player.getLifes(); i++) {
            g.fillOval(73 + (i * 30), 650, 20, 20);
        }

        g.setColor(Color.black);
        g.drawString(String.format("Distanta : %d", distanta), 0, 680);
        g.drawString(String.format("Cunostinte dobandite : %d", iteme), 0, 700);

        switch (toUpd) {
            case SCROL:
                scroll.render(gc, sbg, g);
                break;
            case PAUSE:
                g.setColor(new Color(0, 0, 0, 0.7f));
                g.fill(bkg);
                resume.render(gc, sbg, g);
                meniu.render(gc, sbg, g);
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

    public static long getDistanta() {
        return distanta;
    }

    public void setDistanta(long score) {
        GameplayState.distanta = score;
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

    public static void makeScroll() {
        GameplayState.scroll = new Scroll();
    }

    public static int getIteme() {
        return iteme;
    }

    public static void modIteme(int iteme) {
        GameplayState.iteme += iteme;
        if (GameplayState.iteme % 10 == 0) {
            Player.addLifes(1);
        }
    }
}
