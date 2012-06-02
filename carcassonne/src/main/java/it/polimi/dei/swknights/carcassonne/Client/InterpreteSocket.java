package it.polimi.dei.swknights.carcassonne.Client;

public class InterpreteSocket implements InterpreteServer
{

	public void interpreta(String socketLine)
	{
		if (socketLine.equals("begin"))
			System.out.println("begin");
		else
			System.out.println("what??");
		
	}

	public void eseguiComando()
	{
		// TODO Auto-generated method stub
		
	}

}
