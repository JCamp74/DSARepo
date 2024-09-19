public class TestStadium
{
    public static void main(String[] args){
        Raichu sparky = new Raichu();
        Ninetales howler = new Ninetales();
        Golduck quack = new Golduck();
        Hypno sleepy = new Hypno();

        //Fights were semi-scripted out, played with the moves as I went
        //and once someone won I announced it and then moved on to the next fight.

        System.out.println("A new battle begins! Raichu vs. Golduck!");
        System.out.println("Raichu's health: " + sparky.getHp() + "\nGolduck's health: " + quack.getHp());

        while (sparky.getHp() > 0 && quack.getHp() > 0) {
            if (sparky.randATK() == 1) {
                sparky.thunderBolt(quack);
                System.out.println("Raichu used Thunderbolt! Golduck's health: " + quack.getHp());
            }else if (sparky.randATK() == 2) {
                sparky.quickAttack(quack);
                System.out.println("Raichu used Quick Attack! Golduck's health: " + quack.getHp());
            }else if (sparky.randATK() == 3) {
                sparky.slam(quack);
                System.out.println("Raichu used Slam! Golduck's health: " + quack.getHp());
            }else {
                sparky.megaKick(quack);
                System.out.println("Raichu used Mega Kick! Golduck's health: " + quack.getHp());
            }

            if (quack.randATK() == 1) {
                quack.hydroPump(sparky);
                System.out.println("Golduck used Hydro Pump! Raichu's health: " + sparky.getHp());
            }else if (quack.randATK() == 2) {
                quack.scratch(sparky);
                System.out.println("Golduck used Scratch! Raichu's health: " + sparky.getHp());
            }else if (quack.randATK() == 3) {
                quack.bubbleBeam(sparky);
                System.out.println("Golduck used BubbleBeam! Raichu's health: " + sparky.getHp());
            }else {
                quack.seismicToss(sparky);
                System.out.println("Golduck used Seismic Toss! Raichu's health: " + sparky.getHp());
            }
        }

        /*
        System.out.println("\n" + sparky.compareTo(quack));
        System.out.println(sparky.equals(quack));
         */

        /*
        System.out.println("\nA new battle begins! Ninetales vs. Hypno!");
        System.out.println("Ninetales's health: " + howler.getHp() + "\nHypno's health: " + sleepy.getHp());
        howler.fireBlast(sleepy);
        System.out.println("Ninetales used Fire Blast! Hypno's health: " + sleepy.getHp());
        sleepy.confusion(howler);
        System.out.println("Hypno used Confusion! Ninetales's health: " + howler.getHp());
        howler.ember(sleepy);
        System.out.println("Ninetales used Ember! Hypno's health: " + sleepy.getHp());
        sleepy.skullBash(howler);
        System.out.println("Hypno used Skull Bash! Ninetales's health: " + howler.getHp());
        howler.dig(sleepy);
        System.out.println("Ninetales used Dig! Hypno's health: " + sleepy.getHp());
        sleepy.headbutt(howler);
        System.out.println("Hypno used Headbutt! Ninetales's health: " + howler.getHp());
        howler.doubleEdge(sleepy);
        System.out.println("Ninetales used Double-Edge! Hypno's health: " + sleepy.getHp());
        System.out.println("Hypno fainted! Ninetales wins!");

        //Setting both hp values to original for final fight
        sparky.setHp(135);
        howler.setHp(148);

        System.out.println("\nThe final battle begins! Raichu vs. Ninetales!");
        System.out.println("Raichu's health: " + sparky.getHp() + "\nNinetales's health: " + howler.getHp());
        sparky.megaKick(howler);
        System.out.println("Raichu used Mega Kick! Ninetales's health: " + howler.getHp());
        howler.fireBlast(sparky);
        System.out.println("Ninetales used Fire Blast! Raichu's health: " + sparky.getHp());
        sparky.thunderBolt(howler);
        System.out.println("Raichu used Thunderbolt! Ninetales's health: " + howler.getHp());
        howler.dig(sparky);
        System.out.println("Ninetales used Dig! Raichu's health: " + sparky.getHp());
        sparky.megaKick(howler);
        System.out.println("Raichu used Mega Kick! Ninetales's health: " + howler.getHp());
        System.out.println("Ninetales fainted! Raichu is the Champion!");

        */
    }
}