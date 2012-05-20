package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Controller.Controller;
import it.polimi.dei.swknights.carcassonne.server.Model.AreaDiGioco;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class PlaceHandler extends ControllerHandler
{

	private Controller	controller;
	private AreaDiGioco	areaDiGioco;

	public PlaceHandler(Controller controller, AreaDiGioco areaGioco)
	{
		this.controller = controller;
		this.areaDiGioco = areaGioco;
	}

	@Override
	public void visit(PlaceEvent event)
	{
		Tessera tessera = this.controller.getTesseraCorrente();
		Coordinate coordinate = event.getCoordinateDestinazione();
		if(tuttoVicinatoDAccordo(coordinate, tessera))
		{
			areaDiGioco.addTessera(coordinate, tessera);
		}
		else
		{
			this.controller.fire(new MossaNonValidaEvent(controller));
		}
	}

	private boolean tuttoVicinatoDAccordo(Coordinate coordinate, Tessera tessera)
	{
		boolean dAccordo=true;
		Tessera tesseraVicino =null;
		for(PuntoCardinale punto : PuntoCardinale.values())
		{
		     try
			{
				tesseraVicino = this.areaDiGioco.getTessera(coordinate.getCoordinateA(punto));
				if( tessera.buonVicino(tesseraVicino, punto)==false)
				{
					dAccordo=false;
				}
				
			}
			catch (TesseraNonTrovataException e)
			{
				//a est (o nord o quello che è) non c'è nessuno, meglio, non si lamenta
			}
		}
		return dAccordo;
	}
}










