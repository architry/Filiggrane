/**
 * 
 */
package fr.filiggrane.nutrition.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.filiggrane.nutrition.Food;
import fr.filiggrane.nutrition.FoodComposition;
import fr.filiggrane.nutrition.Meal;
import fr.filiggrane.nutrition.Portion;
import fr.filiggrane.nutrition.Serving;

/**
 * @author cpco_wise
 *
 */
public class MealTest {
	
	private Meal meal = new Meal("omelette au champignons");
	private Food oeuf = new Food("oeuf", new FoodComposition(10.3f,0.7f,12.3f),new Portion(2,Serving.OZ));
	private Food oil = new Food("oil", new FoodComposition(80,0,0), new Portion(1,Serving.TBSP));
	private Food champignon = new Food("champignon", new FoodComposition(0.43f,2.73f,1.8f),new Portion(1,Serving.OZ));

	/**
	 * Test method for {@link fr.filiggrane.nutrition.Meal#getComposition()}.
	 */
	@Test
	public void testGetComposition() {
		meal.addFood(oeuf);
		meal.addFood(oil);
		meal.addFood(champignon);
		
		System.out.println(meal.getComposition().getNutrients().toString());
	}

}
