package item;

public class Armor extends Item{
    private int defence;

    public Armor(int id, int defence, int price,String name) {
        super(id, price, name);
        this.defence = defence;
    }
    public static Armor[] armors(){
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor(1,1,15,"Light");
        armorList[1] = new Armor(2,3,25,"Medium");
        armorList[2] = new Armor(3,5,40,"Heavy");
        return armorList;
    }
    public static Armor getArmorObjBYID(int id){
        for (Armor armor: Armor.armors()) {
            if (armor.getId() == id) {
                return armor;
            }
        }
        return null;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
