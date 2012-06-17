package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * Class representing one of the grid cells
 * 
 * @author dave
 * 
 */
public class JCarcassonneCasella extends JLayeredPane
{
	/**
	 * Default constructor
	 * 
	 * @param bounds
	 *            the dimension of the cell
	 */
	public JCarcassonneCasella(Rectangle bounds)
	{
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.bounds = bounds;
		this.cella = new JLabel();
		this.segnalino = new JLabel();
		this.add(this.cella, DEFAULT_LAYER);
		this.add(this.segnalino, PALETTE_LAYER);
		this.cella.setBounds(this.bounds);
		this.cella.setHorizontalTextPosition(SwingConstants.CENTER);
		this.stato = StatoCasella.nonUsata;
	}

	/**
	 * convenience constructor for square cells
	 * 
	 * @param dimLato
	 */
	public JCarcassonneCasella(int dimLato)
	{
		this(new Rectangle(0, 0, dimLato, dimLato));
	}

	/**
	 * Updates the state of the icon
	 * 
	 * @param icon
	 *            the image of the icon
	 * @param aggiornaStato
	 *            if the logic state of this cell must be updated
	 */
	public void setTessera(Icon icon, boolean aggiornaStato)
	{
		this.cella.setIcon(icon);
		if (aggiornaStato)
		{
			this.stato = StatoCasella.conTessera;
		}
		this.cella.setHorizontalTextPosition(SwingConstants.CENTER);
	}

	/**
	 * Set empty this cell
	 * 
	 * @param coordinateVuota
	 *            the cell coordinates
	 */
	public void setVuota(Coordinate coordinateVuota)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.cella.setText(coordinateVuota.toString());
		Rectangle newBounds = (Rectangle) this.bounds.clone();
		newBounds.grow(-1, -1);
		this.cella.setBounds(newBounds);
		this.cella.setHorizontalTextPosition(SwingConstants.CENTER);
		this.stato = StatoCasella.vuota;
	}

	/**
	 * Clear the cell
	 */
	public void svuota()
	{
		this.cella.setText("");
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.cella.setIcon(null);
		this.segnalino.setIcon(null);
		this.cella.setHorizontalTextPosition(SwingConstants.CENTER);
		this.stato = StatoCasella.nonUsata;
	}

	/**
	 * Set the marker
	 * 
	 * @param icon
	 *            the image of the marker
	 * @param coordinateSegnalino
	 *            the position of the marker
	 */
	public void setSegnalino(Icon icon, Coordinate coordinateSegnalino)
	{
		final int latoSegnalino = 30;
		this.segnalino.setIcon(icon);
		this.stato = StatoCasella.conTessera;
		int x = coordinateSegnalino.getX() - latoSegnalino / 2;
		int y = coordinateSegnalino.getY() - latoSegnalino / 2;
		this.segnalino.setBounds(x, y, latoSegnalino, latoSegnalino);
		this.cella.setHorizontalTextPosition(SwingConstants.CENTER);
	}

	/**
	 * Getter for the cell state
	 * 
	 * @return the cell state
	 */
	public StatoCasella getStato()
	{
		return this.stato;
	}

	private Rectangle			bounds;

	private JLabel				cella;

	private JLabel				segnalino;

	private StatoCasella		stato;

	private static final long	serialVersionUID	= -3373418009078875015L;

}

enum StatoCasella {
	nonUsata, conTessera, vuota;
}
