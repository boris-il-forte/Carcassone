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
		return new TesseraNormale(this.lati.clone(), this.link.clone());
	}

	public Elemento getElementoA(PuntoCardinale punto)
	{
		return this.lati.getTipoElementoInDirezione(punto);
	}

}
