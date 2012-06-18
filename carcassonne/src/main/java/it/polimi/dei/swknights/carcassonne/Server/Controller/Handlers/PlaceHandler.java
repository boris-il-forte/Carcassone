package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ContatoreCartografo;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

/**
 * Handler class for the place event coming from the client. It implements
 * visitor pattern to handle this event
 * 
 * @author dave
 * 
 */
public class PlaceHandler extends ModuloControllerHandler
{

	public PlaceHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	/**
	 * Handler method that uses visitor's pattern for the palce event
	 */
	@Override
	public void visit(PlaceEvent event)
	{
		try
		{
			Coordinate coordinate = event.getCoordinateDestinazione();
			if (this.controller.tuttoVicinatoDAccordo(coordinate))
			{
				ContatoreCartografo contaPunti = this.controller.getContapunti();
				this.model.posizionaTesseraCorrente(coordinate);
				contaPunti.riceviCoordinateTessera(coordinate);
				this.controller.comunicaPosizionamentoTessera();
			}
			else
			{
				throw new MossaNonValidaException("vicinato non d'accordo");
			}

		}
		catch (MossaNonValidaException e)
		{
			this.model.fire(new MossaNonValidaEvent(this.controller));
		}

		this.sveglia();
	}
}
