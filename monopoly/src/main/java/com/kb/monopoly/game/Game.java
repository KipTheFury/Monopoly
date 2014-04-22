/**
 * 
 */
package com.kb.monopoly.game;

import java.util.ArrayList;

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

    private Dice dice = new Dice();

    private int currentPlayerIndex = 0;
    private int currentTurn = 1;

    private Player currentPlayer;

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
        return dice.roll();
    }

    /**
     * Start a Game
     */
    public void startGame() {

        if (playerList.size() >= 2 && playerList.size() <= 4) {
            log.info("===== Starting Game =====");

            currentPlayer = rollForFirstTurn();
            currentPlayerIndex = playerList.indexOf(currentPlayer);

            startTurn(currentPlayer);

        } else {
            throw new IllegalStateException("Not enough players to start a Game");
        }
    }

    /**
     * Start a new turn for the current player.
     * 
     * @param currentPlayer
     */
    private void startTurn(Player currentPlayer) {

        log.info("----- Starting Turn - " + currentPlayer + " -  Turn " + currentTurn + " -----");
    }

    /**
     * End the current Turn and move to the next Player
     */
    public void endTurn() {

        log.info("----- Ending Turn - " + currentPlayer + " - Turn " + currentTurn + " -----");

        if (++currentPlayerIndex == playerList.size()) {
            currentPlayerIndex = 0;
            currentTurn++;
        }

        currentPlayer = playerList.get(currentPlayerIndex);

        startTurn(currentPlayer);
    }

    /**
     * @return
     */
    public Player rollForFirstTurn() {

        int highestRoll = 0;
        Player first = null;

        for (Player player : playerList) {

            int roll = rollDie();

            if (roll > highestRoll) {
                highestRoll = roll;
                first = player;
            }
        }

        return first;
    }

    /**
     * @return the currentPlayer.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @return the Current turn number
     */
    public int getCurrentTurn() {

        return currentTurn;
    }

    /**
     * @param mockDice
     */
    public void setDice(Dice dice) {
        this.dice = dice;
    }
}
