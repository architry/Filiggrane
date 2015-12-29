package fr.filiggrane.nutrition.calculator;

import fr.filiggrane.nutrition.FoodComposition;
import fr.filiggrane.nutrition.NutrientMap;
import fr.filiggrane.nutrition.NutritionCalculator;

/**
 * Calculette Weight-Watchers PointsPlus (US; Nov 2010-..)
 * 
 * @author Olivier Maillard (architry@yahoo.fr)
 */
public class PointsPlusCalculator extends PointsCalculator {
	
	public PointsPlusCalculator() {
		super(49);
	}

	@Override
	public float intake(FoodComposition foodComposition, Object constraints) {

		float fat = foodComposition.getFats();
		float carbs = foodComposition.getCarbs();
		float prot = foodComposition.getProteins();
		float fiber = foodComposition.getNutrient(NutrientMap.FIBER);
		float alcohol = foodComposition.getNutrient(NutrientMap.ALCOHOL);
		float sugarAlcohol = foodComposition.getNutrient(NutrientMap.SUGAR_ALCOHOL);

		double points = (16 * prot + 19 * (carbs - sugarAlcohol) + 45 * fat
				- 14 * fiber + 58.05 * alcohol + 11.4 * sugarAlcohol) / 175;

		return Math.round((float)Math.max(points, 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.filiggrane.ppwmanager.nutrition.NutritionCalculator#dailyRequirement
	 * (int, java.lang.Object)
	 */
	@Override
	public float dailyRequirement(int routine, Object constraints) {
		BasicConstraints c = null;
		if (constraints instanceof BasicConstraints) {
			c = (BasicConstraints) constraints;
		} else {
			throw new IllegalArgumentException(
					"cannot calculate daily requirement : constraints must be BasicConstraints");
		}

		double da = 0; 
		if(c.category==BasicConstraints.GENDER_MALE)
			da = 864 - (9.72*c.age) + 1.12*(14.2*c.weight+503*c.height);
		else if(c.category==BasicConstraints.GENDER_FEMALE)
			da = 387 - (7.31*c.age) + 1.14*(10.9*c.weight+660.7*c.height);
		
		da=0.9*da+200;
		
		float requeriment = (float) Math.min(Math.max(Math.round(Math.max(da-1000, 1000)/35)-11,26), 71);
		return requeriment;
	}
	
	public static void main(String[] args){
		
		NutritionCalculator test=new ProPointsCalculator();
		
		FoodComposition comp = new FoodComposition(4,5,6);
		comp.setNutrient("fibers", 9);
		float points = test.intake(comp, null);
		System.out.println(String.valueOf(points));
		
	}
}
