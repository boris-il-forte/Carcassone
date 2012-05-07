package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

public class Citta extends Elemento
{

	public Integer punti=2;

	@Override
	int getPunteggio()
	{
		return punti;
	}

}