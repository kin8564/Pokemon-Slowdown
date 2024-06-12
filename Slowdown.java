import java.util.Random;

/*
 * Pokemon Battle Simulator
 * TODO
 *      Movesets
 *      PokeDex
 *      AttackDex
 *      Type advantages
 *      Review damage calculation
 *      Iterations
 *          Battles run themselves
 *          User input for battles
 *          Plain text UI for battles
 *          Graphical UI for battles
 */
public class Slowdown {
    
    /*
     * Gen V Calculator
     * Damage = ((((((2*Level)/5)+2) * Power * A/D) / 50) + 2) * Critical * random * STAB * Type
     * Currently: All levels = 50; Power = 80; Crit = 1; 
     * TODO
     *      Crits, move selection
     */
    public static void damageCalc (Pokemon attacker, Pokemon defender, Move move) {
        Random rng = new Random();
        int critCalc = 1;
        //rng.nextInt(256);
        // if (critCalc < (attacker.speed / 2)) {
        //     int critCalc = 2;
        //     System.out.println("It's a critical hit!");
        // } else {
        //     int critCalc = 1;
        // }
        int baseDamage = (((22) * move.getPow() * (attacker.attack / defender.defense)) / 50 + 2) * critCalc;
        int[]dmgArray = typeMultiplier(baseDamage, attacker, defender);
        baseDamage = dmgArray[0];
        if (dmgArray[1] == 1) {
            System.out.println("It's super effective!");
        } else if (dmgArray[2] == 1) {
            System.out.println("It's not very effective...");
        } else if (dmgArray[3] == 1) {
            System.out.println("It doesn't affect " + defender.getName() + "...");
        }
        defender.healthPoints -= baseDamage;
    }

    public static int[] typeMultiplier (int damage, Pokemon attacker, Pokemon defender) {
        double modifier = 1.0;
        int superEff = 0;
        int notVeryEff = 0;
        int immune = 0;
        int[] dmgArray;

        if (defender.getTypeA() != null) {
            switch (defender.getTypeA()) {
                case BUG:
                //Weak: Flying, Rock, Fire
                //Resist: Fighting, Ground, Grass
                    break;
                
                case DARK:
                //Weak: BUG, FAIRY, FIGHTING
                //Resist: GHOST, DARK
                //Immune: Psychic
                    break;
                
                case DRAGON:
                //Weak: DRAGON, FAIRY, ICE
                //Resist: FIRE, WATER, GRASS, ELECTRIC
                    break;
    
                case ELECTRIC:
                //Weak: GROUND
                //Resist: FLYING, ELECTRIC, STEEL
                    break;
    
                case FAIRY:
                //Weak: POISON, STEEL
                //Resist: BUG, DARK, FIGHTING, FIGHTING
                //Immune: DRAGON
                    break;
    
                case FIGHTING:
                //Weak: FAIRY, FLYING, PSYCHIC
                //Resist: BUG, DARK, ROCK
                    break;
    
                case FIRE:
                    if (attacker.type1 == Types.GROUND || attacker.type1 == Types.ROCK || attacker.type1 == Types.WATER || 
                        attacker.type2 == Types.GROUND || attacker.type2 == Types.ROCK || attacker.type2 == Types.WATER) {
                        modifier = modifier * 2;
                    }
                    if (attacker.type1 == Types.BUG || attacker.type1 == Types.STEEL || attacker.type1 == Types.FIRE ||
                        attacker.type1 == Types.GRASS || attacker.type1 == Types.ICE || attacker.type1 == Types.FAIRY ||  
                        attacker.type2 == Types.BUG || attacker.type2 == Types.STEEL || attacker.type2 == Types.FIRE ||
                        attacker.type2 == Types.GRASS || attacker.type2 == Types.ICE || attacker.type2 == Types.FAIRY) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FLYING:
                    if (attacker.type1 == Types.ELECTRIC || attacker.type1 == Types.ICE || attacker.type1 == Types.ROCK ||
                        attacker.type2 == Types.ELECTRIC || attacker.type2 == Types.ICE || attacker.type2 == Types.ROCK) {
                        modifier = modifier * 2;
                       }
                    if (attacker.type1 == Types.BUG || attacker.type1 == Types.FIGHTING || attacker.type1 == Types.GRASS || 
                        attacker.type2 == Types.BUG || attacker.type2 == Types.FIGHTING || attacker.type2 == Types.GRASS) {
                        modifier = modifier / 2;
                    }
                    if (attacker.type1 == Types.GROUND || attacker.type2 == Types.GROUND) {
                        modifier = 0;
                    }
                    break;
    
                case GHOST:
                //Weak: DARK, GHOST
                //Resist: BUG, POISON
                //Immune: NORMAL, FIGHTING
                    break;
    
                case GRASS:
                    if (attacker.type1 == Types.BUG || attacker.type1 == Types.FIRE || attacker.type1 == Types.FLYING || attacker.type1 == Types.ICE || attacker.type1 == Types.POISON ||
                        attacker.type2 == Types.BUG || attacker.type2 == Types.FIRE || attacker.type2 == Types.FLYING || attacker.type2 == Types.ICE || attacker.type2 == Types.POISON) {
                        modifier = modifier * 2;
                        }
                    if (attacker.type1 == Types.ELECTRIC || attacker.type1 == Types.GRASS || attacker.type1 == Types.GROUND || attacker.type1 == Types.WATER ||
                        attacker.type2 == Types.ELECTRIC || attacker.type2 == Types.GRASS || attacker.type2 == Types.GROUND || attacker.type2 == Types.WATER) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case GROUND:
                //Weak: WATER, GRASS, ICE
                //Resist: POISON, ROCK
                //Immune: ELECTRIC
                    break;
    
                case ICE:
                //Weak: FIGHTING, ROCK, STEEL, FIRE
                //Resist: ICE
                    break;
    
                case NORMAL:
                //Weak: FIGHTING
                //Resist:
                //Immune: GHOST
                    break;
    
                case POISON:
                //Weak: Ground, Psychic
                //Resist: Fighting, Poison, Bug, Grass, FAIRY
                    break;
    
                case PSYCHIC:
                //Weak: BUG, DARK, GHOST
                //Resist: FIGHTING, PSYCHIC
                    break;
    
                case ROCK:
                //Weak: FIGHTING, GROUND, STEEL, WATER, GRASS
                //Resist: NORMAL, FLYSING, POISON, FIRE
                    break;
    
                case STEEL:
                //Weak: GROUND, FIGHTING, FIRE
                //Resist: NORMAL, FLYING, ROCK, BUG, STEEL, GRASS, PSYCHIC, ICE, FAIRY, DRAGON
                //Immune: POISON
                    break;
            
                case WATER:
                    if (attacker.type1 == Types.ELECTRIC || attacker.type1 == Types.GRASS ||
                        attacker.type2 == Types.ELECTRIC || attacker.type2 == Types.GRASS) {
                        modifier = modifier * 2;
                    }
                    if (attacker.type1 == Types.STEEL || attacker.type1 == Types.FIRE || attacker.type1 == Types.WATER || attacker.type1 == Types.ICE || 
                        attacker.type2 == Types.STEEL || attacker.type2 == Types.FIRE || attacker.type2 == Types.WATER || attacker.type2 == Types.ICE) {
                        modifier = modifier / 2;
                    }
                    break;
            }
        }
        
        if (defender.getTypeB() != null) {
            switch (defender.type2) {
                case BUG:
                    break;
                
                case DARK:
                    break;
                
                case DRAGON:
                    break;
    
                case ELECTRIC:
                    break;
    
                case FAIRY:
                    break;
    
                case FIGHTING:
                    break;
    
                case FIRE:
                    if (attacker.type1 == Types.GROUND || attacker.type1 == Types.ROCK || attacker.type1 == Types.WATER || 
                        attacker.type2 == Types.GROUND || attacker.type2 == Types.ROCK || attacker.type2 == Types.WATER) {
                        modifier = modifier * 2;
                    }
                    if (attacker.type1 == Types.BUG || attacker.type1 == Types.STEEL || attacker.type1 == Types.FIRE ||
                        attacker.type1 == Types.GRASS || attacker.type1 == Types.ICE || attacker.type1 == Types.FAIRY ||  
                        attacker.type2 == Types.BUG || attacker.type2 == Types.STEEL || attacker.type2 == Types.FIRE ||
                        attacker.type2 == Types.GRASS || attacker.type2 == Types.ICE || attacker.type2 == Types.FAIRY) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case FLYING:
                    if (attacker.type1 == Types.ELECTRIC || attacker.type1 == Types.ICE || attacker.type1 == Types.ROCK ||
                        attacker.type2 == Types.ELECTRIC || attacker.type2 == Types.ICE || attacker.type2 == Types.ROCK) {
                        modifier = modifier * 2;
                       }
                    if (attacker.type1 == Types.BUG || attacker.type1 == Types.FIGHTING || attacker.type1 == Types.GRASS || 
                        attacker.type2 == Types.BUG || attacker.type2 == Types.FIGHTING || attacker.type2 == Types.GRASS) {
                        modifier = modifier / 2;
                    }
                    if (attacker.type1 == Types.GROUND || attacker.type2 == Types.GROUND) {
                        immune++;
                    }
                    break;
    
                case GHOST:
                    break;
    
                case GRASS:
                    if (attacker.type1 == Types.BUG || attacker.type1 == Types.FIRE || attacker.type1 == Types.FLYING || attacker.type1 == Types.ICE || attacker.type1 == Types.POISON ||
                        attacker.type2 == Types.BUG || attacker.type2 == Types.FIRE || attacker.type2 == Types.FLYING || attacker.type2 == Types.ICE || attacker.type2 == Types.POISON) {
                        modifier = modifier * 2;
                        }
                    if (attacker.type1 == Types.ELECTRIC || attacker.type1 == Types.GRASS || attacker.type1 == Types.GROUND || attacker.type1 == Types.WATER ||
                        attacker.type2 == Types.ELECTRIC || attacker.type2 == Types.GRASS || attacker.type2 == Types.GROUND || attacker.type2 == Types.WATER) {
                        modifier = modifier / 2;
                    }
                    break;
    
                case GROUND:
                    break;
    
                case ICE:
                    break;
    
                case NORMAL:
                    break;
    
                case POISON:
                    break;
    
                case PSYCHIC:
                    break;
    
                case ROCK:
                    break;
    
                case STEEL:
                    break;
            
                case WATER:
                    if (attacker.type1 == Types.ELECTRIC || attacker.type1 == Types.GRASS ||
                        attacker.type2 == Types.ELECTRIC || attacker.type2 == Types.GRASS) {
                        modifier = modifier * 2;
                    }
                    if (attacker.type1 == Types.STEEL || attacker.type1 == Types.FIRE || attacker.type1 == Types.WATER || attacker.type1 == Types.ICE || 
                        attacker.type2 == Types.STEEL || attacker.type2 == Types.FIRE || attacker.type2 == Types.WATER || attacker.type2 == Types.ICE) {
                        modifier = modifier / 2;
                    }
                    break;
            }
        }
        
        if (immune == 1) {
            modifier = 0;
        } else if (modifier > 1) {
            superEff++;
        } else if (modifier < 1) {
            notVeryEff++;
        }
        damage = (int)(damage * modifier);
        dmgArray = new int[] {damage, superEff, notVeryEff};
        return dmgArray;
    }

    /*
     * A attacks!
     * (Super, not very, missed, crit, etc.)
     * (Fainted, status, etc.)
     * B attacks!
     * (Super, not very missed, crit, etc.)
     * (Fainted, status, etc.)
     * TODO
     *      Turn order, random moves
     * 
     */
    public static Pokemon battle(Pokemon friend, Pokemon foe) {
        System.out.println(foe.getName() + " appears!");
        Random rng = new Random();
        //Until KO
        while (friend.healthPoints >= 1 && foe.healthPoints >= 1) {
            if (friend.speed > foe.speed) {
                //randomly select an available move
                Move selected = friend.getMove(rng.nextInt(3));
                System.out.println(friend.getName() + " uses " + selected.getName() + "!");
                damageCalc(friend, foe, selected);
                System.out.println("The enemy " + foe.getName() + " attacks!");
                damageCalc(foe, friend);
            } else {
                System.out.println("The enemy " + foe.getName() + " attacks!");
                damageCalc(foe, friend);
                System.out.println(friend.getName() + " attacks!");
                damageCalc(friend, foe);
            }
        }
        if (foe.healthPoints < 1) {
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

        Pokemon winner = battle(PokeDex.pokedex[4], PokeDex.pokedex[7]);
        System.out.println(winner.getName() + " wins!");
    }
}
