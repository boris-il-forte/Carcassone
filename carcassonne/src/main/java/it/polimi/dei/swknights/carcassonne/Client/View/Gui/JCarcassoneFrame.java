package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JFrame;

public class JCarcassoneFrame extends JFrame
{

	public JCarcassoneFrame(Gui gui)
	{
		super();
		this.view = gui;
		this.inizializzaFinestra();
	}

	public void showGui()
	{
		this.pack();
		this.setVisible(true);
	}

	public void aggiornaTesseraCorrente(Icon tessera)
	{
		this.barraLaterale.aggiornaTesseraCorrente(tessera);
	}

	public void aggiornaMappa(int numeroCasella, Icon immagine)
	{
		this.tavolo.setIconTessera(numeroCasella, immagine);
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
		this.setMinimumSize(DIMENSIONE_MINIMA);
	}

	private void creaMenu()
	{
		this.menu = new JCarcassonneMenu();
		this.add(this.menu, BorderLayout.NORTH);
	}

	private void creaTavolo()
	{
		this.tavolo = new JCarcassonneTavolo(this.view);
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(20));
		box.add(Box.createHorizontalGlue());
		box.add(this.tavolo);
		box.add(Box.createHorizontalGlue());
		box.add(Box.createHorizontalStrut(20));
		this.add(box);
	}

	private void creaBarraComandi()
	{
		this.barraLaterale = new JCarcassonneLaterale(this.view);
		this.add(this.barraLaterale, BorderLayout.WEST);
	}

	private JCarcassonneMenu		menu;

	private JCarcassonneLaterale	barraLaterale;

	private JCarcassonneTavolo		tavolo;

	private Gui	view;

	private static final Dimension	DIMENSIONE_MINIMA	= new Dimension(1200, 600);

	private static final long		serialVersionUID	= -5492055857856379920L;

}
