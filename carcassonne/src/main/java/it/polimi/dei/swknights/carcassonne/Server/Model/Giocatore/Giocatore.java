package it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore;

import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;

import java.awt.Color;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * That class stores player data and permits basic operations on him/her
 * 
 * @author Edo & Dave
 * 
 */
public class Giocatore
{
	/**
	 * Default friendly constructor
	 * @param colore
	 */
	Giocatore(Color colore)
	{
		this.colore = colore;
		this.inizializzaSegnalini();
	}

	/**
	 * getter for color
	 * 
	 * @return player color
	 */
	public Color getColore()
	{
		return this.colore;
	}

	/**
	 * get one of the player's pawn and remove it from the available pawn
	 * 
	 * @return
	 * @throws SegnaliniFinitiException
	 */
	public Segnalino getSegnalino() throws SegnaliniFinitiException
	{
		Segnalino segnalino = this.listaSegnalini.poll();
		if (segnalino != null)
		{
			return segnalino;
		}
		else
		{
			throw new SegnaliniFinitiException();
		}
	}

	/**
	 * Adds a marker to the player
	 * @param segnalino
	 */
	public void addSegnalino(Segnalino segnalino)
	{
		this.listaSegnalini.add(segnalino);
	}

	/**
	 * sum scores to current
	 * 
	 * @param punti
	 *            : scores to be added
	 */

	public void addPunti(int punti)
	{
		this.punti += punti;
	}

	/**
	 * Gets the score f the player
	 * @return
	 */
	public Integer getPunti()
	{
		return this.punti;
	}

	/**
	 * set the pawn queque
	 */
	private void inizializzaSegnalini()
	{
		this.listaSegnalini = new ArrayBlockingQueue<Segnalino>(MAX_SEGNALINI);
		for (int i = 0; i < MAX_SEGNALINI; i++)
		{
			this.listaSegnalini.add(new Segnalino(this.colore));
		}
	}

	private Color					colore;

	private Queue<Segnalino>		listaSegnalini;

	private int						punti;

	private static final Integer	MAX_SEGNALINI	= 7;

}
