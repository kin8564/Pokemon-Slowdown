/*
 * Parent class for all Pokemon
 */
public class Pokemon {
    private String name;
    protected int healthPoints, attack, defense, speed;
    private Types type1, type2;
    private Move[] moves;
    //private Move move1, move2, move3, move4;

    protected Pokemon (String monster, int hp, int atk, int def, int spe, Types typeA, Types typeB, Move moveA, Move moveB, Move moveC, Move moveD) {
        this.name = monster;
        this.healthPoints = hp;
        this.attack = atk;
        this.defense = def;
        this.speed = spe;
        this.type1 = typeA;
        this.type2 = typeB;
        this.moves[0] = moveA;
        this.moves[1] = moveB;
        this.moves[2] = moveC;
        this.moves[3] = moveD;
    }

    //Get the name of the Pokemon
    public String getName() {
        return this.name;
    }

    //Get the remaining health points of a Pokemon
    public int getHP() {
        return this.healthPoints;
    }

    //Set the remaining health points of a Pokemon
    public void setHP(int x) {
        this.healthPoints = x;
    }

    //Get the first type of a Pokemon
    public Types getTypeA() {
        return this.type1;
    }

    //Get the second type of a Pokemon
    public Types getTypeB() {
        return this.type2;
    }

    //Get the first move of a Pokemon
    public Move getMoveA() {
        return this.moves[0];
    }

    //Get the second move of a Pokemon
    public Move getMoveB() {
        return this.moves[1];
    }

    //Get the third move of a Pokemon
    public Move getMoveC() {
        return this.moves[2];
    }

    //Get the fourth move of a Pokemon
    public Move getMoveD() {
        return this.moves[3];
    }

    //Set the first move of a Pokemon
    public void setMoveA(Move x) {
        this.moves[0] = x;
    }

    //Set the second move of a Pokemon
    public void setMoveB(Move x) {
        this.moves[1] = x;
    }

    //Set the third move of a Pokemon
    public void setMoveC(Move x) {
        this.moves[2] = x;
    }

    //Set the fourth move of a Pokemon
    public void setMoveD(Move x) {
        this.moves[3] = x;
    }

}
