/**
 * 
 */
package fr.filiggrane.nutrition.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.filiggrane.nutrition.Portion;
import fr.filiggrane.nutrition.Serving;

/**
 * @author cpco_wise
 *
 */
public class PortionTest {

	/**
	 * Test method for {@link fr.filiggrane.nutrition.Portion#clone()}.
	 */
	@Test
	public void testClone() {
		Portion test = new Portion(1,Serving.GRAM);

		assertTrue(test.clone()!=test);
		assertTrue(test.clone().getClass()==test.getClass());
		assertTrue(test.clone().equals(test));

	}

}
