/**
 * 
 */
package com.kb.monopoly.board;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.log4j.Logger;

import com.kb.monopoly.board.space.CardSpace;
import com.kb.monopoly.board.space.CardSpace.CardType;
import com.kb.monopoly.board.space.CornerSpace;
import com.kb.monopoly.board.space.CornerSpace.CornerSpaces;
import com.kb.monopoly.board.space.Space;
import com.kb.monopoly.board.space.TaxSpace;
import com.kb.monopoly.board.space.property.Property;

/**
 * The game board.
 * 
 * Contains:
 * 
 * 40 spaces -
 * 
 * 16 Chance cards 16 Community Cards
 * 
 * 12 Hotels 32 Houses
 * 
 * @author kbennett
 * 
 */
public class Board
{

    private static final Logger LOG = Logger.getLogger(Board.class);

    public static final int GO = 0;
    public static final int JAIL = 10;
    public static final int FREE_PARKING = 20;
    public static final int GO_TO_JAIL = 30;

    public static final int INCOME_TAX = 4;
    public static final int SUPER_TAX = 38;

    public static final int[] CHANCE = new int[] { 7, 22, 36 };
    public static final int[] COMMUNITY_CHEST = new int[] { 2, 17, 33 };

    public static final int MAX_SPACE_INDEX = 39;

    private static final String DEFAULT_FILE_LOCATION = "src/main/resources/DefaultBoard.json";

    private FixedSizeList<Space> spaces;

    private static final int CARD_COUNT = 16;

    private final Collection<String> chanceCards;
    private final Collection<String> communityChestCards;

    private static final int HOUSE_COUNT = 32;
    private static final int HOTEL_COUNT = 12;

    private static final int INCOME_TAX_FEE = 200;
    private static final int SUPER_TAX_FEE = 100;

    private int houses = HOUSE_COUNT;
    private int hotels = HOTEL_COUNT;

    private final BoardLoader boardLoader;
    private final CardLoader cardLoader;

    /**
     * Constructor. Loads the Spaces and Cards
     */
    public Board(final BoardLoader boardLoader)
    {
        this.boardLoader = boardLoader;
        cardLoader = new CardLoader();

        loadSpaces();

        chanceCards = cardLoader.loadChanceCards();
        communityChestCards = cardLoader.loadCommunityChestCards();
    }

    /**
     * Load the Spaces.
     */
    private void loadSpaces()
    {
        LOG.info("Loading Spaces...");

        final Space[] spacesArray = new Space[MAX_SPACE_INDEX + 1];

        spacesArray[GO] = new CornerSpace("Go", CornerSpaces.GO);
        spacesArray[JAIL] = new CornerSpace("Jail", CornerSpaces.JAIL);
        spacesArray[FREE_PARKING] = new CornerSpace("Free Parking", CornerSpaces.FREEPARKING);
        spacesArray[GO_TO_JAIL] = new CornerSpace("Go to Jail", CornerSpaces.GOTOJAIL);

        spacesArray[INCOME_TAX] = new TaxSpace("Income Tax", INCOME_TAX_FEE);
        spacesArray[SUPER_TAX] = new TaxSpace("Super Tax", SUPER_TAX_FEE);

        final Map<Integer, Property> properties = boardLoader.loadProperties(DEFAULT_FILE_LOCATION);

        for (final Entry<Integer, Property> property : properties.entrySet())
        {
            spacesArray[property.getKey()] = property.getValue();
        }

        for (final Integer i : COMMUNITY_CHEST)
        {
            spacesArray[i] = new CardSpace("Community Chest", CardType.CommunityChest);
        }

        for (final Integer i : CHANCE)
        {
            spacesArray[i] = new CardSpace("Chance", CardType.Chance);
        }

        spaces = FixedSizeList.fixedSizeList(Arrays.asList(spacesArray));

        for (final Space s : spaces)
        {
            LOG.info(s);
        }
    }

    /**
     * Get a specific space on the board.
     * 
     * @param index
     *            - the index of the space on the board (0-39)
     * @return - The space at the given index.
     */
    public Space getSpace(final int index)
    {
        return spaces.get(index);
    }

    /**
     * Get a collection of all Spaces on the board.
     * 
     * @return - Collection of all spaces
     */
    public Collection<Space> getSpaces()
    {

        return spaces;
    }

    /**
     * Get all chance Cards.
     * 
     * @return - Collection of all Chance cards.
     */
    public Collection<String> getChanceCards()
    {
        return chanceCards;
    }

    /**
     * Get all Community Chest Cards.
     * 
     * @return - Collection of Community Chest cards.
     */
    public Collection<String> getCommunityChestCards()
    {
        return communityChestCards;
    }

    /**
     * @return the houses
     */
    public int getHouses()
    {
        return houses;
    }

    /**
     * @param houses
     *            the houses to set
     */
    public void setHouses(final int houses)
    {
        this.houses = houses;
    }

    /**
     * @return the hotels
     */
    public int getHotels()
    {
        return hotels;
    }

    /**
     * @param hotels
     *            the hotels to set
     */
    public void setHotels(final int hotels)
    {
        this.hotels = hotels;
    }

}
