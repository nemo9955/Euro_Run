package game.World;

import game.Start;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class BlockMers extends Block {

    public BlockMers() {
        super(-200, 0);
    }

    public void update(GameContainer gc, StateBasedGame sbg) {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.draw(zon);
    }

    protected void Solid() {
        solid = true;
    }

    protected void Zon() {
        zon = new Rectangle(x, y, Start.getWIDTH() + 220, 5);
    }
}
