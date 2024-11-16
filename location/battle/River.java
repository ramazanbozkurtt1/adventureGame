package location.battle;

import main.Player;
import obstacle.Bear;

public class River extends BattleLoc {
    public River(Player player) {
        super(player,"River",new Bear(),"water",2);
    }
}
