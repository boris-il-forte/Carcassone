package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

public class ParserComandi
{
	public ParserComandi(Cli cli)
	{
		this.cli = cli;
	}
	
	public boolean eseguiComando(String stringComando)
	{
		System.out.println(stringComando);
		if (stringComando.matches("[SCsc][1-4]"))
		{
			return this.cli.posizionaSengalino(stringComando);
			
		}
		if (stringComando.matches("\\-?\\d+\\,\\-?\\d+"))
		{
			
		}
		
		if(stringComando.matches("rotate"))
		{
			
		}
		if(stringComando.matches("pass"))
		{
			
		}
		
		if(stringComando.matches("up\\=\\d+"))
		{
			System.out.println("U");
		}
		if(stringComando.matches("down\\=\\d+"))
		{
			System.out.println("r");
		}
		if(stringComando.matches("left\\=\\d+"))
		{
			System.out.println("r");
		}
		if(stringComando.matches("right\\=\\d+"))
		{
			System.out.println("r");
		}
		if (stringComando.matches("goto=\\-?\\d+\\,\\-?\\d+"))
		{
			
		}
		
		return false;
	}
	


	private Cli cli;
}
