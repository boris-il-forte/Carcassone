package it.polimi.dei.swknights.carcassonne.Util;

import java.awt.Color;
import java.util.List;

public class PunteggiSegnalini extends Punteggi
{
	@Override
	public List<Color> getVincitoriAttuale()
	{
		List<Color> papabili = super.getVincitoriAttuale();
		if (papabili.size() == 0) { 
			return papabili;

		}
		Color coloreCampione = papabili.get(0);
		if (this.get(coloreCampione) == 0)
		{
			papabili.clear();
		}
		return papabili;
	}
}
