/*
 * Parent class for all moves
 */
public class Move {
    String name;
    int power, accuracy;
    Types type;

    protected Move (String name, int pw, int acc, Types type) {
        this.name = name;
        this.power = pw;
        this.accuracy = acc;
        this.type = type;
    }
}
