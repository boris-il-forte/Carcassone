package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Controller.CostruzioneCitta;
import it.polimi.dei.swknights.carcassonne.server.Controller.CostruzioneStrada;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;


public class TesseraTest
{
	private static Map<Costruzione, List<PuntoCardinale>>   mappaExp;
	private static Map<Costruzione, List<PuntoCardinale>>   mappaGot;
	
	@BeforeClass
	public static void initializeTest() throws Exception
	{
	 
	}
	
	private Lati creaLatiCittaGrande()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.citta;
		Elemento sud = Elemento.citta;
		Elemento ovest = Elemento.citta;
		Elemento est = Elemento.citta;
		latiCreandi = new  Lati(nord, sud, ovest, est);
		return latiCreandi;
	}
	
	private Link creaLinkCittaGrande() throws IllegalArgumentException
	{
		/*NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl =  { true, true, true, true, true, true }; 
		Link l = new  Link(bl);
		return l;
				
	}
	
	
	@Test
	public void testCittaGrande() throws Exception
	{
		TesseraNormale tessera = new TesseraNormale(creaLatiCittaGrande(), creaLinkCittaGrande());
		
		List<PuntoCardinale> punti = new ArrayList<PuntoCardinale>();
		for(PuntoCardinale p : PuntoCardinale.values() ) //tutti connessi e unica città
		{
			punti.add(p);
		}
		
		mappaExp = new HashMap<Costruzione, List<PuntoCardinale>>();
		mappaExp.put( new CostruzioneCitta(tessera), punti);
		
		mappaGot = tessera.getCostruzioni();
		
		List<Costruzione> CostruzioniExp = listaDa( mappaExp.keySet());
		List<Costruzione> CostruzioniGot = listaDa( mappaGot.keySet());
		
		Collections.sort(CostruzioniExp, new Comparator<Costruzione>(){
	
			public int compare(Costruzione c1, Costruzione c2)
			{
				if(c1.contaElementi() != c2.contaElementi())
				{
					return c1.contaElementi() - c2.contaElementi();
				}
				else
				{
					if(c1 instanceof CostruzioneCitta && c2 instanceof CostruzioneStrada )
						return 1;
					if(c1 instanceof CostruzioneStrada && c2 instanceof CostruzioneCitta )
						return -1;
					
				}
				return 0;
			}
		});
		
		if(CostruzioniExp.size() == CostruzioniGot.size())
		{
		   	for(int i=0; i< CostruzioniExp.size(); i++)
		   	{
		   		Costruzione costruzioneExp = CostruzioniExp.get(i);
		   		Costruzione costruzioneGot = CostruzioniGot.get(i);
		   		List<PuntoCardinale> puntiExp =  mappaExp.get(costruzioneExp);
		   		List<PuntoCardinale> puntiGot =  mappaGot.get(costruzioneGot);
		   		if(puntiExp.equals(puntiGot) == false)
		   		{
		   			assertTrue("c'è una costruzione aggregata per cui i punti cardinali" +
		   					 "ritornati differiscono" ,false);
		   		}
		   	}
		   	assertTrue(true);
		}
		else
		{
			assertTrue("non hanno lo stesso numero di costruzioni!", false);
		}
	}
	
	
	
	private List<Costruzione> listaDa(Set<Costruzione> keySet)
	{
		List<Costruzione> listaC = new ArrayList<Costruzione>();
		for(Costruzione c : keySet)
		{
			listaC.add(c);
		}
		return listaC;
	}

	private boolean stesseCostruzioniMappe
	(Map<Costruzione, List<PuntoCardinale>> m1, Map<Costruzione, List<PuntoCardinale>> m2)
	{
		//DELIRIO!
		Set<Costruzione> costruzioniM1 = m1.keySet();
		Set<Costruzione> costruzioniM2 = m2.keySet();
		return  costruzioniM1.equals(costruzioniM2);

	}
	
	
	
}
