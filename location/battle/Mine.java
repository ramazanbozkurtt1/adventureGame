package location.battle;

import main.Player;
import obstacle.Snake;

import java.util.Random;

public class Mine extends BattleLoc{
    private static Random random = new Random();
    @Override
    public String getAward(){
        int num = random.nextInt(100);
        if(num<=45){return "none";}
        else{
            if(num <= 60){
                //weapon
                if(num<=52){
                    return "Deagle";
                } else if (num<=58) {
                    return "Sword";
                }else{return "Shotgun";}
            } else if(num<=75){
                //armor
                if(num<=67){
                    return "Light";
                } else if (num<=73) {
                    return "Medium";
                }else{return "Heavy";}
            }else{
                //money
                if(num<=88){
                    return "1";
                } else if (num<=95) {
                    return "5";
                }else{return "10";}
            }
        }
    }

    public Mine(Player player) {
        super(player, "Mine", new Snake(),"none", 5);
    }
}
