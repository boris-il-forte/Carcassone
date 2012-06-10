package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JCarcassonneZoom extends JPanel implements ChangeListener
{
	public JCarcassonneZoom(Gui gui)
	{
		this.view = gui;
		final int larghezza = 100;
		final int altezza = 45;
		Dimension dimensione = new Dimension(larghezza, altezza);
		this.setPreferredSize(dimensione);
		JLabel label = new JLabel("Zoom");
		this.add(label);
		this.slider = new JSlider();
		this.slider.setPreferredSize(dimensione);
		this.add(this.slider);
		this.slider.addChangeListener(this);

	}

	public void stateChanged(ChangeEvent e)
	{
		Object source = e.getSource();
		if (source == this.slider)
		{
			int zoom = this.slider.getValue();
			this.view.zoomModificato(zoom / SENSIBILITA_SLIDER);
		}

	}

	private JSlider				slider;

	private Gui					view;

	private static final int	SENSIBILITA_SLIDER	= 20;

	private static final long	serialVersionUID	= -3086842872745087592L;
}
