package game.World;

import game.Imagini.Background;
import game.Imagini.Harta;
import game.Imagini.Imagine;
import game.Imagini.ZonaMers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class WorldMap {

    private static List<Block>   blocks     = new LinkedList<Block>();
    private static List<Imagine> imagini    = new ArrayList<Imagine>();
    private static List<Item>    item       = new ArrayList<Item>();

    public final static short    startGen   = 2000;
    public static short          startSpawn = 1024;
    public final static short    endGen     = -2000;

    private static short         interval;
    private static short         distItem;
    private static short         move;

    private static short         atTime;
    private static short         speed;

    private static short         poz;
    private static short         pozBG;
    private static short         pozSol;

    private Random               zar        = new Random();

    public static Harta          harta;


    public WorldMap() {
        harta = new Harta();
        reset();
    }

    public void reset() {

        blocks.clear();
        imagini.clear();
        item.clear();

        interval = 0;
        distItem = 500;
        move = 18;
        atTime = 10000;
        speed = atTime;

        poz = endGen;
        pozBG = endGen;
        pozSol = endGen;

        harta.reset();

        blocks.add( new BlockMers( -200, 0 ) );

        while ( pozSol <startGen ) {
            imagini.add( new ZonaMers( pozSol, 0 ) );
        }
        while ( pozBG <startGen ) {
            imagini.add( new Background( pozBG, 0 ) );
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        poz -= move;
        pozBG -= move;
        pozSol -= move;

        if ( interval -move >0 )
            interval -= move;
        else
            interval = 0;


        subUpdate( gc, sbg );
        adderReg();
        adderRandom();

        if ( move <35 )
            if ( speed -delta >0 )
                speed -= delta;
            else {
                atTime += 2000;
                speed = atTime;
                move ++;
            }


    }

    private void subUpdate(GameContainer gc, StateBasedGame sbg) {

        harta.update( gc, sbg, move );

        for (int i = 0 ; i <blocks.size() ; i ++ ) {
            blocks.get( i ).update( gc, sbg );
        }

        for (int i = 0 ; i <imagini.size() ; i ++ ) {
            imagini.get( i ).update( gc, sbg );
        }

        for (int i = 0 ; i <item.size() ; i ++ ) {
            item.get( i ).update( gc, sbg );
        }
    }

    private void adderReg() {

        while ( pozBG <startGen ) {
            imagini.add( new Background( pozBG, 0 ) );
        }
        while ( pozSol <startGen ) {
            imagini.add( new ZonaMers( pozSol, 0 ) );
        }

    }

    private void adderRandom() {

        if ( distItem -move <=0 ) {

            if ( zar.nextInt( 100 ) <80 )
                item.add( new Item( startSpawn, -500 +zar.nextInt( 100 ) ) );

            distItem = (short) ( 986 + ( zar.nextInt( 5 ) *200 ) );
        }
        else
            distItem -= move;


        if ( interval ==0 ) {

            int gen = zar.nextInt( 2100 );

            if ( gen <300 ) {
                interval += 200 + ( zar.nextInt( 7 ) *15 );
            }
            else if ( gen <800 ) {
                blocks.add( new BlockSolid( startSpawn, -30 - ( zar.nextInt( 20 ) *10 ) ) );
                interval += 550 +zar.nextInt( 100 );
            }
            else if ( gen <1200 ) {
                blocks.add( new Faller( startSpawn, -500 -zar.nextInt( 30 ) *10 ) );
                interval += 400 +zar.nextInt( 50 );
            }
            else if ( gen <2000 ) {
                interval += 400 +zar.nextInt( 100 ) +Elements.MakeWall( startSpawn );
            }
            else {
                interval += 50 + ( zar.nextInt( 10 ) *25 );
            }

        }
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        for (int i = 0 ; i <=11 ; i ++ ) {
            g.setColor( new Color( 100, 200, 255, 0.2f +i *0.06f ) );
            g.fillRect( -300, -850 + ( i *100 ), gc.getWidth() +400, 100 );
        }


        for (int i = 0 ; i <imagini.size() ; i ++ )
            imagini.get( i ).render( gc, sbg, g );

        harta.render( gc, sbg, g );

        for (int i = 0 ; i <blocks.size() ; i ++ )
            blocks.get( i ).render( gc, sbg, g );

        g.setColor( new Color( 128, 49, 3 ) );
        g.fillRect( -300, 10, gc.getWidth() +400, 500 );

        for (int i = 0 ; i <imagini.size() ; i ++ )
            imagini.get( i ).renderAfter( gc, sbg, g );

        for (int i = 0 ; i <item.size() ; i ++ )
            item.get( i ).render( gc, sbg, g );
    }


    public static List<Block> getBlocks() {
        return blocks;
    }

    public Shape getBlock(int i) {
        return blocks.get( i ).getZon();
    }

    public boolean is_solid(int i) {
        return blocks.get( i ).isSolid();
    }

    public static int getMove() {
        return move;
    }

    public static void setMove(int move) {
        WorldMap.move = (short) move;
    }

    public static short getPozSol() {
        return pozSol;
    }

    public static void modPozSol(short pozSol) {
        WorldMap.pozSol += pozSol;
    }

    public static int getPozBG() {
        return pozBG;
    }

    public static void modPozBG(int pozBG) {
        WorldMap.pozBG += pozBG;
    }

    public static List<Imagine> getImagini() {
        return imagini;
    }

    public static List<Item> getItem() {
        return item;
    }

    public static int getPoz() {
        return poz;
    }
}
