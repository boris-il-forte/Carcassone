package it.polimi.dei.swknights.carcassonne.Server.Model;

import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.NullCardException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
		this.GPS = new HashMap<Tessera, Coordinate>();
		this.coordinateVuote = new HashSet<Coordinate>();
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
		if (tessera != null)
		{
			return tessera;
		}
		else
		{
			throw new TesseraNonTrovataException(coordinate);
		}

	}

	public Coordinate getCoordinateTessera(Tessera tessera)
	{
		return this.GPS.get(tessera);
	}

	public Set<Coordinate> getSetCoordinateVuote()
	{
		return this.coordinateVuote;
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
		if (tessera == null)
		{
			throw new NullCardException("null card was passed");
		}
		else
		{
			if (this.mappa.get(coordinate) == null)
			{
				this.mappa.put(coordinate, tessera);
				this.GPS.put(tessera, coordinate);
				this.addCoordinateVuote(coordinate);
				this.coordinateVuote.remove(coordinate);
			}
			else
			{
				throw new MossaNonValidaException("casella già occupata");
			}
		}
	}

	private void addCoordinateVuote(Coordinate coordinate)
	{
		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			Coordinate coordinateDaTestare = coordinate.getCoordinateA(punto);
			try
			{
				this.getTessera(coordinateDaTestare);
			}
			catch (TesseraNonTrovataException e)
			{
				this.coordinateVuote.add(coordinateDaTestare);
			}
		}

	}

	private Map<Coordinate, Tessera>	mappa;

	private Map<Tessera, Coordinate>	GPS;

	private Set<Coordinate>				coordinateVuote;

}
