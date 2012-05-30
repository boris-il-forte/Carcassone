package it.polimi.dei.swknights.carcassonne.Client.View;

import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * This class is a simple model for the View,
 * it contains a representation of the board game.
 * @author edoardopasi & dave
 *
 */
public class ScenarioDiGioco
{
	public ScenarioDiGioco()
	{
		this.mappaRighe = new TreeMap<Integer, ScenarioDiGioco.Riga>();
	}
   /**
    * Set the Adapter Card in the given position on the board
    * @param coordinate  where place the card
    * @param tessera to place
    */
	public void setTessera(Coordinate coordinate, AdapterTessera tessera)
	{
		Riga riga = this.getRigaInserimento(coordinate.getY());
		riga.setTessera(tessera, coordinate.getX());
	}

	/**
	 * Gets the card in the given position of the board
	 * @param coordinate  coordinates of the card to get
	 * @return Adapter card or null if no card is located in that position
	 */
	
	public AdapterTessera getTessera(Coordinate coordinate)
	{
		int x = coordinate.getX();
		int y = coordinate.getY();
		Riga riga = this.mappaRighe.get(y);
		if (riga != null)
		{
			return riga.getTessera(x);
		}
		else
		{
			return null; // TODO o exception??
		}
	}
	/**
	 * Get the coordinate in the top-left corner
	 * @return
	 */
	public Coordinate getMax()
	{
		int x = this.getMaxX();
		int y = this.mappaRighe.lastKey();
		return new Coordinate(x, y);
	}
	/**
	 * Get the coordinate in tthe top-left corner
	 * @return
	 */
	public Coordinate getMin()
	{
		int x = this.getMinX();
		int y = this.mappaRighe.firstKey();
		return new Coordinate(x, y);
	}
	/**
	 * 
	 * @param min  the top-left corner
	 * @param max the bottom-right corner
	 * @return  a List of all the requested entries @see {@link EntryTessera}
	 * from min to max
	 * Used to Print all the cards of the board game
	 */
	public List<EntryTessera> getEntryList(Coordinate min, Coordinate max)
	{
		List<EntryTessera> list = new ArrayList<EntryTessera>();
		for (Entry<Integer, Riga> entryRiga : this.mappaRighe.entrySet())
		{
			int y = entryRiga.getKey();
			if (y >= min.getY() && y <= max.getY())
			{
				for (Entry<Integer, AdapterTessera> entryTessera : entryRiga.getValue().getEntrySet())
				{
					int x = entryTessera.getKey();
					if (y >= min.getX() && y <= max.getX())
					{
						Coordinate coordinate = new Coordinate(x, y);
						Vicinato vicinato = this.getVicinato(coordinate);
						EntryTessera entry = new EntryTessera(coordinate, entryTessera.getValue(), vicinato);
						list.add(entry);
					}
				}
			}
		}

		return list;
	}

	private Vicinato getVicinato(Coordinate coordinate)
	{
		final int nord = PuntoCardinale.nord.toInt();
		final int sud = PuntoCardinale.sud.toInt();
		final int ovest = PuntoCardinale.ovest.toInt();
		final int est = PuntoCardinale.est.toInt();

		boolean vicinato[] = new boolean[PuntoCardinale.NUMERO_DIREZIONI];
		for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
		{
			Coordinate coordinateVicino = coordinate.getCoordinateA(puntoCardinale);
			vicinato[puntoCardinale.toInt()] = this.esisteTesseraA(coordinateVicino);
		}

		return new Vicinato(vicinato[nord], vicinato[sud], vicinato[ovest], vicinato[est]);
	}

	private boolean esisteTesseraA(Coordinate coordinate)
	{
		return (this.getTessera(coordinate) != null);
	}

	private Integer getMinX()
	{
		int min = 0;
		for (Riga riga : this.mappaRighe.values())
		{
			int localMin = riga.getMin();
			min = (min > localMin) ? localMin : min;
		}
		return min;
	}

	private Integer getMaxX()
	{
		int max = 0;
		for (Riga riga : this.mappaRighe.values())
		{
			int localMax = riga.getMax();
			max = (max < localMax) ? localMax : max;
		}
		return max;
	}

	private Riga getRigaInserimento(Integer y)
	{
		Riga riga = this.mappaRighe.get(y);
		if (riga == null)

		{
			riga = new Riga();
			this.mappaRighe.put(y, riga);
		}
		return riga;
	}



	private SortedMap<Integer, Riga>	mappaRighe;

	private static class Riga
	{
		public Riga()
		{
			this.caselleRiga = new TreeMap<Integer, AdapterTessera>();
		}

		public AdapterTessera getTessera(Integer coordinataX)
		{
			return this.caselleRiga.get(coordinataX);
		}

		public void setTessera(AdapterTessera tessera, Integer coordinataX)
		{
			this.caselleRiga.put(coordinataX, tessera);
		
		}

		public Integer getMax()
		{
			return this.caselleRiga.lastKey();
		}

		public Integer getMin()
		{
			return this.caselleRiga.firstKey();
		}

		public Set<Entry<Integer, AdapterTessera>> getEntrySet()
		{
			return this.caselleRiga.entrySet();
		}

		private SortedMap<Integer, AdapterTessera>	caselleRiga;

	}



}
