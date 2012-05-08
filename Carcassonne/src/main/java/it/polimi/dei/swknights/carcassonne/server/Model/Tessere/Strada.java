package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.CostruzioneStrada;

/**
 * That class represents one of the Carcassonne main elements: the Streets
 * @author Edo & Dave
 *
 */

public class Strada extends Elemento
{

	protected Strada()
	{
		super(TipoElemento.strada);
	}

	private final Integer punti=1;

	@Override
	int getPunteggio()
	{
		return punti;
	}

	@Override
	Costruzione getCostruzione(Tessera tessera)
	{
		return new CostruzioneStrada(tessera);
	}

}