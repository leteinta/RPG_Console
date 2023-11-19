package Game.Wepons;
import Game.Weapon;

public class Hammer extends Weapon {

    //Constucteur
    public Hammer() {
        super("Hamer",150,20);
    }

    public String ascii_art(){
        return
                "             +-+\n" +
                "=============| |\n" +
                "            `:_;'";
    }
}
