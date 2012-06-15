package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JCarcassonneButton extends JPanel
{
	public JCarcassonneButton(Icon icona)
	{
		super();
		final int larghezza = 150;
		final int altezza = 45;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setPreferredSize(dimensione);
		this.button = new JButton(icona);
		this.add(this.button);
		this.button.setMinimumSize(dimensione);
		this.button.setPreferredSize(dimensione);
		this.button.setMaximumSize(dimensione);
	}

	public void setActionListener(ActionListener listener)
	{
		this.button.addActionListener(listener);

	}

	public Object getEventSource()
	{
		return this.button;
	}

	private JButton				button;

	private static final long	serialVersionUID	= 8724265205035677878L;
}
