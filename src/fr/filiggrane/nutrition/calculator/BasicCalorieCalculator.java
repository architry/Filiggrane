package fr.filiggrane.nutrition.calculator;

import fr.filiggrane.nutrition.FoodComposition;
import fr.filiggrane.nutrition.NutrientMap;
import fr.filiggrane.nutrition.NutritionCalculator;

/**
 * Implementation simple du calculateur nutritionnel La methode foodIntake
 * utilise les lipides, glucides, proteines et alcool qu'elle pondère
 * respectivement par 9,4,4 et 7. La methode dailyRequirement utilise la formule
 * de Black et al. (1996) La methode activityCost utilise le métabolisme de base
 * auquel elle applique une pondération en fonction du niveau de l'activité
 * sportive
 * 
 * @author Olivier Maillard (architry@yahoo.fr)
 */
public class BasicCalorieCalculator implements NutritionCalculator {

	/*
	 * Densité energetique des macro nutriments
	 */
	static private int ENERGY_FAT = 9;
	static private int ENERGY_CARBS = 4;
	static private int ENERGY_PROTEIN = 4;
	static private int ENERGY_ALCOHOL = 7;

	/*
	 * Paramètres de calcul de la formule de Black et al. 1996
	 */
	static private double BLACK_AL_WEIGHT = 0.48; // poids
	static private double BLACK_AL_HEIGHT = 0.50; // taille
	static private double BLACK_AL_AGE = -0.13; // age
	static private double BLACK_AL_MALE = 259; // facteur homme
	static private double BLACK_AL_FEMALE = 230; // facteur homme

	@Override
	public float intake(FoodComposition foodComposition, Object constraints) {
		float fats = foodComposition.getFats();
		float carbs = foodComposition.getCarbs();
		float prots = foodComposition.getProteins();
		float alcohol = foodComposition.getNutrient(NutrientMap.ALCOHOL);

		return fats * ENERGY_FAT + carbs * ENERGY_CARBS + prots * ENERGY_PROTEIN
				+ alcohol * ENERGY_ALCOHOL;
	}

	@Override
	public float dailyRequirement(int routine, Object constraints) {

		BasicConstraints c = null;
		if (constraints instanceof BasicConstraints) {
			c = (BasicConstraints) constraints;
		} else {
			throw new IllegalArgumentException(
					"cannot calculate daily requirement : constraints must be BasicConstraints");
		}

		// formule de Black et al. (1996)
		double requirement = Math.pow(c.weight, BLACK_AL_WEIGHT)
				* Math.pow(c.height, BLACK_AL_HEIGHT)
				* Math.pow(c.age, BLACK_AL_AGE);

		if (c.category==BasicConstraints.GENDER_MALE)
			requirement *= BLACK_AL_MALE;
		else
			requirement *= BLACK_AL_FEMALE;

		if (routine == DAILY_SEDENTARY)
			requirement *= 1.37f;
		else if (routine == DAILY_LOW_ACTIVE)
			requirement *= 1.55f;
		else if (routine == DAILY_ACTIVE)
			requirement *= 1.80f;

		return (float) requirement;
	}

	@Override
	public float cost(int level, float duration, Object constraints) {

		BasicConstraints c = null;
		if (constraints instanceof BasicConstraints) {
			c = (BasicConstraints) constraints;
		} else {
			throw new IllegalArgumentException(
					"cannot calculate cost activity : constraints must be BasicConstraints");
		}

		float basal = dailyRequirement(BASAL_METABOLIC, c);

		float cost = basal * (duration / 3600);

		switch (level) {
			case SPORTING_ACTIVITY_LIGHT:
				cost *= 1.5f;
				break;
			case SPORTING_ACTIVITY_INTENSE:
				cost *= 2.8f;
				break;
			case SPORTING_ACTIVITY_MODERATE:
			default:
				cost *= 2.3f;
		}

		return cost;
	}
}
