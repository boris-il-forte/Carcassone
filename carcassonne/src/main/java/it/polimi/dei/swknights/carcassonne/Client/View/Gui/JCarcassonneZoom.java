package it.polimi.dei.swknights.carcassonne.Client.View.Gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class JCarcassonneZoom extends JPanel
{
	public JCarcassonneZoom()
	{
		JLabel label = new JLabel("Zoom");
		this.add(label);
		this.slider = new JSlider();
		this.add(this.slider);
	}
	
	private JSlider	slider;
	
	private static final long	serialVersionUID	= -3086842872745087592L;
}
