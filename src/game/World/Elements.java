package game.World;

import java.util.Random;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Elements {

    public static void MakeWall(int x) {

        short start;
        short end;
        Random zar = new Random();

        switch (zar.nextInt(5)) {
            case 0:
                start = (short) (-210 + zar.nextInt(50));
                end = 10;
                break;
            case 1:
                start = -1500;
                end = (short) (-60 - (zar.nextInt(3))*15);
                break;
            default:
                start = (short) (-280 + zar.nextInt(40));
                end = 10;
                break;
        }

        Shape zon;

        zon = new Rectangle(x, start, 20, end-start);

        WorldMap.getBlocks().add(new Block(zon));

    }
}
