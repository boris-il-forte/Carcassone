package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

/**
 * That class represents a spot in a card where nothing is built: the grass
 * @author Edo & Dave
 *
 */

public class Prato extends Elemento
{

	private final Integer punti=0;

	@Override
	int getPunteggio()
	{ 
		return punti;
	}

}