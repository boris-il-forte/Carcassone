package it.polimi.dei.swknights.carcassonne.server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;

public class InizioGiocoHandler extends ViewHandler
{

	
	@Override
	public void visit (InizioGiocoEvent event) 
	{
		Debug.print(event.toString());
		
	}

	

}
