package it.polimi.dei.swknights.carcassonne.Client.View.Gui;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class JCarcassonneZoom extends JPanel
{
	public JCarcassonneZoom()
	{
		final int larghezza = 100;
		final int altezza = 45;
		Dimension dimensione = new Dimension(larghezza,altezza);
		this.setPreferredSize(dimensione);
		JLabel label = new JLabel("Zoom");
		this.add(label);
		this.slider = new JSlider();
		this.slider.setPreferredSize(dimensione);
		this.add(this.slider);
	}
	
	private JSlider	slider;
	
	private static final long	serialVersionUID	= -3086842872745087592L;
}
