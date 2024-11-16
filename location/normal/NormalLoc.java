package location.normal;

import location.Location;
import main.Player;

public abstract class NormalLoc extends Location {
    public NormalLoc(Player player, String name) {
        super(player, name);
    }

    @Override
    public boolean onLocation(){
        return true;
    }
}
