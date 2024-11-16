package location.battle;

import main.Player;
import obstacle.Zombie;

public class Cave extends BattleLoc {
    public Cave(Player player){
        super(player, "Cave", new Zombie(), "food",3);
    }
}
