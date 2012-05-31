package it.polimi.dei.swknights.carcassonne.server.Controller.Costruzioni;

import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class CostruzioneStrada extends Costruzione
{
	public CostruzioneStrada(Tessera tessera)
	{
		super(tessera);
	}

	@Override 
	public String toString()
	{
		return "Strada" +super.toString();
	}


	@Override
	protected int getPuntiCostruzione(boolean costruzioneCompletata)
	{
		return this.getSize();
	}

}
