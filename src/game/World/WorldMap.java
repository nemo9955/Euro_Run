package game.World;

import game.Start;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class WorldMap {

    private List<Block> blocks = new ArrayList<Block>();
    
    private final int startGen = 1000;
    private final int endGen = -100;
    
    private float move = 1f ;

    public WorldMap() {
        
        blocks.add(new BlockSolid(50,50));
        for( int i = 0; i < Start.getWIDTH(); i += 32 )
            blocks.add(new BlockSolid(i, 550));
    }
    

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        for( int i = 0; i < blocks.size(); i++ )
            blocks.get(i).modX(-move*delta);

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        for( int i = 0; i < blocks.size(); i++ )
            blocks.get(i).render(gc, sbg, g);

    }

    public List<Block> getBlocks() {
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

}
