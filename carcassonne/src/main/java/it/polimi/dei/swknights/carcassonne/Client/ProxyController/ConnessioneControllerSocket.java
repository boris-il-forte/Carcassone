package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import java.net.Socket;

public class ConnessioneControllerSocket extends ConnessioneController
{
	public ConnessioneControllerSocket(Socket socket)
	{
		this.socket = socket;
	}

	public void interpreta(String socketLine)
	{
		String line = socketLine;
		if (line.indexOf(",") != -1 && line.indexOf(":") != -1) // composti
																// update:
																// tile,x,y
		{
			String [] comandoEArgomenti =	line.split(": ");
			String argomenti = comandoEArgomenti[ARGOMENTI];
			String [] partiArgomenti = argomenti.split(",");
			String tessera = partiArgomenti[TESSERA];
			
			if (line.matches("start: " + regTessera +  ", .+" + ", (black|green|red|yellow|blue)" + 
				 ", " + "\\d+")) // es start: tile,
															// name, color, num
			{
				String name = partiArgomenti[NOME];
				String color = partiArgomenti[COLORE_START];
				String numero = partiArgomenti[NUMERO];
			}
			if (line.matches("update: "+ regTessera + ",\\-?\\d+\\,\\-?\\d+")) // es. update:
																// tile 2,3
			{
			   int x = Integer.parseInt( partiArgomenti[X]);
			   int y = Integer.parseInt( partiArgomenti[Y]);
			}

		}
		else
		{
			if (line.indexOf(":") != -1)
			{
				String [] comandoEArgomenti =	line.split(": ");
				String argomenti = comandoEArgomenti[ARGOMENTI];
				
				if (line.matches("turn: (black|green|red|yellow|blue)")) // es.
																			// turn:
																			// red
				{
					
				}
				if (line.matches("next: " + regTessera)) // es next: blabla
				{

				}
				if (line.matches("rotated: " + regTessera)) // es rotate: blabla
				{

				}
				if (line.matches("score: " + regScores)) // es score: red=10 blu=20
				{

				}
				if (line.matches("leave: (black|green|red|yellow|blue)")) // es
																			// leave:
																			// yellow
				{

				}
				if (line.matches("end: " + regScores)) // es next: red=10 blue=30
				{

				}

			}
			else
			{
				if (line.equalsIgnoreCase("move not valid"))
				{

				}
				if (line.equalsIgnoreCase("lock"))
				{

				}
				if (line.equalsIgnoreCase("unlock"))
				{

				}

			}
		}

	}

	public void eseguiComando()
	{
		// TODO Auto-generated method stub

	}

	private static final int ARGOMENTI=1;
	
	private static final String regScores = "￼red=\\d+, blue=\\d+, green=\\d+, yellow=\\d+, black=\\d+";
	private static final String	regTessera	= "*";
			/*TODO: NO! I SEGNALINI !! "N=(S|C|N) S=(S|C|N) W=(S|C|N) E=(S|C|N) NS=(0|1) NE=(0|1)"
													+ " NW=(0|1) WE=(0|1) SE=(0|1) SW=(0|1)";
													*/

	
	
	private final static int TESSERA = 0;
	private final static int NOME = 1;
	private final static int COLORE_START = 2;
	private final static int NUMERO = 3;
	private final static int X=0;
	private final static int Y=1;
	private Socket socket;
}
