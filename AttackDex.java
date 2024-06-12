import java.util.HashMap;

public class AttackDex {
    public static HashMap<String, Move> attackdex;

    public static void populate() {
        attackdex.put("Tackle", new Move("Tackle", Types.NORMAL, "Phys", 40, 100, 35));
        attackdex.put("Tail Whip", new Move("Tail Whip", Types.NORMAL, "Stat", 0, 100, 30));
        attackdex.put("Water Gun", new Move("Water Gun", Types.WATER, "Spe", 40, 100, 25));
        attackdex.put("Withdraw", new Move("Withdraw", Types.WATER, "Stat", 0, 0, 40));
    }
}
