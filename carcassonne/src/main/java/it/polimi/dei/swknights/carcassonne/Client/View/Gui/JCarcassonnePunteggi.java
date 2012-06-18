package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
	public JCarcassonnePunteggi(int numGiocatori, BufferedImage background)
	{
		this.backGround =background;
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
	
	/**
	 * Override superclass method to draw background image
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		Dimension panelSize = getSize();
		int width = this.backGround.getWidth();
		int height = this.backGround.getHeight();
		for (int y = 0; y < panelSize.height; y += height)
		{
			for (int x = 0; x < panelSize.width; x += width)
			{
				g.drawImage(this.backGround, x, y, this);
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

	private BufferedImage	backGround;

	private Map<Color, JCarcassonnePlayer>	players;

	private static final long				serialVersionUID	= 5807426518264359348L;

}
