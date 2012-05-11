package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

public class TesseraNormale extends Tessera
{

	public TesseraNormale(Lati lati, Link link)
	{
		super(lati, link);
	}
	
	@Override
	public String toString()
	{
		int index=0;
		int[] numerazioni = getNumerazioni();
		String stringa = this.lati.toString();
		StringBuilder builder = new StringBuilder(stringa);
		for(int i= stringa.indexOf(' ', 0); i>0 && index<numerazioni.length;  i=stringa.indexOf(' ', i+1))
		{
			builder.insert(i, numerazioni[index++]);
		}
		return builder.toString();
	}

}