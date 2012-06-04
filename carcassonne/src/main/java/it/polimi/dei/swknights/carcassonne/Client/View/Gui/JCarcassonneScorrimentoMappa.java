package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
		final int larghezza = CELLE_RIGA * DIMENSIONE_PULSANTE;
		final int altezza = CELLE_RIGA * DIMENSIONE_PULSANTE;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setLayout(new GridLayout(CELLE_RIGA, CELLE_RIGA));
		this.setMinimumSize(dimensione);
		this.setPreferredSize(dimensione);
		this.setMaximumSize(dimensione);
		this.listaPulsanti = new ArrayList<JButton>();
		this.addPulsanti();
	}

	private void addPulsanti()
	{
		for (int i = 0; i < CELLE_RIGA * CELLE_RIGA; i++)
		{

			String label = labelPulsante.get(i);
			if (label != null)
			{
				JButton pulsante = new JButton(label);
				this.listaPulsanti.add(pulsante);
				this.add(pulsante);
			}
			else
			{
				this.add(new JLabel());
			}
		}
	}

	public void addActionListener(ActionListener listener)
	{
		for (JButton pulsante : this.listaPulsanti)
		{
			pulsante.addActionListener(listener);
		}
	}

	public JButton getSource(PuntoCardinale puntoCardinale)
	{
		int index = 0;
		switch (puntoCardinale)
		// TODO soluzione.... hashmap?
		{
			case nord:
				index = 0;
				break;
			case sud:
				index = 3;
				break;
			case est:
				index = 1;
				break;
			case ovest:
				index = 2;
				break;
		}
		return this.listaPulsanti.get(index);
	}

	private List<JButton>						listaPulsanti;

	private static final Map<Integer, String>	labelPulsante		= inizializzaLabelPulsante();

	private static final int					DIMENSIONE_PULSANTE	= 60;

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
