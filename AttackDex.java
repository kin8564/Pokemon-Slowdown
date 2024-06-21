import java.util.HashMap;

public class AttackDex {
    public static HashMap<String, Move> attackdex;

    public static void populate() {
        attackdex = new HashMap<>();
        int[] status; // {buff/debuff, atk, def, spa, spd, spe, acc, eva}

        attackdex.put("Tackle", new Move("Tackle", Types.NORMAL, 1, 40, 100, 35, null));
        attackdex.put("Scratch", new Move("Scratch", Types.NORMAL, 1, 40, 100, 35, null));
        
        status = new int[] {1, -1, 0, 0, 0, 0, 0, 0}; // lower target atk one stage
        attackdex.put("Growl", new Move("Growl", Types.NORMAL, 3, 0, 100, 40, status));
        
        status = new int[] {1, 0, -1, 0, 0, 0, 0, 0}; // lower target def one stage
        attackdex.put("Tail Whip", new Move("Tail Whip", Types.NORMAL, 3, 0, 100, 30, status));
        attackdex.put("Vine Whip", new Move("Vine Whip", Types.GRASS, 1, 45, 100, 25, null));
        attackdex.put("Water Gun", new Move("Water Gun", Types.WATER, 2, 40, 100, 25, null));
        attackdex.put("Ember", new Move("Ember", Types.FIRE, 2, 40, 100, 25, null));
        
        status = new int[] {0, 1, 0, 1, 0, 0, 0, 0}; // raise user atk and spa one stage
        attackdex.put("Growth", new Move("Growth", Types.NORMAL, 3, 0, 0, 20, status));
        
        status = new int[] {0, 0, 1, 0, 0, 0, 0, 0}; // raise user def one stage
        attackdex.put("Withdraw", new Move("Withdraw", Types.WATER, 3, 0, 0, 40, status));
        
        status = new int[] {1, 0, 0, 0, 0, 0, -1, 0}; // lower target acc one stage
        attackdex.put("Smokescreen", new Move("Smokescreen", Types.NORMAL, 3, 0, 100, 20, status));
    }
}
