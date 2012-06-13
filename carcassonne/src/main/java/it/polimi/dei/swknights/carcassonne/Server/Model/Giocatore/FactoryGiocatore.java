package it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore;

import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * This class is a simplified version of the main classo of the Factory Pattern:
 * it creates players and gives them appropriate colors.
 * 
 * @author Edo e Dave
 * 
 */
public class FactoryGiocatore
{
	private Queue<Color>	codaColoriDisponibili;

	/**
	 * Instantaces the colors, each of them will be given to each created player
	 */
	
	public FactoryGiocatore()
	{
		List<Color> colori = ColoriGioco.getListaColori();
		this.codaColoriDisponibili = new ArrayDeque<Color>(colori);
	}

	/**
	 * The aim of the Factory: create a player and returns it to the caller
	 * 
	 * @return the creted player
	 * @throws FinitiColoriDisponibiliException
	 *             when all colors are used, the caller may not create more
	 *             players.
	 */

	public Giocatore getGiocatore() throws FinitiColoriDisponibiliException
	{
		Color colore;

		colore = this.codaColoriDisponibili.poll();
		if (colore != null)
		{
			return new Giocatore(colore);
		}
		else
		{
			throw new FinitiColoriDisponibiliException();
		}
	}

}
