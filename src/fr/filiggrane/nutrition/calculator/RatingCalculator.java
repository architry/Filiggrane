package fr.filiggrane.nutrition.calculator;

import fr.filiggrane.nutrition.FoodComposition;
import fr.filiggrane.nutrition.NutritionCalculator;

public class RatingCalculator implements NutritionCalculator {

	/*
	 *  constantes de calculs
	 */
	static private float maxRatio = 2.80f;
	static private float ceilRatio = 2.25f;

	/**
	 * Niveau plancher
	 */
	private int startRating = 0;

	/**
	 * niveau plafond
	 */
	private int endRating = 5;

	/**
	 * Pas de progression
	 */
	private float step = 1;

	/**
	 * Instance calculateur de comparaison
	 */
	private PointsCalculator pointsCalculator;

	/**
	 * Initialise une nouvelle instance RatingCalculator
	 */
	public RatingCalculator() {
	}

	/**
	 * Initialise une nouvelle instance RatingCalculator avec les paramètres
	 * spécifiés
	 * 
	 * @param start
	 *            le niveau bas
	 * @param high
	 *            le niveau haut
	 * @param step
	 *            le pas
	 */
	public RatingCalculator(int start, int high, int step) {
		startRating = start;
		endRating = high;
		this.step = step;
		pointsCalculator = new PointsPlusCalculator();
	}

	@Override
	public float intake(FoodComposition foodComposition, Object constraints) {

		BasicCalorieCalculator bc = new BasicCalorieCalculator();
		pointsCalculator = new PointsPlusCalculator();

		float calories = bc.intake(foodComposition, constraints);
		float points = pointsCalculator.intake(foodComposition, constraints);
		float ratio = 100 * points / calories;

		double rateDecimal = (maxRatio - ratio) * (endRating - startRating)
				/ (maxRatio - ceilRatio);
		double rate = Math.ceil(rateDecimal * step) / step;

		return (float) Math.min(endRating, Math.max(startRating, rate));
	}

	@Override
	public float dailyRequirement(int routine, Object constraints) {
		return 0;
	}

	@Override
	public float cost(int level, float duration, Object constraints) {
		return 0;
	}

}
