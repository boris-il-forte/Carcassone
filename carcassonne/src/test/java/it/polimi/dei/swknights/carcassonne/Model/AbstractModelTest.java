package it.polimi.dei.swknights.carcassonne.Model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.dei.swknights.carcassonne.Client.View.AbstractModuloView;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.FinePartitaEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.Model.AbstractModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;

import org.junit.Test;

public class AbstractModelTest
{
	@Test
	public void listenerAddRemoveFire()
	{
		AbstractModel modelT = new ModuloModel();
		View cli = new Cli();
		View proxy=new ProxyView();
		modelT.addListener(cli);
	
		modelT.addListener(proxy);
		modelT.removeListener(proxy);

		modelT.fire(new MossaNonValidaEvent(this));
		modelT.fire(new InizioGiocoEvent(this, "N=S S=S W=S E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0", Color.red, 2,
				"pippoGoal!"));

		Punteggi p = new Punteggi();
		modelT.fire(new FinePartitaEvent(this, p));
		/*dopo fine partita numeropartite sale a 1
		e non dovrebbe ricevere più nulla */
		modelT.removeListener(cli);		
		modelT.fire(new MossaNonValidaEvent(this));

	}

	public int	contaMosseNonValide	= 0;
	public int	contaInizioGioco	= 0;

	class ViewTest extends AbstractModuloView
	{
		public int	numeroPartita	= 0;
		public ViewTest()
		{
			super();
		}

		@Override
		public void riceviModificheModel(ControllerEvent event)
		{
			if (numeroPartita == 0)
			{
				if (event instanceof MossaNonValidaEvent) AbstractModelTest.this.contaMosseNonValide++;

				if (event instanceof InizioGiocoEvent) AbstractModelTest.this.contaInizioGioco++;

				if (event instanceof FinePartitaEvent)
				{
					assertTrue("", AbstractModelTest.this.contaInizioGioco == 1
				
							&& AbstractModelTest.this.contaMosseNonValide == 1);
					this.numeroPartita++;
				}
			}
			else if (numeroPartita == 1)
			{
				fail("non avrebbe dovuto ricevere nessun evento");
			}
		}

		@Override
		public void run()
		{
		}

	}

}
