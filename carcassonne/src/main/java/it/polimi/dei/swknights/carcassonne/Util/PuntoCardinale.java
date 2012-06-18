package it.polimi.dei.swknights.carcassonne.Util;

/**
 * this enum gives a simple implementation of the Cardinal Points, it also gives
 * the opposite Point
 * 
 * @author edoardopasi & dave
 * 
 */

public enum PuntoCardinale {
	nord(0, "N"), sud(1, "S"), ovest(2, "W"), est(3, "E");
	private PuntoCardinale(int numerazione, String string)
	{
		this.numerazione = numerazione;
		this.string = string;
	}

	/**
	 * Getter method
	 * 
	 * @return the ordinal number for this cardinal point
	 */
	public int toInt()
	{
		return this.numerazione;
	}

	/**
	 * Getter method
	 * 
	 * @return the Cardinal Point in the oppposite direction
	 */
	public PuntoCardinale opposto()
	{
		switch (this)
		{
			case nord:
				return sud;
			case sud:
				return nord;
			case ovest:
				return est;
			case est:
				return ovest;
			default:
				return null;
		}
	}

	/**
	 * String serialization of this cardinal point, used in socket protocol
	 */
	@Override
	public String toString()
	{
		return this.string;
	}

	/**
	 * The number of cardinal points
	 */
	public static final int	NUMERO_DIREZIONI	= 4;

	private int				numerazione;

	private String			string;
}
