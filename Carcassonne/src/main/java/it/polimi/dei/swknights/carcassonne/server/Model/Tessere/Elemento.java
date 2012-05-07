package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;
import java.util.List;

public abstract class Elemento
{

	abstract int getPunteggio();
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass() == this.getClass())
			return true;
		else
			return false;		
	}

	public Segnalino removeSegnalino()
	{
		Segnalino segnalino = this.segnalino;
		this.segnalino = null;
		return segnalino;
	}

	public void setSegnalino(Segnalino segnalino)
	{
		this.segnalino = segnalino;
	}

	private Segnalino segnalino;

}