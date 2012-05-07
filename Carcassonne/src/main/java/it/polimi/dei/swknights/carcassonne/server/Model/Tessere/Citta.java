package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;
/**
 * That class represents one of the Carcassonne main elements: the Cities
 * @author Edo & Dave
 *
 */
public class Citta extends Elemento
{

	public Integer punti=2;

	/**
	 * the amount of score that the card gives to its owner
	 */
	@Override
	int getPunteggio()
	{
		return punti;
	}

}