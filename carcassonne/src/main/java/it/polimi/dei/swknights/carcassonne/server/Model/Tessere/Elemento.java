package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

/**
 * That class represents one Carcassonne element, it can be instanciated as City
 * or Streat
 * 
 * @author Edo & Dave
 * 
 */

public abstract class Elemento
{

	protected Elemento(TipoElemento tipoElemento)
	{
		this.tipoElemento = tipoElemento;
	}
	
	
	abstract int getPunteggio();
	
	abstract Costruzione getCostruzione(Tessera tessera);

	/**
	 * Establish whether the given element is of the same type to the current element,
	 * e.g. two streets are of the same type
	 */
	public boolean stessoTipoElemento(Elemento elemento)
	{
	    if ( this.tipoElemento == elemento.tipoElemento)
	    	return true;
	    else
	    	return false;
	}
    

	/**
	 * remove the controlling pawn on the current element
	 * 
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
	 * 
	 * @param segnalino
	 */
	public void setSegnalino(Segnalino segnalino)
	{
		this.segnalino = segnalino;
	}

	private final TipoElemento tipoElemento;

	private Segnalino segnalino;

}