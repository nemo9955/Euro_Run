package game.Imagini;

import game.Extra.Res;
import game.World.ItemTara;
import game.World.WorldMap;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;


public class Harta {

    private final Image harta[] = new Image[28];

    private int         curent;
    private final int   eta     = 20000 /28;
    private int         next    = 300;

    public Harta() {
        loadSiluete();
        curent = 0;
    }


    public void update(GameContainer gc, StateBasedGame sbg, short move) {
        if ( curent <28 ) {
            if ( next -move >0 )
                next -= move;
            else {
                curent ++;
                next = eta;
                WorldMap.getItem().add( new ItemTara( WorldMap.getStartgen(), -500 +new Random().nextInt( 100 ), curent -1 ) );
            }

        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        for (int i = 0 ; i <curent ; i ++ )
            harta[i].draw( -170, -790 );
    }


    private void loadSiluete() {
        for (int i = 0 ; i <28 ; i ++ ) {
            try {
                harta[i] = new Image( String.format( "res/tari/siluete/%s_tara.png", Res.TARI[i] ) );
            }
            catch (Exception e) {
                System.out.println( "Problema la incarcarea siluetei tarii : " +Res.TARI[i] );
            }
        }
    }


}
