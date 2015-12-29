/**
 * 
 */
package fr.filiggrane.nutrition;

import java.util.HashMap;

/**
 * Table de hachage des éléments nutritionels
 * 
 * @author Olivier Maillard
 */
public class NutrientMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 1L; // je ne sais pas a quoi ca
														// sert
	/**
	 * Nom du macronutriment "lipide"
	 */
	public static final String LIPID = "lipids";
	/**
	 * Nom du macronutriment "glucides"
	 */
	public static final String CARBOHYDRATE = "carbohydrates";
	/**
	 * Nom du macronutriment "proteines"
	 */
	public static final String PROTEIN = "proteins";
	/**
	 * Micro nutriment "fibre alimentaire"
	 */
	public static final String FIBER = "fibers";
	/**
	 * Micro nutriment
	 */
	public static final String SUGAR_ALCOHOL = "sugarAlcohols";
	/**
	 * Alcool
	 */
	public static final String ALCOHOL = "alcohol";

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	@Override
	public V get(Object key) {
		if (containsKey(key))
			return super.get(key);
		else {
			V value = getCalculatedValue(key);
			return value;
		}
	}

	/**
	 * Calcul la valeur manquante.
	 * 
	 * @param key
	 * @return
	 */
	private V getCalculatedValue(Object key) {
		return null;
	}
}
