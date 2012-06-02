package it.polimi.dei.swknights.carcassonne.Util;

import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class PunteggiTest
{

	private Punteggi	punti;

	@Before
	public void setUp()
	{
		punti = new Punteggi();
	}

	@Test
	public void addPunteggi()
	{

		punti.addPunteggi(Color.BLACK, 10);

		try
		{
			punti.addPunteggi(Color.BLACK, -10);
		}
		catch (IllegalArgumentException e)
		{
			// do nothing
		}
		int pBlue = punti.get(Color.BLACK);

		if (pBlue != 10) fail(" il blue dovrebbe avere 10 punti!");

		punti.addPunteggi(Color.RED, 0);

		int pRed = punti.get(Color.RED);

		if (pRed != 0) fail("il red dovrebbe avere 0 punti !");

	}

	@Test
	public void addPunteggioOggetto()
	{
		Punteggi puntiParziali = new Punteggi();
		puntiParziali.addPunteggi(Color.BLACK, 10);
		
		punti.addPunteggi(Color.RED, 18);
		punti.addPunteggi(puntiParziali);
		
		if (punti.get(Color.BLACK) != 10)
			fail("il black dovrebbe avere 10 punti");
		if(punti.get(Color.RED ) != 18)
			fail("il rosso dovrebbe avere 18 punti");

	}





	
	
	





}
