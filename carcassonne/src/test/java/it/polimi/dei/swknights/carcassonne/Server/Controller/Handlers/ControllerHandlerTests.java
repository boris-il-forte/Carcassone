package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import static org.junit.Assert.assertTrue;
import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import org.junit.Before;
import org.junit.Test;

public class ControllerHandlerTests
{

	ControllerMock controller;
	ModelMock model;
	
	@Before
	public void init()
	{
		model = new ModelMock();
		controller = new ControllerMock(model);
		model.posiz =0;
		controller.place=0;
		controller.pass =0;
	}
	
	@Test
	public void visitPlace()
	{
		PlaceEvent plev = new PlaceEvent(this, new Coordinate(0, 0));
		
		PlaceHandler placeH = new PlaceHandler(controller, model);

		placeH.visit(plev);

		assertTrue(" non è arrivato il place (oppure 2 o +) ",
				model.mosseNV == 1);
		
	}
	
	
	@Test
	public void visitPass()
	{
		PassEvent pev = new PassEvent(this);
		
		PassHandler pass = new PassHandler(controller, model);
		pass.visit(pev);
		assertTrue(" non è arrivato il pass (oppure 2 o +) ",
				controller.pass == 1 && model.posiz == 0);
	}
	
	
	@Test
	public void visitRuota() throws Exception
	{
		RuotaHandler rhand = new RuotaHandler(controller, model);
		RotateEvent rev = new RotateEvent(this);
		controller.getGestoreFasi().cominciaTurno();
		model.getTesseraDaMazzo();
		Debug.print(""+ controller.getGestoreFasi().getCurrentFase());
		rhand.visit(rev);
		assertTrue("noo! non ruotata", this.model.rotated==1 );
	}
	

	
	
}

class ModelMock extends ModuloModel
{
	public int  posiz =0;
	public int mosseNV =0;
	public int rotated =0;
	@Override
	public void posizionaTesseraCorrente(Coordinate coordinate) 
	{
		posiz++;
	}
	
	
	public void fire(ControllerEvent event)
	{
		if(event instanceof MossaNonValidaEvent)
		{
			mosseNV++;
		}
		else if (event instanceof UpdateRotationEvent)
		{
			rotated++;
		}
	}
			
	
}


class ControllerMock extends ModuloController
{

	public int place =0;
	public int pass =0;
	public ControllerMock(ModuloModel model)
	{
		super(model);
	}
	

	public boolean costruzioneLibera(PuntoCardinale punto)
	{
		return true;
	}
	
	
	@Override
	public synchronized void comunicaPosizionamentoTessera()
	{
		this.pass ++;
	}
	
}