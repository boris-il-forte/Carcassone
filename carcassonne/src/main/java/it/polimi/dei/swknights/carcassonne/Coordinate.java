package it.polimi.dei.swknights.carcassonne;

public class Coordinate
{
	/**
	 * Costruttore per l'oggetto immutabile coordinata
	 * 
	 * @param x
	 *            la coordinata x
	 * @param y
	 *            la coordinata y
	 */
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Integer getX()
	{
		return this.x;
	}

	public Integer getY()
	{
		return this.y;
	}

	/**
	 * Metodo per ritornare le coodinate adiacenti alla seguente
	 * 
	 * @param puntoCardinale
	 *            la direzione della casella adiacente
	 * @return le coordinate della casella
	 */

	public Coordinate getCoordinateA(PuntoCardinale puntoCardinale)
	{
		int incrementoX = 0;
		int incrementoY = 0;
		switch (puntoCardinale)
		{
			case nord:
				incrementoX++;
			case sud:
				incrementoX--;
			case ovest:
				incrementoY--;
			case est:
				incrementoY++;

		}
		return new Coordinate(x + incrementoX, y + incrementoY);
	}

	private Integer	x;

	private Integer	y;
}
