package it.polimi.dei.swknights.carcassonne.server.Controller;

import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

class Confine
{
	public Confine(Tessera tessera, Elemento lato)
	{
		this.tessera = tessera;
		this.lato = lato;
	}

	@Override
	public boolean equals(Object object)
	{
		boolean isEqual= false;
		if (object instanceof Confine)
		{
			Confine confine = (Confine) object; 
			if(this.lato==confine.lato && this.tessera==confine.tessera)isEqual= true;
		}
		return isEqual;
	}

	private final Tessera	tessera;

	private final Elemento	lato;

}
