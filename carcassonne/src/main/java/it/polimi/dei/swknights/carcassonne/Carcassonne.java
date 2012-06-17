package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.ModoInizio.Inizio;

/**
 * Carcassonne, a table game in which you must build cities and streets to
 * control them to achieve victory points and beat your friends!
 * 
 * @author dave & edo
 * 
 */
public final class Carcassonne
{
	/**
	 * Main function. calls a dialog that permit user to choose one way to play
	 * the game or to launch a Carcassonne server
	 * 
	 * @param args
	 *            nothing useful...
	 */
	public static void main(String[] args)
	{

		String risposta = "";

		JCarcassonneBegin begin = new JCarcassonneBegin();

		risposta = begin.showDialog();

		Inizio iniziatore = begin.getIniziatore(risposta);

		iniziatore.inizia();

	}

	private Carcassonne()
	{
	}
}
