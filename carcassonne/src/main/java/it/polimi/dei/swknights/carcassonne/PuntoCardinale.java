package it.polimi.dei.swknights.carcassonne;

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

	public int toInt()
	{
		return this.numerazione;
	}

	/**
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

	@Override
	public String toString()
	{
		return this.string;
	}

	public static final int	NUMERO_DIREZIONI	= 4;

	private int				numerazione;

	private String			string;
}
