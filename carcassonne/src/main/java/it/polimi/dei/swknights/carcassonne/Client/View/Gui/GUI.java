package it.polimi.dei.swknights.carcassonne.Client.View.Gui;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class GUI extends ModuloView implements ActionListener
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
	public void posizionaTessera(Coordinate coordinatePosizione)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ridaiSegnaliniDiTessere( Map<AdapterTessera, Coordinate> tessereAggiornate)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mettiEMostraPrimaTessera(AdapterTessera tessIniziale)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void notificaFinePartita()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void notificaMossaNonValida()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void aggiornaColoreCorrente(Color colore)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void cambiaEMostraTesseraCorrente(AdapterTessera tessera)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void giocaFase()
	{
		// TODO Auto-generated method stub
		
	}

}
