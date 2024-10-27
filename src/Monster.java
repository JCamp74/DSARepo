/**
 * Monster class that extends the living class.
 * Sets the stats of the monsters and houses nothing else.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class Monster extends Living{

    /**
     * Constructor to build the monster's stats.
     * @param uHP The HP value of the monster.
     * @param uATK The Attack value of the monster.
     * @param uDEF The Defense value of the monster.
     */
    public Monster(int uHP, int uATK, int uDEF) {
        super(uHP, uATK, uDEF);
    }
}
