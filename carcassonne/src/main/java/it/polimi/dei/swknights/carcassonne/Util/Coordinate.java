package it.polimi.dei.swknights.carcassonne.Util;

import java.io.Serializable;

public class Coordinate implements Serializable
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

	/**
	 * getter method
	 * 
	 * @return the x coordinate
	 */
	public Integer getX()
	{
		return this.x;
	}

	/**
	 * Getter method
	 * 
	 * @return the y coordinate
	 */
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
		return new Coordinate(this.x + incrementoX, this.y + incrementoY);
	}

	/**
	 * Returns the coordinate near this in the given direction (coordinate) e.g.
	 * if this = 1,1 this.getCoordinateAt ( 0,1) returns 1, 2
	 * 
	 * @param coordinateRelativa
	 * @return
	 */
	public Coordinate getCoordinateA(Coordinate coordinateRelativa)
	{
		return new Coordinate(this.x + coordinateRelativa.x, this.y + coordinateRelativa.y);
	}

	/**
	 * return this coordinate, to be intended as a versor; used to navigate from
	 * a position to another using that vector
	 * 
	 * @return
	 */
	public Coordinate getVersore()
	{
		int ascissa = (this.x != 0) ? (this.x / Math.abs(this.x)) : 0;
		int ordinata = (this.y != 0) ? (this.y / Math.abs(this.y)) : 0;
		return new Coordinate(ascissa, ordinata);
	}

	/**
	 * Hascode function
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
		result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
		return result;
	}

	/**
	 * equals function. two coordinates are equal if and only if the two
	 * components are equals
	 */
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

	/**
	 * String serialization for coordinates. 
	 */
	@Override
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}

	private Integer				x;

	private Integer				y;

	private static final long	serialVersionUID	= 272884138757347349L;
}
