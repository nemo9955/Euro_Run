package game.World;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class WorldMap {

    private static List<Block> blocks = new LinkedList<Block>();

    private final static int startGen = 1024;
    private final static int endGen = -96;
    private final static int size = 32;

    private int move = 2;
    private int poz;

    public WorldMap() {
        poz = startGen;
        for( int i = endGen; i <= startGen; i += size )
            blocks.add(new BlockSolid(i, 550));
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        poz -= move * delta;
        for( int i = 0; i < blocks.size(); i++ ) {
            blocks.get(i).update(gc, sbg, delta);
            blocks.get(i).modX(-move * delta);
        }
        if( startGen - poz >= size ) {
            blocks.add(new BlockSolid((int) poz, 550));
            poz += size;
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

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

    public float getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public static int getStartgen() {
        return startGen;
    }

    public static int getEndgen() {
        return endGen;
    }

}
