package it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni;

import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

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
