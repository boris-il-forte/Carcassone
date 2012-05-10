package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.CostruzioneCitta;

/**
 * That class represents one of the Carcassonne main elements: the Cities
 * @author Edo & Dave
 *
 */
public class Citta extends Elemento
{

	protected Citta()
	{
		super(TipoElemento.citta);
	}

	public Integer punti=2;

	/**
	 * the amount of score that the card gives to its owner
	 */
	@Override
	int getPunteggio()
	{
		return punti;
	}

	@Override
	Costruzione getCostruzione(Tessera tessera)
	{
		// TODO Auto-generated method stub
		return new CostruzioneCitta(tessera);
	}

}