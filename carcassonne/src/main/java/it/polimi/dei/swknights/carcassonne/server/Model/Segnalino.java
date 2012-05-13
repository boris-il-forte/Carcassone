package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.DecoraColore;

import java.awt.Color;

/**
 * This class represent a pawn that the player can use to control streets or
 * cities
 * 
 * @author Edo & Dave
 * 
 */

public class Segnalino
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
		DecoraColore decoraColore= new DecoraColore(this.colore);
		return "," + decoraColore.toString();
	}
	private Color	colore;

}