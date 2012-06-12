package it.polimi.dei.swknights.carcassonne.Server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

public class TesseraNormale extends Tessera
{
	public TesseraNormale(Lati lati, Link link)
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

	@Override
	public Tessera clone()
	{
		try
		{
			return new TesseraNormale(this.lati.clone(), this.link.clone());
		}
		catch (CloneNotSupportedException e)
		{
			throw new AssertionError();
		}
	}

	public Elemento getElementoA(PuntoCardinale punto)
	{
		return this.lati.getTipoElementoInDirezione(punto);
	}
	
	private static final long	serialVersionUID	= 472894599614894746L;

}
