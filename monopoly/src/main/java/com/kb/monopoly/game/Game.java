/**
 * 
 */
package com.kb.monopoly.game;

import java.util.ArrayList;

import com.kb.monopoly.player.Player;

/**
 * @author kbennett
 * 
 */
public class Game {

    private ArrayList<Player> playerList = new ArrayList<Player>();

    public Game() {

    }

    public void addPlayer(Player player) {

        playerList.add(player);

    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}
