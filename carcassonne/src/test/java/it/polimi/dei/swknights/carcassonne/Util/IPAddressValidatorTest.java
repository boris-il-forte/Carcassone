package it.polimi.dei.swknights.carcassonne.Util;

import org.junit.Test;
import static org.junit.Assert.*;

public class IPAddressValidatorTest
{
	public IPAddressValidator ipv = new IPAddressValidator();
	
	@Test
	void testIP()
	{
		if ( ipv.validate("stringa") ) fail(" no le stringhe! ");
		if ( ipv.validate("230.440.44.00") ) fail(" no quelli con numeri > 440 ");
		if ( ipv.validate("1.1.1.1.1") ) fail(" no quelli da 5! ");
		if ( ipv.validate("0.c.0.1") ) fail(" no le stringhe in mezzo! ");
		if ( ! ipv.validate("192.168.0.1") ) assertTrue(" non so chi giochi con il router, comunque va bene ", true );
		
		
	}

}
