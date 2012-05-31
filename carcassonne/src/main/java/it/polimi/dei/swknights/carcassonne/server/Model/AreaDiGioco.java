package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
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
		this.mappa = new HashMap<Coordinate, Tessera>();
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
		Tessera tessera = this.mappa.get(coordinate);
		if(tessera != null)
		{
			return tessera;
		}
		else
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
	 * @throws MossaNonValidaException 
	 */
	public void addTessera(Coordinate coordinate, Tessera tessera) throws MossaNonValidaException
	{
		if (this.mappa.get(coordinate) == null)
		{
				this.mappa.put(coordinate, tessera);
		}
		else
		{
			throw new MossaNonValidaException("casella già occupata");
		}
	}

	private Map<Coordinate, Tessera>	mappa;

}
