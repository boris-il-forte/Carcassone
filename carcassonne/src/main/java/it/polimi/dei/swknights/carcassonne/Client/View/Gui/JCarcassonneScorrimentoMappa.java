package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JCarcassonneScorrimentoMappa extends JPanel
{
	public JCarcassonneScorrimentoMappa(Map<String, Icon> mappaIcone)
	{
		final int larghezza = CELLE_RIGA * DIMENSIONE_PULSANTE;
		final int altezza = CELLE_RIGA * DIMENSIONE_PULSANTE;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setLayout(new GridLayout(CELLE_RIGA, CELLE_RIGA));
		this.setMinimumSize(dimensione);
		this.setPreferredSize(dimensione);
		this.setMaximumSize(dimensione);
		this.listaPulsanti = new ArrayList<JButton>();
		this.addPulsanti(mappaIcone);
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
		final int nord = 0, sud = 3, est = 2, ovest = 1;
		int index = 0;
		switch (puntoCardinale)
		{
			case nord:
				index = nord;
				break;
			case sud:
				index = sud;
				break;
			case est:
				index = est;
				break;
			case ovest:
				index = ovest;
				break;
			default:
				break;
		}
		return this.listaPulsanti.get(index);
	}

	private void addPulsanti(Map<String, Icon> mappaIcone)
	{
		for (int i = 0; i < CELLE_RIGA * CELLE_RIGA; i++)
		{
	
			String label = LABEL_MAP.get(i);
			if (label != null)
			{
				Icon icona = mappaIcone.get(label);
				JButton pulsante = new JButton(icona);
				this.listaPulsanti.add(pulsante);
				this.add(pulsante);
			}
			else
			{
				this.add(new JLabel());
			}
		}
	}

	private static Map<Integer, String> inizializzaLabelPulsante()
	{
		final int nord = 1, sud = 7, est = 5, ovest = 3;
		HashMap<Integer, String> mappa = new HashMap<Integer, String>();
		mappa.put(nord, "up");
		mappa.put(ovest, "lt");
		mappa.put(est, "rt");
		mappa.put(sud, "dw");
		return mappa;
	}

	private List<JButton>	listaPulsanti;

	private static final Map<Integer, String>	LABEL_MAP			= inizializzaLabelPulsante();

	private static final int					DIMENSIONE_PULSANTE	= 60;

	private static final int					CELLE_RIGA			= 3;

	private static final long					serialVersionUID	= 6405877376841871358L;

}
