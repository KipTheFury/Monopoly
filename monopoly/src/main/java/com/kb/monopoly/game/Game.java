/**
 * 
 */
package com.kb.monopoly.game;

import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import com.kb.monopoly.board.Board;
import com.kb.monopoly.player.Player;

/**
 * The main game class. Sets up the board and holds the players.
 * 
 * @author kbennett
 * 
 */
public class Game {

    private static final Logger log = Logger.getLogger(Game.class);

    private final ArrayList<Player> playerList = new ArrayList<Player>();
    private final Board board;

    private final Random dice = new Random(System.nanoTime());

    /**
     * Constructor - Set up the board.
     */
    public Game() {

        this.board = new Board();

    }

    /**
     * Add a player to the game.
     * 
     * @param player
     */
    public void addPlayer(Player player) {

        log.info("Adding Player[" + player + "] to the game");

        playerList.add(player);

    }

    /**
     * Get a list of the players in the current game.
     * 
     * @return - ArrayList of players.
     */
    public ArrayList<Player> getPlayerList() {

        return playerList;
    }

    /**
     * Get the Board for the current game.
     * 
     * @return - The Board.
     */
    public Object getBoard() {

        return board;
    }

    /**
     * Roll a 6 sided dice and return the result.
     * 
     * @return - random result of a 6-sided Dice
     */
    public int rollDie() {

        log.info("Rolling Die...");

        int roll = dice.nextInt(5) + 1;

        log.info("[" + roll + "]");

        return roll;
    }

    /**
     * Start a Game
     */
    public void startGame() {

        if (playerList.size() >= 2 && playerList.size() <= 4) {
            log.info("===== Starting Game =====");
        } else {
            throw new IllegalStateException("Not enough players to start a Game");
        }
    }

}
