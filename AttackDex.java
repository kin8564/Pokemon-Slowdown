import java.util.HashMap;

public class AttackDex {
    public static HashMap<String, Move> attackdex;

    public static void populate() {
        attackdex.put("Tackle", new Move("Tackle", Types.NORMAL, "PHYS", 40, 100, 35));
        attackdex.put("Scratch", new Move("Scratch", Types.NORMAL, "PHYS", 40, 100, 35));
        attackdex.put("Growl", new Move("Growl", Types.NORMAL, "STAT", 0, 100, 40));
        attackdex.put("Tail Whip", new Move("Tail Whip", Types.NORMAL, "STAT", 0, 100, 30));
        attackdex.put("Vine Whip", new Move("Vine Whip", Types.GRASS, "PHYS", 45, 100, 25));
        attackdex.put("Water Gun", new Move("Water Gun", Types.WATER, "SPEC", 40, 100, 25));
        attackdex.put("Ember", new Move("Ember", Types.FIRE, "SPEC", 40, 100, 25));
        attackdex.put("Growth", new Move("Growth", Types.NORMAL, "STAT", 0, 0, 20));
        attackdex.put("Withdraw", new Move("Withdraw", Types.WATER, "STAT", 0, 0, 40));
        attackdex.put("Smokescreen", new Move("Smokescreen", Types.NORMAL, "STAT", 0, 100, 20));
    }
}
