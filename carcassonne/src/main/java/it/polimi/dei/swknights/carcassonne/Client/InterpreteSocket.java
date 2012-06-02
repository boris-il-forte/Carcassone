package it.polimi.dei.swknights.carcassonne.Client;


public class InterpreteSocket implements InterpreteServer
{

	public void interpreta(String socketLine)
	{
		String line = socketLine;
		if (line.indexOf(",") != -1 && line.indexOf(":") != -1) // composti
																// update:
																// tile,x,y
		{
			if (line.matches("start: " + regTessera +  ", .+" + ", (black|green|red|yellow|blue)" + 
				 ", " + "\\d+")) // es start: tile,
															// name, color, num
			{

			}
			if (line.matches("update: "+ regTessera + ",\\-?\\d+\\,\\-?\\d+")) // es. update:
																// tile 2,3
			{

			}

		}
		else
		{
			if (line.indexOf(":") != -1)
			{
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

	private static final String regScores = "￼red=\\d+, blue=\\d+, green=\\d+, yellow=\\d+, black=\\d+";
	private static final String	regTessera	= "*";
			/*TODO: NO! I SEGNALINI !! "N=(S|C|N) S=(S|C|N) W=(S|C|N) E=(S|C|N) NS=(0|1) NE=(0|1)"
													+ " NW=(0|1) WE=(0|1) SE=(0|1) SW=(0|1)";
													*/

	
	
	
	
	
}
