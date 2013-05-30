package game.GamePlay;

import game.Start;
import game.Entitati.Player;
import game.Extra.Camera;
import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {

    private final int ID;
    private Camera camera;

    private static Player player;
    private static WorldMap world;

    private int tick = 0;
    private int tickMax = 50;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player(100, 400);
        camera = new Camera(Start.getWIDTH(), Start.getHEIGHT());
        world = new WorldMap();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Start.setHEIGHT(gc.getHeight());
        Start.setWIDTH(gc.getWidth());
        player.update(gc, sbg, delta);

        tick += delta;
        if( tick > tickMax ) {
            tick=0;
            world.update(gc, sbg, delta);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.lightGray);
        camera.translate(g, player);
        world.render(gc, sbg, g);
        player.render(gc, sbg, g);
    }

    public GameplayState(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return ID;
    }

    public Camera getCamera() {
        return camera;
    }

    public static WorldMap getWorldMap() {
        return world;
    }

    public static Player getPlayer() {
        return player;
    }
}
