package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;
/**
 * This class is used to handle the beginning of the game in proxy mode
 * @author edoardo
 *
 */

public class InizioGiocoHandler extends ProxyViewHandler
{
	/**
	 * basic constructor
	 * @param proxy
	 */
	public InizioGiocoHandler(ProxyView proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(InizioGiocoEvent event)
	{
		StringBuilder stringCommand = null;
		for (Color colore : ColoriGioco.getListaColori())
		{
			stringCommand = new StringBuilder();
			stringCommand.append("start:");
			stringCommand.append(event.getTesseraIniziale().toProtocolString());
			stringCommand.append(",");
			stringCommand.append(event.getIdPartita());
			stringCommand.append(",");
			stringCommand.append(ColoriGioco.getProtocolName(colore));
			stringCommand.append(",");
			stringCommand.append(event.getNumGiocatori());
			this.proxy.setCommandString(stringCommand.toString());
		}
		this.proxy.mandaComandoAvvia(event);
		
	}

}
