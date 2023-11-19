package Game.Wepons;

import Game.Weapon;

public class Axe extends Weapon {
    //Constucteur
    public Axe() {
        super("Axe",120,15);
    }
    public String ascii_art(){
        return
        "    ,  /\\  .    \n" +
        "   //`-||-'\\\\   \n" +
        "  (| -=||=- |)    \n" +
        "   \\\\,-||-.//   \n" +
        "    `  ||  '    \n" +
        "       ||       \n" +
        "       ||       \n" +
        "       ||       \n" +
        "       ||       \n" +
        "       ||       \n" +
        "       ()       \n";
    }

}
