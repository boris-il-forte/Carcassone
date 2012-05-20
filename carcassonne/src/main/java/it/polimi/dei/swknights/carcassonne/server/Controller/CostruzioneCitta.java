package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.awt.Color;

public class CostruzioneCitta extends Costruzione
{
	public CostruzioneCitta(Tessera tessera)
	{
		super(tessera);
	}

	@Override
	public int contaPuntiGiocatore(Color colore)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Costruzione getCopy(Tessera tessera)
	{
		return new CostruzioneCitta(tessera);
	}

}
