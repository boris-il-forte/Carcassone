package it.polimi.dei.swknights.carcassonne.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class AreaDiGiocoTest
{

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
	public void getSetCoordinateVuoteee()
	{
		AreaDiGioco area = new AreaDiGioco();
		TesseraNormale t1 =
				new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
		
		try
		{
			area.addTessera(new Coordinate(0, 0), t1);
		}
		catch (MossaNonValidaException e)
		{
			e.printStackTrace();
		}

		Set<Coordinate> setCoord = area.getSetCoordinateVuote();
		assertTrue(" non c'è 0,1 ", setCoord.contains(new Coordinate(0, 1)));
		assertTrue(" non c'è 0,-1 ", setCoord.contains(new Coordinate(0, -1)));
		assertTrue(" non c'è 1,0 ", setCoord.contains(new Coordinate(1, 0)));
		assertTrue(" non c'è -1,0 ", setCoord.contains(new Coordinate(-1, 0)));
		assertTrue(setCoord.size() == 4);
	
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

		int contaSuccessi = 0;

		area.addTessera(new Coordinate(3, -5), t1);
		area.addTessera(new Coordinate(-3, -15), t1);
		area.addTessera(new Coordinate(0, 0), t1);

		try
		{
			area.getTessera(new Coordinate(1, 1));
		}
		catch (TesseraNonTrovataException e)
		{
			System.out.println("non trovata");
			contaSuccessi++;
		}

		try
		{
			
			area.getTessera(new Coordinate(3, -5));
			contaSuccessi++;
			area.getTessera(new Coordinate(-3, -15));
			contaSuccessi++;
			area.getTessera(new Coordinate(0, 0));
			contaSuccessi++;
		}
		catch (TesseraNonTrovataException e)
		{

		}

		if (contaSuccessi == 4)
		{
			assertTrue(true);
		}
		else
		{
			fail("ma no!!");
		}

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
