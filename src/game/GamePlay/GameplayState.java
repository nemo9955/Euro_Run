package game.GamePlay;

import game.Start;
import game.Entitati.Player;
import game.Extra.Camera;
import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {

    private final int ID;
    private Camera camera;

    private static Player player;
    private static WorldMap world;

    private Image temp;

    private int tick = 0;
    private final int tickMax = 62;

    private long score = 0;
    private boolean mort = false;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        temp = new Image("res/Plants_00001.png");
        player = new Player(0, 300);
        camera = new Camera(Start.getWIDTH(), Start.getHEIGHT());
        world = new WorldMap();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        player.update(gc, sbg, delta);

        tick += delta;
        if( tick > tickMax ) {
            world.update(gc, sbg, tick);
            tick = 0;
            if( Player.getLifes() > 0 ) {
                score += WorldMap.getMove();
            }
        }
        
        if( Player.getLifes() <= 0 &&!mort ) {
            mort=true;
            System.out.println("esti mort , distanta parcursa este de :" + score);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.lightGray);
        camera.translate(g, player);
        temp.draw(0, -100);
        world.render(gc, sbg, g);
        player.render(gc, sbg, g);
        
        for(int i=0 ; i<Player.getLifes() ; i++){
            
        }
    }

    public GameplayState() {
        this.ID = Start.GAMEPLAYSTATE;
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
