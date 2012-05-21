package it.polimi.dei.swknights.carcassonne.Client.View.CLI;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.Vicinato;

import java.util.ArrayList;
import java.util.List;

public class StringBuilder2D
{
	public StringBuilder2D(DatiMappa datiMappa)
	{
		this.datiMappa = datiMappa;
		this.creaLinee();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for (StringBuilder linea : this.linee)
		{
			builder.append(linea.toString());
			builder.append(';');
		}
		return builder.toString();
	}

	public void addTessera(Coordinate coordinate, String tessera, Vicinato vicinato)
	{
		int x = coordinate.getX() - this.datiMappa.getMaxA(PuntoCardinale.ovest);
		int y = coordinate.getY() - this.datiMappa.getMaxA(PuntoCardinale.nord);
		x *= larghezzaTessera;
		y *= altezzaTessera;
		System.out.println("("+this.datiMappa.getMaxA(PuntoCardinale.ovest)+","+this.datiMappa.getMaxA(PuntoCardinale.nord)+")");
		System.out.println("("+x+","+y+")");
		this.scriviAngoli(x,y);
		//TODO: bordi e elementi!
	}

	private void creaLinee()
	{
		this.linee = new ArrayList<StringBuilder>();
		for (int i = 0; i < this.datiMappa.getAltezza() * altezzaTessera + 2 * altezzaTessera; i++)
		{
			linee.add(new StringBuilder());
		}
		this.azzeraLinee();

	}

	private void azzeraLinee()
	{
		int larghezza = this.datiMappa.getLarghezza()*larghezzaTessera+2*larghezzaTessera;
		StringBuilder inizializzatore = new StringBuilder();
		
		for(int i =0; i<larghezza; i++) inizializzatore.append(' ');
		for (StringBuilder linea : this.linee)
		{
			linea.append(inizializzatore);
		}
	}

	private void scriviAngoli(int x, int y)
	{
		this.scriviCarattere(x, y, '+');
		this.scriviCarattere(x+larghezzaTessera, y+altezzaTessera, '+');
		this.scriviCarattere(x, y+altezzaTessera, '+');
		this.scriviCarattere(x+larghezzaTessera,y, '+');
	}

	private void scriviCarattere(int x, int y, char c)
	{
		StringBuilder linea = this.linee.get(y);
		linea.setCharAt(x, c);
	}

	private DatiMappa			datiMappa;

	private List<StringBuilder>	linee;

	private static final int	altezzaTessera	= 7;
	private static final int	larghezzaTessera	= 14;
}
