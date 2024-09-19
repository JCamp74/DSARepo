public class Ninetales extends Pokemon
{
    public Ninetales(){
        super(148, 96, 95, 101, 120, 120, "Fire");
    }

    public Ninetales(int inputHp, int inputAttack, int inputDefense, int inputSpAtk, int inputSpDef, int inputSpeed, String inputType){
        super(inputHp, inputAttack, inputDefense, inputSpAtk, inputSpDef, inputSpeed, inputType);
    }


    public void fireBlast(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 120) * (this.getSpAtk() / anyPokemon.getSpDef()))/50.0) + 2) * 1.5 * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void dig(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 100) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void ember(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 40) * (this.getSpAtk() / anyPokemon.getSpDef()))/50.0) + 2) * 1.5 * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }

    public void doubleEdge(Pokemon anyPokemon){
        double resultingLife = anyPokemon.getHp() - (((((22 * 100) * (this.getAttack() / anyPokemon.getDefense()))/50.0) + 2) * (anyPokemon.getRand() / 255.0));

        if(resultingLife <= 0) {
            anyPokemon.setHp(0);
        } else {
            anyPokemon.setHp(Math.round(resultingLife));
        }
    }
}
