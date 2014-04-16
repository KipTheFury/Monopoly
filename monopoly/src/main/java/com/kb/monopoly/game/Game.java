/**
 * 
 */
package com.kb.monopoly.game;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.kb.monopoly.board.Board;
import com.kb.monopoly.player.Player;

/**
 * @author kbennett
 * 
 */
public class Game {

    private static final Logger log = Logger.getLogger(Game.class);

    private final ArrayList<Player> playerList = new ArrayList<Player>();
    private final Board board;

    public Game() {

        this.board = new Board();

    }

    public void addPlayer(Player player) {

        log.info("Adding Player[" + player + "] to the game");

        playerList.add(player);

    }

    public ArrayList<Player> getPlayerList() {

        return playerList;
    }

    public Object getBoard() {

        return board;
    }

}
