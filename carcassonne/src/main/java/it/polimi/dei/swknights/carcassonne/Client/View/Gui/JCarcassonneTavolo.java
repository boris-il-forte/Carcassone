package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
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

	public JCarcassonneTavolo(Gui gui)
	{
		super();
		final int larghezza = LARGHEZZA * DIMENSIONE_CELLE;
		final int altezza = ALTEZZA * DIMENSIONE_CELLE;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setLayout(new GridLayout(ALTEZZA, LARGHEZZA));
		this.setMinimumSize(dimensione);
		this.setPreferredSize(dimensione);
		this.setMaximumSize(dimensione);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.listaCaselle = new ArrayList<JCarcassonneCasella>();
		this.creaCaselle();
		
		this.view = gui;
	}

	public void creaCaselle()
	{

		for (int i = 0; i < NUMERO_CASELLE; i++)
		{
			JCarcassonneCasella tessera = new JCarcassonneCasella(new Rectangle(0, 0, DIMENSIONE_CELLE,
					DIMENSIONE_CELLE));
			this.listaCaselle.add(tessera);
			tessera.addMouseListener(this);
			this.add(tessera);
		}
	}

	public void setIconTessera(int numeroCasella, Icon immagine)
	{
		JCarcassonneCasella casella = this.listaCaselle.get(numeroCasella);
		casella.setTessera(immagine);
	}

	public void mouseClicked(MouseEvent e)
	{
		Object source = e.getSource();
		Coordinate coordinate = new Coordinate(e.getPoint().x, e.getPoint().y);
		int numeroCasella = this.listaCaselle.indexOf(source);
		this.view.casellaCliccata(numeroCasella, coordinate);
	}

	public void mouseEntered(MouseEvent arg0)
	{
	}

	public void mouseExited(MouseEvent arg0)
	{
	}

	public void mousePressed(MouseEvent arg0)
	{
	}

	public void mouseReleased(MouseEvent arg0)
	{
	}

	private Gui	view;

	private List<JCarcassonneCasella>	listaCaselle;

	private static final int			ALTEZZA				= 7;

	private static final int			LARGHEZZA			= 13;

	private static final int			DIMENSIONE_CELLE	= 100;

	private static final int			NUMERO_CASELLE		= ALTEZZA * LARGHEZZA;

	private static final long			serialVersionUID	= -8246924200035116931L;

}
