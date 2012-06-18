package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Class representing the part of the map shown to the player
 * 
 * @author dave
 * 
 */
public class JCarcassonneTavolo extends JPanel implements MouseListener
{

	/**
	 * Default constructor
	 * 
	 * @param gui
	 *            the GUI using this frame
	 * @param righe
	 *            the number of rows displayed
	 * @param colonne
	 *            the number of columns displayed
	 */
	public JCarcassonneTavolo(Gui gui, int righe, int colonne)
	{
		super();
		final int coloreSfondo = 0xC5FF98;
		this.setBackground(new Color(coloreSfondo));
		this.setOpaque(true);
		this.righe = righe;
		this.colonne = colonne;
		final int larghezza = this.colonne * DIMENSIONE_CELLE;
		final int altezza = this.righe * DIMENSIONE_CELLE;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setLayout(new GridLayout(righe, colonne));
		this.setMinimumSize(dimensione);
		this.setPreferredSize(dimensione);
		this.setMaximumSize(dimensione);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.listaCaselle = new ArrayList<JCarcassonneCasella>();
		this.creaCaselle();

		this.view = gui;
	}

	/**
	 * Method to set the Image of a tile
	 * 
	 * @param numeroCasella
	 *            the number of the position in the grid
	 * @param immagine
	 *            the icon to be set up
	 * @param aggiornaStato
	 *            if the state of the cell must be updated
	 */
	public void setIconTessera(int numeroCasella, Icon immagine, boolean aggiornaStato)
	{
		JCarcassonneCasella casella = this.listaCaselle.get(numeroCasella);
		casella.setTessera(immagine, aggiornaStato);
	}

	/**
	 * Method that set an empty cell
	 * 
	 * @param numeroVuota
	 *            the number of the cell in the grid
	 * @param coordinateVuota
	 *            the coordinates of the empty cell
	 */
	public void setTesseraVuota(int numeroVuota, Coordinate coordinateVuota)
	{
		JCarcassonneCasella casella = this.listaCaselle.get(numeroVuota);
		casella.setVuota(coordinateVuota);
	}

	/**
	 * method that set the marker for the cell
	 * 
	 * @param numeroCasella
	 *            the position on the grid
	 * @param segnalino
	 *            the marker icon
	 * @param coordinateSegnalino
	 *            where to draw the marker
	 */
	public void setSegnalino(int numeroCasella, Icon segnalino, Coordinate coordinateSegnalino)
	{
		JCarcassonneCasella casella = this.listaCaselle.get(numeroCasella);
		casella.setSegnalino(segnalino, coordinateSegnalino);

	}

	/**
	 * Method that clear all the map
	 */
	public void svuotaMappa()
	{
		for (JCarcassonneCasella cella : this.listaCaselle)
		{
			cella.svuota();
		}

	}

	/**
	 * Handler for the mouse click event, used for tile and marker place.
	 */
	public void mouseClicked(MouseEvent e)
	{
		int numeroCasella = this.getNumeroCasella(e);
		JCarcassonneCasella casella = this.listaCaselle.get(numeroCasella);
		if (this.inStatoVuota(numeroCasella) && this.view.getGestoreFasi().posizionaOk())
		{
			this.lastPlaced = this.view.convertiCoordinate(numeroCasella);
			this.view.clicPosizionaTessera(numeroCasella);
		}
		else if (casella.getStato().equals(StatoCasella.conTessera)
				&& this.view.convertiCoordinate(numeroCasella).equals(this.lastPlaced))
		{
			Coordinate coordinateMouse = new Coordinate(e.getPoint().x, e.getPoint().y);
			this.view.clicPosizionaSegnalino(numeroCasella, coordinateMouse);
		}
	}

	/**
	 * Handler for the mouse entered event. used only for overlay
	 */
	public void mouseEntered(MouseEvent e)
	{
		int numeroCasella = this.getNumeroCasella(e);
		if (this.inStatoVuota(numeroCasella))
		{
			this.view.overlayImmagine(numeroCasella);
		}
	}

	/**
	 * Handler for mouse exited event. used to cancel overlay on empty cells
	 */
	public void mouseExited(MouseEvent e)
	{
		int numeroCasella = this.getNumeroCasella(e);
		if (this.inStatoVuota(numeroCasella))
		{
			this.view.togliOverlay(this.listaCaselle.get(numeroCasella), numeroCasella);
		}
	}

	/**
	 * Useless handler from interface
	 */
	public void mousePressed(MouseEvent e)
	{
	}

	/**
	 * Useless handler from interface
	 */
	public void mouseReleased(MouseEvent e)
	{
	}

	/**
	 * Override superclass method to draw background image
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		BufferedImage img = this.view.getSfondo("prato");
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
	
	private int getNumeroCasella(MouseEvent e)
	{
		Object source = e.getSource();
		return this.listaCaselle.indexOf(source);
	}

	private boolean inStatoVuota(int numeroCasella)
	{
		StatoCasella stato = this.listaCaselle.get(numeroCasella).getStato();
		return stato.equals(StatoCasella.vuota);
	}

	private void creaCaselle()
	{
		final int numeroCaselle = this.righe * this.colonne;
		for (int i = 0; i < numeroCaselle; i++)
		{
			JCarcassonneCasella tessera = new JCarcassonneCasella(DIMENSIONE_CELLE);
			this.listaCaselle.add(tessera);
			tessera.addMouseListener(this);
			this.add(tessera);
		}
	}

	private transient Gui				view;

	private List<JCarcassonneCasella>	listaCaselle;

	private int							righe;

	private int							colonne;

	private Coordinate					lastPlaced;

	private static final int			DIMENSIONE_CELLE	= 100;

	private static final long			serialVersionUID	= -8246924200035116931L;

}
