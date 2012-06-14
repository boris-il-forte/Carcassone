package it.polimi.dei.swknights.carcassonne.Model.Giocatore;

import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Exceptions.SegnaliniFinitiException;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.FactoryGiocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Giocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Segnalino;

import org.junit.Test;
import static org.junit.Assert.*;

public class GiocatoreTest
{

	@Test
	public void addPuntiETogliSegnalini() throws Exception
	{
		FactoryGiocatore factory = new FactoryGiocatore();
		Giocatore g1 = factory.getGiocatore();
		g1.addPunti(30);
		assertTrue(" non 30 punti", g1.getPunti() == 30);

		assertTrue("non rosso il primo", g1.getColore().equals(Color.red));
		
		int i = 0;
		try
		{
			for (i = 1; i <= 8; i++)
			{
				g1.getSegnalino();
			}
		}
		catch (SegnaliniFinitiException fe)
		{
			assertTrue(" dopo il 7 deve dare errore, nè prima nè dopo", i == 8);
		}

	
		g1.addSegnalino(new Segnalino(Color.red));
		assertTrue( g1.getSegnalino().getColore() == Color.red );
		
	
	}
	

}
