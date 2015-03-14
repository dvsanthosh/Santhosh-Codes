package com.validate;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * None of the Test cases should fail.
 * @author D.V.Santhosh
 *
 */
public class IsBalancedTest extends IsBalanced {

	@Test
	public void testIsBalanced() {
		assertFalse(isBalanced("(sd)({bf)})"));		//testing non-sequence
		assertFalse(isBalanced("(sd)({fg]s}df)"));  //testing extra closing parenthesis
		assertFalse(isBalanced("(sd)({b[f)}))"));	// testing extra open parenthsis
		assertTrue(isBalanced("(sd)({bf})"));		// testing for success case

	}

}
