package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import java.awt.Color;

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
	public void visit(InizioGiocoEvent event)
	{
		for (Color colore : ColoriGioco.getListaColori())
		{
			StringBuilder stringCommand = new StringBuilder();
			stringCommand.append("start: ");
			stringCommand.append(event.getTesseraIniziale());
			stringCommand.append(event.getIdPartita());
			stringCommand.append(", ");
			stringCommand.append(ColoriGioco.getProtocolName(colore));
			stringCommand.append(", ");
			stringCommand.append(event.getNumGiocatori());
			stringCommand.append(", ");
			this.proxy.setCommandString(stringCommand.toString());
		}
		this.proxy.mandaComandoAvvia();
	}

}
