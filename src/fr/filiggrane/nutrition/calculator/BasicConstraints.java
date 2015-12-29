package fr.filiggrane.nutrition.calculator;

/**
 * La classe BasicConstraints spécifie les contraintes pour les calculs
 * nutritionnels de la classe BasicCalculator.
 * 
 * @author Olivier Maillard (architry@yahoo.fr)
 */
public class BasicConstraints {

	/**
	 * Valeur possible pour l'attribut {@code category}
	 */
	public static int GENDER_MALE = 1;

	/**
	 * Valeur possible pour l'attribut {@code category}
	 */
	public static int GENDER_FEMALE = 2;

	/**
	 * Valeur possible pour l'attribut {@code category}
	 */
	public static int PREGNANT_WOMAN = 3;


	/**
	 * Catégorie
	 */
	public int category;

	/**
	 * Poids en kilogrammes
	 */
	public float weight;

	/**
	 * Taille en centimetres
	 */
	public float height;

	/**
	 * Age en années
	 */
	public int age;

	/**
	 * Constrcuteur par défaut pour les sous-classes
	 */
	public BasicConstraints() {
	}

	/**
	 * Initialise un nouveau objet BodyConstraint
	 * 
	 * @param cat
	 *            une catégorie pour la contrainte
	 * @param weight
	 *            le poids
	 * @param height
	 *            la taille
	 * @param age
	 *            l'age
	 */
	public BasicConstraints(int category, float weight, float height, int age) {
		this.category = category;
		this.weight = weight;
		this.height = height;
		this.age = age;
	}

}
