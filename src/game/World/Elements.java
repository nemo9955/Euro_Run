package game.World;

import java.util.Random;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Elements {

    public static void MakeWall(int x, int y) {

        short alti = 500;
        short gap = 80;
        Random zar = new Random();

        switch (zar.nextInt(3)) {
            case 0:
                gap = 60;
                alti = 502;
                break;
            case 1:
                gap = (short) (120 + zar.nextInt(50));
                alti = 140;
                break;
            case 2:
                gap = (short) (80 + zar.nextInt(50));
                alti = 80;
                break;
        }

        Shape zon;
        Shape zon1;

        zon = new Rectangle(x, -400, WorldMap.getSize(), alti + 400);

        zon1 = new Rectangle(x, zon.getY() + zon.getHeight() + gap, WorldMap.getSize(), 560 - (zon.getY() + zon.getHeight() + gap));

        WorldMap.getBlocks().add(new Block(zon));
        WorldMap.getBlocks().add(new Block(zon1));

    }
}
