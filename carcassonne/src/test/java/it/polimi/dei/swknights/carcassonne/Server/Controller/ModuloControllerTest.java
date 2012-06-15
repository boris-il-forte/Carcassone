package it.polimi.dei.swknights.carcassonne.Server.Controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
public class ModuloControllerTest
{
	
	ModuloModel model;
	ModuloController controller ;
	
	@Before
	public void init()
	{
		model = new ModuloModel();
	    controller = new ModuloController(model);
	}
	
	
	@Test
	public void vicinato() throws Exception
	{
		this.model.getTesseraDaMazzo();
		this.model.posizionaTesseraCorrente(new Coordinate(2, 2));
		boolean td = this.controller.tuttoVicinatoDAccordo(new Coordinate(2, 2));
		assertTrue(" ma no! in mezzo al nulla ritorna false perchè almeno un vicino ci deve essere"
				, td==false);
	}
	
	@Test
	public void getContaPuntiNotNull() throws Exception
	{
		assertTrue("ma no un null!",  this.controller.getContapunti() != null);
	}
	
	@Test
	public void getGestoreFasiNotNull() throws Exception
	{
		assertTrue("ma no un null!",  this.controller.getGestoreFasi() != null);
	}
	

	
	
	
	
	
	
	
	
	
	
}
