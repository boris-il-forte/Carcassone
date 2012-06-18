package it.polimi.dei.swknights.carcassonne.Server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

/**
 * Class representing a normal card of the game
 * 
 * @author dave
 * 
 */
public class TesseraNormale extends Tessera implements Cloneable
{
	/**
	 * Default constructor
	 * 
	 * @param lati
	 *            the sides of this card
	 * @param link
	 *            the links of this card
	 */
	public TesseraNormale(Lati lati, Link link)
	{
		super(lati, link);
	}

	/**
	 * String serialization of normal cards. used in socket protocol
	 */
	@Override
	public String toString()
	{
		String stringaLati = this.lati.toString();
		String stringaConnessioni = this.link.toString();
		return stringaLati + stringaConnessioni;
	}

	/**
	 * Clone method
	 */
	@Override
	public Tessera clone()
	{
		try
		{
			return new TesseraNormale(this.lati.clone(), this.link.clone());
		}
		catch (CloneNotSupportedException e)
		{
			throw new AssertionError(e);
		}
	}

	/**
	 * Getter method
	 * 
	 * @param punto
	 *            a cardinal point to query
	 * @return the element at the given cardinal point
	 */
	public Elemento getElementoA(PuntoCardinale punto)
	{
		return this.lati.getTipoElementoInDirezione(punto);
	}

	private static final long	serialVersionUID	= 472894599614894746L;

}
