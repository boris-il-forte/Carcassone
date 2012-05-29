package it.polimi.dei.swknights.carcassonne.server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.ModuloView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.server.Controller.ContatoreCartografo;
import it.polimi.dei.swknights.carcassonne.server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.Tessera;

public class PlaceHandler extends ControllerHandler
{

	public PlaceHandler(ModuloController controller, ModuloModel model, ModuloView view)
	{
		super(controller, model, view);
	}

	@Override
	public void visit(PlaceEvent event)
	{
		Coordinate coordinate = event.getCoordinateDestinazione();
		if (this.tuttoVicinatoDAccordo(coordinate))
		{
			ContatoreCartografo contaPunti = this.controller.getContapunti();
			this.model.posizionaTesseraCorrente(coordinate);
			contaPunti.riceviCoordinateTessera(coordinate);
		}
		else
		{
			this.model.fire(new MossaNonValidaEvent(controller));
		}
		this.sveglia();
	}

	private boolean tuttoVicinatoDAccordo(Coordinate coordinate)
	{
		Tessera tesseraCorrente = this.model.getTesseraCorrente();
		Tessera tesseraVicino = null;
		int viciniVuoti = 0;
		for (PuntoCardinale punto : PuntoCardinale.values())
		{
			try
			{
				tesseraVicino = this.model.getTessera(coordinate.getCoordinateA(punto));
				if (!tesseraCorrente.buonVicino(tesseraVicino, punto))
				{
					return false;
				}

			}
			catch (TesseraNonTrovataException e)
			{
				viciniVuoti++;
				continue;
			}
		}
		
		if(viciniVuoti == PuntoCardinale.NUMERO_DIREZIONI)
		{
			return false;
		}
		return true;
	}
}
