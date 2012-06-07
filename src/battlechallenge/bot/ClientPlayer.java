package battlechallenge.bot;

import java.util.List;

import battlechallenge.ActionResult;
import battlechallenge.Coordinate;
import battlechallenge.Ship;

/**
 * The Class ClientPlayer.
 */
public class ClientPlayer {
	
	/** The player name. */
	private final String playerName;
	
	private final int networkID;
	
	/** The map width. */
	private final int mapWidth;
	
	/** The map height. */
	private final int mapHeight;
	
	
	/**
	 * Instantiates a new client player.
	 *
	 * @param playerName the player name
	 * @param mapWidth the map width
	 * @param mapHeight the map height
	 */
	public ClientPlayer(final String playerName, final int mapWidth, final int mapHeight, final int networkID) {
		this.playerName = playerName;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.networkID = networkID;
	}
	/**
	 * This method is called at the beginning of the game to determine
	 * where the player wants to place his ships. For each ship set the
	 * starting position and the direction in which the ship's length will extend. Make sure
	 * ships are not overlapping and are within the defined bounds of the game map.
	 * @param shipList A list of ships with all null attributes
	 * @return the shipList with updated values for the starting position
	 * and direction the ship is facing
	 */
	public List<Ship> placeShips(List<Ship> shipList) {
		return shipList;		
	}
	
	/**
	 * This class will be filled in by the player. All logic regarding in game decisions to be
	 * made by your bot should be put in here. This class will be called every turn until the
	 * end of the game
	 * 
	 * @param myShips List of ships belonging to player
	 * @param oppShips List of ships belonging to opponent
	 * @param myLastTurn List of action results from the last turn
	 * @param oppLastTurn List of actions and their results of your opponent
	 * @return a List of coordinates corresponding to where you wish to fire
	 */
	public List<Coordinate> doTurn(List<Ship> myShips, List<Ship> oppShips, List<ActionResult> myLastTurn,
			List<ActionResult> oppLastTurn) {
		return null;
	}
}
