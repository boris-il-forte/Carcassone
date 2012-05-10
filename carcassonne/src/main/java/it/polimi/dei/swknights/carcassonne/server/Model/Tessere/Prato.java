package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;

/**
 * That class represents a spot in a card where nothing is built: the grass
 * @author Edo & Dave
 *
 */

public class Prato extends Elemento
{

	protected Prato()
	{
		super(TipoElemento.prato);
	}

	private final Integer punti=0;

	@Override
	int getPunteggio()
	{ 
		return punti;
	}

	@Override
	Costruzione getCostruzione(Tessera tessera)
	{
		return null;
	}

}