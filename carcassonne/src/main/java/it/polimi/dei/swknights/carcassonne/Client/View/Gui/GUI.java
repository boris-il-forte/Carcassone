package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

public class GUI extends View implements ActionListener
{

	@Override
	public void muoviViewA(PuntoCardinale puntoCardinale, int quantita)
	{
		// TODO Auto-generated method stub
		
	}

	public JFrame				finestra;

	public JCarcassonneMenu		menu;

	public JCarcassonneLaterale	barraLaterale;

	public JCarcassonneTavolo	Tavolo;

	public List					myJCarcassonneMenu;
	public List					myJCarcassonneLaterale;
	public List					myJCarcassonneTavolo;

	public void showGui()
	{
	}

	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaMappa()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void posizionaTessera()
	{
		// TODO Auto-generated method stub
		
	}

}
