package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import java.util.List;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.View.EntryTessera;
import it.polimi.dei.swknights.carcassonne.Client.View.ScenarioDiGioco;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import org.junit.Test;

public class StampanteTest
{
	@Test
	public void stamp()
	{
		ScenarioDiGioco scenario = new ScenarioDiGioco();
		AdapterTessera ad = new AdapterTesseraObject(this.tess());
		AdapterTessera ad2 = new AdapterTesseraObject(this.tess());
		AdapterTessera ad3 = new AdapterTesseraObject(this.tess());
		
		scenario.setTessera(new Coordinate(0, 0), ad);
		scenario.setTessera(new Coordinate(0, 1), ad2);
		scenario.setTessera(new Coordinate(0, 2), ad3);
		
		scenario.setTessera(new Coordinate(1, 0), ad3);
		scenario.setTessera(new Coordinate(1, 1), ad3);			
		scenario.setTessera(new Coordinate(1, 2), ad3);
		
		scenario.setTessera(new Coordinate(2, 0), ad3);
		scenario.setTessera(new Coordinate(2, 1), ad3);
		scenario.setTessera(new Coordinate(2, 2), ad3);
		
		Stampante st = new Stampante();
		List<EntryTessera> et = scenario.getEntryList(new Coordinate(0, 0),
				new Coordinate(2, 2));
		
		Debug.print(""+et.size());
		Debug.print(""+ et.get(0).getCoordinate());
		st.addTessera(et.get(0));
		Debug.print(""+ et.get(1).getCoordinate());
		///!!!st.addTessera(et.get(1));
		
		Debug.print(""+st.toString());
	}
	
	private Tessera tess()
	{
		return new TesseraNormale(this.creaLatiCittaGrande(), this.creaLinkCittaGrande());
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
