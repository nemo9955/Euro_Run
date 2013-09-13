package game.Extra;

import game.Start;
import game.Player.Player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

    private int      transx = 0, transy = 0;
    private Vector2f cen    = new Vector2f();
    private int      h, w;

    public Camera() {
        h = Start.getHEIGHT();
        w = Start.getWIDTH();
    }

    public void translate(Graphics g, Player player) {
        cen.x = player.getPoly().getX();
        cen.y = player.getPoly().getMaxY();
        transx = (int) ( -cen.x +w /6 );
        transy = (int) ( -cen.y +h /1.25f );
        g.translate( transx, transy );
    }

    public int getX() {
        return transx;
    }

    public int getY() {
        return transy;
    }

    public void reset() {
        transx = 0;
        transy = 0;
    }
}
