package it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni;

import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;

public class CostruzioneCitta extends Costruzione
{
	public CostruzioneCitta(Tessera tessera)
	{
		super(tessera);
	}

	@Override
	public String toString()
	{
		return "Città" + super.toString();
	}

	@Override
	protected int getPuntiCostruzione(boolean costruzioneCompletata)
	{
		final int moltiplicatoreCitta = costruzioneCompletata ? 2 : 1;
		return this.getSize() * moltiplicatoreCitta;
	}

}
