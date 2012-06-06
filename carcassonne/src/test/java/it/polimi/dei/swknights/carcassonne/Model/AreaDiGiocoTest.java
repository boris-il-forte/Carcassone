package it.polimi.dei.swknights.carcassonne.Model;

import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.Costruzione;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneCitta;
import it.polimi.dei.swknights.carcassonne.Server.Controller.Costruzioni.CostruzioneStrada;
import it.polimi.dei.swknights.carcassonne.Server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class AreaDiGiocoTest
{

	private static Map<Costruzione, List<PuntoCardinale>>	mappaExp;
	private static Map<Costruzione, List<PuntoCardinale>>	mappaGot;

	@BeforeClass
	public static void initializeTest() throws Exception
	{

	}

	
	@Test
	public void collisionDetectAddTessera() throws Exception
	{
		AreaDiGioco area = new AreaDiGioco();
		
		TesseraNormale t1 = new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		
		try
		{
			area.addTessera(new Coordinate(0, 0), t1);
		}
		catch (MossaNonValidaException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			area.addTessera(new Coordinate(0, 0), t1);
			assertTrue(false);
		}
		catch (MossaNonValidaException e)
		{
			assertTrue(true);
		}		
		
	}
	
	@Test
	public void getCoordinateFromCard() throws Exception
	{
		AreaDiGioco area = new AreaDiGioco();
		TesseraNormale t1 = new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		
		try
		{
			area.addTessera(new Coordinate(2, 3), t1);
		}
		catch (MossaNonValidaException e)
		{
			e.printStackTrace();
		}
		
		Coordinate cTest = area.getCoordinateTessera(t1);
		
		assertEquals(cTest, new Coordinate(2, 3));
	
	}
	
	@Test
	public void getTesseraTrovataONo() throws Exception
	{
		AreaDiGioco area = new AreaDiGioco();
		TesseraNormale t1 = new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		
		int contaSuccessi=0;
		
		area.addTessera(new Coordinate(3, -5), t1);
		area.addTessera(new Coordinate(-3, -15), t1);
		area.addTessera(new Coordinate(0, 0), t1);
		
		try
		{
			area.getTessera(new Coordinate(1, 1));
		}
		catch(TesseraNonTrovataException e)
		{
			System.out.println("non trovata");
			contaSuccessi ++;
		}
		
		try
		{
			area.getTessera(new Coordinate(3, -5));
			contaSuccessi ++;
			area.getTessera(new Coordinate(-3, -15));
			contaSuccessi ++;
			area.getTessera(new Coordinate(0,0));
			contaSuccessi++;
		}
		catch(TesseraNonTrovataException e)
		{
			
		}
		
		if ( contaSuccessi == 4)
			assertTrue(true);
		else
			fail("ma no!!");
		
		
	}
	
	
	
	

	private boolean stesseListePuntiCardinali(List<Costruzione> CostruzioniExp,
			List<Costruzione> CostruzioniGot)
	{
		ordinaCostruzioni(CostruzioniGot);
		ordinaCostruzioni(CostruzioniExp);

		if (CostruzioniExp.size() == CostruzioniGot.size())
		{
			for (int i = 0; i < CostruzioniExp.size(); i++)
			{
				Costruzione costruzioneExp = CostruzioniExp.get(i);
				Costruzione costruzioneGot = CostruzioniGot.get(i);
				List<PuntoCardinale> puntiExp = mappaExp.get(costruzioneExp);
				List<PuntoCardinale> puntiGot = mappaGot.get(costruzioneGot);
				if (puntiExp.equals(puntiGot) == false) { return false; }
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	private void ordinaCostruzioni(List<Costruzione> costruzioniGot)
	{
		Collections.sort(costruzioniGot, new Comparator<Costruzione>()
		{

			public int compare(Costruzione c1, Costruzione c2)
			{
				if (c1.contaElementi() != c2.contaElementi())
				{
					return c1.contaElementi() - c2.contaElementi();
				}
				else
				{
					if (c1 instanceof CostruzioneCitta && c2 instanceof CostruzioneStrada) { return 1; }
					if (c1 instanceof CostruzioneStrada && c2 instanceof CostruzioneCitta) { return -1; }

				}
				return 0;
			}
		});
	}

	private List<Costruzione> listaDa(Set<Costruzione> keySet)
	{
		List<Costruzione> listaC = new ArrayList<Costruzione>();
		for (Costruzione c : keySet)
		{
			listaC.add(c);
		}
		return listaC;
	}

	private Lati creaLatiCittaGrande()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.citta;
		Elemento sud = Elemento.citta;
		Elemento ovest = Elemento.citta;
		Elemento est = Elemento.citta;
		latiCreandi = new Lati(nord, sud, ovest, est);
		return latiCreandi;
	}

	private Link creaLinkCittaGrande() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { true, true, true, true, true, true };
		Link l = new Link(bl);
		return l;
	
	}

}
