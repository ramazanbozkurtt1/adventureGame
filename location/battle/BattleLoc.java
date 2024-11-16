package location.battle;

import item.Armor;
import item.Weapon;
import location.Location;
import main.Inventory;
import main.Player;
import obstacle.Obstacle;

import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("\nYou are in: "+ this.getName());
        System.out.println("Be careful! "+ obsNumber+ " " +this.getObstacle().getName()+ "'s live here!");
        System.out.println("\n<R>un or <F>ight!");
        String selectCase = scanner.nextLine().toLowerCase();
        if(selectCase.equals("f") && combat(obsNumber)){
            System.out.println("\nYou Killed them all!");
            return true;
        }else if(this.getPlayer().getHealth() == 0){
            System.out.println("\nYOU ARE DEAD!!!");
            return false;
        }
        return true;
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }
    public boolean combat(int obsNumber){
        Random random = new Random();
        int next = random.nextInt(1,101);
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0){
                System.out.println("\n<H>it or <R>un!");
                String selectCombat = scanner.nextLine().toLowerCase();
                if(selectCombat.equals("h")){
                    if(next <= 50){
                        System.out.println("\nYou Hit!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth()>0){
                            System.out.println("\n"+this.getObstacle().getName()+" hit you!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getDefence();
                            if(obstacleDamage<0){obstacleDamage = 0;}
                            this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                            afterHit();
                        }
                    }else{
                        if (this.getObstacle().getHealth()>0){
                            System.out.println("\n"+this.getObstacle().getName()+" hit you!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getDefence();
                            if(obstacleDamage<0){obstacleDamage = 0;}
                            this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                            afterHit();
                        }
                        if(this.getPlayer().getHealth()>0){
                            System.out.println("\nYou Hit!");
                            this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                }else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth()<this.getPlayer().getHealth()){
                System.out.println("\nYou killed 1 of the "+this.getObstacle().getName()+"'s remaining "+(obsNumber-i)+" "+this.getObstacle().getName()+"'s");
                if (this.getName().equals("Mine")){
                    String award = this.getAward();
                    switch (award){
                        case "none":
                            System.out.println("\nYou didn't earn anything!");
                            break;
                        case "Deagle":
                            System.out.println("\nYou  earn a \"Deagle\"!");
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjBYID(1));
                            break;
                        case "Sword":
                            System.out.println("\nYou  earn a \"Sword\"!");
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjBYID(2));
                            break;
                        case "Shotgun":
                            System.out.println("\nYou  earn a \"Shotgun\"!");
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjBYID(3));
                            break;
                        case "Light":
                            System.out.println("\nYou  earn a \"Light\"!");
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjBYID(1));
                            break;
                        case "Medium":
                            System.out.println("\nYou  earn a \"Medium\"!");
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjBYID(2));
                            break;
                        case "Heavy":
                            System.out.println("\nYou  earn a \"Heavy\"!");
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjBYID(3));
                            break;
                        case "1":
                            System.out.println("\nYou  earn  \"1\" gold!");
                            this.getPlayer().setBalance(this.getPlayer().getBalance()+1);
                            break;
                        case "5":
                            System.out.println("\nYou  earn  \"5\" gold!");
                            this.getPlayer().setBalance(this.getPlayer().getBalance()+5);
                            break;
                        case "10":
                            System.out.println("\nYou  earn  \"10\" gold!");
                            this.getPlayer().setBalance(this.getPlayer().getBalance()+10);
                            break;
                    }
                } else if (this.getName().equals("Cave")) {
                    this.getPlayer().getInventory().setFood(true);
                    System.out.println("You gain "+this.getObstacle().getAward()+" golds and Food!");
                    this.getPlayer().setBalance(this.getPlayer().getBalance()+this.getObstacle().getAward());
                }else if (this.getName().equals("Forest")) {
                    this.getPlayer().getInventory().setFirewood(true);
                    System.out.println("You gain "+this.getObstacle().getAward()+" golds and Firewood!");
                    this.getPlayer().setBalance(this.getPlayer().getBalance()+this.getObstacle().getAward());
                }else if (this.getName().equals("River")) {
                    this.getPlayer().getInventory().setWater(true);
                    System.out.println("You gain "+this.getObstacle().getAward()+" golds and Water!");
                    this.getPlayer().setBalance(this.getPlayer().getBalance()+this.getObstacle().getAward());
                }

            }else{
                return false;
            }
        }

        return true;
    }

    private void afterHit() {
        System.out.println("\nYour Health: "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+"'s Health: "+this.getObstacle().getHealth());
    }

    private void obstacleStats(int i) {
        System.out.println("\n"+i+". " +this.getObstacle().getName()+" Features");
        System.out.println("--------------------------------");
        System.out.println("Health: "+ this.getObstacle().getHealth());
        System.out.println("Damage: "+ this.getObstacle().getDamage());
        System.out.println("Award: "+ this.getObstacle().getAward());
    }

    private void playerStats() {
        System.out.println("\nPlayer Features");
        System.out.println("--------------------------------");
        System.out.println("Health: "+this.getPlayer().getHealth());
        System.out.println("Weapon: "+this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Damage: "+this.getPlayer().getTotalDamage());
        System.out.println("Armor: "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Defence: "+this.getPlayer().getInventory().getArmor().getDefence());
        System.out.println("Balance: "+this.getPlayer().getBalance());
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
