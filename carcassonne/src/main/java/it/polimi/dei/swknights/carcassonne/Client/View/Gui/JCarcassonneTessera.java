package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class JCarcassonneTessera extends JLayeredPane
{
	public JCarcassonneTessera()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.immagineTessera = new JLabel();
		this.immagineSegnalino = new JLabel();
		this.add(immagineTessera, DEFAULT_LAYER);
		this.add(immagineSegnalino, PALETTE_LAYER);
	}
	
	public void setTessera(Icon icon)
	{
		this.immagineTessera.setIcon(icon);
	}
	
	public void setSegnalino(Icon icon)
	{
		this.immagineSegnalino.setIcon(icon);
	}
	
	private JLabel immagineTessera;
	
	private JLabel	immagineSegnalino;

	private static final long	serialVersionUID	= -3373418009078875015L;

}
