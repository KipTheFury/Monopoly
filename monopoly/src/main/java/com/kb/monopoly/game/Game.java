/**
 * 
 */
package com.kb.monopoly.game;

import java.util.ArrayList;

import com.kb.monopoly.board.Board;
import com.kb.monopoly.player.Player;

/**
 * @author kbennett
 * 
 */
public class Game {

    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Board board;

    public Game() {

        this.board = new Board();

    }

    public void addPlayer(Player player) {

        playerList.add(player);

    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Object getBoard() {

        return board;
    }

}
