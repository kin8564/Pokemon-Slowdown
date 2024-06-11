package Java.Pokemon;


/*
 * Parent class for all Pokemon
 */
public class Pokemon {
    String name;
    int healthPoints, attack, defense, speed;
    enum Types {
        FIRE, WATER, GRASS, ELECTRIC, ICE, FIGHTING,
        POISON, GROUND, FLYING, PSYCHIC, BUG, ROCK,
        GHOST, DARK, DRAGON, STEEL, FAIRY, NORMAL
    }
    Types type1;
    Types type2;

    public Pokemon (String monster, int hp, int atk, int def, int spe, Types type1, Types type2) {
        this.name = monster;
        this.healthPoints = hp;
        this.attack = atk;
        this.defense = def;
        this.speed = spe;
        this.type1 = type1;
        this.type2 = type2;
    }

    public static void main(String[] args) {
        // Pokemon Pikachu = new Pokemon("Pikachu", 142, 117, 112, 156, Types.ELECTRIC, null);
        // Pokemon Bulbasaur = new Pokemon("Bulbasaur", 152, 128, 128, 106, Types.GRASS, Types.POISON);
        // Pokemon Charmander = new Pokemon("Charmander", 146, 123, 112, 128, Types.FIRE, null);

        // Pokemon Venusaur = new Pokemon("Venusaur", 187, 167, 167, 145, Types.GRASS, Types.POISON);
        // Pokemon Charizard = new Pokemon("Charizard", 185, 177, 150, 167, Types.FIRE, Types.FLYING);
        // Pokemon Blastoise = new Pokemon("Blastoise", 186, 150, 172, 143, Types.WATER, null);
        
        // Pokemon winner = battle(Charmander, Bulbasaur);
        // System.out.println(winner.name + " wins!");
    }
}
