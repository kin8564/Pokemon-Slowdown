import java.util.HashMap;

public class AttackDex {
    public static HashMap<String, Move> attackdex;

    public static void populate() {
        attackdex.put("Tackle", new Move("Tackle", Types.NORMAL, 1, 40, 100, 35));
        attackdex.put("Scratch", new Move("Scratch", Types.NORMAL, 1, 40, 100, 35));
        attackdex.put("Growl", new Move("Growl", Types.NORMAL, 3, 0, 100, 40));
        attackdex.put("Tail Whip", new Move("Tail Whip", Types.NORMAL, 3, 0, 100, 30));
        attackdex.put("Vine Whip", new Move("Vine Whip", Types.GRASS, 1, 45, 100, 25));
        attackdex.put("Water Gun", new Move("Water Gun", Types.WATER, 2, 40, 100, 25));
        attackdex.put("Ember", new Move("Ember", Types.FIRE, 2, 40, 100, 25));
        attackdex.put("Growth", new Move("Growth", Types.NORMAL, 3, 0, 0, 20));
        attackdex.put("Withdraw", new Move("Withdraw", Types.WATER, 3, 0, 0, 40));
        attackdex.put("Smokescreen", new Move("Smokescreen", Types.NORMAL, 3, 0, 100, 20));
    }
}
