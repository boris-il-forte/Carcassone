package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Controller.ContatoreCartografo;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class PlaceHandler extends ControllerHandler
{

	public PlaceHandler(ModuloController controller, ModuloModel model)
	{
		this.controller = controller;
		this.model = model;
	}

	@Override
	public void visit(PlaceEvent event)
	{
		Tessera tessera = this.model.getTesseraCorrente();
		Coordinate coordinate = event.getCoordinateDestinazione();
		if (this.tuttoVicinatoDAccordo(coordinate, tessera))
		{
			ContatoreCartografo contaPunti = this.controller.getContapunti();
			this.model.posizionaTessera(tessera, coordinate);
			contaPunti.riceviCoordinateTessera(coordinate);
		}
		else
		{
			this.model.fire(new MossaNonValidaEvent(controller));
		}
	}

	private boolean tuttoVicinatoDAccordo(Coordinate coordinate, Tessera tessera)
	{
		boolean dAccordo = true;
		Tessera tesseraVicino = null;
		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			try
			{
				tesseraVicino = this.model.getTessera(coordinate.getCoordinateA(punto));
				if (!tessera.buonVicino(tesseraVicino, punto))
				{
					dAccordo = false;
				}

			}
			catch (TesseraNonTrovataException e)
			{
				continue;
			}
		}
		return dAccordo;
	}

	private ModuloController	controller;

	private ModuloModel				model;
}
