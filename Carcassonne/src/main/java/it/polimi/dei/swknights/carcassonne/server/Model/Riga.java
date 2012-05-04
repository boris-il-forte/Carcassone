package it.polimi.dei.swknights.carcassonne.server.Model;

import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

import java.util.HashMap;
import java.util.Map;

public class Riga
{
	public Riga()
	{
		this.tessere= new HashMap<Integer, Tessera>();
	}
	
	public void addTessera(Integer coordinataX, Tessera tessera)
	{
		this.tessere.put(coordinataX, tessera);
	}
	
	public Tessera getTessera(Integer coordinataX) throws TesseraNonTrovataException
	{
		Tessera tessera = this.tessere.get(coordinataX);
		if(tessera!=null) return tessera;
		else throw new TesseraNonTrovataException(coordinataX);
	}
	
	private Map<Integer, Tessera> tessere;

}