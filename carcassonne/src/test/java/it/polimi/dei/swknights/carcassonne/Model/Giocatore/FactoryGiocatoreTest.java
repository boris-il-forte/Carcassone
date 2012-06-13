package it.polimi.dei.swknights.carcassonne.Model.Giocatore;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;


import java.awt.Color;
import java.util.LinkedList;

import it.polimi.dei.swknights.carcassonne.Exceptions.FinitiColoriDisponibiliException;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.FactoryGiocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Giocatore;


public class FactoryGiocatoreTest
{

	@Test
	public void addGiocatoreee() throws Exception
	{
		FactoryGiocatore galliani = new FactoryGiocatore();
		LinkedList<Giocatore> squadra = new LinkedList<Giocatore>();
		
		for(int i=1;i<=5;i++)
		{
			squadra.add(  galliani.getGiocatore());
		}
		
		Giocatore g1 = squadra.get(0);
		Giocatore g2 = squadra.get(1);
		Giocatore g3 = squadra.get(2);
		Giocatore g4 = squadra.get(3);
		Giocatore g5 = squadra.get(4);
	
		assertTrue("non rosso il primo  g.color =  " 
				+ g1.getColore(),(g1.getColore() == Color.red) );
		
		assertTrue("non blue il sec  g.color =  " 
				+ g2.getColore(),(g2.getColore() == Color.blue) );
		
		assertTrue("non verde il terzo  g.color =  " 
				+ g3.getColore(),(g3.getColore() == Color.green) );
		
		assertTrue("non giallo il quarto g.color =  " 
				+ g4.getColore(),(g4.getColore() == Color.yellow) );
		
		assertTrue("non nero il quinto  g.color =  " 
				+ g5.getColore(),(g5.getColore() == Color.black) );
		
		
	}
	
	@Test
	public void addSevenPlayers()
	{
		
		FactoryGiocatore galliani = new FactoryGiocatore();
		LinkedList<Giocatore> squadra = new LinkedList<Giocatore>();
		
		for(int i=1;i<=7;i++)
		{
			try
			{
				squadra.add(galliani.getGiocatore());
			}
			catch (FinitiColoriDisponibiliException e)
			{
				if (squadra.size() == 5)
				{
					assertTrue(" non ti fa fare il sesto, giusto!", true);
				}
				else
				{
					fail(" finiti colori disponibili scattato con i != 6, male! i vale:" + i 
							+  "squadra size = " + squadra.size());
				}
			}
		}

		
		
	}
	
	
	
}
