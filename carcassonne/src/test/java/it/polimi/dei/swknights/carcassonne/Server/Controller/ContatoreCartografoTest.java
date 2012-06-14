package it.polimi.dei.swknights.carcassonne.Server.Controller;

import static org.junit.Assert.assertTrue;
import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Elemento;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.FactoryTessere;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.FactoryTessereNormali;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Lati;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Link;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.Tessera;
import it.polimi.dei.swknights.carcassonne.Server.Model.Tessere.TesseraNormale;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class ContatoreCartografoTest
{
	private ModuloModel	model;
	private ContatoreCartografo	contatorecartografo;
	public int	completate;

	@Before
	public void initializeTest() throws Exception
	{
		Debug.print("========== Alzate i piedi devo passare e pulire!!! =============");
		this.completate = 0;
		this.model = new ModuloModel();
		this.model.addPlayer();
		this.contatorecartografo = new ContatoreCartografo(this.model);
	}

	@Test
	public void stradaPiccola() throws Exception
	{
		FactoryTessere factory = new FactoryTessereNormali();		
		factory.acquisisciMazzoDaFile("/Stradella.txt");		
		this.mettiEConta(factory, new Coordinate(1, 0),0);
		this.mettiEConta(factory, new Coordinate(2, 0),0);
		this.mettiEConta(factory, new Coordinate(3, 0),0);
		this.mettiEConta(factory, new Coordinate(4, 0),0);
		this.mettiEConta(factory, new Coordinate(5, 0),1);
		
		Debug.print("completata!");
		assertTrue("Male! aspettavo 1 costruzione completata, viste: " + this.completate , this.completate==1 );
		Debug.print(" il numero di costruzioni completate è: " + this.completate);

	}
	
	@Test
	public void rotonda() throws Exception
	{
		FactoryTessere factory = new FactoryTessereNormali();		
		factory.acquisisciMazzoDaFile("/Rotonda.txt");	
		
		this.mettiEConta(factory, new Coordinate(0, -1),0);
		this.mettiEConta(factory, new Coordinate(0, -2),0);
		this.mettiEConta(factory, new Coordinate(0, -3),0);
		this.mettiEConta(factory, new Coordinate(1, -3),0);
		this.mettiEConta(factory, new Coordinate(1, -2),0);
		this.mettiEConta(factory, new Coordinate(1, -1),1);
		
		Debug.print("completata!");
		assertTrue("Male! aspettavo 1 costruzione completata, viste: " + this.completate , this.completate==1 );
		Debug.print(" il numero di costruzioni completate è: " + this.completate);
		
		
	}
	
	@Test
	public void ciambellaCity() throws Exception
	{
		Debug.print("Sono ciambella city");
		FactoryTessere factory = new FactoryTessereNormali();		
		factory.acquisisciMazzoDaFile("/Springfield.txt");	
		
		///0,0
		this.mettiEConta(factory, new Coordinate(0, -1), 0);
		this.mettiEConta(factory, new Coordinate(0, -2), 0);
		
		this.mettiEConta(factory, new Coordinate(0, -3), 0);		
		this.mettiEConta(factory, new Coordinate(1, -3), 0);
		this.mettiEConta(factory, new Coordinate(2, -3), 0);
		
		this.mettiEConta(factory, new Coordinate(2, -2), 0);		
		this.mettiEConta(factory, new Coordinate(2, -1), 0);
		this.mettiEConta(factory, new Coordinate(1, -1), 1);

		
		Debug.print("completata!");
		assertTrue("Male! aspettavo 1 costruzione completata, viste: " + this.completate , this.completate==1 );
		Debug.print(" il numero di costruzioni completate è: " + this.completate);
		
	}
	
	
	
	
	@Test
	public void cittaFiordoropoli() throws Exception
	{
		FactoryTessere factory = new FactoryTessereNormali();		
		factory.acquisisciMazzoDaFile("/Fiordoropoli.txt");		
		this.mettiEConta(factory, new Coordinate(0, 1),  0); //ponte
		this.mettiEConta(factory, new Coordinate(1, 1), 0 );  //ovest
		this.mettiEConta(factory, new Coordinate(2, 1), 0);  //centro
		this.mettiEConta(factory, new Coordinate(2, 0), 0); //nord
		this.mettiEConta(factory, new Coordinate(2, 2), 0);  //sud
		this.mettiEConta(factory, new Coordinate(3, 1), 1); //est
	
		Debug.print(" il numero di costruzioni completate è: " + this.completate);
		assertTrue(" dovrebbe essercene una!", this.completate==1);
	}
	
	
	@Test
	public void cittaMaledettaMergeBassoAlto() throws Exception
	{
		
		FactoryTessere factory = new FactoryTessereNormali();		
		factory.acquisisciMazzoDaFile("/PechinoMergeBassoAlto.txt");		
		//dal basso verso l'alto a serpente da sx verso destra
		
		//////////////////////////////////////  (0, 0));   c'è già
		this.mettiEConta(factory, new Coordinate(1, 0),  0); 
		this.mettiEConta(factory, new Coordinate(2, 0), 0);
		this.mettiEConta(factory, new Coordinate(3, 0), 0);
		
		this.mettiEConta(factory, new Coordinate(3, -1), 0);
		this.mettiEConta(factory, new Coordinate(2, -1), 0);
		this.mettiEConta(factory, new Coordinate(1, -1), 0);
		
		this.mettiEConta(factory, new Coordinate(1, -2), 0);
		this.mettiEConta(factory, new Coordinate(2, -2), 0);
		this.mettiEConta(factory, new Coordinate(3, -2), 0);
		
		this.mettiEConta(factory, new Coordinate(3, -3), 0);
		this.mettiEConta(factory, new Coordinate(3, -4), 0);
		this.mettiEConta(factory, new Coordinate(3, -5), 0);
		
		this.mettiEConta(factory, new Coordinate(2, -5), 0);
		this.mettiEConta(factory, new Coordinate(1, -5), 0);
		this.mettiEConta(factory, new Coordinate(0, -5), 0);
		
		this.mettiEConta(factory, new Coordinate(0, -4), 0);
		this.mettiEConta(factory, new Coordinate(1, -4), 0);
		this.mettiEConta(factory, new Coordinate(2, -4), 0);
		
		this.mettiEConta(factory, new Coordinate(0, -3), 0);
		this.mettiEConta(factory, new Coordinate(1, -3), 0);
		this.mettiEConta(factory, new Coordinate(2, -3), 1);
		
		this.mettiEConta(factory, new Coordinate(0, -2), 1);
		this.mettiEConta(factory, new Coordinate(0, -1), 1);
		
		
		Debug.print(" il numero di costruzioni completate è: " + this.completate);
		assertTrue(" dovrebbe essercene una! invece sono: " + 
		this.completate, this.completate==1);
		
	}
	
	
	
	@Test
	public void cittaMaledettaBassoAltoSerpe() throws Exception
	{
		FactoryTessere factory = new FactoryTessereNormali();		
		factory.acquisisciMazzoDaFile("/PechinoAltoBassoSerpe.txt");		
		//dal basso verso l'alto a serpente da sx verso destra
		
		//////////////////////////////////////  (0, 0));   c'è già
		this.mettiEConta(factory, new Coordinate(1, 0), 0); //croci basso dx
		this.mettiEConta(factory, new Coordinate(2, 0), 0);  //croce, risalgo da dx
		
		this.mettiEConta(factory, new Coordinate(2, -1), 0);  //croce risale da dx
		this.mettiEConta(factory, new Coordinate(1, -1), 0);  //strada in alto, angolo strada giù dx  
		this.mettiEConta(factory, new Coordinate(0, -1), 0);  //prato e città sx, risalgo da sx
		
		this.mettiEConta(factory, new Coordinate(0, -2), 0);  //prato e città sx
		this.mettiEConta(factory, new Coordinate(1, -2), 0);  //3 città dx alto basso, prato sx
		this.mettiEConta(factory, new Coordinate(2, -2), 0);  // città alto e sx strada giu-dx, risalgo dx
		
		this.mettiEConta(factory, new Coordinate(2, -3), 0);  //città sotto , strada EO, prato sopra
		this.mettiEConta(factory, new Coordinate(1, -3), 0);
		this.mettiEConta(factory, new Coordinate(0, -3), 0);
		
		this.mettiEConta(factory, new Coordinate(0, -4), 0);  //città alto, sx   prato basso e dx
		this.mettiEConta(factory, new Coordinate(1, -4), 0);
		this.mettiEConta(factory, new Coordinate(2, -4), 0);
		
		this.mettiEConta(factory, new Coordinate(2, -5), 1);  // città sotto prato resto
		this.mettiEConta(factory, new Coordinate(1, -5), 1);
		this.mettiEConta(factory, new Coordinate(0, -5), 1); // strada NS, prato resto
		
	
		Debug.print(" il numero di costruzioni completate è: " + this.completate);
		assertTrue(" dovrebbe essercene una!", this.completate==1);
	}
	
	
	
	

	private void mettiEConta(FactoryTessere factory, Coordinate coord, int quanteCompleta) throws Exception
	{	
		Tessera tessera = factory.getTessera();
		Debug.print("" + tessera);
		this.model.posizionaTessera(tessera, coord);
		this.contatorecartografo.riceviCoordinateTessera(coord);
		if(this.contatorecartografo.areCostruzioniCompletate())
		{
			Debug.print(" metto la tessera alla coordinata:  " + coord +
					" la tessera e': " + tessera);
			this.completate+= this.contatorecartografo.getCostruzioniCompletate().size();
		}
		assertTrue(" le costruzioni finite a questo punto dovrebbero essere: " + quanteCompleta,
				this.completate ==  quanteCompleta);
		
	}

	@Test
	public void testCostruzioniCorrette() throws Exception
	{

	}

	@Test
	public void testCostruzioniScorrette() throws Exception
	{

	}

	public CostruzioneCoord[] stradella()
	{
		CostruzioneCoord[] stradaPiccola = new CostruzioneCoord[5];

		for (int i = 0; i < 5; i++)
		{
			stradaPiccola[i] = new CostruzioneCoord();
		}

		Tessera incrocio0 = new TesseraNormale(this.creaLatiIncrocioAQuattro(),
				this.creaLinkIncrocioAQuattro());
		Coordinate c0 = new Coordinate(-1, 0);

		Tessera t1 = new TesseraNormale(this.creaLatiStradaEO(), this.creaLinkStradaEO());
		Coordinate c1 = new Coordinate(0, 0);

		Tessera t2 = new TesseraNormale(this.creaLatiStradaEO(), this.creaLinkStradaEO());
		Coordinate c2 = new Coordinate(1, 0);

		Tessera t3 = new TesseraNormale(this.creaLatiStradaEO(), this.creaLinkStradaEO());
		Coordinate c3 = new Coordinate(2, 0);

		Tessera incrocio4 = new TesseraNormale(this.creaLatiIncrocioAQuattro(),
				this.creaLinkIncrocioAQuattro());
		Coordinate c4 = new Coordinate(3, 0);

		stradaPiccola[0].daiCoppia(incrocio0, c0);
		stradaPiccola[1].daiCoppia(t1, c1);
		stradaPiccola[2].daiCoppia(t2, c2);
		stradaPiccola[3].daiCoppia(t3, c3);
		stradaPiccola[4].daiCoppia(incrocio4, c4);

		return stradaPiccola;
	}

	private Lati creaLatiStradaEO()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.prato;
		Elemento sud = Elemento.prato;
		Elemento ovest = Elemento.strada;
		Elemento est = Elemento.strada;
		latiCreandi = new Lati(nord, sud, ovest, est);
		return latiCreandi;
	}

	private Link creaLinkStradaEO() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { false, false, false, true, false, false };
		Link l = new Link(bl);
		return l;

	}

	private Lati creaLatiIncrocioAQuattro()
	{
		Lati latiCreandi;
		Elemento nord = Elemento.strada;
		Elemento sud = Elemento.strada;
		Elemento ovest = Elemento.strada;
		Elemento est = Elemento.strada;
		latiCreandi = new Lati(nord, sud, ovest, est);
		return latiCreandi;
	}

	private Link creaLinkIncrocioAQuattro() throws IllegalArgumentException
	{
		/* NS(0), NE(1), NW(2), WE(3), SE(4), SW(5); */
		boolean[] bl = { false, false, false, false, false, false };
		Link l = new Link(bl);
		return l;

	}

	static class CostruzioneCoord
	{

		public Tessera		tessera;
		public Coordinate	coord;

		public void daiCoppia(Tessera t1, Coordinate c1)
		{
			this.tessera = t1;
			this.coord = c1;

		}

	}
	

	
	
	
	
	

}
