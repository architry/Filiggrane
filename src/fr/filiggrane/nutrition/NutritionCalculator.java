/**
 * 
 */
package fr.filiggrane.nutrition;


/**
 * Calculateur nutritionnel
 *
 */
public interface NutritionCalculator {

	/**
	 * Metabolisme basal
	 */
	public static final int BASAL_METABOLIC		= 0;
	
	/**
	 * Activités journalière de type sédentaire
	 */
	public static final int DAILY_SEDENTARY 	= 1;

	/**
	 * Activités journalière de personnes peu actives
	 */
	public static final int DAILY_LOW_ACTIVE	= 2;

	/**
	 * Activités journalière de personnes actives
	 */
	public static final int DAILY_ACTIVE		= 3;
	
	/**
	 *	Activité sportive légère 
	 */
	public static final int SPORTING_ACTIVITY_LIGHT			= 1;
	
	/**
	 *	Activité sportive modérée 
	 */
	public static final int SPORTING_ACTIVITY_MODERATE		= 2;

	/**
	 *	Activité sportive modérée 
	 */
	public static final int SPORTING_ACTIVITY_INTENSE		= 3;
	
	/**
	 * Apport energetique d'un composé alimentaire
	 * 
	 * @param foodComposition
	 *            la composition alimentaire
	 * @param constraints
	 *            les contraintes de calcul
	 * @return l'apport energetique dans le systeme du calculateur
	 */
	public float intake(FoodComposition foodComposition, Object constraints);

	/**
	 * Determine le besoin quotidien en energie
	 * 
	 * @param routine
	 *            niveau d'activité journalière
	 * @param constraints
	 *            les contraintes de calcul
	 * @return le besoin quotidien
	 */
	public float dailyRequirement(int routine, Object constraints);

	/**
	 * Dépense energetique d'une activité phyisque
	 * 
	 * @param level
	 *            intensité de l'activité
	 * @param duration
	 *            la durée en minutes
	 * @param constraints
	 *            les contraintes de calcul
	 * @return la depense energetique dans le systeme du calculateur
	 */
	public float cost(int level, float duration, Object constraints);
}
