package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFrame;

/**
 * GUI swing implementation
 * 
 * @author dave
 * 
 */
public class JCarcassoneFrame extends JFrame
{
	/**
	 * Default constructor
	 * 
	 * @param gui
	 *            the high level GUI
	 * @param righe
	 *            the number of rows of the map grid
	 * @param colonne
	 *            the number of columns of the map grid
	 */
	public JCarcassoneFrame(Gui gui, int righe, int colonne)
	{
		super();
		this.view = gui;
		this.inizializzaFinestra(righe, colonne);
	}

	/**
	 * shows the GUI
	 */
	public void showGui()
	{
		this.pack();
		this.setVisible(true);
	}

	/**
	 * updates the score
	 * 
	 * @param punteggio
	 *            the current score
	 */
	public void aggiornaPunteggi(Punteggi punteggio)
	{
		this.contaPunti.aggiornaPunteggi(punteggio);

	}

	/**
	 * Updates the current tile
	 * 
	 * @param tessera
	 *            the current tile
	 */
	public void aggiornaTesseraCorrente(Icon tessera)
	{
		this.barraLaterale.aggiornaTesseraCorrente(tessera);
	}

	/**
	 * Updates current player's color and how many markers he has.
	 * 
	 * @param colorethe
	 *            current player's color
	 * @param numeroSegnalini
	 *            the current player's number of markers
	 */
	public void aggiornaGiocatoreCorrente(Color colore, int numeroSegnalini)
	{
		this.barraLaterale.aggiornaGiocatoreCorrente(colore, numeroSegnalini);
	}

	/**
	 * Updates the card at the given position
	 * 
	 * @param numeroCasella
	 *            teh position number in the grid
	 * @param immagine
	 *            the icon tho set in the position
	 */
	public void aggiornaTessera(int numeroCasella, Icon immagine)
	{
		this.tavolo.setIconTessera(numeroCasella, immagine, true);
	}

	/**
	 * Method to update the marker on the card
	 * 
	 * @param numeroCasella
	 *            the number of the position in the grid
	 * @param segnalino
	 *            the icon of the marker to set up
	 * @param coordinateSegnalino
	 *            where to set up the icon
	 */
	public void aggiornaSegnalinoTessera(int numeroCasella, Icon segnalino, Coordinate coordinateSegnalino)
	{
		this.tavolo.setSegnalino(numeroCasella, segnalino, coordinateSegnalino);
	}

	/**
	 * Set the shadow card on the empty position
	 * 
	 * @param numeroCasella
	 *            the number of the position in the grid
	 * @param icon
	 *            the card icon
	 */
	public void overlayTessera(int numeroCasella, Icon icon)
	{
		this.tavolo.setIconTessera(numeroCasella, icon, false);

	}

	/**
	 * Method to update empty cards
	 * 
	 * @param numeroVuota
	 * @param coordinateVuota
	 */
	public void aggiornaVuote(int numeroVuota, Coordinate coordinateVuota)
	{
		this.tavolo.setTesseraVuota(numeroVuota, coordinateVuota);
	}

	/**
	 * Method used to clear all the map
	 */
	public void svuotaMappa()
	{
		this.tavolo.svuotaMappa();
	}

	/**
	 * Method used to create the score bar
	 * 
	 * @param numeroGiocatori
	 *            the number of players of this game
	 */
	public final void creaContaPunti(int numeroGiocatori)
	{
		if (this.contaPunti != null)
		{
			this.remove(this.contaPunti);
		}
		this.contaPunti = new JCarcassonnePunteggi(numeroGiocatori, this.view.getSfondo("front"));
		this.add(this.contaPunti, BorderLayout.SOUTH);
	}

	private void inizializzaFinestra(int righe, int colonne)
	{
		this.creaFinestra();
		this.creaMenu();
		this.creaBarraComandi();
		this.creaTavolo(righe, colonne);
		this.creaContaPunti(0);
	}

	private void creaFinestra()
	{
		this.setTitle("Carcassonne - swKnights");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMinimumSize(DIMENSIONE_MINIMA);
	}

	private void creaMenu()
	{
		this.menu = new JCarcassonneMenu();
		this.add(this.menu, BorderLayout.NORTH);
	}

	private void creaTavolo(int righe, int colonne)
	{
		final int minSpaziaturaOrizzontale = 20;
		this.tavolo = new JCarcassonneTavolo(this.view, righe, colonne);
		Box box = new JCarcassonneBox();
		box.add(Box.createHorizontalStrut(minSpaziaturaOrizzontale));
		box.add(Box.createHorizontalGlue());
		box.add(this.tavolo);
		box.add(Box.createHorizontalGlue());
		box.add(Box.createHorizontalStrut(minSpaziaturaOrizzontale));
		this.add(box);
	}

	private void creaBarraComandi()
	{
		this.barraLaterale = new JCarcassonneLaterale(this.view);
		this.add(this.barraLaterale, BorderLayout.WEST);
	}

	private JCarcassonnePunteggi	contaPunti;

	private JCarcassonneMenu		menu;

	private JCarcassonneLaterale	barraLaterale;

	private JCarcassonneTavolo		tavolo;

	private transient Gui			view;

	private static final Dimension	DIMENSIONE_MINIMA	= new Dimension(1200, 700);

	private static final long		serialVersionUID	= -5492055857856379920L;

	private class JCarcassonneBox extends Box
	{
		public JCarcassonneBox()
		{
			super(BoxLayout.X_AXIS);
		}
		
		
		public void paintComponent(Graphics g)
		{
			BufferedImage img = JCarcassoneFrame.this.view.getSfondo("behind");
			Dimension panelSize = getSize();
			int width = img.getWidth(this);
			int height = img.getHeight(this);
			for (int y = 0; y < panelSize.height; y += height)
			{
				for (int x = 0; x < panelSize.width; x += width)
				{
					g.drawImage(img, x, y, this);
				}
			}
		}
		
		private static final long	serialVersionUID	= 4176262019821017182L;
	}
}
