package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class JCarcassonneTavolo extends JPanel implements MouseListener
{

	public JCarcassonneTavolo(Gui gui, int righe, int colonne)
	{
		super();
		this.righe = righe;
		this.colonne = colonne;
		final int larghezza = this.colonne * DIMENSIONE_CELLE;
		final int altezza = this.righe * DIMENSIONE_CELLE;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setLayout(new GridLayout(righe, colonne));
		this.setMinimumSize(dimensione);
		this.setPreferredSize(dimensione);
		this.setMaximumSize(dimensione);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.listaCaselle = new ArrayList<JCarcassonneCasella>();
		this.creaCaselle();

		this.view = gui;
	}

	public void setIconTessera(int numeroCasella, Icon immagine, boolean aggiornaStato)
	{
		JCarcassonneCasella casella = this.listaCaselle.get(numeroCasella);
		casella.setTessera(immagine, aggiornaStato);
	}

	public void setTesseraVuota(int numeroVuota, Coordinate coordinateVuota)
	{
		JCarcassonneCasella casella = this.listaCaselle.get(numeroVuota);
		casella.setVuota(coordinateVuota);
	}

	public void setSegnalino(int numeroCasella, Icon segnalino, Coordinate coordinateSegnalino)
	{
		JCarcassonneCasella casella = this.listaCaselle.get(numeroCasella);
		casella.setSegnalino(segnalino, coordinateSegnalino);

	}

	public void svuotaMappa()
	{
		for (JCarcassonneCasella cella : this.listaCaselle)
		{
			cella.svuota();
		}

	}

	public void mouseClicked(MouseEvent e)
	{
		int numeroCasella = this.getNumeroCasella(e);
		JCarcassonneCasella casella = this.listaCaselle.get(numeroCasella);
		if (this.inStatoVuota(numeroCasella))
		{
			this.lastPlaced = this.view.convertiCoordinate(numeroCasella);
			this.view.clicPosizionaTessera(numeroCasella);
		}
		else if (casella.getStato().equals(StatoCasella.conTessera)
				&& this.view.convertiCoordinate(numeroCasella).equals(this.lastPlaced))
		{
			Coordinate coordinateMouse = new Coordinate(e.getPoint().x, e.getPoint().y);
			this.view.clicPosizionaSegnalino(numeroCasella, coordinateMouse);
		}
	}

	public void mouseEntered(MouseEvent e)
	{
		int numeroCasella = this.getNumeroCasella(e);
		if (this.inStatoVuota(numeroCasella))
		{
			this.view.overlayImmagine(numeroCasella);
		}
	}

	public void mouseExited(MouseEvent e)
	{
		int numeroCasella = this.getNumeroCasella(e);
		if (this.inStatoVuota(numeroCasella))
		{
			this.view.togliOverlay(this.listaCaselle.get(numeroCasella), numeroCasella);
		}
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	private int getNumeroCasella(MouseEvent e)
	{
		Object source = e.getSource();
		return this.listaCaselle.indexOf(source);
	}

	private boolean inStatoVuota(int numeroCasella)
	{
		StatoCasella stato = this.listaCaselle.get(numeroCasella).getStato();
		return stato.equals(StatoCasella.vuota);
	}

	private void creaCaselle()
	{
		final int numeroCaselle = this.righe * this.colonne;
		for (int i = 0; i < numeroCaselle; i++)
		{
			JCarcassonneCasella tessera = new JCarcassonneCasella(DIMENSIONE_CELLE);
			this.listaCaselle.add(tessera);
			tessera.addMouseListener(this);
			this.add(tessera);
		}
	}

	private Gui							view;

	private List<JCarcassonneCasella>	listaCaselle;

	private int							righe;

	private int							colonne;

	private Coordinate					lastPlaced;

	private static final int			DIMENSIONE_CELLE	= 100;

	private static final long			serialVersionUID	= -8246924200035116931L;

}
