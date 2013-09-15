package game.Imagini;

import game.Extra.Res;
import game.World.ItemTara;
import game.World.WorldMap;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;


public class Harta {

    private final Image harta[]  = new Image[28];

    private final int   eta      = 96165 /28;
    private int         next;
    private int         from;
    private final int   fromTimp = 1000;
    private int         curent;

    private Sound       pass;

    public Harta() {
        loadSiluete();
        reset();
    }


    public void reset() {
        curent = 0;
        next = 300;
        from = 0;
    }


    public void update(GameContainer gc, StateBasedGame sbg, short move) {
        if ( curent <28 ) {
            if ( next -move >0 )
                next -= move;
            else {
                from = fromTimp;
                next = eta;
                curent ++;
                WorldMap.getItem().add( new ItemTara( WorldMap.startSpawn, -500 +new Random().nextInt( 100 ), curent -1 ) );
                pass.play( new Random().nextFloat() +0.7f, gc.getSoundVolume() );
            }

        }

        if ( from -move >0 )
            from -= move;
        else
            from = 0;

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        if ( from >0 )
            harta[curent -1].setAlpha( 1f - ( (float) from /fromTimp ) );

        for (int i = 0 ; i <curent ; i ++ )
            harta[i].draw( -170, -790 );
    }


    private void loadSiluete() {
        for (int i = 0 ; i <28 ; i ++ ) {
            try {
                harta[i] = new Image( String.format( "res/tari/siluete/%s_tara.png", Res.TARI[i] ) );
                pass = new Sound( "res/sunet/electric_sweep.wav" );
            }
            catch (Exception e) {
                System.out.println( "Problema la incarcarea siluetei tarii : " +Res.TARI[i] );
            }
        }
    }

    public int getTariVizitate() {
        return curent;
    }

}
