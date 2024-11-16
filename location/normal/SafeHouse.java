package location.normal;
import main.Player;

public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation(){
        System.out.println("\nYou are in Safe House, renewing health!");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        if(getPlayer().getInventory().isFood() && getPlayer().getInventory().isWater()&& getPlayer().getInventory().isFirewood()){
            System.out.println("Currently collected awards: Food: "+this.getPlayer().getInventory().isFood()
                    +" ,Firewood: "+this.getPlayer().getInventory().isFirewood()
                    +" ,Water: "+this.getPlayer().getInventory().isWater());
            System.out.println("\n\nYOU WON THE GAME!!!");
            return false;
        }else{
            System.out.println("\nTo win the game collect all the award of the regions and come back here!");
            System.out.println("Currently collected awards: Food: "+this.getPlayer().getInventory().isFood()
            +" ,Firewood: "+this.getPlayer().getInventory().isFirewood()
            +" ,Water: "+this.getPlayer().getInventory().isWater());
        }
        return true;
    }
}
