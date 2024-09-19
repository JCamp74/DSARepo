public class Golduck extends Pokemon
{
    public Golduck(){
        super(155, 102, 98, 115, 100, 105, "Water");
    }

    public Golduck(int inputHp, int inputAttack, int inputDefense, int inputSpAtk, int inputSpDef, int inputSpeed, String inputType){
        super(inputHp, inputAttack, inputDefense, inputSpAtk, inputSpDef, inputSpeed, inputType);
    }


    public void hydroPump(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 120) * (this.getSpAtk() / anyPokemon.getSpDef()))/50.0) + 2) * 1.5 * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void scratch(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 40) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void bubbleBeam(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 65) * (this.getSpAtk() / anyPokemon.getSpDef()))/50.0) + 2) * 1.5 * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void seismicToss(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - 50;
        /* Seismic Toss disregards the damage formula and instead
         * relies on the user Pokemon's current level in order to
         * grab the damage value. In Golduck's case (and all other cases),
         * its' level is 50, and thus deals 50 exact damage.
         */

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }
}
