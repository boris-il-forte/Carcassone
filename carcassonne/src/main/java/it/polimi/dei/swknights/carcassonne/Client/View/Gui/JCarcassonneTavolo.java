package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class JCarcassonneTavolo extends JPanel
{

	public JCarcassonneTavolo()
	{
		final int larghezza = LARGHEZZA * DIMENSIONE_CELLE;
		final int altezza = ALTEZZA * DIMENSIONE_CELLE;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setLayout(new GridLayout(ALTEZZA, LARGHEZZA));
		this.setMinimumSize(dimensione);
		this.setPreferredSize(dimensione);
		this.setMaximumSize(dimensione);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.listaTessere = new ArrayList<JCarcassonneTessera>();
		this.creaCaselle();
	}

	public void creaCaselle()
	{

		for (int i = 0; i < NUMERO_CASELLE; i++)
		{
			JCarcassonneTessera tessera = new JCarcassonneTessera(new Rectangle(0,0,DIMENSIONE_CELLE,DIMENSIONE_CELLE));
			this.listaTessere.add(tessera);
			this.add(tessera);
		}
	}

	private List<JCarcassonneTessera>	listaTessere;

	private static final int			ALTEZZA				= 6;

	private static final int			LARGHEZZA			= 12;

	private static final int			DIMENSIONE_CELLE	= 100;

	private static final int			NUMERO_CASELLE		= ALTEZZA * LARGHEZZA;

	private static final long			serialVersionUID	= -8246924200035116931L;
	
}
