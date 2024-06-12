public class PokeDex {
    public static Pokemon[] pokedex = new Pokemon[151];

    public static void populate() {
        pokedex[0] = new Pokemon("Dummy", 200, 200, 200, 200, Types.DRAGON, Types.PSYCHIC, null, null, null, null);
        pokedex[1] = new Pokemon("Bulbasaur", 152, 128, 128, 106, Types.GRASS, Types.POISON, null, null, null, null);
        pokedex[7] = new Pokemon("Squirtle", 151, 112, 128, 104, Types.WATER, null, null, null, null, null);
        pokedex[4] = new Pokemon("Charmander", 146, 123, 112, 128, Types.FIRE, null, null, null, null, null);
        //Pokemon Pikachu = new Pokemon("Pikachu", 142, 117, 112, 156, Types.ELECTRIC, null, null, null, null, null);
        // Pokemon Venusaur = new Pokemon("Venusaur", 187, 167, 167, 145, Types.GRASS, Types.POISON);
        // Pokemon Charizard = new Pokemon("Charizard", 185, 177, 150, 167, Types.FIRE, Types.FLYING);
        // Pokemon Blastoise = new Pokemon("Blastoise", 186, 150, 172, 143, Types.WATER, null);
    }
    public static void main(String[] args) {
        populate();
    }
}
