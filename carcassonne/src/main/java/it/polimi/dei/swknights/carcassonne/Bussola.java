package it.polimi.dei.swknights.carcassonne;

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

	public int toInt()
	{
		return numero;
	}
	
	public static Bussola componi(PuntoCardinale punto1, PuntoCardinale punto2)
	{
		
		if(punto1.toInt() < punto2.toInt())
		{
			PuntoCardinale tmp = punto1;
			punto1 = punto2;
			punto2 = tmp;
		}
		String stringBussola = punto1.toString() +punto2.toString();
		return Bussola.valueOf(stringBussola);	
	}

	public static final int	NUMERO_DIREZIONI	= 6;

	private int				numero;
}
