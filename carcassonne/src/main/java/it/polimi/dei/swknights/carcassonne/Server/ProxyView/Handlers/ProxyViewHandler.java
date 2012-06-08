package it.polimi.dei.swknights.carcassonne.Server.ProxyView.Handlers;

import it.polimi.dei.swknights.carcassonne.ModuliAstratti.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
import java.util.Map.Entry;

public class ProxyViewHandler extends ViewHandler
{
	public ProxyViewHandler(ProxyView proxy)
	{
		this.proxy = proxy;
	}

	protected String getStringaUpdate(String tile, Coordinate coordinata)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("update: ");
		builder.append(tile);
		builder.append(", ");
		builder.append(coordinata.getX());
		builder.append(", ");
		builder.append(coordinata.getY());

		return builder.toString();
	}

	protected String getStringaPunteggi(Punteggi punteggi)
	{
		StringBuilder builder = new StringBuilder();
		for (Entry<Color, Integer> entry : punteggi.entrySet())
		{
			String color = ColoriGioco.getProtocolName(entry.getKey());
			builder.append(color);
			builder.append('=');
			builder.append(entry.getValue());
			builder.append(", ");
		}
		builder.deleteCharAt(builder.lastIndexOf(","));

		return builder.toString();
	}

	protected ProxyView	proxy;

}
