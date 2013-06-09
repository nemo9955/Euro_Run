package game.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.state.StateBasedGame;

public class Faller extends Block {

    public Faller(int x, int y) {
        super(x, y);
    }

    protected void Solid() {
        solid = true;
    }

    public void update(GameContainer gc, StateBasedGame sbg) {

        if( !colid() )
            modY(10);

        modX(-WorldMap.getMove());

        if( zon.getX() <= WorldMap.getEndgen() ) {
            WorldMap.getBlocks().remove(this);
        }

    }

    protected void Zon() {
        //   zon = new Rectangle(x, y, WorldMap.getSize(), WorldMap.getSize());
        zon = new Ellipse(x, y, 50, 50);
    }
}
