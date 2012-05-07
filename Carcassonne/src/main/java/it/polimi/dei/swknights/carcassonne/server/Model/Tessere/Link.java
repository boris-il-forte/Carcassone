package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

public class Link
{

	public void ruota()
	{
		boolean tempSN = this.SN;
		this.SN = this.OE;
		this.OE = tempSN;
		boolean tempSE = this.SE;
		this.SE = this.NE;
		this.NE = this.NO;
		this.NO = this.SO;
		this.SO = tempSE;

	}

	public Boolean SN;

	public Boolean SE;

	public Boolean OE;

	public Boolean SO;

	public Boolean NE;

	public Boolean NO;

	public Tessera myTessera;

}