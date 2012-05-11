package it.polimi.dei.swknights.carcassonne;

public enum PuntoCardinale
{
	nord(0), sud(1), ovest(2), est(3);
	private int	numerazione;

	private PuntoCardinale(int numerazione)
	{
		this.numerazione = numerazione;
	}

	public int toInt()
	{
		return this.numerazione;
	}

	public PuntoCardinale opposto()
	{
		switch (this)
		{
			case nord:
				return sud;
			case sud:
				return nord;
			case ovest:
				return est;
			case est:
				return ovest;
			default:
				return null;
		}
	}

	public static final int	numero	= 4;
}
