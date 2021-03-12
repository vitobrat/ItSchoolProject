package fragments.GamesPresident;

public class Situation {
    Situation direction[];
    String text, subject;
    int dM, dS, dD;
    Situation (String subject, String text,int variants, int dm, int ds, int dd){
        this.subject = subject;
        this.text = text;
        dM = dm;
        dS = ds;
        dD = dd;
        this.direction = new Situation[variants];
    }
}
