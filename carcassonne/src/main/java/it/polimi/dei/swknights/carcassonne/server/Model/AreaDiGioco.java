package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Exceptions.RigaNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the representation of the game table, control basic methods to
 * handle game cards
 * 
 * @author Edo & Dave
 * 
 */

public class AreaDiGioco
{
	public AreaDiGioco()
	{
		this.righe = new HashMap<Integer, Riga>();
	}

	/**
	 * get the card at the specified coordinate
	 * 
	 * @param coordinate
	 *            : the coordinate of the card to get
	 * @return the card at that coordinate
	 * @throws TesseraNonTrovataException
	 *             if the card can't be found
	 */
	public Tessera getTessera(Coordinate coordinate) throws TesseraNonTrovataException
	{
		try
		{
			Riga riga = this.getRiga(coordinate.getY());
			Tessera tessera = riga.getTessera(coordinate.getX());
			return tessera;
		} catch (Exception e)
		{
			throw new TesseraNonTrovataException(coordinate);
		}

	}

	/**
	 * Add a card at the specified coordinate
	 * 
	 * @param coordinate
	 *            : coordinate where place the card
	 * @param tessera
	 *            card to be placed
	 */
	public void addTessera(Coordinate coordinate, Tessera tessera)
	{
		Riga riga;
		try
		{
			riga = getRiga(coordinate.getY());
		} catch (RigaNonTrovataException e)
		{
			riga = new Riga();
			addRiga(coordinate.getY(), riga);
		}
		riga.addTessera(coordinate.getX(), tessera);
	}

	/**
	 * Get a full row of the game grid
	 * 
	 * @param rigaCercata
	 *            : the number of the row to get
	 * @return the specified row
	 * @throws RigaNonTrovataException
	 */
	public Riga getRiga(Integer rigaCercata) throws RigaNonTrovataException
	{
		Riga riga = this.righe.get(rigaCercata);
		if (riga != null)
			return riga;
		else
			throw new RigaNonTrovataException(rigaCercata);
	}

	/**
	 * Add a row in the specified position
	 * 
	 * @param numeroRiga
	 * @param riga
	 */
	private void addRiga(Integer numeroRiga, Riga riga)
	{
		this.righe.put(numeroRiga, riga);
	}

	private Map<Integer, Riga>	righe;

}