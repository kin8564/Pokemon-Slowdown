import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Pokemon Battle Simulator
 */
public class Slowdown {

    public static HashMap<Integer, Double> statMultiplier;

    public static HashMap<Integer, Double> accMultiplier;

    public static HashMap<Integer, Double> evaMultiplier;

    /**
     * Populates the three stat multiplier hash maps. Attack, Defense, Special Attack, Special Defense, and Speed share the same stage-multipler hash map.
     * Accuracy and evasion have separate hash maps, yet the vaules are similar.
     */
    public static void multiPopulate() {
        statMultiplier = new HashMap<>();
        statMultiplier.put(-6, 2.0/8.0);
        statMultiplier.put(-5, 2.0/7.0);
        statMultiplier.put(-4, 2.0/6.0);
        statMultiplier.put(-3, 2.0/5.0);
        statMultiplier.put(-2, 2.0/4.0);
        statMultiplier.put(-1, 2.0/3.0);
        statMultiplier.put(0, 2.0/2.0);
        statMultiplier.put(1, 3.0/2.0);
        statMultiplier.put(2, 4.0/2.0);
        statMultiplier.put(3, 5.0/2.0);
        statMultiplier.put(4, 6.0/2.0);
        statMultiplier.put(5, 7.0/2.0);
        statMultiplier.put(6, 8.0/2.0);

        accMultiplier = new HashMap<>();
        accMultiplier.put(-6, 3.0/9.0);
        accMultiplier.put(-5, 3.0/8.0);
        accMultiplier.put(-4, 3.0/7.0);
        accMultiplier.put(-3, 3.0/6.0);
        accMultiplier.put(-2, 3.0/5.0);
        accMultiplier.put(-1, 3.0/4.0);
        accMultiplier.put(0, 3.0/3.0);
        accMultiplier.put(1, 4.0/3.0);
        accMultiplier.put(2, 5.0/3.0);
        accMultiplier.put(3, 6.0/3.0);
        accMultiplier.put(4, 7.0/3.0);
        accMultiplier.put(5, 8.0/3.0);
        accMultiplier.put(6, 9.0/3.0);

        evaMultiplier = new HashMap<>();
        evaMultiplier.put(6, 3.0/9.0);
        evaMultiplier.put(5, 3.0/8.0);
        evaMultiplier.put(4, 3.0/7.0);
        evaMultiplier.put(3, 3.0/6.0);
        evaMultiplier.put(2, 3.0/5.0);
        evaMultiplier.put(1, 3.0/4.0);
        evaMultiplier.put(0, 3.0/3.0);
        evaMultiplier.put(-1, 4.0/3.0);
        evaMultiplier.put(-2, 5.0/3.0);
        evaMultiplier.put(-3, 6.0/3.0);
        evaMultiplier.put(-4, 7.0/3.0);
        evaMultiplier.put(-5, 8.0/3.0);
        evaMultiplier.put(-6, 9.0/3.0);
    }
    
    /**
     * Gen V Calculator
     * Damage = ((((((2*Level)/5)+2) * Power * A/D) / 50) + 2) * Critical * random * STAB * Type
     * Currently: All levels = 50 
     * @param attacker
     * @param defender
     * @param move
     */
    public static void damageCalc (Pokemon attacker, Pokemon defender, Move move) {
		Random rng = new Random();
        double critCalc = 0;
        double stab = 1.0;
        double randFact = 0;
        double force = 0;
        double object = 0;
        //physical or special attack
        if (move.getCat() == 1) {
            force = attacker.getAtk();
            object = defender.getDef();
        } else if (move.getCat() == 2) {
            force = attacker.getSpa();
            object = defender.getSpd();
        }
        //critical hit
        critCalc = rng.nextInt(256);
        if (critCalc < (attacker.getSpe() / 2)) {
            critCalc = 1.5;
            System.out.println("It's a critical hit!");
        } else {
            critCalc = 1.0;
        }
        //random factor
        randFact = ((rng.nextInt(16)+85) / 100.0);
        //same type attack bonus
        if (move.getType() == attacker.getTypeA() || move.getType() == attacker.getTypeB()) {
            stab = 1.5;
        }

        int baseDamage = (int) ((((22) * move.getPow() * (force / object)) / (50.0 + 2)) * critCalc * randFact * stab);
        int[]dmgArray = typeMultiplier(baseDamage, move, defender);
        baseDamage = dmgArray[0];
        if (dmgArray[1] == 1) {
            System.out.println("It's super effective!");
        } else if (dmgArray[2] == 1) {
            System.out.println("It's not very effective...");
        } else if (dmgArray[3] == 1) {
            System.out.println("It doesn't affect " + defender.getName() + "...");
        }
        defender.setHP(defender.getHP() - baseDamage);
        System.out.println(baseDamage + " damage!");
    }

    /**
     * Multiplies damage based on interactions between Types
     * @param damage base damage to be multiplied
     * @param move Move used by attacking pokemon
     * @param defender Defending pokemon
     * @return int array of calculated damage and flags for effectiveness
     */
    public static int[] typeMultiplier (int damage, Move move, Pokemon defender) {
        double modifier = 1.0;
        int superEff = 0;
        int notVeryEff = 0;
        int noEff = 0;
        int[] dmgArray;

        if (defender.getTypeA() != null) {
            switch (defender.getTypeA()) {
                case BUG:
                    //Weak: Flying, Rock, Fire
                    if (move.getType() == Types.FLYING || move.getType() == Types.ROCK || move.getType() == Types.FIRE) {
                        modifier = modifier * 2;
                    }
                    //Resist: Fighting, Ground, Grass
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.GROUND || move.getType() == Types.GRASS) {
                        modifier = modifier / 2;
                    }
                    break;
                
                case DARK:
                    //Weak: BUG, FAIRY, FIGHTING
                    if (move.getType() == Types.BUG || move.getType() == Types.FAIRY || move.getType() == Types.FIGHTING) {
                        modifier = modifier * 2;
                    }
                    //Resist: GHOST, DARK
                    if (move.getType() == Types.GHOST || move.getType() == Types.DARK) {
                        modifier = modifier / 2;
                    }
                    //Immune: Psychic
                    if (move.getType() == Types.PSYCHIC) {
                        modifier = 0;
                    }
                    break;
                
                case DRAGON:
                    //Weak: DRAGON, FAIRY, ICE
                    if (move.getType() == Types.DRAGON || move.getType() == Types.FAIRY || move.getType() == Types.ICE) {
                        modifier = modifier * 2;
                    }
                    //Resist: FIRE, WATER, GRASS, ELECTRIC
                    if (move.getType() == Types.FIRE || move.getType() == Types.WATER || move.getType() == Types.GRASS || move.getType() == Types.ELECTRIC) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case ELECTRIC:
                    //Weak: GROUND
                    if (move.getType() == Types.GROUND) {
                        modifier = modifier * 2;
                    }
                    //Resist: FLYING, ELECTRIC, STEEL
                    if (move.getType() == Types.FLYING || move.getType() == Types.ELECTRIC || move.getType() == Types.STEEL) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FAIRY:
                    //Weak: POISON, STEEL
                    if (move.getType() == Types.POISON || move.getType() == Types.STEEL) {
                        modifier = modifier * 2;
                    }
                    //Resist: BUG, DARK, FIGHTING
                    if (move.getType() == Types.BUG || move.getType() == Types.DARK || move.getType() == Types.FIGHTING) {
                        modifier = modifier / 2;
                    }
                    //Immune: DRAGON
                    if (move.getType() == Types.DRAGON) {
                        modifier = 0;
                    }
                    break;
    
                case FIGHTING:
                    //Weak: FAIRY, FLYING, PSYCHIC
                    if (move.getType() == Types.FAIRY || move.getType() == Types.FLYING || move.getType() == Types.PSYCHIC) {
                        modifier = modifier * 2;
                    }
                    //Resist: BUG, DARK, ROCK
                    if (move.getType() == Types.BUG || move.getType() == Types.DARK || move.getType() == Types.ROCK) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FIRE:
                    //Weak: Ground, Rock, Water
                    if (move.getType() == Types.GROUND || move.getType() == Types.ROCK || move.getType() == Types.WATER) {
                        modifier = modifier * 2;
                    }
                    //Resist: Bug, Steel, Fire, Grass, Ice, Fairy
                    if (move.getType() == Types.BUG || move.getType() == Types.STEEL || move.getType() == Types.FIRE ||
                        move.getType() == Types.GRASS || move.getType() == Types.ICE || move.getType() == Types.FAIRY) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FLYING:
                    //Weak: Electric, Ice, Rock
                    if (move.getType() == Types.ELECTRIC || move.getType() == Types.ICE || move.getType() == Types.ROCK) {
                        modifier = modifier * 2;
                       }
                    //Resist: Bug, Fighting, Grass
                    if (move.getType() == Types.BUG || move.getType() == Types.FIGHTING || move.getType() == Types.GRASS) {
                        modifier = modifier / 2;
                    }
                    //Immune: Ground
                    if (move.getType() == Types.GROUND) {
                        modifier = 0;
                    }
                    break;
    
                case GHOST:
                    //Weak: DARK, GHOST
                    if (move.getType() == Types.DARK || move.getType() == Types.GHOST) {
                        modifier = modifier * 2;
                    }
                    //Resist: BUG, POISON
                    if (move.getType() == Types.BUG || move.getType() == Types.POISON) {
                        modifier = modifier / 2;
                    }
                    //Immune: NORMAL, FIGHTING
                    if (move.getType() == Types.NORMAL || move.getType() == Types.FIGHTING) {
                        modifier = 0;
                    }
                    break;
    
                case GRASS:
                    //Weak: Bug, Fire, Flying, Ice, Poison
                    if (move.getType() == Types.BUG || move.getType() == Types.FIRE || move.getType() == Types.FLYING ||
                        move.getType() == Types.ICE || move.getType() == Types.POISON) {
                        modifier = modifier * 2;
                        }
                    //Resist: Electric, Grass, Ground, Water
                    if (move.getType() == Types.ELECTRIC || move.getType() == Types.GRASS || move.getType() == Types.GROUND || move.getType() == Types.WATER) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case GROUND:
                    //Weak: WATER, GRASS, ICE
                    if (move.getType() == Types.WATER || move.getType() == Types.GRASS || move.getType() == Types.ICE) {
                        modifier = modifier * 2;
                    }
                    //Resist: POISON, ROCK
                    if (move.getType() == Types.POISON || move.getType() == Types.ROCK) {
                        modifier = modifier / 2;
                    }
                    //Immune: ELECTRIC
                    if (move.getType() == Types.ELECTRIC) {
                        modifier = 0;
                    }
                    break;
    
                case ICE:
                    //Weak: FIGHTING, ROCK, STEEL, FIRE
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.ROCK || move.getType() == Types.STEEL || move.getType() == Types.FIRE) {
                        modifier = modifier * 2;
                    }
                    //Resist: ICE
                    if (move.getType() == Types.ICE) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case NORMAL:
                    //Weak: FIGHTING
                    if (move.getType() == Types.FIGHTING) {
                        modifier = modifier * 2;
                    }
                    //Immune: GHOST
                    if (move.getType() == Types.GHOST) {
                        modifier = 0;
                    }
                    break;
    
                case POISON:
                    //Weak: Ground, Psychic
                    if (move.getType() == Types.GROUND || move.getType() == Types.PSYCHIC) {
                        modifier = modifier * 2;
                    }
                    //Resist: Fighting, Poison, Bug, Grass, FAIRY
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.POISON || move.getType() == Types.BUG ||
                        move.getType() == Types.GRASS || move.getType() == Types.FAIRY) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case PSYCHIC:
                    //Weak: BUG, DARK, GHOST
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.POISON || move.getType() == Types.BUG) {
                        modifier = modifier * 2;
                    }
                    //Resist: FIGHTING, PSYCHIC
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.PSYCHIC) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case ROCK:
                    //Weak: FIGHTING, GROUND, STEEL, WATER, GRASS
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.GROUND || move.getType() == Types.STEEL||
                        move.getType() == Types.GRASS || move.getType() == Types.GRASS) {
                        modifier = modifier * 2;
                    }
                    //Resist: NORMAL, FLYING, POISON, FIRE
                    if (move.getType() == Types.NORMAL || move.getType() == Types.FLYING || move.getType() == Types.POISON || move.getType() == Types.FIRE) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case STEEL:
                    //Weak: GROUND, FIGHTING, FIRE
                    if (move.getType() == Types.GROUND || move.getType() == Types.FIGHTING || move.getType() == Types.FIRE) {
                        modifier = modifier * 2;
                    }
                    //Resist: NORMAL, FLYING, ROCK, BUG, STEEL, GRASS, PSYCHIC, ICE, FAIRY, DRAGON
                    if (move.getType() == Types.NORMAL || move.getType() == Types.FLYING || move.getType() == Types.ROCK || move.getType() == Types.BUG ||
                        move.getType() == Types.STEEL || move.getType() == Types.GRASS || move.getType() == Types.PSYCHIC || move.getType() == Types.ICE ||
                        move.getType() == Types.FAIRY || move.getType() == Types.DRAGON) {
                        modifier = modifier / 2;
                    }
                    //Immune: POISON
                    if (move.getType() == Types.POISON) {
                        modifier = 0;
                    }
                    break;
            
                case WATER:
                    //Weak: Electric, Grass
                    if (move.getType() == Types.ELECTRIC || move.getType() == Types.GRASS) {
                        modifier = modifier * 2;
                    }
                    //Resist: Steel, Fire, Water, Ice
                    if (move.getType() == Types.STEEL || move.getType() == Types.FIRE || move.getType() == Types.WATER || move.getType() == Types.ICE) {
                        modifier = modifier / 2;
                    }
                    break;
            }
        }
        
        if (defender.getTypeB() != null) {
            switch (defender.getTypeB()) {
                case BUG:
                    //Weak: Flying, Rock, Fire
                    if (move.getType() == Types.FLYING || move.getType() == Types.ROCK || move.getType() == Types.FIRE) {
                        modifier = modifier * 2;
                    }
                    //Resist: Fighting, Ground, Grass
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.GROUND || move.getType() == Types.GRASS) {
                        modifier = modifier / 2;
                    }
                    break;
                
                case DARK:
                    //Weak: BUG, FAIRY, FIGHTING
                    if (move.getType() == Types.BUG || move.getType() == Types.FAIRY || move.getType() == Types.FIGHTING) {
                        modifier = modifier * 2;
                    }
                    //Resist: GHOST, DARK
                    if (move.getType() == Types.GHOST || move.getType() == Types.DARK) {
                        modifier = modifier / 2;
                    }
                    //Immune: Psychic
                    if (move.getType() == Types.PSYCHIC) {
                        modifier = 0;
                    }
                    break;
                
                case DRAGON:
                    //Weak: DRAGON, FAIRY, ICE
                    if (move.getType() == Types.DRAGON || move.getType() == Types.FAIRY || move.getType() == Types.ICE) {
                        modifier = modifier * 2;
                    }
                    //Resist: FIRE, WATER, GRASS, ELECTRIC
                    if (move.getType() == Types.FIRE || move.getType() == Types.WATER || move.getType() == Types.GRASS || move.getType() == Types.ELECTRIC) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case ELECTRIC:
                    //Weak: GROUND
                    if (move.getType() == Types.GROUND) {
                        modifier = modifier * 2;
                    }
                    //Resist: FLYING, ELECTRIC, STEEL
                    if (move.getType() == Types.FLYING || move.getType() == Types.ELECTRIC || move.getType() == Types.STEEL) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FAIRY:
                    //Weak: POISON, STEEL
                    if (move.getType() == Types.POISON || move.getType() == Types.STEEL) {
                        modifier = modifier * 2;
                    }
                    //Resist: BUG, DARK, FIGHTING
                    if (move.getType() == Types.BUG || move.getType() == Types.DARK || move.getType() == Types.FIGHTING) {
                        modifier = modifier / 2;
                    }
                    //Immune: DRAGON
                    if (move.getType() == Types.DRAGON) {
                        modifier = 0;
                    }
                    break;
    
                case FIGHTING:
                    //Weak: FAIRY, FLYING, PSYCHIC
                    if (move.getType() == Types.FAIRY || move.getType() == Types.FLYING || move.getType() == Types.PSYCHIC) {
                        modifier = modifier * 2;
                    }
                    //Resist: BUG, DARK, ROCK
                    if (move.getType() == Types.BUG || move.getType() == Types.DARK || move.getType() == Types.ROCK) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FIRE:
                    //Weak: Ground, Rock, Water
                    if (move.getType() == Types.GROUND || move.getType() == Types.ROCK || move.getType() == Types.WATER) {
                        modifier = modifier * 2;
                    }
                    //Resist: Bug, Steel, Fire, Grass, Ice, Fairy
                    if (move.getType() == Types.BUG || move.getType() == Types.STEEL || move.getType() == Types.FIRE ||
                        move.getType() == Types.GRASS || move.getType() == Types.ICE || move.getType() == Types.FAIRY) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FLYING:
                    //Weak: Electric, Ice, Rock
                    if (move.getType() == Types.ELECTRIC || move.getType() == Types.ICE || move.getType() == Types.ROCK) {
                        modifier = modifier * 2;
                       }
                    //Resist: Bug, Fighting, Grass
                    if (move.getType() == Types.BUG || move.getType() == Types.FIGHTING || move.getType() == Types.GRASS) {
                        modifier = modifier / 2;
                    }
                    //Immune: Ground
                    if (move.getType() == Types.GROUND) {
                        modifier = 0;
                    }
                    break;
    
                case GHOST:
                    //Weak: DARK, GHOST
                    if (move.getType() == Types.DARK || move.getType() == Types.GHOST) {
                        modifier = modifier * 2;
                    }
                    //Resist: BUG, POISON
                    if (move.getType() == Types.BUG || move.getType() == Types.POISON) {
                        modifier = modifier / 2;
                    }
                    //Immune: NORMAL, FIGHTING
                    if (move.getType() == Types.NORMAL || move.getType() == Types.FIGHTING) {
                        modifier = 0;
                    }
                    break;
    
                case GRASS:
                    //Weak: Bug, Fire, Flying, Ice, Poison
                    if (move.getType() == Types.BUG || move.getType() == Types.FIRE || move.getType() == Types.FLYING ||
                        move.getType() == Types.ICE || move.getType() == Types.POISON) {
                        modifier = modifier * 2;
                        }
                    //Resist: Electric, Grass, Ground, Water
                    if (move.getType() == Types.ELECTRIC || move.getType() == Types.GRASS || move.getType() == Types.GROUND || move.getType() == Types.WATER) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case GROUND:
                    //Weak: WATER, GRASS, ICE
                    if (move.getType() == Types.WATER || move.getType() == Types.GRASS || move.getType() == Types.ICE) {
                        modifier = modifier * 2;
                    }
                    //Resist: POISON, ROCK
                    if (move.getType() == Types.POISON || move.getType() == Types.ROCK) {
                        modifier = modifier / 2;
                    }
                    //Immune: ELECTRIC
                    if (move.getType() == Types.ELECTRIC) {
                        modifier = 0;
                    }
                    break;
    
                case ICE:
                    //Weak: FIGHTING, ROCK, STEEL, FIRE
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.ROCK || move.getType() == Types.STEEL || move.getType() == Types.FIRE) {
                        modifier = modifier * 2;
                    }
                    //Resist: ICE
                    if (move.getType() == Types.ICE) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case NORMAL:
                    //Weak: FIGHTING
                    if (move.getType() == Types.FIGHTING) {
                        modifier = modifier * 2;
                    }
                    //Immune: GHOST
                    if (move.getType() == Types.GHOST) {
                        modifier = 0;
                    }
                    break;
    
                case POISON:
                    //Weak: Ground, Psychic
                    if (move.getType() == Types.GROUND || move.getType() == Types.PSYCHIC) {
                        modifier = modifier * 2;
                    }
                    //Resist: Fighting, Poison, Bug, Grass, FAIRY
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.POISON || move.getType() == Types.BUG ||
                        move.getType() == Types.GRASS || move.getType() == Types.FAIRY) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case PSYCHIC:
                    //Weak: BUG, DARK, GHOST
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.POISON || move.getType() == Types.BUG) {
                        modifier = modifier * 2;
                    }
                    //Resist: FIGHTING, PSYCHIC
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.PSYCHIC) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case ROCK:
                    //Weak: FIGHTING, GROUND, STEEL, WATER, GRASS
                    if (move.getType() == Types.FIGHTING || move.getType() == Types.GROUND || move.getType() == Types.STEEL||
                        move.getType() == Types.GRASS || move.getType() == Types.GRASS) {
                        modifier = modifier * 2;
                    }
                    //Resist: NORMAL, FLYING, POISON, FIRE
                    if (move.getType() == Types.NORMAL || move.getType() == Types.FLYING || move.getType() == Types.POISON || move.getType() == Types.FIRE) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case STEEL:
                    //Weak: GROUND, FIGHTING, FIRE
                    if (move.getType() == Types.GROUND || move.getType() == Types.FIGHTING || move.getType() == Types.FIRE) {
                        modifier = modifier * 2;
                    }
                    //Resist: NORMAL, FLYING, ROCK, BUG, STEEL, GRASS, PSYCHIC, ICE, FAIRY, DRAGON
                    if (move.getType() == Types.NORMAL || move.getType() == Types.FLYING || move.getType() == Types.ROCK || move.getType() == Types.BUG ||
                        move.getType() == Types.STEEL || move.getType() == Types.GRASS || move.getType() == Types.PSYCHIC || move.getType() == Types.ICE ||
                        move.getType() == Types.FAIRY || move.getType() == Types.DRAGON) {
                        modifier = modifier / 2;
                    }
                    //Immune: POISON
                    if (move.getType() == Types.POISON) {
                        modifier = 0;
                    }
                    break;
            
                case WATER:
                    //Weak: Electric, Grass
                    if (move.getType() == Types.ELECTRIC || move.getType() == Types.GRASS) {
                        modifier = modifier * 2;
                    }
                    //Resist: Steel, Fire, Water, Ice
                    if (move.getType() == Types.STEEL || move.getType() == Types.FIRE || move.getType() == Types.WATER || move.getType() == Types.ICE) {
                        modifier = modifier / 2;
                    }
                    break;
            }
        }
        
        if (modifier > 1) {
            superEff++;
        } else if (modifier == 0) {
            noEff++;
        } else if (modifier < 1) {
            notVeryEff++;
        }
        damage = (int)(damage * modifier);
        dmgArray = new int[] {damage, superEff, notVeryEff, noEff};
        return dmgArray;
    }

	/**
     * Change a Pokemon's state depending on the status move used
     * TODO accuracy and evasion
	 * @param attacker
	 * @param defender
	 * @param move
	 */
	public static void statusCalc(Pokemon attacker, Pokemon defender, Move move) {
        //{buff/debuff, atk, def, spa, spd, spe, acc, eva}
        int[] effect = move.getEff();
        double mult;
        int multStage;
        if (effect[0] == 0) { //if buffing self
            if (effect[1] != 0) { //buff attack
                multStage = effect[1];
                attacker.setAtkStage(multStage);
                multStage = attacker.getAtkStage();
                mult = statMultiplier.get(multStage);
                int newAtk = (int) (mult * attacker.getAtk());
                attacker.setAtk(newAtk);
                System.out.println(attacker.getName() + "'s attack rose!");
            } if (effect[2] != 0) { //buff defence
                multStage = effect[2];
                attacker.setDefStage(multStage);
                multStage = attacker.getDefStage();
                mult = statMultiplier.get(multStage);
                int newDef = (int) (mult * attacker.getDef());
                attacker.setDef(newDef);
                System.out.println(attacker.getName() + "'s defence rose!");
            } if (effect[3] != 0) { //buff special attack
                multStage = effect[3];
                attacker.setSpaStage(multStage);
                multStage = attacker.getSpaStage();
                mult = statMultiplier.get(multStage);
                int newSpa = (int) (mult * attacker.getSpa());
                attacker.setSpa(newSpa);
                System.out.println(attacker.getName() + "'s special attack rose!");
            } if (effect[4] != 0) { //buff special defence
                multStage = effect[4];
                attacker.setSpdStage(multStage);
                multStage = attacker.getSpdStage();
                mult = statMultiplier.get(multStage);
                int newSpd = (int) (mult * attacker.getSpd());
                attacker.setSpd(newSpd);
                System.out.println(attacker.getName() + "'s special defence rose!");
            } if (effect[5] != 0) { //buff speed
                multStage = effect[5];
                attacker.setSpeStage(multStage);
                multStage = attacker.getSpeStage();
                mult = statMultiplier.get(multStage);
                int newSpe = (int) (mult * attacker.getSpe());
                attacker.setSpe(newSpe);
                System.out.println(attacker.getName() + "'s speed rose!");
            } if (effect[6] != 0) { //buff accuracy
                
                System.out.println(attacker.getName() + "'s accuracy rose!");
            } if (effect[7] != 0) { //evasion
                
                System.out.println(attacker.getName() + "'s evasion rose!");
            } 
        } else if (effect[0] == 1) { //debuffing opponent
            if (effect[1] != 0) { //debuff attack
                multStage = effect[1];
                defender.setAtkStage(multStage);
                multStage = defender.getAtkStage();
                mult = statMultiplier.get(multStage);
                int newAtk = (int) (mult * defender.getAtk());
                defender.setAtk(newAtk);
                System.out.println(defender.getName() + "'s attack fell!");
            } if (effect[2] != 0) { //debuff defence
                multStage = effect[2];
                defender.setDefStage(multStage);
                multStage = defender.getDefStage();
                mult = statMultiplier.get(multStage);
                int newDef = (int) (mult * defender.getDef());
                defender.setDef(newDef);
                System.out.println(defender.getName() + "'s defence fell!");
            } if (effect[3] != 0) { //debuff special attack
                multStage = effect[3];
                defender.setSpaStage(multStage);
                multStage = defender.getSpaStage();
                mult = statMultiplier.get(multStage);
                int newSpa = (int) (mult * defender.getSpa());
                defender.setSpa(newSpa);
                System.out.println(defender.getName() + "'s special attack fell!");
            } if (effect[4] != 0) { //debuff special defence
                multStage = effect[4];
                defender.setSpdStage(multStage);
                multStage = defender.getSpdStage();
                mult = statMultiplier.get(multStage);
                int newSpd = (int) (mult * defender.getSpd());
                defender.setSpd(newSpd);
                System.out.println(defender.getName() + "'s special defence fell!");
            } if (effect[5] != 0) { //debuff speed
                multStage = effect[5];
                defender.setSpeStage(multStage);
                multStage = defender.getSpeStage();
                mult = statMultiplier.get(multStage);
                int newSpe = (int) (mult * defender.getSpe());
                defender.setSpe(newSpe);
                System.out.println(defender.getName() + "'s speed fell!");
            } if (effect[6] != 0) { // debuff accuracy

                System.out.println(defender.getName() + "'s accuracy fell!");
            } if (effect[7] != 0) { //debuff evasion

                System.out.println(defender.getName() + "'s evasion fell!");
            }
        }

	}

    /*
     * TODO
     */
    public static void accuracyCheck() {

    }

    /**
     * Battle between two Pokemon
     * @param friend
     * @param foe
     * @return
     */
    public static Pokemon battle(Pokemon friend, Pokemon foe) {
        TimeUnit time = TimeUnit.SECONDS;
        Random rng = new Random();
        System.out.println(foe.getName() + " appears!");
        //Until KO
        while (friend.getHP() >= 1 && foe.getHP() >= 1) {
			Move selected;
            if (friend.getSpe() > foe.getSpe()) {
                //randomly select an available move
                selected = friend.getMove(rng.nextInt(friend.getMovesNum()-1));
                System.out.println(friend.getName() + " uses " + selected.getName() + "!");
                    // try {
                    //     time.sleep((2));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
				if (selected.getCat() == 3) {
					statusCalc(friend, foe, selected);
				} else {
					damageCalc(friend, foe, selected);
				}
                    //Pause after every attack
                    // try {
                    //     time.sleep((3));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
                
                if (foe.getHP() < 1) break; 
				//enemy attacks next
				selected = foe.getMove(rng.nextInt(foe.getMovesNum()-1));
				System.out.println(foe.getName() + " uses " + selected.getName() + "!");
                    // try {
                    //     time.sleep((2));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
                if (selected.getCat() == 3) {
					statusCalc(foe, friend, selected);
				} else {
					damageCalc(foe, friend, selected);
				}
                    //Pause after every attack
                    // try {
                    //     time.sleep((3));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
            } else {
                //enemy randomly selects an available move
				selected = foe.getMove(rng.nextInt(foe.getMovesNum()-1));
				System.out.println(foe.getName() + " uses " + selected.getName() + "!");
                    // try {
                    //     time.sleep((2));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
                if (selected.getCat() == 3) {
					statusCalc(foe, friend, selected);
				} else {
					damageCalc(foe, friend, selected);
				}
                    //Pause after every attack
                    // try {
                    //     time.sleep((3));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
                
                if (friend.getHP() < 1) break;
				//you attack next
				selected = friend.getMove(rng.nextInt(friend.getMovesNum()-1));
				System.out.println(friend.getName() + " uses " + selected.getName() + "!");
                    // try {
                    //     time.sleep((2));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
                if (selected.getCat() == 3) {
					statusCalc(friend, foe, selected);
				} else {
					damageCalc(friend, foe, selected);
				}
                    //Pause after every attack
                    // try {
                    //     time.sleep((3));
                    // } catch (InterruptedException e) {
                    //     System.out.println("G a m e  c r a s h e d");
                    // }
            }
        }
        if (foe.getHP() < 1) {
            System.out.println("The enemy " + foe.getName() + " fainted!");
            return friend;
        } else {
            System.out.println(friend.getName() + " fainted...");
            return foe;
        }

    }

    public static void main(String[] args) {    
        AttackDex.populate();        
        PokeDex.populate();
        multiPopulate();

        //User Charmander vs enemy Squirtle
        Pokemon winner = battle(PokeDex.pokedex[4], PokeDex.pokedex[7]);
        System.out.println(winner.getName() + " wins!");
    }
}
