package game.World;

import java.util.Random;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;


public class Elements {

    private static Random zar = new Random();

    public static short MakeWall(int x) {

        int rand;

        rand = zar.nextInt( 650 );
        // rand = 300;

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
        Block temp = new Block( new Rectangle( WorldMap.startSpawn+250, -1500, width, 1420 - ( zar.nextInt( 3 ) *15 ) ) );
        temp.setSolid( false );
        WorldMap.getBlocks().add( temp );
        return (short) ( width +zar.nextInt( 100 ) +400 );
    }

    private static short genRampa() {

        Polygon ramp;
        ramp = new Polygon();

        ramp.addPoint( WorldMap.startSpawn, 5 );
        ramp.addPoint( WorldMap.startSpawn +700, -100 );
        ramp.addPoint( WorldMap.startSpawn +1000, 5 );


        WorldMap.getBlocks().add( new Block( ramp ) );
        return 900;

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

        WorldMap.getBlocks().add( new Block( new Rectangle( WorldMap.startSpawn, -280 +zar.nextInt( 50 ), width = (short) ( 30 + ( zar.nextInt( 7 ) *30 ) ), 270 ) ) );
        return width;
    }
}
