package game.GamePlay;

import game.Start;
import game.Extra.Camera;
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
    private Camera camera;

    private static Player player;
    private static WorldMap world;

    private byte tick = 0;
    private final byte tickMax = 62;

    private static long score = 0;
    private boolean mort = false;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
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

        if( Player.getLifes() <= 0 && !mort ) {
            mort = true;
            System.out.println("esti mort , distanta parcursa este de :" + score);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.cyan);
        camera.translate(g, player);

        for( int i = 0; i <= 11; i++ ) {
            g.setColor(new Color( 100 , 200 , 255 ,0.2f+ i*0.06f ));
            g.fillRect(-200, -300+(i*100) , gc.getWidth()+100, 100 );
        }

        world.render(gc, sbg, g);
        player.render(gc, sbg, g);
        g.setColor(Color.red);
        g.drawString("Vieti : ", 0, 650);
        for( int i = 0; i < Player.getLifes(); i++ ) {
            g.fillOval(73 + (i * 30), 650, 20, 20);
        }
        g.setColor(Color.black);
        g.drawString(String.format("Distanta : %d", score), 0, 680);
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

    public static long getScore() {
        return score;
    }

    public void setScore(long score) {
        GameplayState.score = score;
    }

    public boolean isMort() {
        return mort;
    }

    public void setMort(boolean mort) {
        this.mort = mort;
    }
    
    
}
