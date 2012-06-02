package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class JCarcassonneTessera extends JLayeredPane
{
	public JCarcassonneTessera(Rectangle bounds)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.bounds = bounds;
		this.immagineTessera = new JLabel();
		this.immagineSegnalino = new JLabel();
		this.add(this.immagineTessera, DEFAULT_LAYER);
		this.add(this.immagineSegnalino, PALETTE_LAYER);
		this.immagineTessera.setBounds(this.bounds);
	}
	
	public void setTessera(Icon icon)
	{
		System.out.println("provo a settare la tessera");
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
