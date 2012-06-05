package it.polimi.dei.swknights.carcassonne.Server.Controller.Handlers;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.MossaNonValidaException;
import it.polimi.dei.swknights.carcassonne.Exceptions.TesseraNonTrovataException;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ContatoreCartografo;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

public class PlaceHandler extends ControllerHandler
{

	public PlaceHandler(ModuloController controller, ModuloModel model)
	{
		super(controller, model);
	}

	@Override
	public void visit(PlaceEvent event)
	{
		Debug.print("visito place event");
		try
		{
			Coordinate coordinate = event.getCoordinateDestinazione();
			if (this.tuttoVicinatoDAccordo(coordinate))
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
				if (!tesseraCorrente.buonVicino(tesseraVicino, punto)) { return false; }

			}
			catch (TesseraNonTrovataException e)
			{
				viciniVuoti++;
				continue;
			}
		}

		if (viciniVuoti == PuntoCardinale.NUMERO_DIREZIONI) { return false; }
		return true;
	}
}
