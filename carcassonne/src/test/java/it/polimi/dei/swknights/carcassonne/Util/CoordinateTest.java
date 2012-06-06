package it.polimi.dei.swknights.carcassonne.Util;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CoordinateTest
{
	private static Coordinate c1;
	
	@BeforeClass
	public static void setUp()
	{
		
	}
	
	@Before
	public void createC()
	{
		c1 = new Coordinate(0, 0);
	}
	
	@After
	public void clear()
	{
		c1 = null;
	}
	
	@Test
	public void getCoordinateA() throws Exception
	{
		
		c1 = new Coordinate(4, -3);
		List<Coordinate> listEXP = new ArrayList<Coordinate>();
		List<Coordinate> listGOT = new ArrayList<Coordinate>();
		
		listGOT.add( c1.getCoordinateA(new Coordinate(0, 1)) );
		listGOT.add( c1.getCoordinateA(new Coordinate(1, 0)) );
		listGOT.add( c1.getCoordinateA(new Coordinate(-1, 0)) );
		listGOT.add( c1.getCoordinateA(new Coordinate(0, -1)) );
	
		listEXP.add(new Coordinate(4, -2));
		listEXP.add(new Coordinate(5, -3));
		listEXP.add(new Coordinate(3, -3));
		listEXP.add(new Coordinate(4, -4));
		
		assertEquals(listEXP, listGOT);
		
	}
	
	@Test
	public void g() throws Exception
	{
		List<CoordPunto> listEXP = new ArrayList<CoordPunto>();
		List<CoordPunto> listGOT = new ArrayList<CoordPunto>();
		
		c1 = new Coordinate(0, 0);
		
		for(PuntoCardinale punto : PuntoCardinale.values())
		{
			listGOT.add (new CoordPunto( c1.getCoordinateA(punto), punto) );
		}
		
		//le coordinate + in alto hanno y negativa
		listEXP.add( new CoordPunto( new Coordinate(0, -1), PuntoCardinale.nord));
		listEXP.add( new CoordPunto( new Coordinate(0, 1), PuntoCardinale.sud));
		listEXP.add( new CoordPunto( new Coordinate(-1, 0), PuntoCardinale.ovest));
		listEXP.add( new CoordPunto( new Coordinate(1, 0), PuntoCardinale.est));
		
		pezzaliSortCoordinates(listGOT);
		pezzaliSortCoordinates(listEXP);
		

		for(CoordPunto cp : listGOT)
		{
			System.out.print(cp.punto.toString());
			System.out.println(cp.coord.toString());
			
		}
		System.out.println("__________________");
		for(CoordPunto cp : listEXP)
		{
			System.out.print(cp.punto.toString());
			System.out.println(cp.coord.toString());
		
		}
		
		
		assertEquals(listEXP, listGOT);
		
		
	}
	
	private void pezzaliSortCoordinates(List<CoordPunto> list) 
	{
	
		Collections.sort(list, new Comparator<CoordPunto>()
		{

			public int compare(CoordPunto cp1, CoordPunto cp2)
			{
				
				return cp1.punto.toInt() - cp2.punto.toInt();
			}
		}
		);
	}
	
	private class CoordPunto
	{
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((coord == null) ? 0 : coord.hashCode());
			result = prime * result + ((punto == null) ? 0 : punto.hashCode());
			return result;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj) { return true; }
			if (obj == null) { return false; }
			if (!(obj instanceof CoordPunto)) { return false; }
			CoordPunto other = (CoordPunto) obj;
			if (!getOuterType().equals(other.getOuterType())) { return false; }
			if (coord == null)
			{
				if (other.coord != null) { return false; }
			}
			else if (!coord.equals(other.coord)) { return false; }
			if (punto != other.punto) { return false; }
			return true;
		}
		public CoordPunto(Coordinate c, PuntoCardinale p) { this.coord = c; this.punto = p; }
		public Coordinate coord;
		public PuntoCardinale punto;
		private CoordinateTest getOuterType()
		{
			return CoordinateTest.this;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}