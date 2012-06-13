package it.polimi.dei.swknights.carcassonne.Model.Giocatore;

import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.FactoryGiocatore;
import it.polimi.dei.swknights.carcassonne.Server.Model.Giocatore.Giocatore;

import org.junit.Test;
import static org.junit.Assert.*;

public class GiocatoreTest
{

	@Test
	public void gio() throws Exception
	{
		FactoryGiocatore factory = new FactoryGiocatore();
		Giocatore g1 =  factory.getGiocatore();
		g1.addPunti(30);
		assertTrue(" non 30 punti", g1.getPunti() == 30);
	
		
		assertTrue(true);
	
	
	
	}
	
	
}
