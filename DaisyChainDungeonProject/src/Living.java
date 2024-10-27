/**
 * Living superclass that houses stats for live beings,
 * and all methods to get or set said stats.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class Living {
    private double hp;
    private double atk;
    private double def;


    /**
     * Constructor to set the values to user-assigned values.
     * All subclasses of this superclass house this constructor.
     * @param uHP The HP value set.
     * @param uATK The Attack value set.
     * @param uDEF The Defense value set.
     */
    public Living(int uHP, int uATK, int uDEF) {
        hp = uHP;
        atk = uATK;
        def = uDEF;
    }

    /**
     * Method to get the HP value of the current object.
     * @return The HP value of the object.
     */
    public double getHp(){
        return hp;
    }

    /**
     * Method to get the Attack value of the current object.
     * @return The Attack value of the object.
     */
    public double getAtk(){
        return atk;
    }

    /**
     * Method to return the Defense value of the current object.
     * @return The Defense value of the object.
     */
    public double getDef(){
        return def;
    }

    /**
     * Method to set the HP value of the current object.
     * @param inputHp The set HP value used.
     */
    public void setHp(double inputHp){
        hp = inputHp;
    }

    /**
     * Method to set the Attack value of the current object.
     * @param inputATK The set Attack value used.
     */
    public void setAttack(double inputATK){
        atk = inputATK;
    }

    /**
     * Method to set the Defense value of the current object.
     * @param inputDEF The set Defense value used.
     */
    public void setDefense(double inputDEF){
        def = inputDEF;
    }
}
