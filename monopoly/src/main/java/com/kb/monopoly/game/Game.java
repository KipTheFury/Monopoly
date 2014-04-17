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

}
