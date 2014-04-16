# Monopoly

This is my Monopoly game created in Java. It is purely a coding exercise using Test Driven Development and experimenting with Design Patterns.

http://en.wikipedia.org/wiki/Monopoly_(game)

## Basic Requirements

#### Game
  
  * Set up board and spaces
  * Add Players (2-4)
  * Roll for first turn
  * Take player turns until game ends
  * Game ends when only one player is not bankrupt/retired

#### Players

  * Starting balance - £1500
  * Can own Buyable property
  * Can buy houses/hotels (if owns whole street set)
  * Can mortgage property
  * Can Trade with other players
  * Can go bankrupt - out of the game
  * Can hold "Get Out Of Jail Free" Cards (0-2)  
  * Can retire from game

#### Player Turn

  * Before Moving
    * Can houses/hotels
    * Trade
    * Can mortgage properties
    * Get out of Jail (Roll doubles/pay £50/get out of jail free card)

  * Roll Dice and move round Board
    * Take any Actions (Pass go / go to jail / chance card etc) 
    * Buy Property / Auction
    * Pay Rent
    * If doubles roll again, 3 doubles = Jail

  * After Moving
    * Can buy houses/hotels
    * Trade
    * Can mortgage properties

  * End Turn - Next Player
  

#### Board
  
  * Roll 2 6 sided dice
  
  * Ownable Properties
    * 22 Streets
      * 8 Colour sets 
      * 32 Houses + 12 Hotels (finite supply)    
    * 4 Stations
    * 2 Utilities

  * Cards
    * 16 Chance Cards + 3 Chance Spaces
    * 16 Community Chest Cards + 3 Community Chest Spaces
    * Perform Actions from cards (Pay Money, Collect money, Move to Space, Go to Jail)  

  * 2 Tax Spaces
  * GO
  * Jail/Go To Jail
  * Free Parking


# Future Features


AI Opponents
Save Game
GUI - Java FX/Web based
Customisable Board
House Rules
