package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.ModoInizio.Inizio;

class Carcassonne
{
	public static void main(String[] args)
	{

		
		String risposta = "";

		JCarcassonneBegin begin = new JCarcassonneBegin();

		risposta = begin.ShowDialog();

		System.out.println(" risposta = " + risposta);
		Inizio iniziatore = begin.getIniziatore(risposta);

		iniziatore.inizia();

	}
}
