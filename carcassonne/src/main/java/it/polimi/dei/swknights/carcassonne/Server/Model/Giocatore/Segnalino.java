package it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;
import java.io.Serializable;

/**
 * This class represent a pawn that the player can use to control streets or
 * cities
 * 
 * @author Edo & Dave
 * 
 */

public class Segnalino implements Serializable
{
	public Segnalino(Color colore)
	{
		this.colore = colore;
	}

	/**
	 * gets the color of the pawn
	 * 
	 * @return
	 */
	public Color getColore()
	{
		return this.colore;
	}

	@Override
	public String toString()
	{
		return "+" + ColoriGioco.getSigla(this.colore);
	}

	private Color	colore;
	
	private static final long	serialVersionUID	= -8789130292379134917L;

}
