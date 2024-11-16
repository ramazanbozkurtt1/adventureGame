package location.normal;

import item.Armor;
import item.Weapon;
import main.Player;

public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player,"Tool Store");
    }

    public void menu(){}

    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while(showMenu){
            System.out.println("\n------------ Welcome to Tool Store! ------------");
            System.out.println("1- Weapon");
            System.out.println("2- Armor");
            System.out.println("\n0- Quit");
            System.out.print("\nYour choice: ");
            int selectCase = scanner.nextInt();
            while (selectCase<0 || selectCase>2){
                System.out.print("\nInvalid option, please try again: ");
                selectCase = scanner.nextInt();
            }
            switch (selectCase){
                case 0:
                    System.out.println("\nSee you again!");
                    showMenu = false;
                    break;
                case 1:
                    printWeapon();
                    break;
                case 2:
                    printArmor();
                    break;
            }
        }
        return true;
    }

    private void printArmor() {
        System.out.println("\n------------------------------ Armors ------------------------------");
        for (Armor armor: Armor.armors()){
            System.out.println("Armor ID: "+armor.getId()+
                    "\t Name: "+armor.getName()+
                    "\t Price: "+ armor.getPrice()+
                    "\t Defence: "+ armor.getDefence());
        }
        System.out.println("\n0- Quit");
        buyArmor();
    }

    private void buyArmor() {
        System.out.print("\nSelect a armor: ");
        int selectArmorID = scanner.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.print("\nInvalid option, please try again: ");
            selectArmorID = scanner.nextInt();
        }
        if(selectArmorID != 0){
            Armor selectArmor = Armor.getArmorObjBYID(selectArmorID);
            if (selectArmor!= null){
                if (selectArmor.getPrice() > this.getPlayer().getBalance()){
                    System.out.println("\nYou don't have enough money to buy this armor!");
                }else {
                    System.out.println("\nYou bought the " + selectArmor.getName() + " armor!");
                    int balance = this.getPlayer().getBalance() - selectArmor.getPrice();
                    this.getPlayer().setBalance(balance);
                    System.out.println("\nCurrent balance: " + this.getPlayer().getBalance());
                    System.out.println("\nYour previous armor: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectArmor);
                    System.out.println("Your current armor: " + this.getPlayer().getInventory().getArmor().getName());
                }
            }
        }
    }

    private void printWeapon() {
        System.out.println("\n------------------------------ Weapons ------------------------------");
        for (Weapon weapon: Weapon.weapons()){
            System.out.println("Weapon ID: "+weapon.getId()+
                    "\t Name: "+weapon.getName()+
                    "\t Price: "+ weapon.getPrice()+
                    "\t Damage: "+ weapon.getDamage());
        }
        System.out.println("\n0- Quit");
        buyWeapon();
    }

    public void buyWeapon(){
        System.out.print("\nSelect a weapon: ");
        int selectWeaponID = scanner.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.print("\nInvalid choice, please try again: ");
            selectWeaponID = scanner.nextInt();
        }
        if(selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjBYID(selectWeaponID);
            if (selectedWeapon!= null){
                if(this.getPlayer().getInventory().getWeapon().getId() == selectedWeapon.getId()){
                    System.out.println("\nYou already have this weapon please select another weapon!");
                }else{
                    if (selectedWeapon.getPrice() > this.getPlayer().getBalance()){
                        System.out.println("\nYou don't have enough money to buy this weapon!");
                    }else{
                        System.out.println("\nYou bought the "+selectedWeapon.getName()+ " weapon!");
                        int balance = this.getPlayer().getBalance() - selectedWeapon.getPrice();
                        this.getPlayer().setBalance(balance);
                        System.out.println("\nCurrent balance: "+this.getPlayer().getBalance());
                        System.out.println("\nYour previous weapon: "+this.getPlayer().getInventory().getWeapon().getName());
                        this.getPlayer().getInventory().setWeapon(selectedWeapon);
                        System.out.println("Your current weapon: "+this.getPlayer().getInventory().getWeapon().getName());
                    }
                }
            }
        }
    }
}
