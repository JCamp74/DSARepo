/**
 * Interface that houses the abstract attack methods.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public interface MonsterAttackInterface {

    /**
     * Abstract Method to attack regularly.
     * Used in all monster subclasses.
     * @param anyLife The Living object being attacked.
     */
    public void attack(Living anyLife);

    /**
     * Abstract Method to attack while the player is defending.
     * Used in all monster subclasses.
     * @param anyLife The Living object being attacked.
     */
    public void attackDefended(Living anyLife);

}
