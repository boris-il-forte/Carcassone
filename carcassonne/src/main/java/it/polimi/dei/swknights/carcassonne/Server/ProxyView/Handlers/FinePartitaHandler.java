package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;
/**
 * This class is used to handles the end of the game in proxy mode
 * @author edoardo
 *
 */
public class FinePartitaHandler extends ProxyViewHandler
{
	/**
	 * basic constructor
	 * @param proxy
	 */
	public FinePartitaHandler(ProxyView proxy)
	{
		super(proxy);
	}

	@Override
	public void visit(FinePartitaEvent event)
	{
		StringBuilder stringCommand = new StringBuilder();
		stringCommand.append("end:");
		Punteggi punteggi = event.getPunteggi();
		String stringaPunteggi = this.getStringaPunteggi(punteggi);
		stringCommand.append(stringaPunteggi);
		this.proxy.setCommandString(stringCommand.toString());
	}

}
