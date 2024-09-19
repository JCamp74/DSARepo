public class Hypno extends Pokemon
{
    public Hypno(){
        super(160, 93, 90, 93, 135, 87, "Psychic");
    }

    public Hypno(int inputHp, int inputAttack, int inputDefense, int inputSpAtk, int inputSpDef, int inputSpeed, String inputType){
        super(inputHp, inputAttack, inputDefense, inputSpAtk, inputSpDef, inputSpeed, inputType);
    }


    public void psychic(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 90) * (this.getSpAtk() / anyPokemon.getSpDef()))/50.0) + 2) * 1.5 * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void headbutt(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 70) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void confusion(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 50) * (this.getSpAtk() / anyPokemon.getSpDef()))/50.0) + 2) * 1.5 * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void skullBash(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 100) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }
}