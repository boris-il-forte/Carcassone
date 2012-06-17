package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Client.View.Vicinato;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Fasi.FaseTurno;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
import java.io.PrintWriter;

/**
 * This class is a component of the CLI that handles the basic communication
 * with the user
 * 
 * @author edoardopasi
 * 
 */
public class AvvisiUser
{
	/**
	 * Default constructor
	 * 
	 * @param out
	 *            the PrintWriter object to write into
	 */
	public AvvisiUser(PrintWriter out)
	{
		this.out = out;
	}

	/**
	 * Gives the user a little description of the requested input, specifying
	 * the sintax of it
	 */
	public void chiediComando()
	{
		this.notificaVideo("Tocca a te giocatore " + ColoriGioco.getName(this.coloreGiocatore) + "!");
		this.notificaVideo("Inserisci comando");
		this.notificaVideo(this.faseTurno.toString());

	}

	/**
	 * set the color of the current player
	 * 
	 * @param colore
	 */
	public void setColore(Color colore)
	{
		this.coloreGiocatore = colore;

	}

	/**
	 * Print a message if the move is invalid
	 */
	public void notificaMossaNonValida()
	{
		this.notificaVideo("Mossa non valida!");
	}

	/**
	 * Show in the CLI the current card as an indipendent card to be placed or
	 * rotated
	 */
	public void mostraTesseraCorrente()
	{

		// DatiMappa datiDeg = new DatiMappa(min, max)
		Stampante stampanteTessera = new Stampante();
		Vicinato vicinatoVuoto = new Vicinato(false);
		stampanteTessera.addTessera(new Coordinate(0, 0), this.tesseraCorrente.toCliString(), vicinatoVuoto);

		this.out.print(stampanteTessera);
		this.out.flush();

	}

	/**
	 * set the current phase of the turn
	 * 
	 * @param faseTurno
	 */
	public void setPhase(FaseTurno faseTurno)
	{
		this.faseTurno = faseTurno;
	}

	/**
	 * Set the current tile
	 * 
	 * @param tessera
	 */
	public void setTesseraCorrente(AdapterTessera tessera)
	{
		this.tesseraCorrente = tessera;
	}

	/**
	 * notify the end of the game
	 */
	public void notificaFinePartita()
	{
		this.notificaVideo("Fine Partita!");
	}

	/**
	 * shows the current score
	 * 
	 * @param punteggio
	 *            the score to be show
	 */
	public void notificaPunteggi(Punteggi punteggio)
	{
		if (punteggio == null)
		{
			throw new IllegalArgumentException("punteggio non dovrebbe essere null!");
		}
		else
		{
			this.out.println(punteggio.toString());
		}

	}

	/*
	 * public void notificaPunteggi(Punteggi punteggio) { if (punteggio == null)
	 * { throw new
	 * IllegalArgumentException("punteggio non dovrebbe essere null!"); } else {
	 * this.out.println(punteggio.toString()); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @param message
	 */
	private void notificaVideo(String message)
	{
		this.out.println(message);
		this.out.flush();

	}

	private AdapterTessera	tesseraCorrente;

	private PrintWriter		out;

	private FaseTurno		faseTurno;

	private Color			coloreGiocatore;

}
