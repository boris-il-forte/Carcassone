package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.AdapterColore;

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
		AdapterColore decoraColore = new AdapterColore(this.colore);
		return "," + decoraColore.toString();
	}

	private Color	colore;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colore == null) ? 0 : colore.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof Segnalino)) { return false; }
		Segnalino other = (Segnalino) obj;
		if (colore == null)
		{
			if (other.colore != null) { return false; }
		}
		else if (!colore.equals(other.colore)) { return false; }
		return true;
	}

}
