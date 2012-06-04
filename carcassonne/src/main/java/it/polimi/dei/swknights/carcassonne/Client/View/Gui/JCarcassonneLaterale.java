package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.border.BevelBorder;

public class JCarcassonneLaterale extends Box implements ActionListener
{

	private JCarcassonneCurrentPlayer	giocatoreCorrente;

	public JCarcassonneLaterale(Gui gui)
	{
		super(BoxLayout.Y_AXIS);
		this.view = gui;
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.aggiungiComponenti();
	}

	public void aggiornaTesseraCorrente(Icon tessera)
	{
		this.tesseraCorrente.setTessera(tessera);
	}

	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == this.pulsantePass.getEventSource())
		{
			this.view.passCliccato();
		}
		else if(source == this.pulsanteRotate.getEventSource())
		{
			this.view.rotateCliccato();
		}
		else
		{
			for(PuntoCardinale punto : PuntoCardinale.values())
			{
				if(this.scorrimentoMappa.getSource(punto) == source)
				{
					this.view.muoviViewA(punto, 1);
				}
			}
		}
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
		this.aggiungiZoom();
		this.aggiungiSpazio();
	}

	private void aggiungiSpazio()
	{
		this.add(Box.createVerticalGlue());
	}

	private void aggiungiGiocatoreCorrente()
	{
		this.giocatoreCorrente = new JCarcassonneCurrentPlayer();
		this.add(this.giocatoreCorrente);
		
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
		this.pulsanteRotate = new JCarcassonneRotate();
		this.pulsanteRotate.setActionListener(this);
		this.add(this.pulsanteRotate);
	}

	private void aggiungiPass()
	{
		this.pulsantePass = new JCarcassonnePass();
		this.pulsantePass.setActionListener(this);
		this.add(this.pulsantePass);
	}

	private void aggiungiScorrimento()
	{
		this.scorrimentoMappa = new JCarcassonneScorrimentoMappa();
		this.scorrimentoMappa.addActionListener(this);
		this.add(this.scorrimentoMappa);
	}

	private void aggiungiZoom()
	{
		this.zoom = new JCarcassonneZoom(this.view);
		this.add(this.zoom);
	}

	private JCarcassonneCasella				tesseraCorrente;

	private JCarcassonneButton				pulsanteRotate;

	private JCarcassonneButton				pulsantePass;

	private JCarcassonneScorrimentoMappa	scorrimentoMappa;

	private JCarcassonneZoom				zoom;

	private Gui								view;

	private static final int				LATO_TESSERA		= 150;

	private static final Rectangle			BOUNDING_BOX		= new Rectangle(1, 1, 147, 147);

	private static final long				serialVersionUID	= -1423697317579326895L;

}
