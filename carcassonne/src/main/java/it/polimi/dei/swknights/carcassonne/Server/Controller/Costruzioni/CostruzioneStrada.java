package it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni;

import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

public class CostruzioneStrada extends Costruzione
{
	/**
	 * Default constructor
	 * @param tessera the first tile of this construction
	 */
	public CostruzioneStrada(Tessera tessera)
	{
		super(tessera);
	}
	
	/**
	 * Specific string serialization
	 */

	@Override
	public String toString()
	{
		return "Strada" + super.toString();
	}

	@Override
	protected int getPuntiCostruzione(boolean costruzioneCompletata)
	{
		return this.getSize();
	}

}
