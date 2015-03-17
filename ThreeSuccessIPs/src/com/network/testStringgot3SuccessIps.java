package com.network;

import static org.junit.Assert.*;

import org.junit.Test;

public class testStringgot3SuccessIps extends Ip {

	@Test
	public final void testGetSuccessIps() {
		String test = "Reply from 206.190.36.45: bytes=32 time=4ms TTL=51Reply from 206.190.36.45: bytes=32 time=4ms TTL=51Reply from 206.190.36.45: bytes=32 time=4ms TTL=51";
		assertTrue(getSuccessIps(test).equals("[206.190.36.45]"));
	}
		
	@Test
	public final void testGetSuccesIpsonFile(){
		
		String test2 = null;
		String inputFile = "input000.txt";
		try {
			test2 = readFile(inputFile);
		} catch (Exception e) {}

				      
		String result = getSuccessIps(test2);

		assertTrue(result.equals("[10.16.205.101]"));
	}

}
