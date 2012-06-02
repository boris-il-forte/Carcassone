package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JCarcassonneRotate extends JPanel
{
	public JCarcassonneRotate()
	{
		super();
		final int larghezza = 150;
		final int altezza = 45;
		Dimension dimensione = new Dimension(larghezza,altezza);
		this.setPreferredSize(dimensione);
		this.rotateButton = new JButton("Ruota");
		this.add(this.rotateButton);
		this.rotateButton.setMinimumSize(dimensione);
		this.rotateButton.setPreferredSize(dimensione);
		this.rotateButton.setMaximumSize(dimensione);
	}
	
	private JButton rotateButton;
	
	private static final long	serialVersionUID	= -2188971767426785605L;

}
