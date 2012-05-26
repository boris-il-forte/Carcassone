package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.server.Controller.Costruzione;
import it.polimi.dei.swknights.carcassonne.server.Model.Segnalino;

import java.util.HashMap;
import java.util.Map;

/**
 * That class encapsulate the four elements of a card
 * 
 * @author Edo & Dave
 * 
 */
public class Lati
{

	public Lati(Elemento nord, Elemento sud, Elemento ovest, Elemento est)
	{
		this.nord = nord;
		this.sud = sud;
		this.ovest = ovest;
		this.est = est;
		this.datiSegnalino = new DatiSegnalino();
	}

	public Lati(Elemento[] elementiTessera)
	{
		this(elementiTessera[PuntoCardinale.nord.toInt()], elementiTessera[PuntoCardinale.sud.toInt()],
				elementiTessera[PuntoCardinale.ovest.toInt()], elementiTessera[PuntoCardinale.est.toInt()]);

	}

	/**
	 * gets the element on the specified cardinal point
	 * 
	 * @param puntoCardinale
	 *            cardinal point
	 * @return the element on that cardinal point
	 */
	public Elemento getTipoElementoInDirezione(PuntoCardinale puntoCardinale)
	{
		Elemento[] elementi = { this.nord, this.sud, this.ovest, this.est };
		return elementi[puntoCardinale.toInt()];
	}

	/**
	 * rotate 90° clockwise the 4 elements of the card.
	 */
	public void ruota()
	{
		Elemento nordTmp = this.nord;
		this.nord = this.ovest;
		this.ovest = this.sud;
		this.sud = this.est;
		this.est = nordTmp;
	}

	/**
	 * Gets non-aggregated Buildings of a given card
	 * 
	 * @param tessera
	 *            of which the caller wants to know the Building
	 * @return a map binding each Building to a Cardinal Point
	 */
	public Map<Costruzione, PuntoCardinale> getMappaCostruzioniPrimitive(Tessera tessera)
	{
		Map<Costruzione, PuntoCardinale> mappaCostruzioni = new HashMap<Costruzione, PuntoCardinale>();
		Elemento[] elementi = { this.nord, this.sud, this.est, this.ovest };
		PuntoCardinale[] punti = { PuntoCardinale.nord, PuntoCardinale.sud, PuntoCardinale.est,
				PuntoCardinale.ovest };
		int indicePuntiCardinali = 0;
		for (Elemento elemento : elementi)
		{
			Costruzione costruzione = elemento.getCostruzione(tessera);
			if (costruzione != null)
			{
				mappaCostruzioni.put(costruzione, punti[indicePuntiCardinali]);
			}
			indicePuntiCardinali++;
		}
		return mappaCostruzioni;
	}

	/**
	 * 
	 * @param marker
	 *            the marker to be placed
	 * @param puntoCardinale
	 *            where place the marker
	 */
	public void setSegnalino(Segnalino marker, PuntoCardinale puntoCardinale)
	{
		this.datiSegnalino.segnalino = marker;
		this.datiSegnalino.puntoCardinale = puntoCardinale;
	}

	@Override
	public String toString()
	{
		Elemento[] elementi = { this.nord, this.sud, this.ovest, this.est };
		StringBuilder componiStringhe = new StringBuilder();
		for (PuntoCardinale puntoCardinale : PuntoCardinale.values())
		{
			String stringPuntoCardinale = puntoCardinale.toString();
			String stringElemento = elementi[puntoCardinale.toInt()].toString();
			componiStringhe.append(stringPuntoCardinale + '=' + stringElemento + ' ');
		}
		String stringSegnalino = printSegnalino();
		if (stringSegnalino.length() > 0)
		{
			final int caratteriPerElemento = 4;
			final int offsetPrimoElemento = caratteriPerElemento - 1;
			PuntoCardinale direzione = this.datiSegnalino.puntoCardinale;
			int index = direzione.toInt() * caratteriPerElemento + offsetPrimoElemento;
			componiStringhe.insert(index, stringSegnalino);
		}
		return componiStringhe.toString();
	}

	private String printSegnalino()
	{
		Segnalino segnalino = this.datiSegnalino.segnalino;
		if (segnalino != null)
		{
			return segnalino.toString();
		}
		else
		{
			return "";
		}
	}

	private Elemento		nord;

	private Elemento		sud;

	private Elemento		ovest;

	private Elemento		est;

	private DatiSegnalino	datiSegnalino;

	private static class DatiSegnalino
	{
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((puntoCardinale == null) ? 0 : puntoCardinale.hashCode());
			result = prime * result + ((segnalino == null) ? 0 : segnalino.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj) { return true; }
			if (obj == null) { return false; }
			if (!(obj instanceof DatiSegnalino)) { return false; }
			DatiSegnalino other = (DatiSegnalino) obj;
			if (puntoCardinale != other.puntoCardinale) { return false; }
			if (segnalino == null)
			{
				if (other.segnalino != null) { return false; }
			}
			else if (!segnalino.equals(other.segnalino)) { return false; }
			return true;
		}

		public Segnalino		segnalino;
		
		public PuntoCardinale	puntoCardinale;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datiSegnalino == null) ? 0 : datiSegnalino.hashCode());
		result = prime * result + ((est == null) ? 0 : est.hashCode());
		result = prime * result + ((nord == null) ? 0 : nord.hashCode());
		result = prime * result + ((ovest == null) ? 0 : ovest.hashCode());
		result = prime * result + ((sud == null) ? 0 : sud.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Lati other = (Lati) obj;
		if (datiSegnalino == null)
		{
			if (other.datiSegnalino != null) return false;
		}
		else if (!datiSegnalino.equals(other.datiSegnalino)) return false;
		if (est != other.est) return false;
		if (nord != other.nord) return false;
		if (ovest != other.ovest) return false;
		if (sud != other.sud) return false;
		return true;
	}

}
