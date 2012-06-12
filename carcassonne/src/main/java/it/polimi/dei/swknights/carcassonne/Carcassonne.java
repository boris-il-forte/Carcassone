package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.ModoInizio.Inizio;

public final class Carcassonne
{
	public static void main(String[] args)
	{

		String risposta = "";

		JCarcassonneBegin begin = new JCarcassonneBegin();

		risposta = begin.showDialog();

		Debug.print(" risposta = " + risposta);
		Inizio iniziatore = begin.getIniziatore(risposta);

		iniziatore.inizia();

	}
	
	private Carcassonne(){}
}
