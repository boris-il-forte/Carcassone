package it.polimi.dei.swknights.carcassonne.Server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneCitta;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneStrada;

/**
 * Each element of this enum represent one of the possible Buildings, like City
 * or Streat, or the grass that represent an empty space
 * 
 * @author edoardopasi & dave
 * 
 */

public enum Elemento {
	prato("N"), citta("C"), strada("S");

	private String	strig;

	/**
	 * Used to get the Building from the Element
	 * 
	 * @param tessera
	 * @return a Building of the appropriate type
	 * @see CostruzioneCitta
	 * @see CostruzioneStrada
	 */
	public Costruzione getCostruzione(Tessera tessera)
	{
		switch (this)
		{
			case citta:
				return new CostruzioneCitta(tessera);
			case strada:
				return new CostruzioneStrada(tessera);
			default:
				return null;
		}
	}

	/**
	 * 
	 * @param sigla
	 *            the Initial letter of the required element N for nothing =>
	 *            grass S for Streat C for City
	 * @return the type of the Element
	 * @throws IllegalArgumentException
	 *             if a char different from N, S or C is passed
	 */
	public static Elemento getElemento(char sigla)
	{
		switch (sigla)
		{
			case 'N':
				return Elemento.prato;
			case 'S':
				return Elemento.strada;
			case 'C':
				return Elemento.citta;
			default:
				throw new IllegalArgumentException("Non esiste nessun elemento con la sigla  " + sigla);
		}

	}

	/**
	 * returns string serialization of this object. used in protocol string
	 * representing cards
	 */
	@Override
	public String toString()
	{
		return this.strig;
	}

	private Elemento(String string)
	{
		this.strig = string;
	}

}
