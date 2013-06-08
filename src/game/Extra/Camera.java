package game.Extra;

import game.Start;
import game.Player.Player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

    private int transx, transy;
    private Vector2f cen;

    public Camera(int mapWidth, int mapHeight) {
        transx = 0;
        transy = 1000;
    }

    public void translate(Graphics g, Player player) {

        cen = new Vector2f(player.getPoly().getX(), player.getPoly().getMaxY());
        
        transx = (int) -cen.x + Start.getWIDTH() / 6;

        transy = (int) ((int) -cen.y + Start.getHEIGHT() / 1.8);

        g.translate(transx, transy);
    }

    public int getX() {
        return transx;
    }

    public int getY() {
        return transy;
    }

}
