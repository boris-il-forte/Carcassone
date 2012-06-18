package it.polimi.dei.swknights.carcassonne.Util;

import java.awt.Color;
import java.util.List;

/**
 * Class used to represent the marker score of a player on the construction
 * 
 * @author dave
 * 
 */
public class PunteggiSegnalini extends Punteggi
{
	/**
	 * Overrides the upper class method, but if no player has markers on the
	 * construction, returns the empty list
	 */
	@Override
	public List<Color> getVincitoriAttuale()
	{
		List<Color> papabili = super.getVincitoriAttuale();
		if (papabili.size() == 0) { return papabili;

		}
		Color coloreCampione = papabili.get(0);
		if (this.get(coloreCampione) == 0)
		{
			papabili.clear();
		}
		return papabili;
	}

	/**
	 * method used to inverse count the number of markers
	 * 
	 * @param coloreSegnalino
	 *            the color we want to modify
	 */
	public void diminuisci(Color coloreSegnalino)
	{
		final int unoInMeno = -1;
		this.modificaPunteggi(coloreSegnalino, unoInMeno);
	}

	private static final long	serialVersionUID	= -2652989721742972154L;
}
