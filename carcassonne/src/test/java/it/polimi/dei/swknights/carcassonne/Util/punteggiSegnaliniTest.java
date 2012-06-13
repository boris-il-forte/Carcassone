package it.polimi.dei.swknights.carcassonne.Util;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class punteggiSegnaliniTest
{
	@Test
	public void testaPuntiUnVincitore()
	{
		PunteggiSegnalini puntiSegnalini = new PunteggiSegnalini();

		puntiSegnalini.addPunteggi(Color.BLACK, 10);

		List<Color> listaVincitori = puntiSegnalini.getVincitoriAttuale();

		if (listaVincitori.size() != 1)
		{
			fail("dovrebbe essere solo il nero a vincere!");
		}
		if (!listaVincitori.get(0).equals(Color.black))
		{ 
			fail("dovrebbe essere il nero a vincere!");
		}

		assertTrue(" ok ha vinto il nero e basta, bene ", true);

	}
	
	
	@Test
	public void testaPuntiDueVincitori()
	{
		
		PunteggiSegnalini puntiSegnalini = new PunteggiSegnalini();

		puntiSegnalini.addPunteggi(Color.BLACK, 10);
		puntiSegnalini.addPunteggi(Color.RED, 10);

		List<Color> listaVincitori = puntiSegnalini.getVincitoriAttuale();

		Collections.sort(listaVincitori, new Comparator<Color>()
		{

			public int compare(Color c1, Color c2)
			{
				return c1.toString().compareTo(c2.toString());
			}
		});
		
		if (listaVincitori.size() == 1)
		{
			fail("dovrebbero essere in 2 a vincere!");
		}
		if (!listaVincitori.get(0).equals(Color.black))
		{ 
			fail("a nero a vincere! il get(0) = " + listaVincitori.get(0));
		}
		if (!listaVincitori.get(1).equals(Color.red))
		{ 
			fail("a nero a vincere! il get(1) = " + listaVincitori.get(1));
		}

		assertTrue(" ok ha vinto il nero e basta, bene ", true);
		
		
	}
	
	@Test
	public void testaPuntiTuttiZeroListaVuota() throws Exception
	{
		
		PunteggiSegnalini puntiSegnalini = new PunteggiSegnalini();

		puntiSegnalini.addPunteggi(Color.black, 0);
		puntiSegnalini.addPunteggi(Color.red, 0);
		puntiSegnalini.addPunteggi(Color.green, 0);
		puntiSegnalini.addPunteggi(Color.blue, 0);
		puntiSegnalini.addPunteggi(Color.yellow, 0);
		
		List<Color> listaVincitori = puntiSegnalini.getVincitoriAttuale();

		if (listaVincitori.size() != 0)
		{
			fail("dovrebbe essere vuota questa lista, è usata per le costruzioni!");
		}

		assertTrue(" ok non ha vinto nessuno, la lista è vuota ", true);
		
	}
	
	
	
	
	
	
}
