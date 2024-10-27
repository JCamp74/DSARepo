/**
 * The Skeleton class, a basic monster that extends the Monster class for stats,
 * and implements the MonsterAttackInterface for attack methods.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class Skeleton extends Monster implements MonsterAttackInterface{

    /**
     * Skeleton constructor that extends the Monster superclass.
     */
    public Skeleton() {
        super(30, 6,4);
    }

    /**
     * Override of the attack method from MonsterAttackInterface.
     * Normal amount of damage is used here for both a traditional attack and running.
     * A skeleton's attack is "head hitter."
     * @param anyLife The Living object being attacked.
     */
    @Override
    public void attack(Living anyLife) {
        double resHealth = anyLife.getHp() - this.getAtk();

        anyLife.setHp(resHealth);
        if(anyLife.getHp() > 0) {
            System.out.println("The skeleton hit you on the head! Damage dealt: " + this.getAtk());
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
        double resHealth = anyLife.getHp() - 3;

        anyLife.setHp(resHealth);
        if(anyLife.getHp() > 0) {
            System.out.println("The skeleton hit you on the head! Damage dealt: " + 3);
            System.out.println("Your health: " + resHealth);
        }
        if(anyLife.getHp() <= 0) {
            System.out.println("You died! Game Over!");
        }

    }
}
