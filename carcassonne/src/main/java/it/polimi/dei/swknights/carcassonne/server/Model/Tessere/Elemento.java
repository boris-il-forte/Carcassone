package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.CostruzioneCitta;
import it.polimi.dei.swknights.carcassonne.server.Controller.CostruzioneStrada;

public enum Elemento
{
	prato, citta, strada;

	public Costruzione getCostruzione(Tessera tessera)
	{
		switch (this)
		{
			case prato:
				return null;
			case citta:
				return new CostruzioneCitta(tessera);
			case strada:
				return new CostruzioneStrada(tessera);
			default:
				return null;
		}
	}

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

}
