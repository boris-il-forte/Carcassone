package it.polimi.dei.swknights.carcassonne.Client.ControllerProxy;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Connessioni.Connessione;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 */
public class ControllerProxy extends Controller
{

	public Connessione	realSubject;

	public List			myConnessioneAController;

	public void request()
	{
	}

	// TODO: potrebbe servire a breve (modificato) quindi lascio, commentato...
	/*
	 * protected int[] getNumerazioni() { int[] numerazione = new
	 * int[PuntoCardinale.NUMERO_DIREZIONI]; int counter; int stradaCounter = 0;
	 * int cittaCounter = 0; List<PuntoCardinale> puntiDaProvare = new
	 * ArrayList<PuntoCardinale>(Arrays.asList(PuntoCardinale .values())); for
	 * (PuntoCardinale punto1 : puntiDaProvare) { switch
	 * (this.lati.getTipoElementoInDirezione(punto1)) { case strada: counter =
	 * ++stradaCounter; break; case citta: counter = ++cittaCounter; break;
	 * default: continue; } for(PuntoCardinale punto2 : puntiDaProvare) {
	 * if(this.isConnected(punto1, punto2)) { puntiDaProvare.remove(punto2);
	 * numerazione[punto2.toInt()] = counter; } } } return null; }
	 * 
	 * private boolean isConnected(PuntoCardinale puntoCardinale1,
	 * PuntoCardinale puntoCardinale2) { if (puntoCardinale1 == puntoCardinale2)
	 * return true; else return this.link.areConnected(puntoCardinale1,
	 * puntoCardinale2); }
	 */

}
