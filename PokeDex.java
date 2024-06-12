public class PokeDex {
    protected static Pokemon[] pokedex = new Pokemon[151];

    //hp atk def spa spd spe
    public static void populate() {
        pokedex[0] = new Pokemon("Dummy", 200, 200, 200, 200, 200, 200, Types.DRAGON, Types.PSYCHIC);
        pokedex[1] = new Pokemon("Bulbasaur", 152, 111, 111, 128, 128, 106, Types.GRASS, Types.POISON);
            pokedex[1].setMoveA(AttackDex.attackdex.get("Tackle"));
            pokedex[1].setMoveB(AttackDex.attackdex.get("Growl"));
            pokedex[1].setMoveC(AttackDex.attackdex.get("Vine Whip"));
            pokedex[1].setMoveD(AttackDex.attackdex.get("Growth"));

        pokedex[4] = new Pokemon("Charmander", 146, 114, 104, 123, 112, 128, Types.FIRE, null);
            pokedex[4].setMoveA(AttackDex.attackdex.get("Scratch"));
            pokedex[4].setMoveB(AttackDex.attackdex.get("Growl"));
            pokedex[4].setMoveC(AttackDex.attackdex.get("Ember"));
            pokedex[4].setMoveD(AttackDex.attackdex.get("Smokescreen"));

        pokedex[7] = new Pokemon("Squirtle", 151, 110, 128, 112, 127, 104, Types.WATER, null);
            pokedex[4].setMoveA(AttackDex.attackdex.get("Tackle"));
            pokedex[4].setMoveB(AttackDex.attackdex.get("Tail Whip"));
            pokedex[4].setMoveC(AttackDex.attackdex.get("Water Gun"));
            pokedex[4].setMoveD(AttackDex.attackdex.get("Withdraw"));
        //Pokemon Pikachu = new Pokemon("Pikachu", 142, 117, 112, 156, Types.ELECTRIC, null, null, null, null, null);
        // Pokemon Venusaur = new Pokemon("Venusaur", 187, 167, 167, 145, Types.GRASS, Types.POISON);
        // Pokemon Charizard = new Pokemon("Charizard", 185, 177, 150, 167, Types.FIRE, Types.FLYING);
        // Pokemon Blastoise = new Pokemon("Blastoise", 186, 150, 172, 143, Types.WATER, null);
    }
    public static void main(String[] args) {
        populate();
    }
}
