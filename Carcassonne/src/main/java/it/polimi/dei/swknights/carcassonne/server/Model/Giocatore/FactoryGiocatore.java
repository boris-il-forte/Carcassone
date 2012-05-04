package it.polimi.dei.swknights.carcassonne.server.Model.Giocatore;

import it.polimi.dei.swknights.carcassonne.Exceptions.finitiColoriDisponibiliException;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class FactoryGiocatore
{
	private Queue<Color> codaColoriDisponibili ;
	
	public FactoryGiocatore()
	{
		int i=0;
		Color colori[]=
				new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK};
		codaColoriDisponibili = new ArrayDeque<Color>();
		
		for(i=0; i<colori.length; i++)
			codaColoriDisponibili.add(colori[i]);
		
	}
	
	public Giocatore getGiocatore() throws finitiColoriDisponibiliException
	{
		Color colore;	
	
			colore = codaColoriDisponibili.poll();
			if (colore != null)	
				return new Giocatore(colore);
			else throw new finitiColoriDisponibiliException();
	}
	

}
