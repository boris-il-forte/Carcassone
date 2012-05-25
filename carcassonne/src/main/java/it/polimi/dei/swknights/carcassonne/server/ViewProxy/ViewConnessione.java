package it.polimi.dei.swknights.carcassonne.server.ViewProxy;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Client.View.View;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.ControllerListener;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;

import java.util.EventObject;
import java.util.List;

public class ViewConnessione implements ControllerListener
{

	public List	myPartita;

	public void request()
	{

	}
	
	public View view ;

	public void setView(View view)
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
