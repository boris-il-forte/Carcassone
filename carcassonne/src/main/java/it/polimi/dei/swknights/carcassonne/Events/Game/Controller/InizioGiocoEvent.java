package it.polimi.dei.swknights.carcassonne.Events.Game.Controller;
import java.awt.Color;

import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.Game.MessaggiController;
/**
 * Event to be triggered at the beginning of the game
 * @author edoardopasi
 *
 */
public class InizioGiocoEvent extends ControllerEvent
{


	public InizioGiocoEvent(Object source, AdapterTessera tesseraIniziale, Color giocatore)
	{
		super(source);
		this.giocatore=giocatore;
		this.tesseraIniziale = tesseraIniziale;
		this.setComando(MessaggiController.beginGame);
		
	}
	
	public AdapterTessera getTesseraIniziale()
	{
		return this.tesseraIniziale;
	}
	
	public Color getGiocatore()
	{
		return this.giocatore;
	}
	
	private static final long	serialVersionUID	= -5256224715700170795L;

	 private final AdapterTessera tesseraIniziale;
	 private final Color giocatore;
	
	 @Override
		public void accept(ViewHandler handler)
		{
			handler.visit(this);
			
		}
}
