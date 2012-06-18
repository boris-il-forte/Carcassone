package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
/**
 * This class gives a basic schema of a view handler, plus it gives basic methods 
 * to make common strings of the protocol
 * Each proxy view handler basically makes a protocol string (or a part of it for multiple
 * events) to be sent via netwok
 * @author edoardo
 *
 */
public class ProxyViewHandler extends ViewHandler
{
	/**
	 * basic constructor
	 * @param proxy
	 */
	public ProxyViewHandler(ProxyView proxy)
	{
		this.proxy = proxy;
	}
	
	protected String getStringaUpdate(String tile, Coordinate coordinata)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("update:");
		builder.append(tile);
		builder.append(",");
		builder.append(coordinata.getX());
		builder.append(",");
		builder.append(coordinata.getY());

		return builder.toString();
	}

	protected String getStringaPunteggi(Punteggi punteggi)
	{
		StringBuilder builder = new StringBuilder();
		for (Color colore : ColoriGioco.getListaColori())
		{
			String stringColor = ColoriGioco.getProtocolName(colore);
			builder.append(stringColor);
			builder.append('=');
			builder.append(punteggi.get(colore));
			builder.append(",");
		}
		builder.deleteCharAt(builder.lastIndexOf(","));

		return builder.toString();
	}

	protected ProxyView	proxy;

}
