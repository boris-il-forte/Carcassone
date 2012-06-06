package it.polimi.dei.swknights.carcassonne.Util;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


public class ColoriGiocoTest
{
	
	@BeforeClass
	public static void setUp()
	{
		
	}
	
	@Test
	public void getListaColoriOrdineGiusto() throws Exception
	{
		List<Color> lista =  ColoriGioco.getListaColori();
		
		List<Color> listaExp = new LinkedList<Color>();
		//R, B, G, Y or K 
		listaExp.add(Color.RED);
		listaExp.add(Color.BLUE);
		listaExp.add(Color.GREEN);
		listaExp.add(Color.YELLOW);
		listaExp.add(Color.BLACK);
		
		
		assertEquals(lista, listaExp);
		
	}
	
	@Test
	public  void nomiColoriGiusti() throws Exception
	{
		List<String> nomiColoriGot = new LinkedList<String>() ;
		List<String> nomiColoriExp  = new LinkedList<String>();
		
		for(Color colore : ColoriGioco.getListaColori())
		{
			nomiColoriGot.add( ColoriGioco.getName(colore));
		}
		
		nomiColoriExp.add("Rosso");
		nomiColoriExp.add("Blu");
		nomiColoriExp.add("Verde");
		nomiColoriExp.add("Giallo");
		nomiColoriExp.add("Nero");
		
		assertEquals(nomiColoriExp, nomiColoriGot);	
	
	}
	
	@Test
	public  void sigleColoriGiusti() throws Exception
	{
		List<String> sigleColoriGot = new LinkedList<String>() ;
		List<String> sigleColoriExp  = new LinkedList<String>();
		
		for(Color colore : ColoriGioco.getListaColori())
		{
			sigleColoriGot.add( ColoriGioco.getSigla(colore));
		}
		
		sigleColoriExp.add("R");
		sigleColoriExp.add("B");
		sigleColoriExp.add("G");
		sigleColoriExp.add("Y");
		sigleColoriExp.add("K");
		
		assertEquals(sigleColoriExp, sigleColoriGot);	
	}
	
	
	
}
