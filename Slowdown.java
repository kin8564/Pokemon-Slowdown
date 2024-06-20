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
     * Currently: All levels = 50; Power = 80; 
     * TODO
     * 		phys / spec
     */
    public static void damageCalc (Pokemon attacker, Pokemon defender, Move move) {
		Random rng = new Random();
        double stab = 1.0;
        int randFact = 0;

        int critCalc = rng.nextInt(256);
        if (critCalc < (attacker.getSpe() / 2)) {
            critCalc = 2;
            System.out.println("It's a critical hit!");
        } else {
            critCalc = 1;
        }
        randFact = (rng.nextInt(85, 101) / 100);
        if (move.getType() == attacker.getTypeA() || move.getType() == attacker.getTypeB()) {
            stab = 1.5;
        }

        int baseDamage = (int) ((((22) * move.getPow() * (attacker.getAtk() / defender.getDef())) / 50 + 2) * critCalc * randFact * stab);
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
        } else if (modifier < 1) {
            notVeryEff++;
        }
        damage = (int)(damage * modifier);
        dmgArray = new int[] {damage, superEff, notVeryEff};
        return dmgArray;
    }

	//Change a Pokemon's state depending on the status move used
	//TODO
	public static void statusCalc(Pokemon attacker, Pokemon defenfer, Move move) {

	}

    /*
     * Two Pokemon battle until one's HP drops to 0 
     */
    public static Pokemon battle(Pokemon friend, Pokemon foe) {
        System.out.println(foe.getName() + " appears!");
        Random rng = new Random();
        //Until KO
        while (friend.getHP() >= 1 && foe.getHP() >= 1) {
			Move selected;
            if (friend.getSpe() > foe.getSpe()) {
                //randomly select an available move
                selected = friend.getMove(rng.nextInt(friend.getMovesNum()-1));
                System.out.println(friend.getName() + " uses " + selected.getName() + "!");
				if (selected.getCat().equals("STAT")) {
					statusCalc(friend, foe, selected);
				} else {
					damageCalc(friend, foe, selected);
				}
                
				//enemy attacks next
				selected = friend.getMove(rng.nextInt(foe.getMovesNum()-1));
				System.out.println(foe.getName() + " uses " + selected.getName() + "!");
                if (selected.getCat().equals("STAT")) {
					statusCalc(friend, foe, selected);
				} else {
					damageCalc(friend, foe, selected);
				}
            } else {
                //enemy randomly selects an available move
				selected = friend.getMove(rng.nextInt(foe.getMovesNum()-1));
				System.out.println(foe.getName() + " uses " + selected.getName() + "!");
                if (selected.getCat().equals("STAT")) {
					statusCalc(friend, foe, selected);
				} else {
					damageCalc(friend, foe, selected);
				}
                
				//you attack next
				selected = friend.getMove(rng.nextInt(friend.getMovesNum()-1));
				System.out.println(friend.getName() + " uses " + selected.getName() + "!");
                if (selected.getCat().equals("STAT")) {
					statusCalc(friend, foe, selected);
				} else {
					damageCalc(friend, foe, selected);
				}
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

        Pokemon winner = battle(PokeDex.pokedex[4], PokeDex.pokedex[7]);
        System.out.println(winner.getName() + " wins!");
    }
}
