package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Exceptions.RigaNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.util.HashMap;
import java.util.Map;

public class AreaDiGioco
{
	public AreaDiGioco()
	{
		this.righe = new HashMap<Integer, Riga>();
	}
	
	public Tessera getTessera(Coordinate coordinate) throws TesseraNonTrovataException
	{
		try
		{
			Riga riga = this.getRiga(coordinate.getY());
			Tessera tessera = riga.getTessera(coordinate.getX());
			return tessera;
		}
		catch(Exception e)
		{
			throw new TesseraNonTrovataException(coordinate);
		}
		
	}
	
	public void addTessera(Coordinate coordinate, Tessera tessera)
	{
		Riga riga;
		try
		{
			riga=getRiga(coordinate.getY());
		}
		catch (RigaNonTrovataException e)
		{
			riga = new Riga();
			addRiga(coordinate.getY(), riga);
		}
		riga.addTessera(coordinate.getX(), tessera);
	}

	public Riga getRiga(Integer rigaCercata) throws RigaNonTrovataException
	{
		Riga riga = this.righe.get(rigaCercata);
		if(riga!=null)
			return riga;
		else throw new RigaNonTrovataException(rigaCercata);
	}
	
	private void addRiga(Integer numeroRiga, Riga riga)
	{
		this.righe.put(numeroRiga, riga);
	}
	
	private Map<Integer, Riga> righe;

}