package game.World;

import game.GamePlay.GameplayState;
import game.Imagini.Background;
import game.Imagini.Imagine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class WorldMap {

    private static List<Block> blocks = new LinkedList<Block>();
    private static List<Imagine> bg = new ArrayList<Imagine>();

    private final static int startGen = 1024;
    private final static int endGen = -256;
    private final static int size = 64;

    private static int move = 21;
    private static int poz;

    private final int interval = 2000;
    private short curent = 0;

    protected Random zar = new Random();

    public WorldMap() {
        poz = startGen;
        for( int i = endGen; i <= startGen; i += size )
            blocks.add(new BlockSolid(i, 550));
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        poz -= move;

        for( int i = 0; i < blocks.size(); i++ ) {
            blocks.get(i).update(gc, sbg, delta);
        }
        for( int i = 0; i < bg.size(); i++ ) {
            bg.get(i).update(gc, sbg, delta);
        }

        while (startGen - poz >= size) {
            blocks.add(new BlockSolid(poz, 550));
            poz += size;
        }

        if( (short) Math.round(GameplayState.getScore() / interval) > curent ) {
            curent = (short) Math.round(GameplayState.getScore() / interval);
            bg.add(new Background(startGen, 500));
        }

        //    if(zar.nextInt(1000) < 50)            blocks.add(new BlockSolid(poz, 500-zar.nextInt(100)));

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        for( int i = 0; i < bg.size(); i++ )
            bg.get(i).render(gc, sbg, g);

        for( int i = 0; i < blocks.size(); i++ )
            blocks.get(i).render(gc, sbg, g);

    }

    public static List<Block> getBlocks() {
        return blocks;
    }

    public boolean isBlock(int i) {
        return blocks.get(i).isExists();
    }

    public Rectangle getBlock(int i) {
        return blocks.get(i).getZon();
    }

    public boolean is_solid(int i) {
        return blocks.get(i).isSolid();
    }

    public static int getStartgen() {
        return startGen;
    }

    public static int getEndgen() {
        return endGen;
    }

    public static int getMove() {
        return move;
    }

    public static void setMove(int move) {
        WorldMap.move = move;
    }

    public static int getSize() {
        return size;
    }

}
