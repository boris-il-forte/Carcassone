package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

public class InizioGiocoHandler extends ProxyViewHandler
{

	public InizioGiocoHandler(ProxyView proxy)
	{
		super(proxy);
	}
	
	@Override
	public void visit (InizioGiocoEvent event) 
	{
		StringBuilder stringCommand = new StringBuilder();
		stringCommand.append("start: ");
		stringCommand.append( event.getTesseraIniziale() );
		stringCommand.append( event.getIdPartita());
		stringCommand.append(", ");		
		stringCommand.append( ColoriGioco.getProtocolName(event.getGiocatore()));
		stringCommand.append(", ");		
		stringCommand.append( event.getNumGiocatori() );
		stringCommand.append(", ");				
		this.proxy.setCommandString(stringCommand.toString());
	}
}
