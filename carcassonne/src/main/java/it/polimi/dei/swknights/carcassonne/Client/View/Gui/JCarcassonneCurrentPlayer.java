package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JCarcassonneCurrentPlayer extends JPanel
{

	public JCarcassonneCurrentPlayer()
	{
		this.setLayout(new BorderLayout());
		this.addIntestazione();
		//TODO passare icone segnalino
		this.addSegnalini();
	}
	
	public void setGiocatoreCorrente(Color colore, int segnalini)
	{
		this.colore.setBackground(colore);
		this.nome.setText(ColoriGioco.getName(colore));
		this.segnalini.setGiocatore(colore, segnalini);
	}

	private void addSegnalini()
	{
		this.segnalini = new JCarcassonneSegnalini(null);
		this.add(this.segnalini);		
	}

	private void addIntestazione()
	{
		JPanel panel = new JPanel();
		this.colore = new JLabel();
		this.colore.setPreferredSize(SIZE_COLORE);
		panel.add(this.colore);
		this.nome = new JLabel();
		panel.add(this.nome);
		this.colore.setPreferredSize(SIZE_COLORE);
		
	}

	private JCarcassonneSegnalini	segnalini;

	private JLabel					nome;

	private JLabel					colore;

	private static final Dimension	SIZE_COLORE	= new Dimension(40,40);

	private static final long		serialVersionUID	= -3035714996845561072L;
}
