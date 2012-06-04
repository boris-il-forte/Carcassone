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

public class JCarcassonneCasella extends JLayeredPane
{
	public JCarcassonneCasella(Rectangle bounds)
	{
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.bounds = bounds;
		this.cella = new JLabel();
		this.segnalino = new JLabel();
		this.add(this.cella, DEFAULT_LAYER);
		this.add(this.segnalino, PALETTE_LAYER);
		this.cella.setBounds(this.bounds);
	}

	public JCarcassonneCasella(int dimLato)
	{
		this(new Rectangle(0, 0, dimLato, dimLato));
	}

	public void setTessera(Icon icon)
	{
		this.cella.setIcon(icon);
	}

	public void setVuota(Coordinate coordinateVuota)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.cella.setText(coordinateVuota.toString());
		this.cella.setHorizontalTextPosition(SwingConstants.CENTER);
		Rectangle newBounds = (Rectangle) this.bounds.clone();
		newBounds.grow(-1, -1);
		this.cella.setBounds(newBounds);		
	}

	public void svuota()
	{
		this.cella.setText("");
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.cella.setIcon(null);
	}

	public void setSegnalino(Icon icon)
	{
		this.segnalino.setIcon(icon);
	}

	private Rectangle			bounds;

	private JLabel				cella;

	private JLabel				segnalino;

	private static final long	serialVersionUID	= -3373418009078875015L;

}
