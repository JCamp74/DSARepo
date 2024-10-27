/**
 * The Zombie class, a basic monster that extends the Monster class for stats,
 * and implements the MonsterAttackInterface for attack methods.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class Zombie extends Monster implements MonsterAttackInterface{

    /**
     * Zombie constructor that extends the Monster superclass.
     */
    public Zombie() {
        super(20, 10,6);
    }

    /**
     * Override of the attack method from MonsterAttackInterface.
     * Normal amount of damage is used here for both a traditional attack and running.
     * A zombie's attack is "club."
     * @param anyLife The Living object being attacked.
     */
    @Override
    public void attack(Living anyLife) {
        double resHealth = anyLife.getHp() - this.getAtk();

        anyLife.setHp(resHealth);
        if(anyLife.getHp() > 0) {
            System.out.println("The zombie clubbed you! Damage dealt: " + 6);
            System.out.println("Your health: " + resHealth);
        }
        if(anyLife.getHp() <= 0) {
            System.out.println("You died! Game Over!");
        }
    }

    /**
     * Override of the attackDefended method from MonsterAttackInterface.
     * Damage is halved as you are defending against this attack.
     * @param anyLife The Living object being attacked.
     */
    @Override
    public void attackDefended(Living anyLife) {
        double resHealth = anyLife.getHp() - 5;

        anyLife.setHp(resHealth);
        if(anyLife.getHp() > 0) {
            System.out.println("The zombie clubbed you! Damage dealt: " + 3);
            System.out.println("Your health: " + resHealth);
        }
        if(anyLife.getHp() <= 0) {
            System.out.println("You died! Game Over!");
        }

    }
}
