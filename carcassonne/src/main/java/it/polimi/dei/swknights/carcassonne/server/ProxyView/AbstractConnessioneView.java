package it.polimi.dei.swknights.carcassonne.server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.View;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;

import java.util.EventObject;
import java.util.List;

public class AbstractConnessioneView implements View
{

	public List	myPartita;

	public void request()
	{

	}
	
	public ModuloView view ;

	public void setView(ModuloView view)
	{
		this.view = view;
	}

	public void riceviModificheModel(EventObject event)
	{
		
		if (event instanceof PlaceEvent)
		{
			PlaceEvent pe = (PlaceEvent)event;
			System.out.println("sono viewConness: place event" + event.toString());
			
			Coordinate destinazione = pe.getCoordinateDestinazione();			
			this.view.posizionaTessera(destinazione);
			
		}

	}

}
