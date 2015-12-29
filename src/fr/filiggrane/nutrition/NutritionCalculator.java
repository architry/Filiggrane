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
	 * Activit�s journali�re de type s�dentaire
	 */
	public static final int DAILY_SEDENTARY 	= 1;

	/**
	 * Activit�s journali�re de personnes peu actives
	 */
	public static final int DAILY_LOW_ACTIVE	= 2;

	/**
	 * Activit�s journali�re de personnes actives
	 */
	public static final int DAILY_ACTIVE		= 3;
	
	/**
	 *	Activit� sportive l�g�re 
	 */
	public static final int SPORTING_ACTIVITY_LIGHT			= 1;
	
	/**
	 *	Activit� sportive mod�r�e 
	 */
	public static final int SPORTING_ACTIVITY_MODERATE		= 2;

	/**
	 *	Activit� sportive mod�r�e 
	 */
	public static final int SPORTING_ACTIVITY_INTENSE		= 3;
	
	/**
	 * Apport energetique d'un compos� alimentaire
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
	 *            niveau d'activit� journali�re
	 * @param constraints
	 *            les contraintes de calcul
	 * @return le besoin quotidien
	 */
	public float dailyRequirement(int routine, Object constraints);

	/**
	 * D�pense energetique d'une activit� phyisque
	 * 
	 * @param level
	 *            intensit� de l'activit�
	 * @param duration
	 *            la dur�e en minutes
	 * @param constraints
	 *            les contraintes de calcul
	 * @return la depense energetique dans le systeme du calculateur
	 */
	public float cost(int level, float duration, Object constraints);
}
