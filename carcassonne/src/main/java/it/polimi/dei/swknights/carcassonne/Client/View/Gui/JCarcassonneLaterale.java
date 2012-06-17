package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class JCarcassonneLaterale extends Box implements ActionListener, KeyListener, FocusListener
{

	/**
	 * Default constructor
	 * 
	 * @param gui
	 *            the GUI object who uses this frame.
	 */
	public JCarcassonneLaterale(Gui gui)
	{
		super(BoxLayout.Y_AXIS);
		this.view = gui;
		this.mappaIcone = this.view.getIcone();
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.aggiungiComponenti();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	/**
	 * Method that set up the current tile.
	 * 
	 * @param tessera
	 *            the icon of the current tile
	 */
	public void aggiornaTesseraCorrente(Icon tessera)
	{
		this.tesseraCorrente.setTessera(tessera, true);
	}

	/**
	 * method that updates current player's panel
	 * 
	 * @param colore
	 *            current player's color
	 * @param numeroSegnalini
	 *            current player's number of markersf
	 */
	public void aggiornaGiocatoreCorrente(Color colore, int numeroSegnalini)
	{
		this.giocatoreCorrente.setGiocatoreCorrente(colore, numeroSegnalini);
		this.labelCorrente.setText(this.view.getLabelCorrente());
		this.labelCorrente.setHorizontalTextPosition(SwingConstants.CENTER);
	}

	/**
	 * Buttons action handler
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == this.pulsantePass.getEventSource())
		{
			this.view.passCliccato();
		}
		else if (source == this.pulsanteRotate.getEventSource())
		{
			this.view.rotateCliccato();
		}
		else
		{
			for (PuntoCardinale punto : PuntoCardinale.values())
			{
				if (this.scorrimentoMappa.getSource(punto) == source)
				{
					this.view.muoviViewA(punto, 1);
				}
			}
		}
	}

	/**
	 * Keyboard action handler
	 */
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		switch (key)
		{
			case KeyEvent.VK_LEFT:
				this.view.muoviViewA(PuntoCardinale.ovest, 1);
				break;
			case KeyEvent.VK_RIGHT:
				this.view.muoviViewA(PuntoCardinale.est, 1);
				break;
			case KeyEvent.VK_UP:
				this.view.muoviViewA(PuntoCardinale.nord, 1);
				break;
			case KeyEvent.VK_DOWN:
				this.view.muoviViewA(PuntoCardinale.sud, 1);
				break;
			case KeyEvent.VK_R:
				this.view.rotateCliccato();
				break;
			case KeyEvent.VK_P:
				this.view.passCliccato();
				break;
			default:
				break;
		}
	}

	/**
	 * useless handler from interface
	 */
	public void keyReleased(KeyEvent e)
	{
	}

	/**
	 * useless handler from interface
	 */
	public void keyTyped(KeyEvent e)
	{
	}
	
	private void aggiungiComponenti()
	{
		this.aggiungiSpazio();
		this.aggiungiGiocatoreCorrente();
		this.aggiungiSpazio();
		this.aggiugiTesseraCorrente();
		this.aggiungiSpazio();
		this.aggiungiRotate();
		this.aggiungiSpazio();
		this.aggiungiSpazio();
		this.aggiungiPass();
		this.aggiungiSpazio();
		this.aggiungiScorrimento();
		this.aggiungiSpazio();
	}

	private void aggiungiSpazio()
	{
		this.add(Box.createVerticalGlue());
	}

	private void aggiungiGiocatoreCorrente()
	{
		Map<Color, Icon> mappaSegnalini = this.view.getMappaSegnalini();
		this.giocatoreCorrente = new JCarcassonneCurrentPlayer(mappaSegnalini);
		this.labelCorrente = new JLabel();
		this.add(this.giocatoreCorrente);
		this.aggiungiSpazio();
		this.add(this.labelCorrente);
	}

	private void aggiugiTesseraCorrente()
	{
		final Dimension dimensione = new Dimension(LATO_TESSERA, LATO_TESSERA);
		this.tesseraCorrente = new JCarcassonneCasella(BOUNDING_BOX);
		this.tesseraCorrente.setBorder(BorderFactory.createEtchedBorder());
		this.tesseraCorrente.setMinimumSize(dimensione);
		this.tesseraCorrente.setPreferredSize(dimensione);
		this.tesseraCorrente.setMaximumSize(dimensione);
		this.add(this.tesseraCorrente);
	}

	private void aggiungiRotate()
	{
		Icon icona = this.mappaIcone.get("rotate");
		this.pulsanteRotate = new JCarcassonneButton(icona);
		this.pulsanteRotate.setActionListener(this);
		this.add(this.pulsanteRotate);
	}

	private void aggiungiPass()
	{
		this.pulsantePass = new JCarcassonneButton(this.mappaIcone.get("pass"));
		this.pulsantePass.setActionListener(this);
		this.add(this.pulsantePass);
	}

	private void aggiungiScorrimento()
	{
		this.scorrimentoMappa = new JCarcassonneScorrimentoMappa(this.mappaIcone);
		this.scorrimentoMappa.addActionListener(this);
		this.add(this.scorrimentoMappa);
	}

	private Map<String, Icon>				mappaIcone;

	private JCarcassonneCurrentPlayer		giocatoreCorrente;

	private JLabel							labelCorrente;

	private JCarcassonneCasella				tesseraCorrente;

	private JCarcassonneButton				pulsanteRotate;

	private JCarcassonneButton				pulsantePass;

	private JCarcassonneScorrimentoMappa	scorrimentoMappa;

	private transient Gui					view;

	private static final int				LATO_TESSERA		= 150;

	private static final Rectangle			BOUNDING_BOX		= new Rectangle(1, 1, 147, 147);

	private static final long				serialVersionUID	= -1423697317579326895L;

	public void focusGained(FocusEvent arg0)
	{
	}

	public void focusLost(FocusEvent arg0)
	{
		this.grabFocus();
	}

}
