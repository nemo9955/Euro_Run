package game.Player;

import game.Extra.Res;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.PackedSpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Physics {

    private Image       img[][];
    private final byte  frames[]      = { 8, 6, 9, 5, 6 };
    /*
     * 0 - run
     * 1 - to_jump
     * 2 - roll
     * 3 - slide
     * 4 - to_slide
     */
    private Image       scut;
    private short       imunitate;
    private short       rezist;

    private byte        actiune, frame;
    private byte        interval;
    private final byte  intervalTo    = 80;
    private boolean     hasNext;
    private byte        buff;
    private byte        next;
    private byte        isActiv;

    private short       isRolling;

    private boolean     canjump;
    private float       accel;
    private byte        jumpNo;
    private final byte  jumpMax       = 2;

    private static byte lifes;
    private float       marY;
    private boolean     teleportation = true;


    public Player(float x, float y) {
        Imagini();
        reset( x, y );
    }

    public void reset(float x, float y) {
        this.x = x;
        this.y = y;
        rezist = 0;
        actiune = 0;
        frame = 0;
        interval = 0;
        lifes = 3;
        isRolling = 0;
        hasNext = false;
        isActiv = 0;
        canjump = true;
        accel = 1f;
        jumpNo = 0;
        imunitate = 0;
        setPoly( x, y, img[0][0].getWidth(), img[0][0].getHeight() );
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        marY = poly.getHeight();
        if ( rezist -delta >0 )
            rezist -= delta;
        else
            rezist = 0;

        if ( isRolling -delta >0 )
            isRolling -= delta;
        else
            isRolling = 0;

        if ( gc.getInput().isKeyDown( Res.jump ) ) {
            if ( jumpNo <jumpMax &&canjump &&litleColid() &&isActiv <=1 ) {
                if ( gc.getInput().isKeyPressed( Res.jump ) ) {
                    jumpNo ++;
                    if ( jumpNo ==1 ) {
                        accel = -1.5f;
                    }
                    if ( jumpNo >1 ) {
                        accel = -1.2f;
                    }
                }
                if ( jumpNo >0 ) {
                    accel -= 0.002f *delta;
                }
            }
        }

        jump_gravity( delta );

        Move_st_dr( gc, delta );

        
        if ( gc.getInput().isKeyPressed( Res.roll ) &&isRolling ==0 ) {
            isRolling = 800;
        }

        if ( isRolling !=0 &&isActiv <=2 ) {
            next = 2;
            hasNext = false;
            isActiv = 2;
        }

        if ( gc.getInput().isKeyDown( Res.slide ) && ( isActiv ==0 ||isActiv ==3 ) ) {
            if ( gc.getInput().isKeyPressed( Res.slide ) ) {
                buff = 4;
                next = 3;
                hasNext = true;
            }
            isActiv = 3;
        }

        Animatie( delta );

        if ( isActiv !=0 ) {
            if ( hasNext ) {
                schAct( buff );
            }
            else {
                schAct( next );
            }
        }

        if ( ( !gc.getInput().isKeyDown( Res.slide ) &&isActiv ==3 ) || ( isRolling ==0 &&isActiv ==2 ) || ( accel >=0 &&isActiv ==1 ) ) {
            isActiv = 0;
            schAct( (byte) 0 );
        }


        poly.setSize( img[actiune][frame].getWidth(), img[actiune][frame].getHeight() );
        modY( marY -poly.getHeight() );

        if ( gc.getInput().isKeyPressed( Input.KEY_F1 ) ) {
            System.out.println( x +" " +y );
        }


        if ( teleportation ==true )
            if ( y >50 ||y <-1000 ) {
                System.out.println( "teleport" );
                setY( 300 );
            }

        if ( y +poly.getHeight() >0 )
            setY( -poly.getHeight() );

    }

    private void schAct(byte act) {
        if ( act !=actiune )
            frame = 0;
        actiune = act;
    }


    private final short limitaSD = 150;

    private void Move_st_dr(GameContainer gc, int delta) {
        if ( gc.getInput().isKeyDown( Res.dreapta ) &&x <limitaSD )
            modX( speed *delta );

        if ( gc.getInput().isKeyDown( Res.stanga ) &&x >-limitaSD )
            modX( -speed *delta );

    }

    private void jump_gravity(int delta) {
        if ( jumpNo >=1 ) {
            if ( accel <0 ) {
                next = 1;
                hasNext = false;
                isActiv = 1;
            }
        }
        modY( accel *delta );
        if ( colid() ) {
            modY( -accel *delta );
            if ( colid() &&imunitate ==0 &&rezist ==0 &&!canMakeStep() ) {
                addLifes( -1 );
                imunitate = 3000;
            }
            jumpNo = 0;
            if ( accel >=0 ) {
                adapt( 1 );
                canjump = true;
            }
            else {
                adapt( -1 );
                canjump = false;
            }
            accel = 0;
        }
        imunitate -= delta;
        if ( imunitate <0 )
            imunitate = 0;
        accel += 0.006f *delta;
        if ( accel >1 )
            accel = 1;
    }

    private boolean canMakeStep() {
        modY( -35 );
        if ( !colid() ) {
            modY( 35 );
            while ( !colid() ) {
                modY( -5 );
            }
            return true;
        }
        modY( 35 );
        return false;
    }

    private boolean litleColid() {

        modY( -20 );
        if ( !colid() ) {
            modY( 20 );
            return true;
        }
        else {
            modY( 20 );
            return false;
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img[actiune][frame].setAlpha( 1f );

        if ( imunitate >0 )
            img[actiune][frame].setAlpha( 0.1f +(float) Math.abs( Math.sin( Math.toRadians( imunitate ) ) ) );

        img[actiune][frame].draw( x, y );

        if ( rezist >0 )
            scut.draw( poly.getCenterX() - ( poly.getWidth() *1.6f /2 ), poly.getCenterY() - ( poly.getHeight() *1.4f /2 ), poly.getWidth() *1.6f, poly.getHeight() *1.4f );

    }

    private void Animatie(int delta) {
        interval += delta;
        if ( interval >intervalTo ) {
            interval = 0;
            frame ++;
        }
        if ( frame >=frames[actiune] ) {
            frame = 0;
            if ( hasNext ) {
                hasNext = false;
            }
        }
    }

    private void Imagini() {
        String links[] = { "run", "to_jump", "roll", "slide", "to_slide" };
        img = new Image[frames.length][9];
        PackedSpriteSheet sheet = null;
        try {
            sheet = new PackedSpriteSheet( "res/player/sheet_activ.def", Color.white );
            scut = new Image( "res/player/scut.png" );
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        scut.setAlpha( 0.8f );
        for (int i = 0 ; i <links.length ; i ++ ) {
            boolean finish = false;
            frames[i] = 0;
            for (int k = 0 ; !finish ; k ++ ) {
                try {
                    img[i][k] = sheet.getSprite( String.format( "%s%d.bmp", links[i], k ) );
                    frames[i] ++;
                }
                catch (Exception e) {
                    finish = true;
                }
            }
        }
    }

    private void setPoly(float x, float y, float w, float h) {
        poly = new Rectangle( x, y, w, h );
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        poly.setX( x );
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        poly.setY( y );
    }

    public Rectangle getPoly() {
        return poly;
    }

    public static short getLifes() {
        return lifes;
    }

    public static void setLifes(byte lifes) {
        Player.lifes = lifes;
    }

    public static void addLifes(int i) {
        if ( lifes >0 )
            if ( lifes +i >=0 &&lifes +i <=5 )
                lifes += i;
    }

    public short getRezist() {
        return rezist;
    }

    public void setRezist(short rezist) {
        this.rezist = rezist;
    }

    public void modRezist(short rezist) {
        this.rezist += rezist;
    }

    public void setTeleportation(boolean teleportation) {
        this.teleportation = teleportation;
    }
}
