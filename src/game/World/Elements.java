package game.World;

import java.util.Random;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;


public class Elements {

    private static Random zar = new Random();

    public static short MakeWall(int x) {

        int rand;

        rand = zar.nextInt( 650 );
    //    rand = 300;

        if ( rand <200 )
            return genWallLow();
        else if ( rand <350 )
            return genBumps();
        else if ( rand <500 )
            return genRampa();
        else if ( rand <600 )
            return genTunel();

        return 100;

    }

    private static short genTunel() {
        short width = (short) ( 30 + ( zar.nextInt( 9 ) *35 ) );

        WorldMap.getBlocks().add( new Block( new Rectangle( WorldMap.startSpawn, -1500, width, 1440 - ( zar.nextInt( 3 ) *15 ) ) ) );
        return (short) ( width +zar.nextInt( 100 ) );
    }

    private static short genRampa() {

        Polygon ramp;
        ramp = new Polygon();

        ramp.addPoint( WorldMap.startSpawn, 5 );
        ramp.addPoint( WorldMap.startSpawn +370, -50 );
        ramp.addPoint( WorldMap.startSpawn +550 +zar.nextInt( 60 ), 5 );


        WorldMap.getBlocks().add( new Block( ramp ) );
        return 500;

    }

    private static short genBumps() {
        short width = 0;
        short lung;
        byte nr = (byte) ( 3 +zar.nextInt( 6 ) );

        for (byte i = 0 ; i <=nr ; i ++ ) {
            lung = (short) ( 60 + ( zar.nextInt( 10 ) *5 ) );

            WorldMap.getBlocks().add( new Block( new Rectangle( WorldMap.startSpawn +width, -180 +zar.nextInt( 50 ), lung, 300 ) ) );

            width += lung;
        }

        return (short) ( width +150 );

    }

    private static short genWallLow() {
        short width;

        WorldMap.getBlocks().add( new Block( new Rectangle( WorldMap.startSpawn, -210 +zar.nextInt( 50 ), width = (short) ( 30 + ( zar.nextInt( 7 ) *30 ) ), 270 ) ) );
        return width;
    }
}
