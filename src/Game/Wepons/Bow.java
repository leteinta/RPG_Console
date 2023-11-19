package Game.Wepons;
import Game.Weapon;

public class Bow extends Weapon {
    //Constucteur
    public Bow() {
        super("Bow",100,10);
    }

    public String ascii_art(){
        return
                "   (        \n" +
                "    \\      \n" +
                "     )      \n" +
                "##--------> \n" +
                "     )      \n" +
                "    /       \n" +
                "   (        \n";
    }
}
