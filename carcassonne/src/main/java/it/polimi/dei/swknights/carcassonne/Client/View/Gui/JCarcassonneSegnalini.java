package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Class representing the markers' counter
 * 
 * @author dave
 * 
 */
public class JCarcassonneSegnalini extends JPanel
{
	/**
	 * Default constructor
	 * 
	 * @param mappaIcone
	 *            the marker images map
	 */
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

	/**
	 * Set the current player's marker
	 * 
	 * @param colore
	 *            the current player's color
	 * @param numeroSegnalini
	 *            the current player's number of markers
	 */
	public void setGiocatore(Color colore, Integer numeroSegnalini)
	{
		Icon icon = this.mappaIcone.get(colore);
		this.segnalino.setIcon(icon);
		this.contatore.setText(numeroSegnalini.toString());
	}

	private JLabel					segnalino;

	private JLabel					contatore;

	private Map<Color, Icon>		mappaIcone;

	private static final Dimension	DIMENSIONE_SEGNALINO	= new Dimension(40, 40);

	private static final long		serialVersionUID		= -4862597979260290196L;

}
