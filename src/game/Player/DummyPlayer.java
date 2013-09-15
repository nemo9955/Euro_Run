package game.Player;

import org.newdawn.slick.GameContainer;

import game.Start;
import game.Extra.Res;
import game.States.MenuState;


public class DummyPlayer extends Player {

    public DummyPlayer(float x, float y) {
        super( x, y );
        teleportation = false;
    }

    public void reset(float x, float y) {
        this.x = x;
        this.y = y;
        actiune = 0;
        frame = 0;
        interval = 0;
        isRolling = 0;
        hasNext = false;
        lifes = 3;
        isActiv = 0;
        canjump = true;
        accel = 1f;
        jumpNo = 0;
        imunitate = 0;
        setPoly( x, y, img[0][0].getWidth(), img[0][0].getHeight() );
    }

    protected void Move_st_dr(GameContainer gc, int delta) {
        if ( gc.getInput().isKeyDown( Res.dreapta ) &&x <Start.getWIDTH() -poly.getWidth() )
            modX( speed *delta );

        if ( gc.getInput().isKeyDown( Res.stanga ) &&x >0 )
            modX( -speed *delta );

    }

    protected boolean colid() {

        if ( poly.intersects( MenuState.mers.getZon() ) )
            return true;
        return false;
    }

}
