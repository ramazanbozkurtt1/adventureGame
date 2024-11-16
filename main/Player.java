package main;

import character.Archer;
import character.GameChar;
import character.Knight;
import character.Samurai;

import java.util.Scanner;

public class Player {
    private Inventory inventory;
    private int damage;
    private int health;
    private int originalHealth;

    private int balance;
    private String name;

    private String charName;
    Scanner scanner = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }
    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health<0){
            health = 0;
        }
        this.health = health;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public void selectChar(){
        GameChar[] charList = {new Samurai(),new Archer(), new Knight()};
        System.out.println("-------------------------------------------------------------------");
        for(GameChar gameChar: charList){
            System.out.println("ID: "+gameChar.getId()
                    +" \tCharacter: "+gameChar.getName()
                    +" \t Damage: "+gameChar.getDamage()
                    +" \t Health: "+gameChar.getHealth()
                    +" \t Balance: "+gameChar.getMoney());
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.print("\nPlease select a character with ID: ");
        int selectChar = scanner.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Knight());

        }
//        System.out.println("\nCharacter: "+this.getName()
//                +" \t Damage: "+this.getDamage()
//                +" \t Health: "+this.getHealth()
//                +" \t Money: "+this.getMoney());
    }

    public void initPlayer(GameChar gameChar){
        this.setName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setOriginalHealth(gameChar.getHealth());
        this.setHealth(gameChar.getHealth());
        this.setBalance(gameChar.getMoney());
        this.setInventory(new Inventory());
    }
    public void printInfo(){
        System.out.println("\n\nYour current Weapon: "+this.getInventory().getWeapon().getName()
                +" \t Armor: "+this.getInventory().getArmor().getName()
                +" \t Defence: "+this.getInventory().getArmor().getDefence()
                +" \t Damage: "+this.getTotalDamage()
                +" \t Health: "+this.getHealth()
                +" \t Balance: "+this.getBalance());
    }
    public int getTotalDamage(){
        return damage+ this.inventory.getWeapon().getDamage();
    }
}
