/**
 * Raichu stats based on level 50: 135 hp, 110 atk, 75 def, 110 spatk, 100 spdef, 120 spd
 * Moves will be based on learnset/HM/TM learnage (gym badges not taken into account)
 * Will try to implement moves using either Special or Physical based on type of attack.
 * MIGHT try to implement super effective moves if typing can be figured out.
 * DAMAGE FORMULA = ((((22) * Power * (Atk/Def))/50) + 2) * STAB * type1 * random mult
 * The formula comes from pokemon itself, and I'm going to try to implement it.
 *
 */
public class Raichu extends Pokemon
{
    public Raichu(){
        super(135, 110, 75, 110, 100, 120, "Electric");
    }
    public Raichu(int inputHp, int inputAttack, int inputDefense, int inputSpAtk, int inputSpDef, int inputSpeed, String inputType){
        super(inputHp, inputAttack, inputDefense, inputSpAtk, inputSpDef, inputSpeed, inputType);
    }


    public void thunderBolt(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 95) * (this.getSpAtk() / anyPokemon.getSpDef()))/50.0) + 2) * 1.5 * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void quickAttack(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 40) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void slam(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 80) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void megaKick(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 120) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }




}
