package game.Player;

import game.GamePlay.GameplayState;
import game.World.WorldMap;

import org.newdawn.slick.geom.Rectangle;

public class Physics {

    protected float x;
    protected float y;
    protected float speed = 0.5f;

    protected Rectangle poly;

    public boolean colid() {
        for( int i = 0; i < WorldMap.getBlocks().size(); i++ ) {
            if( poly.intersects(GameplayState.getWorldMap().getBlock(i)) ) {
                return true ;
            }
        }

        return false;
    }

}
