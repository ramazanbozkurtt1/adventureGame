package location.battle;

import main.Player;
import obstacle.Bear;
import obstacle.Vampire;

public class Forest extends BattleLoc {

    public Forest(Player player) {
        super(player,"Forest",new Vampire(),"firewood",3);
    }
}
