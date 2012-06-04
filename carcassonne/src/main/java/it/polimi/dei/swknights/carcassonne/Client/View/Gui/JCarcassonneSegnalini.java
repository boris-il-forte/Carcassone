package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class JCarcassonneSegnalini extends JPanel
{
	public JCarcassonneSegnalini(Map<Color, Icon> mappaIcone)
	{
		this.mappaIcone = mappaIcone;
		this.segnalino = new JLabel();
		this.segnalino.setPreferredSize(DIMENSIONE_SEGNALINO);
		this.segnalino.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.add(this.segnalino);
		this.contatore = new JLabel();
		this.add(this.contatore);
	}

	public void setGiocatore(Color colore, Integer numeroSegnalini)
	{
		Icon icon = null;// this.mappaIcone.get(colore);
		this.segnalino.setIcon(icon);
		this.contatore.setText(numeroSegnalini.toString());
	}

	private JLabel					segnalino;

	private JLabel					contatore;

	private Map<Color, Icon>		mappaIcone;

	private static final Dimension	DIMENSIONE_SEGNALINO	= new Dimension(40, 40);

	private static final long		serialVersionUID		= -4862597979260290196L;

}
