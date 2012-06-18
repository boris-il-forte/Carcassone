package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class representing Score entry for the score panel
 * @author dave
 *
 */
public class JCarcassonnePlayer extends JPanel
{

	/**
	 * default constructor
	 * @param colore the player's color
	 */
	public JCarcassonnePlayer(Color colore)
	{
		this.setPreferredSize(DIMENSIONE);
		this.setLayout(new FlowLayout());
		this.addLabelColore(colore);
		this.addLabelNome(colore);
		this.addLabelPunteggio();
		this.setOpaque(false);

	}

	/**
	 * method that set the player's score
	 * @param value
	 */
	public void setPunteggio(Integer value)
	{
		this.labelPunteggio.setText(value.toString());
		this.labelPunteggio.setForeground(Color.YELLOW);

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
		labelNome.setForeground(Color.YELLOW);
		this.add(labelNome);

	}

	private void addLabelPunteggio()
	{
		this.labelPunteggio = new JLabel("0");
		this.add(this.labelPunteggio);
	}

	private JLabel					labelPunteggio;

	private static final int		ALTEZZA_QUADRATO	= 30;

	private static final int		LARGHEZZA			= 150;

	private static final int		ALTEZZA				= 50;

	private static final Dimension	DIMENSIONE_QUADRATO	= new Dimension(ALTEZZA_QUADRATO, ALTEZZA_QUADRATO);

	private static final Dimension	DIMENSIONE			= new Dimension(LARGHEZZA, ALTEZZA);

	private static final long		serialVersionUID	= 1038809477224426681L;

}
