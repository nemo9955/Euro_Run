package game.Imagini;

import game.Extra.Res;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;


public class Harta {

    private final Image harta[] = new Image[28];

    private byte        curent  = 0;
    private final int   eta     = 5000 /28;
    private int         next    = 100;


    public Harta() {
        loadSiluete();
    }


    public void update(GameContainer gc, StateBasedGame sbg, short move) {
        if ( curent <28 ) {
            if ( next -move >0 )
                next -= move;
            else {
                curent ++;
                next = eta;
            }

        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        for (int i = 0 ; i <curent ; i ++ )
            harta[i].draw( -133, -786 );
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
