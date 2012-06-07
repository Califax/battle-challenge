package battlechallenge.server;

import java.net.Socket;
import java.util.List;
import java.util.Set;

import battlechallenge.ActionResult;
import battlechallenge.ActionResult.ShotResult;
import battlechallenge.ConnectionLostException;
import battlechallenge.Coordinate;
import battlechallenge.Ship;

/**
 * The Class ServerPlayer.
 */
public class ServerPlayer {

	/** The name. */
	private String name;

	/** The ships. */
	private List<Ship> ships;

	/** The conn. */
	private ClientConnection conn;

	/** The score. */
	private int score;

	/** The action log. */
	private List<ActionResult> actionLog;

	/** The hit log. */
	private Set<String> hitLog;
	
	/** The id. */
	private final int id;
	
	/**
	 * Gets the ships.
	 *
	 * @return the ships
	 */
	public List<Ship> getShips() {
		return ships;
	}
	
	private boolean hasShips = true;

	/**
	 * Instantiates a new server player.
	 *
	 * @param socket the socket
	 * @param id the id
	 */
	public ServerPlayer(Socket socket, int id) {
		this.id = id;
		this.conn = new ClientConnection(socket);
		// TODO: initialize player
		try {
			if (!conn.setupHandshake()) {
				// TODO: handle invalid player
				throw new IllegalArgumentException();
			}
			this.name = conn.setPlayerCredentials();
			if (name == null) {
				// TODO: handle invalid player name
				throw new IllegalArgumentException();
			}
		} catch (ConnectionLostException e) {
			// TODO; Handle lost connection
		}
	}

	/**
	 * Kill the player and socket connection.
	 * 
	 * DO NOT FORGET TO DO THIS!!!
	 */
	public void kill() {
		this.conn.kill();
	}

	/**
	 * Request turn.
	 *
	 * @param ships the ships
	 * @return true, if successful
	 */
	public boolean requestPlaceShips(List<Ship> ships) {
		try {
			this.ships = ships;
			return conn.requestPlaceShips(ships);
		} catch (ConnectionLostException e) {
			// TODO: handle lost connection
		}
		return false;
	}

	/**
	 * Gets the turn.
	 * 
	 * @return the turn
	 */
	public List<Ship> getPlaceShips() {
		try {
			List<Ship> temp = conn.getPlaceShips();
			for(Ship s : temp) {
				// TODO: save new ship info into instance variables
			}
			return ships; // return instance ships for placement verification by game
		} catch (ConnectionLostException e) {
			// TODO: handle lost connection
		}
		return null;
	}

	/**
	 * Request turn.
	 * 
	 * @return true, if successful
	 */
	public boolean requestTurn() {
		try {
			return conn.requestTurn();
		} catch (ConnectionLostException e) {
			// TODO: handle lost connection
		}
		return false;
	}

	/**
	 * Gets the turn.
	 * 
	 * @return the turn
	 */
	public List<Coordinate> getTurn() {
		try {
			return conn.getTurn();
		} catch (ConnectionLostException e) {
			// TODO: handle lost connection
		}
		return null;
	}

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Win.
	 */
	public void win() {
		// TODO: decide how to score a win
		score++;
	}

	
	/**
	 * Checks for ships left.
	 *
	 * @return true, if player has ships left
	 */
	public boolean hasShipsLeft() {
		if (hasShips) {
			for (Ship s : ships) {
				if (!s.isSunken()) {
					return true;
				}
			}
			hasShips = false;
		}
		return hasShips;
	}
	
	/**
	 * Lose.
	 */
	public void lose() {
		// TODO: decide how to score a loss
		score--;
	}
	
	
	/**
	 * Checks if is hit.
	 *
	 * @param c the c
	 * @param damage the damage
	 * @return the action result
	 */
	public ActionResult isHit(Coordinate c, int damage) {
		if (c == null)
			return new ActionResult(c, ShotResult.MISS, -1, id);
		for (Ship s : ships) {
			if (s.isHit(c, damage)) {
				return new ActionResult(c, s.isSunken() ? ShotResult.SUNK : ShotResult.HIT, s.getHealth(), id);
			}
		}
		return new ActionResult(c, ShotResult.MISS, -1, id);
	}
	
	
}
