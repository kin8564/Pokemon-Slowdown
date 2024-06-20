/**
 * Parent class for all Pokemon
 */
public class Pokemon {
    private String name;
    private int healthPoints, attack, defense, specialAttack, specialDefense, speed;
    private Types type1, type2;
    private Move[] moves;
    //private Move move1, move2, move3, move4;

    protected Pokemon (String monster, int hp, int atk, int def, int spa, int spd, int spe, Types typeA, Types typeB) {
        this.name = monster;
        this.healthPoints = hp;
        this.attack = atk;
        this.defense = def;
        this.specialAttack = spa;
        this.specialDefense = spd;
        this.speed = spe;
        this.type1 = typeA;
        this.type2 = typeB;
        this.moves = new Move[4];
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

    public int getAtk() {
        return this.attack;
    }

    public void setAtk(int x) {
        this.attack = x;
    }

    public int getDef() {
        return this.defense;
    }

    public void setDef(int x) {
        this.defense = x;
    }

    public int getSpa() {
        return this.specialAttack;
    }

    public void setSpa(int x) {
        this.specialAttack = x;
    }

    public int getSpd() {
        return this.specialDefense;
    }

    public void setSpd(int x) {
        this.specialDefense = x;
    }

    public int getSpe() {
        return this.speed;
    }

    public void setSpe(int x) {
        this.speed = x;
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

    //Get the selected move of a Pokemon
    public Move getMove(int x) {
        return this.moves[x];
    }

    //Get number of available moves
    public int getMovesNum() {
        return this.moves.length;
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
