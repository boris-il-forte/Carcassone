package it.polimi.dei.swknights.carcassonne.Util;

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
				incrementoY--;
				break;
			case sud:
				incrementoY++;
				break;
			case ovest:
				incrementoX--;
				break;
			case est:
				incrementoX++;
				break;
			default:
				break;
		}
		return new Coordinate(x + incrementoX, y + incrementoY);
	}
	/**
	 * Returns the coordinate near this in the given direction (coordinate)
	 * e.g. if this = 1,1   this.getCoordinateAt ( 0,1)  returns
	 * 1, 2
	 * @param coordinateRelativa
	 * @return
	 */
	public Coordinate getCoordinateA(Coordinate coordinateRelativa)
	{
		return new Coordinate(this.x + coordinateRelativa.x, this.y + coordinateRelativa.y);
	}
    /**
     * return this coordinate, to be intended as a versor; used to navigate
     * from a position to another using that vector
     * @return
     */
	public Coordinate getVersore()
	{
		int x = (this.x != 0) ? (this.x / Math.abs(this.x)) : 0;
		int y = (this.y != 0) ? (this.y / Math.abs(this.y)) : 0;
		return new Coordinate(x, y);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object)
	{
		if (object instanceof Coordinate)
		{
			Coordinate coordinate = (Coordinate) object;
			if (this.x.equals(coordinate.x) && this.y.equals(coordinate.y)) { return true; }
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}

	private Integer	x;

	private Integer	y;
}
