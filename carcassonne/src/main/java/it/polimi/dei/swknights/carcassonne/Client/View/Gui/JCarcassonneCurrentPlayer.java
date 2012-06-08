package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JCarcassonneCurrentPlayer extends JPanel
{

	public JCarcassonneCurrentPlayer(Map<Color, Icon> mappaSegnalini)
	{
		this.addIntestazione();
		// TODO passare icone segnalino
		this.addSegnalini(mappaSegnalini);
	}

	public void setGiocatoreCorrente(Color colore, int segnalini)
	{
		this.colore.setBackground(colore);
		this.nome.setText(ColoriGioco.getName(colore));
		this.segnalini.setGiocatore(colore, segnalini);
	}

	private void addSegnalini(Map<Color, Icon> mappaSegnalini)
	{
		this.segnalini = new JCarcassonneSegnalini(mappaSegnalini);
		this.add(this.segnalini);
	}

	private void addIntestazione()
	{
		JPanel panel = new JPanel();
		this.colore = new JLabel();
		this.colore.setPreferredSize(SIZE_COLORE);
		this.colore.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.colore.setOpaque(true);
		panel.add(this.colore);
		this.nome = new JLabel();
		this.nome.setPreferredSize(SIZE_NOME);
		panel.add(this.nome);
		this.add(panel);
	}

	private JCarcassonneSegnalini	segnalini;

	private JLabel					nome;

	private JLabel					colore;

	private static final Dimension	SIZE_COLORE			= new Dimension(40, 40);

	private static final Dimension	SIZE_NOME			= new Dimension(110, 40);

	private static final long		serialVersionUID	= -3035714996845561072L;
}
