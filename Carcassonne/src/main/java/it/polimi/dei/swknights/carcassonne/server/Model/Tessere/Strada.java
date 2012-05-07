package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;
/**
 * That class represents one of the Carcassonne main elements: the Streets
 * @author Edo & Dave
 *
 */

public class Strada extends Elemento
{

	private final Integer punti=1;

	@Override
	int getPunteggio()
	{
		return punti;
	}

}