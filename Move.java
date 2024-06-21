/**
 * Parent class for all moves
 * Currently: Category and PP are non-functional
 */
public class Move {
    private final String name;
    private final Types type;
    private final int category;
    int power, accuracy, pp;
    private int[] statEffect; //{buff/debuff, atk, def, spa, spd, spe, acc, eva}

    protected Move (String name, Types type, int cat, int pw, int acc, int points, int[] statArray) {
        this.name = name;
        this.type = type;
        this.category = cat;
        this.power = pw;
        this.accuracy = acc;
        this.pp = points;
        this.statEffect = new int[8];
        this.statEffect = statArray;
        
    }

    public String getName() {
        return this.name;
    }

    public Types getType() {
        return this.type;
    }

    public int getCat() {
        return this.category;
    }
    
    public int getPow() {
        return this.power;
    }

    public int getAcc() {
        return this.accuracy;
    }

    public int getPP() {
        return this.pp;
    }

    public int[] getEff() {
        return this.statEffect;
    }
}
