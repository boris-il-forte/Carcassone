package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JPanel;

public class JCarcassonneLaterale extends JPanel
{

	public JCarcassonneLaterale()
	{
		this.setLayout(new FlowLayout());
		this.aggiungiBox();

	}
	
	private void aggiungiBox()
	{
		this.box = Box.createVerticalBox();
		this.add(this.box);
		this.aggiungiComponenti();
	}
	
	private void aggiungiComponenti()
	{
		this.aggiungiScorrimento();
		this.aggiungiZoom();
	}

	private void aggiungiScorrimento()
	{
		this.scorrimentoMappa = new JCarcassonneScorrimentoMappa();
		this.box.add(this.scorrimentoMappa);
		
		
	}
	
	private void aggiungiZoom()
	{
		this.zoom = new JCarcassonneZoom();
		this.box.add(this.zoom);
	}



	private Box								box;
	
	private JCarcassonneScorrimentoMappa	scorrimentoMappa;
	
	private JCarcassonneZoom				zoom;
	
	private static final long	serialVersionUID	= -1423697317579326895L;
	

}
