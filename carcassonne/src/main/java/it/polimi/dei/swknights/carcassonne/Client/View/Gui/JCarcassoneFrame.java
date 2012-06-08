package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JFrame;

public class JCarcassoneFrame extends JFrame
{

	public JCarcassoneFrame(Gui gui, int righe, int colonne)
	{
		super();
		this.view = gui;
		this.inizializzaFinestra(righe, colonne);
	}

	public void showGui()
	{
		this.pack();
		this.setVisible(true);
	}

	public void aggiornaPunteggi(Punteggi punteggio)
	{
		this.contaPunti.aggiornaPunteggi(punteggio);

	}

	public void aggiornaTesseraCorrente(Icon tessera)
	{
		this.barraLaterale.aggiornaTesseraCorrente(tessera);
	}

	public void aggiornaGiocatoreCorrente(Color colore, int numeroSegnalini)
	{
		this.barraLaterale.aggiornaGiocatoreCorrente(colore, numeroSegnalini);
	}

	public void aggiornaTessera(int numeroCasella, Icon immagine)
	{
		this.tavolo.setIconTessera(numeroCasella, immagine, true);
	}

	public void aggiornaSegnalinoTessera(int numeroCasella, Icon segnalino, Coordinate coordinateSegnalino)
	{
		this.tavolo.setSegnalino(numeroCasella, segnalino, coordinateSegnalino);

	}

	public void overlayTessera(int numeroCasella, Icon icon)
	{
		this.tavolo.setIconTessera(numeroCasella, icon, false);

	}

	public void aggiornaMappa(int numeroVuota, Coordinate coordinateVuota)
	{
		this.tavolo.setTesseraVuota(numeroVuota, coordinateVuota);

	}

	public void svuotaMappa()
	{
		this.tavolo.svuotaMappa();
	}

	private void inizializzaFinestra(int righe, int colonne)
	{
		this.creaFinestra();
		this.creaMenu();
		this.creaBarraComandi();
		this.creaTavolo(righe, colonne);
		this.creaContaPunti();
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

	private void creaTavolo(int righe, int colonne)
	{
		this.tavolo = new JCarcassonneTavolo(this.view, righe, colonne);
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

	private void creaContaPunti()
	{
		this.contaPunti = new JCarcassonnePunteggi(this.numeroGiocatori);
		this.add(this.contaPunti, BorderLayout.SOUTH);

	}

	private JCarcassonnePunteggi	contaPunti;

	private JCarcassonneMenu		menu;

	private JCarcassonneLaterale	barraLaterale;

	private JCarcassonneTavolo		tavolo;

	private Gui						view;

	private int						numeroGiocatori		= 2;

	private static final Dimension	DIMENSIONE_MINIMA	= new Dimension(1200, 600);

	private static final long		serialVersionUID	= -5492055857856379920L;

}
