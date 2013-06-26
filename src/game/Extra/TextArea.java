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
    private Font      font2;

    public TextArea(GameContainer gc, int x, int y, String mesaj, int def) {
        this.x = x;
        this.y = y;
        this.mesaj = mesaj;
        Font font = new TrueTypeFont(new java.awt.Font(java.awt.Font.DIALOG, java.awt.Font.BOLD, 15), false);
        txt = new TextField(gc, font, x + 2, y, 100, 24);
        txt.setTextColor(Color.gray);
        txt.setBackgroundColor(Color.blue);
        txt.setAcceptingInput(true);
        txt.setBorderColor(Color.blue);
        txt.setMaxLength(1);
        val = def;
        font2 = new TrueTypeFont(new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.BOLD, 20), false);
        
    }

    public void update(GameContainer gc) {
        
        if (gc.getInput().isKeyPressed(Input.KEY_F5)) {
            System.out.println(txt.getText());
        }
    }

    public void render(GameContainer gc, Graphics g) {
        g.setFont(font2);
        g.setColor(Color.white);
        g.drawString(mesaj, x - (mesaj.length() * 12), y - 5);
        txt.render(gc, g);
        g.setColor(Color.green);
        g.drawString(Input.getKeyName(val), x + 6, y - 2);
    }

    public int getVal() {
        return val;
    }
    
}
