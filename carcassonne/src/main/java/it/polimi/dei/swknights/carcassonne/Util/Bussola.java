package it.polimi.dei.swknights.carcassonne.Util;

/**
 * Enum used for links in the cards
 * 
 * @author edoardopasi & dave
 * 
 */
public enum Bussola {
	NS(0), NE(1), NW(2), WE(3), SE(4), SW(5);

	private Bussola(int numero)
	{
		this.numero = numero;
	}

	/**
	 * Return the number corresponding to this direction
	 * 
	 * @return
	 */
	public int toInt()
	{
		return this.numero;
	}

	/**
	 * Return a composite cardinal point e.g. if punto1 is N and punto2 is E, it
	 * returns NE
	 * 
	 * @param punto1
	 *            first Cardinal point to compose
	 * @param punto2
	 *            second Cardinal point to compose
	 * @return the composite cardinal point
	 */
	public static Bussola componi(PuntoCardinale punto1, PuntoCardinale punto2)
	{

		if (punto1.toInt() > punto2.toInt())
		{
			PuntoCardinale tmp = punto1;
			punto1 = punto2;
			punto2 = tmp;
		}
		String stringBussola = punto1.toString() + punto2.toString();
		return Bussola.valueOf(stringBussola);
	}

	/**
	 * the number of possible directions
	 */
	public static final int	NUMERO_DIREZIONI	= 6;

	private int				numero;
}
