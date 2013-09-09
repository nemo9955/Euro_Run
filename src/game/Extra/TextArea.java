package game.Extra;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

public class TextArea {

    private int       x;
    private int       y;
    private String    mesaj;
    private int       val;
    private TextField txt;
    private Font      font;

    public TextArea(GameContainer gc, int x, int y, String mesaj, int def) {
        this.x = x;
        this.y = y;
        this.mesaj = mesaj;
        // font = new TrueTypeFont( new java.awt.Font( java.awt.Font.DIALOG, java.awt.Font.BOLD, 15 ), false );
        font = new TrueTypeFont( new java.awt.Font( java.awt.Font.MONOSPACED, java.awt.Font.BOLD, 20 ), false );
        val = def;

        txt = new TextField( gc, font, x +2, y, 100, 24 );
        txt.setTextColor( Color.transparent );
        txt.setBackgroundColor( Color.cyan );
        txt.setBorderColor( Color.transparent );
        txt.setMaxLength( 0 );
    }

    public void render(GameContainer gc, Graphics g) {
        g.setFont( font );
        g.setColor( Color.white );
        g.drawString( mesaj, x - ( mesaj.length() *12 ), y -5 );

        if ( txt.hasFocus() )
            txt.setBorderColor( Color.lightGray );
        else
            txt.setBorderColor( Color.transparent );

        txt.render( gc, g );
        g.setColor( Color.black );
        g.drawString( Input.getKeyName( val ), x +6, y -2 );
    }

    public int getVal() {
        return val;
    }

    public void setVal(int key) {
        val = key;
        txt.setFocus( false );
    }

    public boolean hasFocus() {
        return txt.hasFocus();
    }

    public TextField getTxt() {
        return txt;
    }

    public Font getFont2() {
        return font;
    }


}
