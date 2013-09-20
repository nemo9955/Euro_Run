package game.Extra;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;


public class Slider {

    private Rectangle poly;
    private Ellipse   slide;


    private Image     main;
    private Image     move;

    public Slider(int x, int y, int size) {

        try {
            main = new Image( "res/meniu/slider_main.png" );
            move = new Image( "res/meniu/slider.png" );
        }
        catch (Exception e) {

        }

        int inalt = 20;

        poly = new Rectangle( x +inalt, y, size - ( inalt *2 ), inalt );
        slide = new Ellipse( x +size - ( inalt *2 ), y, inalt, inalt );

        // poly.setCenterY( zon.getCenterY() );
        slide.setCenterY( poly.getCenterY() );
        slide.setCenterX( poly.getMaxX() );

    }

    public Slider(int x, int y, int size, int poz) {
        this( x, y, size );
        if ( poz <=size )
            slide.setCenterX( poly.getMinX() +poz );
    }

    public float getRap() {
        return ( slide.getCenterX() -poly.getMinX() ) /poly.getWidth();

    }

    public void update(GameContainer gc) {
        if ( poly.contains( gc.getInput().getMouseX(), gc.getInput().getMouseY() ) &&gc.getInput().isMouseButtonDown( Input.MOUSE_LEFT_BUTTON ) ) {
            // if ( poly.contains( slide ) )
            slide.setCenterX( gc.getInput().getMouseX() );
        }

    }

    public void render(Graphics g) {
        main.draw( poly.getX(), poly.getY(), poly.getWidth(), poly.getHeight() );
        move.drawCentered( slide.getCenterX(), slide.getCenterY() );
    }


}
