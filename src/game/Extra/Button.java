package game.Extra;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class Button {

    private Rectangle zon;
    private float     rap = 1;
    private Image     img;

    public Button(int x, int y, String link) {
        try {
            img = new Image(String.format("res/meniu/%s", link));
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        zon = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.setColor(Color.red);
        img.draw(zon.getX()-(((zon.getWidth()*rap)-zon.getWidth())/2), zon.getY(), rap);
    }

    public boolean clikOn(GameContainer gc) {

        if (zon.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
            rap = 1.1f;

            if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                return true;
            }

        }
        else {
            rap = 1;
        }

        return false;
    }

}
