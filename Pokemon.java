/**
 * Parent class for all Pokemon
 */
public class Pokemon {
    private String name;
    private int healthPoints, attack, defense, specialAttack, specialDefense, speed, accuracy, evasion;
    private Types type1, type2;
    private Move[] moves; //{move1, move2, move3, move4}
    private int[] statAtk, statDef, statSpa, statSpd, statSpe;
    private double[] statAcc, statEva;
    

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
        this.accuracy = 0;
        this.evasion = 0;
        this.statAtk = new int[] {0, this.attack};
        this.statDef = new int[] {0, this.defense};
        this.statSpa = new int[] {0, this.specialAttack};
        this.statSpd = new int[] {0, this.specialDefense};
        this.statSpe = new int[] {0, this.speed};
        this.statAcc = new double[] {0, 1};
        this.statEva = new double[] {0, 1};
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
        return this.statAtk[1];
    }

    public int getAtkStage() {
        return this.statAtk[0];
    }

    public void setAtk(int x) {
        int stage = this.statAtk[0];
        stage += x;
        // limit check in showdown
        // if (stage > 6) stage = 6;
        // if (stage < -6) stage = -6;
        double mult = Slowdown.statMultiplier.get(stage);
        this.statAtk[1] = (int) (this.attack * mult);
    }

    public int getDef() {
        return this.statDef[1];
    }

    public int getDefStage() {
        return this.statDef[0];
    }

    public void setDef(int x) {
        int stage = this.statDef[0];
        stage += x;
        double mult = Slowdown.statMultiplier.get(stage);
        this.statDef[1] = (int) (this.defense * mult);
    }

    public int getSpa() {
        return this.statSpa[1];
    }

    public int getSpaStage() {
        return this.statSpa[0];
    }

    public void setSpa(int x) {
        int stage = this.statSpa[0];
        stage += x;
        double mult = Slowdown.statMultiplier.get(stage);
        this.statSpa[1] = (int) (this.specialAttack * mult);
    }

    public int getSpd() {
        return this.statSpd[1];
    }

    public int getSpdStage() {
        return this.statSpd[0];
    }

    public void setSpd(int x) {
        int stage = this.statSpd[0];
        stage += x;
        double mult = Slowdown.statMultiplier.get(stage);
        this.statSpd[1] = (int) (this.specialDefense * mult);
    }

    public int getSpe() {
        return this.statSpe[0];
    }

    public int getSpeStage() {
        return this.statSpe[1];
    }

    public void setSpe(int x) {
        int stage = this.statSpe[0];
        stage += x;
        double mult = Slowdown.statMultiplier.get(stage);
        this.statSpe[1] = (int) (this.speed * mult);
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

    public int getAcc() {
        return this.accuracy;
    }

    public void setAcc(int x) {
        this.accuracy = x;
    }

    public void incAcc(int x) {
        if (this.accuracy < 6) this.accuracy += x;
    }

    public void decAcc(int x) {
        if (this.accuracy > -6) this.accuracy -= x;
    }

    public int getEva() {
        return this.evasion;
    }

    public void setEva(int x) {
        this.evasion = x;
    }

    public void incEva(int x) {
        if (this.evasion < 6) this.evasion += x;
    }

    public void decEva(int x) {
        if (this.evasion > 6) this.evasion -= x;
    }
}
