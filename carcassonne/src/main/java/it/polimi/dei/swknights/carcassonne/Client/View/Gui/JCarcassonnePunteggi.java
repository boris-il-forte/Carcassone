package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

/**
 * Class representing the score panel
 * 
 * @author dave
 * 
 */
public class JCarcassonnePunteggi extends JPanel
{
	/**
	 * Default constructor
	 * 
	 * @param numGiocatori
	 *            the number of player's
	 */
	public JCarcassonnePunteggi(int numGiocatori)
	{
		this.players = new HashMap<Color, JCarcassonnePlayer>();
		this.setLayout(new FlowLayout());
		this.creaColori(numGiocatori);

	}

	/**
	 * Method used to update the players' score
	 * 
	 * @param punteggi
	 *            the players' score
	 */
	public void aggiornaPunteggi(Punteggi punteggi)
	{
		for (Entry<Color, Integer> entry : punteggi.entrySet())
		{
			JCarcassonnePlayer player = this.players.get(entry.getKey());
			if (player != null)
			{
				player.setPunteggio(entry.getValue());
			}
		}
	}

	private void creaColori(int numGiocatori)
	{
		Iterator<Color> iteratore = ColoriGioco.getListaColori().iterator();
		for (int i = 0; iteratore.hasNext() && i < numGiocatori; i++)
		{
			Color colore = iteratore.next();
			JCarcassonnePlayer player = new JCarcassonnePlayer(colore);
			this.players.put(colore, player);
			this.add(player);
		}

	}

	private Map<Color, JCarcassonnePlayer>	players;

	private static final long				serialVersionUID	= 5807426518264359348L;

}
