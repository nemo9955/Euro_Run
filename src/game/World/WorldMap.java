package game.World;

import game.Imagini.Background;
import game.Imagini.Imagine;

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
    private static List<Imagine> bg       = new ArrayList<Imagine>();
    private static List<Item>    item     = new ArrayList<Item>();
    private final static int     startGen = 1024;
    private final static int     endGen   = -256;
    private final static int     size     = 64;
    private static int           terval   = 0;
    private static int           move     = 21;
    private static int           poz      = 0;
    private static int           pozBG    = 0;
    private Random               zar      = new Random();

    public WorldMap() {
        poz = endGen;
        pozBG = endGen;
        while (startGen - poz >= size) {
            blocks.add(new BlockSolid(poz, 550));
            poz += size;
        }
        while (pozBG < startGen) {
            bg.add(new Background(pozBG, 700));
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg) {
        poz -= move;
        pozBG -= move;
        if (terval - move > 0) {
            terval -= move;
        }
        else {
            terval = 0;
        }


        // sub-updates - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).update(gc, sbg);
        }
        for (int i = 0; i < bg.size(); i++) {
            bg.get(i).update(gc, sbg);
        }
        for (int i = 0; i < item.size(); i++) {
            item.get(i).update(gc, sbg);
        }


        // fundal & podea - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        while (pozBG < startGen) {
            bg.add(new Background(pozBG, 700));
        }
        while (startGen - poz >= size) {
            blocks.add(new BlockSolid(poz, 550));
            poz += size;
        }


        // adders - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        if (zar.nextInt(1000) < 20) {
            item.add(new Item(poz + zar.nextInt(50), 50 + zar.nextInt(50)));
        }

        if (terval == 0) {

            int gen = zar.nextInt(1000);
            if (gen > 900) {
                terval += 200 + (zar.nextInt(10) * 10);
            }
            else if (gen > 700) {
                blocks.add(new BlockSolid(startGen, 530 - zar.nextInt(150)));
                terval += 200 + zar.nextInt(100);
            }
            else if (gen > 600) {
                blocks.add(new Faller(startGen + 50, -zar.nextInt(30) * 10));
                terval += 150 + zar.nextInt(100);
            }
            else if (gen > 400) {
                Elements.MakeWall(startGen, 500);
                terval += 300 + zar.nextInt(100);
            }

        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        for (int i = 0; i < bg.size(); i++)
            bg.get(i).render(gc, sbg, g);
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
        WorldMap.move = move;
    }

    public static int getSize() {
        return size;
    }

    public static int getPozBG() {
        return pozBG;
    }

    public static void modPozBG(int pozBG) {
        WorldMap.pozBG += pozBG;
    }

    public static List<Imagine> getBg() {
        return bg;
    }

    public static List<Item> getItem() {
        return item;
    }

    public static int getPoz() {
        return poz;
    }
}
