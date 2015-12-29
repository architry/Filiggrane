package fr.filiggrane.nutrition;

import java.util.Map;

/**
 * Composition nutritionnelle des aliments.
 * 
 * 
 * @author Olivier Maillard (architry@yahoo.fr}
 */
public class FoodComposition implements Cloneable {
	/**
	 * Table des nutriments
	 */
	private Map<String, Float> nutrients = new NutrientMap<String, Float>();
	/**
	 * La teneur en eau
	 */
	private float water;
	/**
	 * Portion de référence de la composition
	 */
	private Serving serving = new Serving(100, Serving.GRAM);

	/**
	 * Initialise une composition alimentaire
	 */
	public FoodComposition() {
		// rien à faire
	}

	/**
	 * Initialise une composition alimentaire à partir des macronutriments
	 * (lipides, glucides et protéines)
	 * 
	 * @param nutrients
	 *            la quantité de lipides
	 * @param carbohydrates
	 *            la quantité de glucides
	 * @param nutrients
	 *            la quantité de protéines
	 */
	public FoodComposition(float fat, float carbs, float protein) {
		setFats(fat);
		setCarbs(carbs);
		setProteins(protein);
	}

	/**
	 * Initialise la composition alimentaire à partir d'une table nutritionelle
	 * 
	 * @param nutrients
	 */
	public FoodComposition(Map<String, Float> nutrients) {
		this.nutrients = nutrients;
	}

	/**
	 * Retourne le poids total des graisses
	 * 
	 * @return le poids total des graisses
	 */
	public float getFats() {
		return nutrients.get(NutrientMap.LIPID);
	}

	/**
	 * Définit le poids total des graisses
	 * 
	 * @param fat
	 *            le poids total des graisses
	 */
	public void setFats(float fat) {
		this.nutrients.put(NutrientMap.LIPID, fat);
	}

	/**
	 * Retourne le poids total en gramme des glucides
	 * 
	 * @return le poids des glucides en grammes
	 */
	public float getCarbs() {
		return nutrients.get(NutrientMap.CARBOHYDRATE);
	}

	/**
	 * @param carbohydrates
	 *
	 */
	public void setCarbs(float carbs) {
		this.nutrients.put(NutrientMap.CARBOHYDRATE, carbs);
	}

	/**
	 * @return the proteins
	 */
	public float getProteins() {
		return nutrients.get(NutrientMap.PROTEIN);
	}

	/**
	 * @param nutrients
	 *            .proteins the proteins to set
	 */
	public void setProteins(float protein) {
		this.nutrients.put(NutrientMap.PROTEIN, protein);
	}

	/**
	 * @return the nutrients
	 */
	public Map<String, Float> getNutrients() {
		return nutrients;
	}

	/**
	 * @param nutrients
	 *            the nutrients to set
	 */
	public void setNutrients(Map<String, Float> nutrients) {
		if (nutrients == null)
			throw new IllegalArgumentException("nutrients can't be null");

		this.nutrients = nutrients;
	}

	/**
	 * @return the water
	 */
	public float getWater() {
		return water;
	}

	/**
	 * @param water
	 *            the water to set
	 */
	public void setWater(float water) {
		this.water = water;
	}

	/**
	 * @return the serving
	 */
	public Serving getServing() {
		return serving;
	}

	/**
	 * @param serving
	 *            the serving to set
	 */
	public void setServing(Serving serving) {
		if (serving == null)
			throw new IllegalArgumentException("serving can't be null");

		this.serving = (Serving) serving.clone();
	}

	/**
	 * Retourne la valeur d'un élément nutritionel
	 * 
	 * @param nutrient
	 *            le nutriment à retourner
	 * @return la valeur de l'élément nutritionel
	 * @todo transformer le switch en capitalize
	 */
	public float getNutrient(String nutrient) {
		Float value = nutrients.get(nutrient);
		if (value == null)
			value = Float.valueOf(0);
		return (float) value;
	}

	/**
	 * Fixe la valeur d'un élément nutritionel
	 * 
	 * @param nutrient
	 *            le nom de l'élement nutritionnel
	 * @param value
	 *            la valeur de l'élément
	 */
	public void setNutrient(String nutrient, float value) {
		nutrients.put(nutrient, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public FoodComposition clone() {
		FoodComposition composition = null;
		try {
			composition = (FoodComposition) super.clone();
			composition.serving = serving.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}

		return composition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		return ((FoodComposition) o).water == this.water
				&& ((FoodComposition) o).nutrients.equals(this.nutrients)
				&& ((FoodComposition) o).serving.equals(this.serving);
	}

}
