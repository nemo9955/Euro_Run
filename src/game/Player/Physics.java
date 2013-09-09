package game.Player;

import game.States.GameplayState;
import game.World.WorldMap;

import org.newdawn.slick.geom.Rectangle;


public class Physics {

    protected float     x;
    protected float     y;
    protected float     speed = 0.5f;
    protected Rectangle poly;

    protected boolean colid() {
        
        for (int i = 0; i < WorldMap.getBlocks().size(); i++) {
            if (poly.intersects(GameplayState.getWorldMap().getBlock(i))) {
                return true;
            }
        }
        return false;
    }

    protected void adapt(int cantitate) {
        while (!colid()) {
            modY(cantitate);
        }
        modY(-cantitate);
    }

    protected void modX(float amont) {
        x += amont;
        poly.setX(x);
    }

    protected void modY(float amont) {
        y += amont;
        poly.setY(y);
    }
}
