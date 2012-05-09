package it.polimi.dei.swknights.carcassonne;

import it.polimi.dei.swknights.carcassonne.server.Model.Tessere.FactoryTessereNormali;

/**
 * Hello world!
 *
 */
public class Carcassonne 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        //TODO:togliere questo baby-schifo test
        FactoryTessereNormali fnTest = new FactoryTessereNormali();
        fnTest.acquisisciMazzoDaFile("File/tessere.dat");
        
        System.out.println(PuntoCardinale.valueOf("sud").toInt() );
    }
}
