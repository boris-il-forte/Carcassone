package it.polimi.dei.swknights.carcassonne.Client.View.Gui;


import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JCarcassonneScorrimentoMappa extends JPanel
{
	public JCarcassonneScorrimentoMappa()
	{
		this.setLayout(new GridLayout(3, 3));
		this.setSize(CELLE_RIGA*DIMENSIONE_PULSANTE, CELLE_RIGA*DIMENSIONE_PULSANTE);
		this.listaPulsanti = new ArrayList<JButton>();
		this.addPulsanti();
	}

	private void addPulsanti()
	{
		for (int i = 0; i < CELLE_RIGA * CELLE_RIGA; i++)
		{
			if (i % 2 == 1)
			{
				JButton pulsante = new JButton(labelPulsante.get(i));
				this.listaPulsanti.add(pulsante);
				this.add(pulsante);
			}
			else
			{
				this.add(new JLabel());
			}
		}

	}

	private List<JButton>						listaPulsanti;

	private static final Map<Integer, String>	labelPulsante		= inizializzaLabelPulsante();

	private static final int					DIMENSIONE_PULSANTE	= 20;

	private static final int					CELLE_RIGA			= 3;

	private static final long					serialVersionUID	= 6405877376841871358L;

	private static Map<Integer, String> inizializzaLabelPulsante()
	{
		HashMap<Integer, String> mappa = new HashMap<Integer, String>();
		mappa.put(1, "Up");
		mappa.put(3, "Lt");
		mappa.put(5, "Rt");
		mappa.put(7, "Dw");
		return mappa;
	}

}
