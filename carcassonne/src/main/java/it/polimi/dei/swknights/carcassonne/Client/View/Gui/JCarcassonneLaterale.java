package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JPanel;

public class JCarcassonneLaterale extends JPanel
{

	public JCarcassonneLaterale()
	{
		this.setLayout(new FlowLayout());
		this.aggiungiBox();
	}

	public void aggiornaTesseraCorrente(Icon tessera)
	{
		this.tesseraCorrente.setTessera(tessera);
	}

	private void aggiungiBox()
	{
		this.box = Box.createVerticalBox();
		this.aggiungiComponenti();
		this.add(this.box);

	}

	private void aggiungiComponenti()
	{
		this.aggiugiTesseraCorrente();
		this.aggiungiScorrimento();
		this.aggiungiZoom();
	}

	private void aggiugiTesseraCorrente()
	{
		final Dimension dimensione = new Dimension(LATO_TESSERA, LATO_TESSERA);
		final int spazio = 50;
		this.tesseraCorrente = new JCarcassonneTessera(BOUNDING_BOX);
		this.tesseraCorrente.setBorder(BorderFactory.createEtchedBorder());
		this.tesseraCorrente.setMinimumSize(dimensione);
		this.tesseraCorrente.setPreferredSize(dimensione);
		this.tesseraCorrente.setMaximumSize(dimensione);
		this.box.add(this.tesseraCorrente);
		this.box.add(Box.createRigidArea(new Dimension(0, spazio)));

	}

	private void aggiungiScorrimento()
	{
		this.box.add(Box.createVerticalGlue());
		this.scorrimentoMappa = new JCarcassonneScorrimentoMappa();
		this.box.add(this.scorrimentoMappa);
	}

	private void aggiungiZoom()
	{
		this.box.add(Box.createVerticalGlue());
		this.zoom = new JCarcassonneZoom();
		this.box.add(this.zoom);
	}

	private Box								box;

	private JCarcassonneTessera				tesseraCorrente;

	private JCarcassonneScorrimentoMappa	scorrimentoMappa;

	private JCarcassonneZoom				zoom;

	private static final int				LATO_TESSERA		= 150;

	private static final Rectangle			BOUNDING_BOX		= new Rectangle(1, 1, 147, 147);

	private static final long				serialVersionUID	= -1423697317579326895L;

}
