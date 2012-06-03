package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.modoInizio.Inizio;

class Carcassonne
{
	public static void main(String[] args)
	{

		Debug d1 = new Debug();
		d1.visualizzaImmagineENota("CNCN000000.jpg", "nome file: cncn000000");
		
		String risposta = "";

		JCarcassonneBegin begin = new JCarcassonneBegin();

		risposta = begin.ShowDialog();

		System.out.println(" risposta = " + risposta);
		Inizio iniziatore = begin.getIniziatore(risposta);

		iniziatore.inizia();

	}
}
