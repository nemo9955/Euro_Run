package game.Player;

import game.Extra.Res;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Physics {

    protected Image        img[][];
    protected final byte   frames[]      = { 10, 3,6, 1, 6 };
    /*
     * 0 - run
     * 1 - jump
     * 2 - roll
     * 3 - slide
     * 4 - to_slide
     */
    protected Image        scut;
    protected short        imunitate;
    protected short        rezist;

    protected byte         actiune, frame;
    protected byte         interval;
    protected final byte   intervalTo    = 60;
    protected boolean      hasNext;
    protected byte         buff;
    protected byte         next;
    protected byte         isActiv;

    protected short        isRolling;

    protected boolean      canjump;
    protected float        accel;
    protected byte         jumpNo;
    protected final byte   jumpMax       = 2;

    protected static byte  lifes;
    protected float        marY;
    protected boolean      teleportation = true;

    protected static Sound lifeUp;
    protected static Sound lifeDown;


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


        if ( teleportation ==true ) {
            if ( y >50 ||y <-1000 ) {
                System.out.println( "teleport" );
                setY( 300 );


            }
            if ( y +poly.getHeight() >0 )
                setY( -poly.getHeight() );
        }
        else {
            if ( y >600 ||y <-100 ) {
                System.out.println( "teleport" );
                setY( 300 );
            }
        }
        if ( gc.getInput().isKeyPressed( Input.KEY_F9 ) )
            addLifes( -1 );
        if ( gc.getInput().isKeyPressed( Input.KEY_F10 ) )
            addLifes( 1 );
    }

    protected void schAct(byte act) {
        if ( act !=actiune )
            frame = 0;
        actiune = act;
    }


    protected final short limitaSD = 150;

    protected void Move_st_dr(GameContainer gc, int delta) {
        if ( gc.getInput().isKeyDown( Res.dreapta ) &&x <limitaSD )
            modX( speed *delta );

        if ( gc.getInput().isKeyDown( Res.stanga ) &&x >-limitaSD )
            modX( -speed *delta );

    }

    protected void jump_gravity(int delta) {
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

    protected boolean canMakeStep() {
        modY( -50 );
        if ( !colid() ) {
            modY( 50 );
            while ( !colid() ) {
                modY( -10 );
            }
            return true;
        }
        modY( 50 );
        return false;
    }

    protected boolean litleColid() {

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

    protected void Animatie(int delta) {
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

    /*
     * protected void Imagini() {
     * String links[] = { "run", "to_jump", "roll", "slide", "to_slide" };
     * img = new Image[frames.length][9];
     * PackedSpriteSheet sheet = null;
     * try {
     * lifeUp = new Sound( "res/sunet/harpa.wav" );
     * lifeDown = new Sound( "res/sunet/lovitura.wav" );
     * sheet = new PackedSpriteSheet( "res/player/sheet_activ.def", Color.white );
     * scut = new Image( "res/player/scut.png" );
     * }
     * catch (SlickException e) {
     * e.printStackTrace();
     * }
     * scut.setAlpha( 0.8f );
     * for (int i = 0 ; i <links.length ; i ++ ) {
     * boolean finish = false;
     * frames[i] = 0;
     * for (int k = 0 ; !finish ; k ++ ) {
     * try {
     * img[i][k] = sheet.getSprite( String.format( "%s%d.bmp", links[i], k ) );
     * frames[i] ++;
     * }
     * catch (Exception e) {
     * finish = true;
     * }
     * }
     * }
     * }
     */

    protected void Imagini() {
        String links[] = { "run", "jump", "roll", "slide", "to_slide" };
        img = new Image[frames.length][10];
        try {

            lifeUp = new Sound( "res/sunet/harpa.wav" );
            lifeDown = new Sound( "res/sunet/lovitura.wav" );

            scut = new Image( "res/player/scut.png" );
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        scut.setAlpha( 0.8f );
        for (int i = 0 ; i <links.length ; i ++ ) {
            for (int k = 0 ; k <frames[i] ; k ++ ) {
                try {
                    img[i][k] = new Image( String.format( "res/player/%s%d.png", links[i], k +1 ) );
                }
                catch (Exception e) {
                    System.out.println( "eroare la incarcarea pozei " +i +" " +k );
                }
            }
        }
    }

    protected void setPoly(float x, float y, float w, float h) {
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
            if ( lifes +i >=0 &&lifes +i <=5 ) {
                if ( i >0 )
                    lifeUp.play();
                else
                    lifeDown.play();
                lifes += i;
            }
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
