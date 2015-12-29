/**
 * 
 */
package fr.filiggrane.nutrition.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.filiggrane.nutrition.Serving;

/**
 * @author cpco_wise
 *
 */
public class ServingTest {

	/**
	 * Test method for {@link fr.filiggrane.nutrition.Serving#clone()}.
	 */
	@Test
	public void testClone() {
		Serving test = (Serving) Serving.GRAM.clone();

		assertTrue(test!=Serving.GRAM);
		assertTrue(test.getClass()==Serving.GRAM.getClass());
		assertTrue(test.equals(Serving.GRAM));
	}

}
