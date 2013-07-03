package game.World;

import game.Imagini.Background;
import game.Imagini.Imagine;
import game.Imagini.ZonaMers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class WorldMap {

    private static List<Block>   blocks   = new LinkedList<Block>();
    private static List<Imagine> imagini  = new ArrayList<Imagine>();
    private static List<Item>    item     = new ArrayList<Item>();

    private final static short   startGen = 1024;
    private final static short   endGen   = -256;

    private final static short   size     = 64;
    private static short         interval = 0;
    private static short         distItem = 1000;
    private static short         move     = 21;

    private static short         atTime   = 10000;
    private static short         speed;

    private static short         poz;
    private static short         pozBG;
    private static short         pozSol;

    private Random               zar      = new Random();

    public WorldMap() {
        blocks.clear();
        imagini.clear();
        item.clear();
        poz = endGen;
        pozBG = endGen;
        pozSol = endGen;
        speed = atTime;

        blocks.add(new BlockMers());

        while (pozSol < startGen) {
            imagini.add(new ZonaMers(pozSol, 0));
        }
        while (pozBG < startGen) {
            imagini.add(new Background(pozBG, 0));
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        poz -= move;
        pozBG -= move;
        pozSol -= move;


        if (interval - move > 0)
            interval -= move;
        else
            interval = 0;

        subUpdate(gc, sbg);
        adderReg();
        adderRandom();

        if (speed - delta > 0)
            speed -= delta;
        else {
            atTime += 2000;
            speed = atTime;
            move++;
            System.out.println(move);
        }

    }

    private void subUpdate(GameContainer gc, StateBasedGame sbg) {
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).update(gc, sbg);
        }

        for (int i = 0; i < imagini.size(); i++) {
            imagini.get(i).update(gc, sbg);
        }

        for (int i = 0; i < item.size(); i++) {
            item.get(i).update(gc, sbg);
        }
    }

    private void adderReg() {

        while (pozBG < startGen) {
            imagini.add(new Background(pozBG, 0));
        }
        while (pozSol < startGen) {
            imagini.add(new ZonaMers(pozSol, 0));
        }

    }

    private void adderRandom() {

        if (distItem - move <= 0) {

            if (zar.nextInt(100) < 80) {
                item.add(new Item(startGen, -500 + zar.nextInt(100)));
            }

            distItem = (short) (1000 + (zar.nextInt(5) * 200));
        }
        else {
            distItem -= move;
        }

        if (interval == 0) {

            int gen = zar.nextInt(1600);

            if (gen < 300) {
                interval += 200 + (zar.nextInt(7) * 15);
            }
            else if (gen < 800) {
                blocks.add(new BlockSolid(startGen, -64 - (zar.nextInt(14) * 10)));
                interval += 350 + zar.nextInt(100);
            }
            else if (gen < 1200) {
                blocks.add(new Faller(startGen, -500 - zar.nextInt(30) * 10));
                interval += 400 + zar.nextInt(50);
            }
            else if (gen < 1500) {
                Elements.MakeWall(startGen);
                interval += 500 + zar.nextInt(100);
            }
            else {
                interval += 50 + (zar.nextInt(10) * 25);
            }

        }
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        for (int i = 0; i < imagini.size(); i++)
            imagini.get(i).render(gc, sbg, g);
        for (int i = 0; i < blocks.size(); i++)
            blocks.get(i).render(gc, sbg, g);
        for (int i = 0; i < item.size(); i++)
            item.get(i).render(gc, sbg, g);
    }

    public static List<Block> getBlocks() {
        return blocks;
    }

    public Shape getBlock(int i) {
        return blocks.get(i).getZon();
    }

    public boolean is_solid(int i) {
        return blocks.get(i).isSolid();
    }

    public static int getStartgen() {
        return startGen;
    }

    public static int getEndgen() {
        return endGen;
    }

    public static int getMove() {
        return move;
    }

    public static void setMove(int move) {
        WorldMap.move = (short) move;
    }

    public static int getSize() {
        return size;
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

    public static List<Imagine> getBg() {
        return imagini;
    }

    public static List<Item> getItem() {
        return item;
    }

    public static int getPoz() {
        return poz;
    }
}
