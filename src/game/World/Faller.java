package game.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.state.StateBasedGame;

public class Faller extends Block {

    private final byte fall = (byte) (7 + zar.nextInt(8));

    public Faller(int x, int y) {
        super(x, y);
    }

    protected void Solid() {
        solid = true;
    }

    public void update(GameContainer gc, StateBasedGame sbg) {
        if (!colid())
            modY(fall);
        modX(-WorldMap.getMove());
        if (zon.getX() <= WorldMap.getEndgen()) {
            WorldMap.getBlocks().remove(this);
        }
    }

    protected void Zon() {
        zon = new Ellipse(x, y, 40+zar.nextInt(30), 40+zar.nextInt(20));
    }
}
