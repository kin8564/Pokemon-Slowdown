package Java.Pokemon;

import java.util.Random;
import Java.Pokemon.Pokemon.Types;;

/*
 * Pokemon Battle Simulator
 * TODO
 *      Type advantages
 *      Movesets
 *      Review damage calculation
 *      Iterations
 *          Battles run themselves
 *          User input for battles
 *          Plain text UI for battles
 *          Graphical UI for battles
 */
public class Slowdown {
    
    /*
     * Gen I Calculator
     * Damage = ((((((2*Level*Critical)/5)+2) * Power * A/D) / 50) + 2) * Modifier
     * Currently: All levels = 50; Power = 80
     */
    public static void damageCalc (Pokemon attacker, Pokemon defender) {
        Random rn = new Random();
        int critCalc = 1;
        //rn.nextInt(256);
        // if (critCalc < (attacker.speed / 2)) {
        //     critCalc = 2;
        //     System.out.println("It's a critical hit!");
        // } else {
        //     critCalc = 1;
        // }
        int baseDamage = (((100 * critCalc) * 80 * (attacker.attack / defender.defense)) / 50 + 2);
        int[]dmgArray = typeMultiplier(baseDamage, attacker, defender);
        baseDamage = dmgArray[0];
        if (dmgArray[1] == 1) {
            System.out.println("It's super effective!");
        } else if (dmgArray[2] == 1) {
            System.out.println("It's not very effective...");
        } else if (dmgArray[3] == 1) {
            System.out.println("It doesn't affect " + defender.name + "...");
        }
        defender.healthPoints -= baseDamage;
    }

    public static int[] typeMultiplier (int damage, Pokemon attacker, Pokemon defender) {
        double modifier = 1.0;
        int superEff = 0;
        int notVeryEff = 0;
        int immune = 0;
        int[] dmgArray;

        if (defender.type1 != null) {
            switch (defender.type1) {
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
                        modifier = 0;
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
                //Weak: Ground, Psychic
                //Resist: Fighting, Poison, Bug, Grass
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
        
        if (defender.type2 != null) {
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
     * 
     */
    public static Pokemon battle(Pokemon friend, Pokemon foe) {
        System.out.println(foe.name + " appears!");
        while (friend.healthPoints >= 1 && foe.healthPoints >= 1) {
            if (friend.speed > foe.speed) {
                System.out.println(friend.name + " attacks!");
                damageCalc(friend, foe);
                System.out.println("The enemy " + foe.name + " attacks!");
                damageCalc(foe, friend);
            } else {
                System.out.println("The enemy " + foe.name + " attacks!");
                damageCalc(foe, friend);
                System.out.println(friend.name + " attacks!");
                damageCalc(friend, foe);
            }
        }
        if (foe.healthPoints < 1) {
            System.out.println("The enemy " + foe.name + " fainted!");
            return friend;
        } else {
            System.out.println(friend.name + " fainted...");
            return foe;
        }

    }

    public static void main(String[] args) {
        Pokemon Pikachu = new Pokemon("Pikachu", 142, 117, 112, 156, Types.ELECTRIC, null);
        Pokemon Bulbasaur = new Pokemon("Bulbasaur", 152, 128, 128, 106, Types.GRASS, Types.POISON);
        Pokemon Charmander = new Pokemon("Charmander", 146, 123, 112, 128, Types.FIRE, null);

        Pokemon Venusaur = new Pokemon("Venusaur", 187, 167, 167, 145, Types.GRASS, Types.POISON);
        Pokemon Charizard = new Pokemon("Charizard", 185, 177, 150, 167, Types.FIRE, Types.FLYING);
        Pokemon Blastoise = new Pokemon("Blastoise", 186, 150, 172, 143, Types.WATER, null);
        
        Pokemon winner = battle(Charmander, Bulbasaur);
        System.out.println(winner.name + " wins!");
    }
}
