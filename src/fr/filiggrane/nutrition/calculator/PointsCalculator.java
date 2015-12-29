package fr.filiggrane.nutrition.calculator;

import fr.filiggrane.nutrition.FoodComposition;
import fr.filiggrane.nutrition.NutrientMap;
import fr.filiggrane.nutrition.NutritionCalculator;

/**
 * Calculette Weight-Watchers Points (US; Circa 1998-2010)
 * 
 * 
 * @author Olivier Maillard (architry@yahoo.fr)
 */
public class PointsCalculator implements NutritionCalculator {
	
	/**
	 * Nombre de point allou� par semaine
	 */
	protected float weeklyAllowance = 35;
	
	/**
	 * Initialise un nouveau calculateur poionts weight-watchers
	 */
	public PointsCalculator(){
	}

	/**
	 * Initialise un nouveau calculateur points weigth watchers avec un nombre de points alloué par semaine
	 * 
	 * @param weeklyAllowance le nombre de points alloué par semaine
	 */
	public PointsCalculator(float weeklyAllowance) {
		this.weeklyAllowance = weeklyAllowance;
	}

	@Override
	public float intake(FoodComposition foodComposition, Object constraints) {
		BasicCalorieCalculator bc = new BasicCalorieCalculator();

		float calories = bc.intake(foodComposition, constraints);
		float fat = foodComposition.getFats();
		float fiber = foodComposition.getNutrient(NutrientMap.FIBER);

		float points = calories / 50 + fat / 12 - Math.min(fiber, 4) / 5;
		points = (float) (Math.ceil(points * 2) / 2);
		points = Math.max(0,points);
		
		return points;
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

		float da = 0;

		float ptsFromWeight = Math.round(c.weight * 2.205 / 10);
		da += ptsFromWeight;

		if (c.category == BasicConstraints.GENDER_MALE)
			da += 8;
		else
			da += 2;

		if (c.age <= 26)
			da += 4;
		else if (c.age <= 37)
			da += 3;
		else if (c.age <= 47)
			da += 2;
		else if (c.age <= 57)
			da += 1;

		if (c.height > 178)
			da += 2;
		else if (c.height >= 155)
			da += 1;

		if (routine == DAILY_ACTIVE)
			da += 6;
		else if (routine == DAILY_LOW_ACTIVE)
			da += 2;

		if (c.category == PointsConstraints.NURSING_WOMAN)
			da += 10;
		if (c.category == PointsConstraints.NURSING_WOMAN_WITH_SOLID)
			da += 5;

		float requirement = (float) Math.max(Math.min(da, 44), 18);

		return requirement;
	}

	/**
	 * Retourne le nombre de point-bonus accord� par semaine
	 * 
	 * @param routine
	 *            le niveau d'activit�
	 * @param constraints
	 *            les contraintes de calcul
	 * @return le nombre de point bonus accord�s par semaine
	 */
	public float weeklyAllowance(int routine, Object constraints) {
		return weeklyAllowance;
	}

	@Override
	public float cost(int level, float duration, Object constraints) {
		BasicConstraints c = null;
		if (constraints instanceof BasicConstraints) {
			c = (BasicConstraints) constraints;
		} else {
			throw new IllegalArgumentException(
					"cannot calculate daily requirement : constraints must be BasicConstraints");
		}

		float cost, k4 = 0;
		switch (level) {
		case SPORTING_ACTIVITY_LIGHT:
			k4 = 0.051f;
			break;
		case SPORTING_ACTIVITY_MODERATE:
			k4 = 0.0711f;
			break;
		case SPORTING_ACTIVITY_INTENSE:
			k4 = 0.1783f;
			break;
		}

		cost = (k4 * c.weight * duration) / 100;
		return Math.round(cost);
	}
	
	

}
