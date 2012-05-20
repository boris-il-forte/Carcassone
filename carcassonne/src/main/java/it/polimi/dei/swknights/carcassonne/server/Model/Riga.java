package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represent a single row of the game table, permits to add or get a
 * card from it.
 * 
 * @author edo & dave
 * 
 */
public class Riga
{
	public Riga()
	{
		this.tessere = new HashMap<Integer, Tessera>();
	}

	/**
	 * 
	 * @param coordinataX
	 *            : the x value of the wanted card in the row e.g. addTessera(5,
	 *            validCard) will put a card in (5, r) when r is the row number
	 *            if the (5,r) is free
	 * @param tessera
	 *            :
	 */
	public void addTessera(Integer coordinataX, Tessera tessera)
	{
		this.tessere.put(coordinataX, tessera);
	}

	/**
	 * 
	 * @param coordinataX
	 *            : the x value of the wanted card in the row e.g. addTessera(5,
	 *            validCard) will get the card in (5, r) when r is the row
	 *            number
	 * @return the card in that position in the row
	 * @throws TesseraNonTrovataException
	 */
	public Tessera getTessera(Integer coordinataX) throws TesseraNonTrovataException
	{
		Tessera tessera = this.tessere.get(coordinataX);
		if (tessera != null)
			return tessera;
		else throw new TesseraNonTrovataException(coordinataX);
	}

	private Map<Integer, Tessera>	tessere;

}
