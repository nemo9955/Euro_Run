package game.World;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class WorldMap {

    private static List<Block> blocks = new LinkedList<Block>();

    private final static int startGen = 1024;
    private final static int endGen = -96;
    private final static int size = 32;

    private static int move = 4;
    private static int poz;

    public WorldMap() {
        poz = startGen;
        for( int i = endGen; i <= startGen; i += size + 1 )
            blocks.add(new BlockSolid(i, 550));
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        poz -= move;

        for( int i = 0; i < blocks.size(); i++ ) {
            blocks.get(i).update(gc, sbg, delta);
        }

        while (startGen - poz >= size) {
            blocks.add(new BlockSolid(poz, 550));
            poz += size;
        }

        if( gc.getInput().isKeyPressed(Input.KEY_F2) ) {
            System.out.println(  );
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
