/**
 * Player class. Extends living. Houses the stats of the player,
 * along with the attack method and the method to print stats.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class Player extends Living {

    /**
     * Constructor to set the stats of the player initially.
     * Uses the superclass Living to set variables.
     */
    public Player() {
        super(50, 10, 10);
    }

    /**
     * Attack method for the player. Used in the battle sequence of DungeonFest.
     * @param anyLife The Living object being attacked.
     */
    public void attack(Living anyLife) {
        double damage = Math.round(this.getAtk() - (anyLife.getDef() / 2));
        double resHealth = anyLife.getHp() - damage;

        anyLife.setHp(resHealth);
        if(anyLife.getHp() > 0) {
            System.out.println("You attack! Damage dealt: " + damage);
            System.out.println("Enemy's health: " + resHealth);
        }
        if(anyLife.getHp() <= 0) {
            System.out.println("You win!");
        }
    }

    /**
     * Method to print the player's current stats.
     * Used in both the initialization and new floor, and the treasure room
     * when the player gains or loses stats based on what is found.
     */
    public void playerStats() {
        System.out.println("Current health: " + this.getHp());
        System.out.println("Current attack: " + this.getAtk());
        System.out.println("Current defense: " + this.getDef() + "\n");
    }

}
