package game.Extra;

import game.GamePlay.GameplayState;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

public class Scroll {
    
    private Image img;
    private short x, y;
    private Rectangle zon;
    private TextField txt ;
    
    
    public Scroll(GameContainer gc) {

        try {
            img = new Image("res/item/scroll.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        x = (short) ((450 - img.getWidth()  / 2) - GameplayState.getCamera().getX());
        y = (short) ((300 - img.getHeight() / 2) - GameplayState.getCamera().getY());

     //   zon = new Rectangle(x, y, img.getWidth(), img.getHeight());
        zon = new Rectangle(-50, 300, 50, 50);        
        
        genText(gc);
    }

    private void genText(GameContainer gc) {

        Color trans = new Color(0,0,0,0.2f);
        
        Font font = new TrueTypeFont(new java.awt.Font(java.awt.Font.DIALOG,java.awt.Font.BOLD , 15), false);
        txt = new TextField(gc , font , x+55 , y+40 , 290 , 210 );
        txt.setTextColor(Color.white);
        txt.setBackgroundColor(trans);
        txt.setAcceptingInput(true);
        txt.setBorderColor(Color.transparent);
        
        txt.setText("cevafgsdfhg sdfh h s     \n dfg argaerwtgasergsae");
        
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if( gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            if( zon.contains(gc.getInput().getMouseX() - GameplayState.getCamera().getX(), gc.getInput().getMouseY() - GameplayState.getCamera().getY()) ) {
                GameplayState.setTaken(false);
            }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img.draw(x, y);
        g.draw(zon);
        txt.render(gc, g);
    }
}
