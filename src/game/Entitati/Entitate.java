package game.Entitati;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Entitate extends Physics {

    protected Image img[][];
    protected short activ = 0 , frame = 1;
    protected       int interval = 0  ;
    protected final int intervalTo = 200 ;
    // jos=0 , stanga=1 , dreapta=2 , sus=3
    
    protected boolean isMoving = false ;
    
    protected int team;
    protected int viata;
    protected float dimH, dimW;

    public Entitate(float x, float y) {
        this.x = x;
        this.y = y;
        Imagini( LoadSheet() );
        setPoly(x, y, dimW, dimH);
        setViata();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

    }
    
    protected void Animatie(int delta){

        if( isMoving == true )
            interval += delta;
        else{
            frame=0;
            interval=intervalTo-1;
        }
        
        if( interval > intervalTo ) {
            interval = 0;
            frame++;
        }
        
        if( frame == img[0].length )
            frame = 0;

    }
    
    protected String LoadSheet(){
        dimW = 32;
        dimH = 48;
        return null;
    }

    protected void Imagini(String link) {
        SpriteSheet sheet = null;
        
        try {
            sheet = new SpriteSheet( link , (int)dimW, (int)dimH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        img = new Image[sheet.getVerticalCount()][sheet.getHorizontalCount()];

        for( int j = 0; j < sheet.getVerticalCount(); j++ )
            for( int i = 0; i < sheet.getHorizontalCount(); i++ ) {
                img[j][i] = sheet.getSprite(i, j);
            }
    }

    protected void setViata() {
        viata = 100;
    }

    protected void setPoly(float x, float y, float w, float h) {
        poly = new Rectangle (x,y,w,h);
    }

    protected void modX(float amont) {
        x += amont;
        poly.setX(x);
    }

    protected void modY(float amont) {
        y += amont;
        poly.setY(y);
    }
    
    
    //                 getters

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Rectangle getPoly() {
        return poly;
    }

    public void setPoly(Rectangle poly) {
        this.poly = poly;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getViata() {
        return viata;
    }

    public void setViata(int viata) {
        this.viata = viata;
    }

}
