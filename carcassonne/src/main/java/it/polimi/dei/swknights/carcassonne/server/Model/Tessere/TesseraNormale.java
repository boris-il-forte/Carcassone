package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

public class TesseraNormale extends Tessera
{

	TesseraNormale(Lati lati, Link link)
	{
		super(lati, link);
	}

	@Override
	public String toString()
	{
		String stringaLati = this.lati.toString();
		String stringaConnessioni = this.link.toString();
		return stringaLati + stringaConnessioni;
	}

}
