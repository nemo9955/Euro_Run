package game.World;

import game.Extra.Res;
import game.States.GameplayState;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class ItemTara extends Item {

    protected int tara;

    public ItemTara(int x, int y, int tara) {
        super( x, y );
        this.tara = tara;
        makeImage();
        poly = new Rectangle( x, y, img.getWidth(), img.getHeight() );
    }

    protected void makeSpecifScroll() {
        GameplayState.getPlayer().modRezist( (short) 2500 );
        GameplayState.makeTaraScroll( tara );
        GameplayState.modTariCunoscute( (byte) 1 );
    }

    protected void makeImage() {
        try {
            img = new Image( String.format( "res/tari/steaguri/%s_flag.png", Res.TARI[tara] ) );
        }
        catch (SlickException e) {
            System.out.println( "eroare in itemul tarii : " +Res.TARI[tara] );
        }
    }

}
