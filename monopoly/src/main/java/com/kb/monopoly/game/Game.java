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

    private static final Logger LOG = Logger.getLogger(Game.class);

    private final ArrayList<Player> playerList = new ArrayList<Player>();
    private final Board board;

    private DiceRoller dice = new DiceRoller();

    private int currentPlayerIndex;
    private int currentTurn = 1;

    private Player currentPlayer;

    /**
     * Constructor - Set up the board.
     */
    public Game() {

        board = new Board();

    }

    /**
     * Add a player to the game.
     * 
     * @param player
     */
    public void addPlayer(final Player player) {

        LOG.info("Adding Player[" + player + "] to the game");

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
     * Start a Game.
     */
    public void startGame() {

        if (playerList.size() >= 2 && playerList.size() <= 4) {
            LOG.info("===== Starting Game =====");

            currentPlayer = rollForFirstTurn();
            currentPlayerIndex = playerList.indexOf(currentPlayer);

            startTurn(currentPlayer);

        } else
            throw new IllegalStateException("Not enough players to start a Game");
    }

    /**
     * Start a new turn for the current player.
     * 
     * @param currentPlayer
     */
    private void startTurn(final Player currentPlayer) {

        LOG.info("----- Starting Turn - " + currentPlayer + " -  Turn " + currentTurn + " -----");
    }

    /**
     * End the current Turn and move to the next Player.
     */
    public void endTurn() {

        LOG.info("----- Ending Turn - " + currentPlayer + " - Turn " + currentTurn + " -----");

        if (++currentPlayerIndex == playerList.size()) {
            currentPlayerIndex = 0;
            currentTurn++;
        }

        currentPlayer = playerList.get(currentPlayerIndex);

        startTurn(currentPlayer);
    }

    /**
     * Each player rolls a dice, return the player who scored the highest to take the first turn.
     * 
     * @return - player with the highest dice roll.
     */
    public Player rollForFirstTurn() {

        int highestRoll = 0;
        Player first = null;

        for (final Player player : playerList) {

            final int roll = rollDie();

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
     * Set the dice to use.
     * 
     * For unit test mocking only.
     * 
     * @param dice
     */
    public void setDice(final DiceRoller dice) {
        this.dice = dice;
    }
}
