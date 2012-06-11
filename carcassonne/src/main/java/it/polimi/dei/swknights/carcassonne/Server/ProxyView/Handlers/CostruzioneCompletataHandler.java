package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.util.Map;
import java.util.Map.Entry;

public class CostruzioneCompletataHandler extends ProxyViewHandler
{

	public CostruzioneCompletataHandler(ProxyView proxy)
	{
		super(proxy);

	}

	@Override
	public void visit(CostruzioneCompletataEvent event)
	{
		Map<AdapterTessera, Coordinate> mappa = event.getTessereAggiornate();
		this.setComandoUpdateStringhe(mappa);
		this.setComandoUpdatePunteggi(event.getPunteggi());
	}

	private void setComandoUpdateStringhe(Map<AdapterTessera, Coordinate> mappa)
	{
		StringBuilder stringCommand;
		for (Entry<AdapterTessera, Coordinate> entry : mappa.entrySet())
		{
			stringCommand = new StringBuilder();
			String tile = entry.getKey().toProtocolString();
			String stringa = this.getStringaUpdate(tile, entry.getValue());
			stringCommand.append(stringa);
			stringCommand.append(",");
			this.proxy.setCommandString(stringCommand.toString());
		}
	}

	private void setComandoUpdatePunteggi(Punteggi punteggi)
	{
		StringBuilder stringCommand = new StringBuilder();
		stringCommand.append("score:");
		stringCommand.append(this.getStringaPunteggi(punteggi));
		this.proxy.setCommandString(stringCommand.toString());
	}

}



