package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

/**
 * This class is a Parser used to process the commands given from the user to
 * the CLI
 * 
 * @author edoardopasi & dave
 * 
 */

public class ParserComandi
{
	public ParserComandi(Cli cli)
	{
		this.cli = cli;
	}

	/**
	 * Execute a command if it is a valid one and given in a valid phase of the
	 * turn
	 * 
	 * @param stringComando
	 *            the given command
	 * @return true if the command is executed, false otherwise Accepted command
	 *         are: rotate x,y up, down left or right tile C1|C2|S1|S2|... pass
	 */
	public boolean eseguiComando(String stringComando)
	{
		int quantitaSpostamento = 0;
		if (stringComando.matches("[SCsc][1-4]")) // es c1 s2
		{ return this.cli.provaPosizionareSengalino(stringComando); }
		if (stringComando.matches("\\-?\\d+\\,\\-?\\d+")) // es (2,-4)
		{
			int x, y;
			String comando = stringComando;
			String[] parti = comando.split(",");
			x = Integer.parseInt(parti[STRINGA_X]);
			y = Integer.parseInt(parti[STRINGA_Y]);
			return this.cli.provaPosizionareTessera(new Coordinate(x, y));
		}

		if (stringComando.matches("rotate")) { return this.cli.provaRuotareTessera(); }
		if (stringComando.matches("pass")) { return this.cli.provaNonMettereSegnalino(); }

		if (stringComando.matches("(up|down|left|right)\\=\\d+"))
		{

			String dopoUguale = stringComando.substring(stringComando.indexOf('=') + 1);
			quantitaSpostamento = Integer.parseInt(dopoUguale);
		}

		if (stringComando.matches("up\\=\\d+"))
		{
			quantitaSpostamento = this.getInteger(stringComando);
			this.cli.muoviViewA(PuntoCardinale.nord, quantitaSpostamento);
		}
		if (stringComando.matches("down\\=\\d+"))
		{
			quantitaSpostamento = this.getInteger(stringComando);
			this.cli.muoviViewA(PuntoCardinale.sud, quantitaSpostamento);
		}
		if (stringComando.matches("left\\=\\d+"))
		{
			quantitaSpostamento = this.getInteger(stringComando);
			this.cli.muoviViewA(PuntoCardinale.ovest, quantitaSpostamento);
		}
		if (stringComando.matches("right\\=\\d+"))
		{
			quantitaSpostamento = this.getInteger(stringComando);
			this.cli.muoviViewA(PuntoCardinale.est, quantitaSpostamento);
		}
		if (stringComando.matches("goto=\\-?\\d+\\,\\-?\\d+"))
		{
			Coordinate coordinate = this.getCoordinate(stringComando);
			this.cli.muoviViewA(coordinate);
		}

		return false;
	}

	private String getArgument(String stringComando)
	{
		String elementi[] = stringComando.split("=");
		return elementi[elementi.length - 1];
	}

	private Integer getInteger(String stringComando)
	{
		String string = this.getArgument(stringComando);
		return Integer.parseInt(string);
	}

	private Coordinate getCoordinate(String stringComando)
	{
		String parse1 = this.getArgument(stringComando);
		String parse2[] = parse1.split(",");
		int x = Integer.parseInt(parse2[STRINGA_X]);
		int y = Integer.parseInt(parse2[STRINGA_Y]);
		return new Coordinate(x, y);
	}

	private Cli					cli;

	private static final int	STRINGA_X	= 0;

	private static final int	STRINGA_Y	= 1;
}
