package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JCarcassonnePlayer extends JPanel
{

	public JCarcassonnePlayer(Color colore)
	{
		this.setPreferredSize(DIMENSIONE);
		this.setLayout(new FlowLayout());
		this.addLabelColore(colore);
		this.addLabelNome(colore);
		this.addLabelPunteggio();

	}

	public void setPunteggio(Integer value)
	{
		this.labelPunteggio.setText(value.toString());

	}

	private void addLabelColore(Color colore)
	{
		JLabel labelColore = new JLabel();
		labelColore.setBackground(colore);
		labelColore.setOpaque(true);
		labelColore.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		labelColore.setPreferredSize(DIMENSIONE_QUADRATO);
		this.add(labelColore);

	}

	private void addLabelNome(Color colore)
	{
		String nome = ColoriGioco.getName(colore);
		JLabel labelNome = new JLabel(nome);
		this.add(labelNome);

	}

	private void addLabelPunteggio()
	{
		this.labelPunteggio = new JLabel("0");
		this.add(this.labelPunteggio);
	}

	private JLabel					labelPunteggio;
	private static final Dimension	DIMENSIONE_QUADRATO	= new Dimension(30, 30);
	private static Dimension		DIMENSIONE			= new Dimension(150, 50);
	private static final long		serialVersionUID	= 1038809477224426681L;

}
