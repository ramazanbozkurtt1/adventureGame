package item;

public class Weapon extends Item{

    private int damage;

    public Weapon(int id, int damage, int price, String name) {
        super(id,price,name);
        this.damage = damage;
    }
    public static Weapon[] weapons(){
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] = new Weapon(1,2,5,"Deagle");
        weaponList[1] = new Weapon(2,3,35,"Sword");
        weaponList[2] = new Weapon(3,7,45,"Shotgun");
        return weaponList;
    }
    public static Weapon getWeaponObjBYID(int id){
        for (Weapon weapon: Weapon.weapons()) {
            if (weapon.getId() == id) {
                return weapon;
            }
        }
        return null;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
