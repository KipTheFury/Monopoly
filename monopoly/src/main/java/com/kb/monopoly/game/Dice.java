/**
 * com.kb.monopoly.game.Dice.java
 *
 * Created - 22 Apr 2014
 * Last Updated - 22 Apr 2014
 */
package com.kb.monopoly.game;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * @author Kyle
 * 
 */
public class Dice {

    private static final Logger log = Logger.getLogger(Dice.class);

    private final Random dice = new Random(System.nanoTime());

    /**
     * Roll a 6 sided dice and return the result.
     * 
     * @return - random result of a 6-sided Dice
     */
    public int roll() {

        int roll = dice.nextInt(5) + 1;

        log.info("Roll... [" + roll + "]");

        return roll;
    }
}
