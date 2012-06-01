package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;

public class JCarcassoneFrame extends JFrame
{

	public JCarcassoneFrame()
	{
		super();
		this.inizializzaFinestra();
	}

	private void inizializzaFinestra()
	{
		this.creaFinestra();
		this.creaMenu();
		this.creaBarraComandi();
		this.creaTavolo();
	}

	private void creaFinestra()
	{
		this.setTitle("Carcassonne - swKnights");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1200, 600));

	}

	private void creaMenu()
	{
		this.menu = new JCarcassonneMenu();
		this.add(this.menu, BorderLayout.NORTH);
	}

	private void creaTavolo()
	{
		Box box = Box.createHorizontalBox();
		this.add(box);
		this.tavolo = new JCarcassonneTavolo();
		box.add(this.tavolo);
	}

	private void creaBarraComandi()
	{
		this.barraLaterale = new JCarcassonneLaterale();
		this.add(this.barraLaterale, BorderLayout.WEST);
	}

	public void showGui()
	{
		this.pack();
		this.setVisible(true);
	}

	private JCarcassonneMenu		menu;

	private JCarcassonneLaterale	barraLaterale;

	private JCarcassonneTavolo		tavolo;

	private static final long		serialVersionUID	= -5492055857856379920L;

}
