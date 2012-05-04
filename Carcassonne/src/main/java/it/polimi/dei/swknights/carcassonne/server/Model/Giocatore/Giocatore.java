package it.polimi.dei.swknights.carcassonne.server.Model.Giocatore;

import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

import java.awt.Color;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Giocatore
{
	Giocatore(Color colore)
	{
		this.colore = colore;
		this.inizializzaSegnalini();
		
	}

	public Color getColore()
	{
		return this.colore;
	}

	public Segnalino getSegnalino() throws SegnaliniFinitiException
	{
		Segnalino segnalino = this.listaSegnalini.poll();
		if(segnalino!=null) return segnalino;
		else throw new SegnaliniFinitiException();
	}
	
	public void addPunti(int punti)
	{
		this.punti+=punti;
	}
	
	public Integer getPunti()
	{
		return this.punti;
	}
	
	private void inizializzaSegnalini()
	{
		this.listaSegnalini = new ArrayBlockingQueue<Segnalino>(this.maxSegnalini);
		for(int i=0; i<maxSegnalini; i++)
		{
			this.listaSegnalini.add(new Segnalino(this.colore));
		}
	}

	private Color colore;

	private Queue<Segnalino> listaSegnalini;

	private int punti;

	private final Integer maxSegnalini = 7;

}