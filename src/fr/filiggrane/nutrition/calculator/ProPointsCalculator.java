package fr.filiggrane.nutrition.calculator;

import fr.filiggrane.nutrition.FoodComposition;
import fr.filiggrane.nutrition.NutrientMap;

/**
 * Calculette Weight-Watchers ProPoints (UK; Nov 2010-..)
 * 
 * @author Olivier Maillard (architry@yahoo.fr)
 */
public class ProPointsCalculator extends PointsPlusCalculator {

	private boolean useAlcohol;

	/**
	 * Initilise un nouveau calculateur ProPoint Weight-Watchers
	 */
	public ProPointsCalculator() {
		this(false);
	}

	/**
	 * @param useAlcohol
	 */
	public ProPointsCalculator(boolean useAlcohol) {
		super();
		this.useAlcohol = useAlcohol;
	}

	@Override
	public float intake(FoodComposition foodComposition, Object constraints) {

		float fat = foodComposition.getFats();
		float carbs = foodComposition.getCarbs();
		float prot = foodComposition.getProteins();
		float fiber = foodComposition.getNutrient(NutrientMap.FIBER);
		float alcohol = foodComposition.getNutrient(NutrientMap.ALCOHOL);
		float sugarAlcohol = foodComposition.getNutrient(NutrientMap.SUGAR_ALCOHOL);

		double points;
		if (alcohol != 0 && useAlcohol)
			points = (16 * prot + 19 * (carbs - sugarAlcohol) + 45 * fat + 5
					* fiber + 58.05 * alcohol + 11.4 * sugarAlcohol) / 175;
		else
			points = (16 * prot + 19 * carbs + 45 * fat + 5 * fiber) / 175;
		
		return (float)Math.max(Math.round(points), 0);
	}

}
