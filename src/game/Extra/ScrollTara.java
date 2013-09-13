package game.Extra;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.newdawn.slick.util.ResourceLoader;


public class ScrollTara extends Scroll {

    private int tara;

    public ScrollTara(int tara) {
        super();
        this.tara = tara;
        gatFact();
        genText();
    }


    protected void gatFact() {

        BufferedReader br = null;
        String temp;

        try {

            br = new BufferedReader( new InputStreamReader( ResourceLoader.getResourceAsStream( String.format( "res/tari/facts/%s_info.txt", Res.TARI[tara] ) ) ) );


            message = br.readLine();
            while ( ( temp = br.readLine() ) !=null ) {
                message += "*";
                message += temp;
            }

        }

        catch (Exception e) {
            System.out.println( "fisierul text cu informatii nu e in zona potrivita. Tara : " +Res.TARI[tara] );
        }
        br = null;

    }
}
