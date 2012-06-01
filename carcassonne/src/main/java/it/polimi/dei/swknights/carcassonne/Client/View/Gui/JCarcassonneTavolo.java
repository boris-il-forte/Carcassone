package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class JCarcassonneTavolo extends JPanel
{
	
	public JCarcassonneTavolo()
	{
		this.setLayout(new GridLayout(ALTEZZA, LARGHEZZA));
		this.setMinimumSize(new Dimension(LARGHEZZA*DIMENSIONE_CELLE, ALTEZZA*DIMENSIONE_CELLE));
		this.setMaximumSize(new Dimension(LARGHEZZA*DIMENSIONE_CELLE, ALTEZZA*DIMENSIONE_CELLE));
		this.listaTessere = new ArrayList<JCarcassonneTessera>();
		this.creaCaselle();
	}
	public void creaCaselle()
	{
		
		for(int i=0; i<NUMERO_CASELLE; i++)
		{
			JCarcassonneTessera tessera = new JCarcassonneTessera();
			this.listaTessere.add(tessera);
			this.add(tessera);
		}		
	}


	private List<JCarcassonneTessera>	listaTessere;
	
	private static final int ALTEZZA = 6;
	
	private static final int LARGHEZZA = 12;
	
	private static final int DIMENSIONE_CELLE = 80;
	
	private static final int NUMERO_CASELLE = ALTEZZA*LARGHEZZA;
	
	private static final long	serialVersionUID	= -8246924200035116931L;
}
