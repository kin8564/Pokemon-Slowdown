/*
 * Parent class for all moves
 * Currently: Category and PP are non-functional
 */
public class Move {
    final String name, category;
    final Types type;
    int power, accuracy, pp;

    protected Move (String name, Types type, String cat, int pw, int acc, int points) {
        this.name = name;
        this.type = type;
        this.category = cat;
        this.power = pw;
        this.accuracy = acc;
        this.pp = points;
    }

    public String getName() {
        return this.name;
    }

    public Types getType() {
        return this.type;
    }

    public String getCat() {
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
}
