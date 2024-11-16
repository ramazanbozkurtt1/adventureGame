package main;

import location.Location;
import location.battle.Cave;
import location.battle.Forest;
import location.battle.Mine;
import location.battle.River;
import location.normal.SafeHouse;
import location.normal.ToolStore;

import java.util.Scanner;

public class Game {
    private Player player;
    private Location location;

    public Game(Player player, Location location) {
        this.player = player;
        this.location = location;
    }
    Game() {}

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    Scanner scanner = new Scanner(System.in);
    public void start(){
        System.out.println("\nWelcome to Adventure Game");
        System.out.print("\nPlease type a username: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println("\nWelcome "+player.getName());
        System.out.println("\nCharacters");
        player.selectChar();
        System.out.println("\nTo win the game collect all the award of the regions and come back here!");
        System.out.println("Currently collected awards: Food: "+player.getInventory().isFood()
                +" ,Firewood: "+player.getInventory().isFirewood()
                +" ,Water: "+player.getInventory().isWater());
        Location location = null;
        while (true){
            player.printInfo();
            System.out.println("\n------------ Regions ------------");
            System.out.println("1- Safe House --> No Enemies, renews health!");
            System.out.println("2- Tool Store --> Purchase Armor or Weapon!");
            System.out.println("3- Cave       --> Kill Zombies to get some gold and food!");
            System.out.println("4- Forest     --> Kill Vampires to get some gold and firewood!");
            System.out.println("5- River      --> Kill Bears to get some gold and water!");
            System.out.println("6- Mine       --> Kill Snakes to get mystery award!");
            System.out.println("\n0- Quit Game!");
            System.out.print("\nPlease select the region you want to go: ");
            int selectLoc = scanner.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInventory().isFood()){
                        System.out.println("\nYou have already got the award of this region! Returning to Safe House!");
                        location = new SafeHouse(player);
                    }else{
                        location = new Cave(player);
                    }
                    break;
                case 4:
                    if(player.getInventory().isFirewood()){
                        System.out.println("\nYou have already got the award of this region! Returning to Safe House!");
                        location = new SafeHouse(player);
                    }else{
                        location = new Forest(player);
                    }
                    break;
                case 5:
                    if(player.getInventory().isWater()){
                        System.out.println("\nYou have already got the award of this region! Returning to Safe House!");
                        location = new SafeHouse(player);
                    }else{
                        location = new River(player);
                    }
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("\nInvalid option please try again!");
                    break;
            }
            if(location == null){
                System.out.println("\n\nGAME OVER! See you again!");
                break;
            }
            if(!location.onLocation()){
                System.out.println("\n\nGAME OVER! See you again!");
                break;
            }
        }
    }
}
