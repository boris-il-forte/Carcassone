package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

public class Strada extends Elemento
{

	private final Integer punti=1;

	@Override
	int getPunteggio()
	{
		return punti;
	}

}