package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

/**
 * That class represents one Carcassonne element, it can be
 * instanciated as City or Streat 
 * 
 * @author Edo & Dave
 *
 */

public abstract class Elemento
{

	abstract int getPunteggio();
	/**
	 * Establish whether the given object is equivalent to the current element,
	 * NB: we consider equal two elements if they are of the same type e.g. two streets
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass() == this.getClass())
			return true;
		else
			return false;		
	}

	/**
	 * remove the controlling pawn on the current element
	 * @return the removed pawn
	 */
	
	public Segnalino removeSegnalino()
	{
		Segnalino segnalino = this.segnalino;
		this.segnalino = null;
		return segnalino;
	}

	/**
	 * place a pawn on the current element
	 * @param segnalino
	 */
	public void setSegnalino(Segnalino segnalino)
	{
		this.segnalino = segnalino;
	}

	private Segnalino segnalino;

}