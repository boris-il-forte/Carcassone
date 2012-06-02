package it.polimi.dei.swknights.carcassonne.server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Connessione.ComandiConnessione;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandiView;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;

public class InterpreteSocket extends InterpreteClient
{

	private static final int	PLACE_COORD		= 1;
	private static final int	X				= 0;
	private static final int	Y				= 1;
	private static final int	SIDE			= 1;
	private static final int	DOPO_RECONNECT	= 1;
	private static final int	PARTITA			= 1;
	private static final int	COLOR			= 0;

	public InterpreteSocket(ProxyView proxyView)
	{
		comandiConnessione = null;
		comandiView = null;
	}

	public void capisci(String line)
	{
		if (line.indexOf(",") != -1 && line.indexOf(":") != -1)
		{
			if (line.matches("place: \\-?\\d+\\,\\-?\\d+")) // es place: 2,3
			{
				String[] partiPlace = line.split(": ");
				String coord = partiPlace[PLACE_COORD];
				String[] partiCoord = coord.split(",");
				int x = Integer.parseInt(partiCoord[X]);
				int y = Integer.parseInt(partiCoord[Y]);
			}
			if (line.matches("reconnect: (black|green|red|yellow|blue),.+")) // es.
																				// reconnect:
																				// yellow,PARTITA02
			{
				String[] recoPart = line.split(": ");
				String dopoReco = recoPart[DOPO_RECONNECT];
				String[] colorEPartita = dopoReco.split(",");
				String colore = colorEPartita[COLOR];
				String nomePartita = colorEPartita[PARTITA];
			}
		}

		{
			if (line.indexOf(":") != -1)
			{
				if (line.matches("tile: [SCsc][1-4]")) // es tile: c1
				{
					String[] partiTile = line.split(": ");
					String side = partiTile[SIDE];
					
				}
			}
			else
			{
				if (line.equalsIgnoreCase("connect"))
				{
					comandiConnessione = ComandiConnessione.connect;
				}
				if (line.equalsIgnoreCase("rotate"))
				{
					comandiView = ComandiView.rotate;
				}
				if (line.equalsIgnoreCase("pass"))
				{
					comandiView = ComandiView.pass;
				}

			}
		}

	}

	private ComandiView			comandiView;
	private ComandiConnessione	comandiConnessione;
}
