package game.Extra;

import game.Start;
import game.Entitati.Player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

    private int transX;
    private int transY;
    private int mapWidth, mapHeight;
    private Vector2f cen;

    public Camera(int mapWidth, int mapHeight) {
        this.setMapWidth(mapWidth);
        this.setMapHeight(mapHeight);
        transX = 0;
        transY = 0;
    }

    public void translate(Graphics g, Player player) {

        cen = new Vector2f(player.getPoly().getCenterX() , player.getPoly().getCenterY());

        transX = (int) -cen.x + Start.getWIDTH() / 6;

        transY = (int) -cen.y + Start.getHEIGHT() - Start.getHEIGHT() / 2;

        g.translate(transX, transY);
    }

    public int getX() {
        return transX;
    }

    public int getY() {
        return transY;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

}
