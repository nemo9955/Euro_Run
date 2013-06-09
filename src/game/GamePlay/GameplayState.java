package game.GamePlay;

import game.Start;
import game.Extra.Camera;
import game.Extra.Scroll;
import game.Player.Player;
import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {

    private final byte ID;
    private static Camera camera;

    private static Player player;
    private static WorldMap world;

    private byte tick = 0;
    private final byte tickMax = 62;

    private static long distanta = 0;
    private static int iteme = 0;
    private boolean mort = false;

    private static boolean taken = false;
    private static Scroll scroll = null;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player(0, 300);
        camera = new Camera(Start.getWIDTH(), Start.getHEIGHT());
        world = new WorldMap();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if( !taken ) {
            updateGame(gc, sbg, delta);
        } else {
            scroll.update(gc, sbg, delta);
        }
    }

    private void updateGame(GameContainer gc, StateBasedGame sbg, int delta) {
        player.update(gc, sbg, delta);

        tick += delta;
        if( tick > tickMax ) {
            world.update(gc, sbg, tick);
            tick = 0;
            if( Player.getLifes() > 0 ) {
                distanta += WorldMap.getMove();
            }
        }

        if( Player.getLifes() <= 0 && !mort ) {
            mort = true;
            System.out.println("esti mort , distanta parcursa este de :" + distanta);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.cyan);
        camera.translate(g, player);

        for( int i = 0; i <= 11; i++ ) {
            g.setColor(new Color(100, 200, 255, 0.2f + i * 0.06f));
            g.fillRect(-200, -300 + (i * 100), gc.getWidth() + 100, 100);
        }

        world.render(gc, sbg, g);
        player.render(gc, sbg, g);
        g.setColor(Color.red);
        g.drawString("Vieti : ", 0, 650);
        for( int i = 0; i < Player.getLifes(); i++ ) {
            g.fillOval(73 + (i * 30), 650, 20, 20);
        }
        g.setColor(Color.black);
        g.drawString(String.format("Distanta : %d", distanta), 0, 680);
        
        if(taken){
            scroll.render(gc, sbg, g);
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

    public static boolean isTaken() {
        return taken;
    }

    public static void setTaken(boolean taken) {
        GameplayState.taken = taken;
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
    }
    
    
}
