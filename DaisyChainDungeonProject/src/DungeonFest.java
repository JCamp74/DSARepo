import java.util.Scanner;
import java.util.Random;

/**
 * The DungeonFest class, where the game is built and created.
 * The main data structure the game builds around is the DaisyChainConnector created in class.
 * Builds with the DaisyChainConnector, Player, Room, TreasureRoom, ExitRoom, MonsterRoom classes,
 * and the currentRoom integer variable.
 * Uses both Scanner and Random imports to work.
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class DungeonFest {
    JacksonDaisyChainConnector<Room> daisyChain = new JacksonDaisyChainConnector<>();
    Scanner input = new Scanner(System.in);
    Player player = new Player();
    Room room = new Room();
    TreasureRoom treasRoom = new TreasureRoom();
    ExitRoom exit = new ExitRoom();
    MonsterRoom battle = new MonsterRoom();
    private int currentRoom;

    /**
     * Constructor for DungeonFest. Sets the current room to 0, as you aren't in a room yet.
     */
    public DungeonFest() {
        currentRoom = 0;
    }

    /**
     * Encounter Method for the Skeleton Battle. Takes into account whether you run,
     * and whether you defend a turn. Battle will break with either the skeleton dies
     * or the player dies.
     */
    private void encounterSkele() {
        Skeleton skeleton = new Skeleton();
        String inp = null;

        System.out.println("A skeleton appears! What do you want to do?");
        System.out.println("1. Fight \n2. Run");
        inp = input.next();
        if(inp.equals("1")) {
            System.out.println("Battle Started!");
            while(!inp.equals("FORCE_STOP")) {
                System.out.println("What do you want to do? \n1. Attack \n2. Defend");
                inp = input.next();
                if (inp.equals("1")) {
                    System.out.println("You attack the skeleton!");
                    player.attack(skeleton);
                    if(skeleton.getHp() <= 0) {
                        break;
                    }
                }
                else if(inp.equals("2")) {
                    System.out.println("You defended! You took on the attack!");
                    skeleton.attackDefended(player);
                    if(player.getHp() <= 0) {
                        break;
                    }
                }
            }
        }
        else if(inp.equals("2")) {
            System.out.println("You ran! The zombie got to hit you for free! \nYou now lose health!");
            player.setHp(player.getHp() - skeleton.getAtk());
        }
    }

    /**
     * Encounter Method for the Zombie Battle. Takes into account whether you run,
     * and whether you defend a turn. Battle will break with either the zombie dies
     * or the player dies.
     */
    private void encounterZomb() {
        Zombie zombie = new Zombie();
        String inp = null;

        System.out.println("A zombie appears! What do you want to do?");
        System.out.println("1. Fight \n2. Run");
        inp = input.next();
        if(inp.equals("1")) {
            System.out.println("Battle Started!");
            while(!inp.equals("FORCE_STOP")) {
                System.out.println("What do you want to do? \n1. Attack \n2. Defend");
                inp = input.next();
                if (inp.equals("1")) {
                    System.out.println("You attack the skeleton!");
                    player.attack(zombie);
                    if(zombie.getHp() <= 0) {
                        break;
                    }
                }
                else if(inp.equals("2")) {
                    System.out.println("You defended! You took on the attack!");
                    zombie.attackDefended(player);
                    if(player.getHp() <= 0) {
                        break;
                    }
                }
            }
        }
        else if(inp.equals("2")) {
            System.out.println("You ran! The zombie got to hit you for free! \nYou now lose health!");
            player.setHp(player.getHp() - zombie.getAtk());
        }
    }

    /**
     * Method to set the rooms in the list. Used in initializing the game, and in
     * jumping down to another floor.
     * Ensures that the Treasure Room, Battle Room and Exit Room are all on different indexes.
     */
    private void setRooms() {
        int randRoom = 0;
        int randRoom2 = 0;
        int randRoom3 = 0;
        daisyChain.clear();
        for(int i = 0; i < 8; i++) {
            daisyChain.add(room);
        }
        Random rnd = new Random();
        randRoom = rnd.nextInt(1, 9);
        daisyChain.addAtIndex(randRoom, treasRoom);
        daisyChain.removeAtIndex(randRoom - 1);
        do {
            randRoom2 = rnd.nextInt(1, 9);
        } while (randRoom == randRoom2);
        daisyChain.addAtIndex(randRoom2, exit);
        daisyChain.removeAtIndex(randRoom2 - 1);
        do {
            randRoom3 = rnd.nextInt(1, 9);
        } while (randRoom3 == randRoom2 || randRoom3 == randRoom);
        daisyChain.addAtIndex(randRoom3, battle);
        daisyChain.removeAtIndex(randRoom3 - 1);
        daisyChain.circulate();
    }

    /**
     * Method to determine the behavior in each room.
     * Checks for all four room types, and uses setRooms in an exit if the user
     * decides to exit the current floor and move to the next one.
     */
    private void behavior() {
        if(daisyChain.get(currentRoom) == room) {
            System.out.println("A regular room.");
        }
        else if(daisyChain.get(currentRoom) == treasRoom) {
            Random rnd = new Random();
            int mimic = rnd.nextInt(1, 21);
            if(mimic == 1) {
                System.out.println("A treasure room!");
                System.out.println("YIKES!!! A mimic has appeared instead!");
                System.out.println("The mimic really weakened you, stats are down now!");
                player.setHp(player.getHp() - 5);
                player.setAttack(player.getAtk() - 4);
                player.setDefense(player.getDef() - 4);
            }
            else {
                System.out.println("A treasure room!");
                System.out.println("Congratulations! You get a treasure boost to your stats!");
                System.out.println("Current Stats: ");
                player.playerStats();
                player.setAttack(player.getAtk() + 2);
                player.setDefense(player.getDef() + 2);
                System.out.println("Updated Stats: ");
                player.playerStats();
            }

        }
        else if(daisyChain.get(currentRoom) == exit) {
            System.out.println("The exit room!");
            System.out.println("Would you like to move to the next floor? \n1. Yes \n2. No");
            String inp = input.next();
            if(inp.equals("1")) {
                System.out.println("Hooray! Jumping to a new floor...");
                setRooms();
                currentRoom = (daisyChain.size() / 2) - 1;
            }
        }
        else if(daisyChain.get(currentRoom) == battle) {
            Random rnd = new Random();
            int battleDecision = rnd.nextInt(2);
            System.out.println("A monster room! Entering battle... ");
            if(battleDecision == 0) {
                encounterSkele();
            }
            else {
                encounterZomb();
            }
        }
    }

    /**
     * Method to check the rooms that are both forward and behind the current room.
     * checks stress cases when currentRoom equals either 0 or 7, as the index
     * is out of bounds below 0 and above 7. This method handles these cases, and sets
     * the index to the proper peek value.
     */
    private void peekForwardAndBehind() {
        System.out.print("Room forward: ");
        if(currentRoom == 7) {
            if(daisyChain.get(0) == room) {
                System.out.println("A regular room.");
            }
            else if(daisyChain.get(0) == treasRoom) {
                System.out.println("A treasure room!");
            }
            else if(daisyChain.get(0) == exit) {
                System.out.println("The exit room!");
            }
            else if(daisyChain.get(0) == battle) {
                System.out.println("A battle room!");
            }
        }
        else {
            if(daisyChain.get(currentRoom + 1) == room) {
                System.out.println("A regular room.");
            }
            else if(daisyChain.get(currentRoom + 1) == treasRoom) {
                System.out.println("A treasure room!");
            }
            else if(daisyChain.get(currentRoom + 1) == exit) {
                System.out.println("The exit room!");
            }
            else if(daisyChain.get(currentRoom + 1) == battle) {
                System.out.println("A battle room!");
            }
        }

        System.out.print("Room behind: ");
        if(currentRoom == 0) {
            if(daisyChain.get(currentRoom + 7) == room) {
                System.out.println("A regular room.");
            }else if(daisyChain.get(currentRoom + 7) == treasRoom) {
                System.out.println("A treasure room!");
            }else if(daisyChain.get(currentRoom + 7) == exit) {
                System.out.println("The exit room!");
            }else if(daisyChain.get(currentRoom + 7) == battle) {
                System.out.println("A battle room!");
            }
        }
        else {
            if(daisyChain.get(currentRoom - 1) == room) {
                System.out.println("A regular room.");
            }else if(daisyChain.get(currentRoom - 1) == treasRoom) {
                System.out.println("A treasure room!");
            }else if(daisyChain.get(currentRoom - 1) == exit) {
                System.out.println("The exit room!");
            }else if(daisyChain.get(currentRoom - 1) == battle) {
                System.out.println("A battle room!");
            }
        }
    }

    /**
     * Method to move forward or backwards.
     * Will either increment or decrement the currentRoom count depending on the player's choice.
     */
    private void mover() {
        String inp = null;
        System.out.println("What do you want to do? \n1. Move Forward \n2. Move Behind");
        inp = input.next();
        if(inp.equals("1")) {
            currentRoom++;
            if(currentRoom >= 7) {
                currentRoom = 0;
            }
        }
        else if(inp.equals("2")) {
            currentRoom--;
            if(currentRoom <= 0) {
                currentRoom = 7;
            }
        }

    }

    /**
     * Method to run the entire game. Uses all helper methods above to make the game work.
     * Initializes the first floor using setRooms, and the game will loop using scanners and
     * by checking the player's health after. Has a "FORCE_STOP" command to forcefully quit the game.
     * Game will also close if player chooses not to play in the beginning of the method.
     */
    public void run() {
        String inp = null;
        do {
            System.out.println("Welcome to DungeonFest! Would you like to play? \n1. Yes \n2. No");
            inp = input.next();
            System.out.println(" ");
            if(inp.equals("1")) {

                System.out.println("Game Started! Starting first floor...");
                player.playerStats();
                setRooms();
                currentRoom = (daisyChain.size() / 2) - 1;
                do {
                    System.out.print("You are currently at: ");
                    behavior();
                    if(player.getHp() <= 0) {
                        break;
                    }
                    peekForwardAndBehind();
                    mover();

                } while(player.getHp() > 0);
                if(player.getHp() <= 0) {
                    System.out.println("Game Over!");
                    break;
                }
            }
            else if(inp.equals("2")) {
                System.out.println("Aw man.");
                break;
            }
        } while(!inp.equals("FORCE_STOP"));
    }

    /**
     * UNUSED METHOD: roomTest
     * Method checks the room list to see if rooms are initialized properly.
     * Used for debug purposes.
     */
    public void roomTest() {
        setRooms();
        System.out.println(daisyChain);
    }


}
