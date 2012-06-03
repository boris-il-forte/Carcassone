package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.border.BevelBorder;

public class JCarcassonneLaterale extends Box
{

	public JCarcassonneLaterale()
	{
		super(BoxLayout.Y_AXIS);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.aggiungiComponenti();
	}

	public void aggiornaTesseraCorrente(Icon tessera)
	{
		this.tesseraCorrente.setTessera(tessera);
	}

	private void aggiungiComponenti()
	{
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
		this.add(Box.createRigidArea(MINIMO_SPAZIO));
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
		this.add(this.pulsanteRotate);
	}

	private void aggiungiPass()
	{
		this.pulsantePass = new JCarcassonnePass();
		this.add(this.pulsantePass);
	}

	private void aggiungiScorrimento()
	{
		this.scorrimentoMappa = new JCarcassonneScorrimentoMappa();
		this.add(this.scorrimentoMappa);
	}

	private void aggiungiZoom()
	{
		this.zoom = new JCarcassonneZoom();
		this.add(this.zoom);
	}

	private JCarcassonneCasella				tesseraCorrente;

	private JCarcassonneButton				pulsanteRotate;

	private JCarcassonneButton				pulsantePass;

	private JCarcassonneScorrimentoMappa	scorrimentoMappa;

	private JCarcassonneZoom				zoom;

	private static final Dimension			MINIMO_SPAZIO		= new Dimension(0, 50);

	private static final int				LATO_TESSERA		= 150;

	private static final Rectangle			BOUNDING_BOX		= new Rectangle(1, 1, 147, 147);

	private static final long				serialVersionUID	= -1423697317579326895L;

}
