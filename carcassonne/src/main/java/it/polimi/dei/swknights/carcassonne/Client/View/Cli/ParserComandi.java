package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

public class ParserComandi
{
	public ParserComandi(Cli cli)
	{
		this.cli = cli;
	}

	public boolean eseguiComando(String stringComando)
	{
		int quantitaSpostamento = 0;
		System.out.println(stringComando);
		if (stringComando.matches("[SCsc][1-4]")) // es c1 s2
		{ return this.cli.posizionaSengalino(stringComando); }
		if (stringComando.matches("\\-?\\d+\\,\\-?\\d+")) // es (2,-4)
		{
			int x, y;
			String comando = stringComando;
			String[] parti = comando.split(",");
			x = Integer.parseInt(parti[STRINGA_X]);
			y = Integer.parseInt(parti[STRINGA_Y]);
			return this.cli.provaPosizionareTessera(new Coordinate(x, y));
		}

		if (stringComando.matches("rotate")) { return this.cli.ruotaTessera(); }
		if (stringComando.matches("pass")) { return this.cli.nonMettereSegnalino(); }

		if (stringComando.matches("(up|down|left|right)\\=\\d+"))
		{

			String dopoUguale = stringComando.substring(stringComando.indexOf('=') + 1);
			quantitaSpostamento = Integer.parseInt(dopoUguale);
		}

		if (stringComando.matches("up\\=\\d+"))
		{
			this.cli.muoviViewA(PuntoCardinale.nord, quantitaSpostamento);
		}
		if (stringComando.matches("down\\=\\d+"))
		{
			this.cli.muoviViewA(PuntoCardinale.sud, quantitaSpostamento);
		}
		if (stringComando.matches("left\\=\\d+"))
		{
			this.cli.muoviViewA(PuntoCardinale.ovest, quantitaSpostamento);
		}
		if (stringComando.matches("right\\=\\d+"))
		{
			this.cli.muoviViewA(PuntoCardinale.est, quantitaSpostamento);
		}
		if (stringComando.matches("goto=\\-?\\d+\\,\\-?\\d+"))
		{

		}

		return false;
	}

	private Cli					cli;
	
	private final static int	STRINGA_X	= 0;
	
	private final static int	STRINGA_Y	= 1;
}
