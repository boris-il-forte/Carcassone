package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;

public class JCarcassonneCasella extends JLayeredPane
{
	public JCarcassonneCasella(Rectangle bounds)
	{
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.bounds = bounds;
		this.immagineTessera = new JLabel();
		this.immagineSegnalino = new JLabel();
		this.add(this.immagineTessera, DEFAULT_LAYER);
		this.add(this.immagineSegnalino, PALETTE_LAYER);
		this.immagineTessera.setBounds(this.bounds);
	}
	
	public JCarcassonneCasella(int dimLato)
	{
		this(new Rectangle(0,0,dimLato,dimLato));
	}
	
	public void setTessera(Icon icon)
	{
		this.immagineTessera.setIcon(icon);
	}
	
	public void setSegnalino(Icon icon)
	{
		this.immagineSegnalino.setIcon(icon);
	}
	
	private Rectangle	bounds;

	private JLabel immagineTessera;
	
	private JLabel	immagineSegnalino;

	private static final long	serialVersionUID	= -3373418009078875015L;

}
