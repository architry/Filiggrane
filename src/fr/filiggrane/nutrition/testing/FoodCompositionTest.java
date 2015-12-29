/**
 * 
 */
package fr.filiggrane.nutrition.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.filiggrane.nutrition.FoodComposition;

/**
 * @author cpco_wise
 *
 */
public class FoodCompositionTest {

	/**
	 * Test method for {@link fr.filiggrane.nutrition.FoodComposition#clone()}.
	 */
	@Test
	public void testClone() {
		
		FoodComposition test = new FoodComposition(1,2,3);

		assertTrue(test.clone()!=test);
		assertTrue(test.clone().getClass()==test.getClass());
		assertTrue(test.clone().equals(test));

	}

}
