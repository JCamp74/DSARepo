import java.util.Random;

public class Pokemon
{
    // instance variables - replace the example below with your own
    private double hp;
    private double attack;
    private double defense;
    private double spAtk;
    private double spDef;
    private int speed;
    private String type;


    public Pokemon(int userHp, int userAttack, int userDefense, int userSpAtk, int userSpDef, int userSpeed, String userType){
        hp = userHp;
        attack = userAttack;
        defense = userDefense;
        spAtk = userSpAtk;
        spDef = userSpDef;
        speed = userSpeed;
        type = userType;
    }

    public double getHp(){
        return hp;
    }

    public double getAttack(){
        return attack;
    }

    public double getDefense(){
        return defense;
    }

    public double getSpAtk(){
        return spAtk;
    }

    public double getSpDef(){
        return spDef;
    }

    public int getSpeed(){
        return speed;
    }

    public String getType(){
        return type;
    }

    public int getRand(){
        Random rand = new Random();
        int randMult = rand.nextInt(217, 256);

        return randMult;
        /*
         * Random number generator adapted from https://www.geeksforgeeks.org/generating-random-numbers-in-java/
         * Section 1: java.util.random, code modified from geeksforgeeks: int rand_int1 = rand.nextInt(1000);
         * and was turned into the randMult number given above. If not cited correctly, I will change ASAP
         * and be sure to let me know as I do NOT condone plagiarism and NEVER intend to steal or copy
         * intellectual property for my own usage.
         */


    }

    public int randATK(){
        Random rand = new Random();
        int randATK = rand.nextInt(1, 5);
        return randATK;
    }

    public void setHp(double inputHp){
        hp = inputHp;
    }

    public void setAttack(double inputAttack){
        attack = inputAttack;
    }

    public void setDefense(double inputDefense){
        defense = inputDefense;
    }

    public void setSpAtk(double inputSpAtk){
        spAtk = inputSpAtk;
    }

    public void setSpDef(double inputSpDef){
        spDef = inputSpDef;
    }

    public void setSpeed(int inputSpeed){
        speed = inputSpeed;
    }

    public void setType(String inputType){
        type = inputType;
    }

    public void prntStats(){
        System.out.println("HP: " + getHp());
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
        System.out.println("Spec. Atk: " + getSpAtk());
        System.out.println("Spec. Def: " + getSpDef());
        System.out.println("Speed: " + getSpeed());
        System.out.println("Type: " + getType());
    }
}
