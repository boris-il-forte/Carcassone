package it.polimi.dei.swknights.carcassonne;

public class Coordinate
{
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

	private Integer x;
	
	private Integer y;
}
