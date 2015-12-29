package fr.filiggrane.nutrition;

/**
 * Représente un portion-mesure immuable.
 * 
 * @author Olivier Maillard
 */
public final class Serving implements Cloneable {

	/**
	 * Unité mesure en gramme
	 */
	public static final Serving GRAM = new Serving(1, 0);

	/**
	 * Unité mesure en Mililitre
	 */
	public static final Serving MILLILTER = new Serving(0, 1);

	/**
	 * Unité mesure en Kilogramme USI
	 */
	public static final Serving KILOGRAM = new Serving(1000, GRAM);

	/**
	 * Unité mesure en Litre USI
	 */
	public static final Serving LITER = new Serving(1000, MILLILTER);

	/**
	 * Unité mesure en US CUP
	 */
	public static final Serving CUPS = new Serving(240, MILLILTER);

	/**
	 * Unité mesure en Tablespoon
	 */
	public static final Serving TBSP = new Serving(14.7867648F, MILLILTER);

	/**
	 * Unité mesure en Teaspoon
	 */
	public static final Serving TSP = new Serving(1 / 3F, TBSP);

	/**
	 * Unité mesure en Once
	 */
	public static final Serving OZ = new Serving(28.3495231F, GRAM);

	/**
	 * Unité mesure en Once liquide
	 */
	public static final Serving FLOZ = new Serving(29.5735296F, MILLILTER);

	/**
	 * Poids en grammes
	 */
	private final float weight;

	/**
	 * Volume en mililitres
	 */
	private final float volume;

	/**
	 * Initialise une part avec un poids et un volume
	 * 
	 * @param weight
	 *            le poids en grammes
	 * @param volume
	 *            le volume en mililitre
	 */
	public Serving(float weight, float volume) {
		this.weight = weight;
		this.volume = volume;
	}

	/**
	 * Initialise une portion par homothétie.
	 * 
	 * @param size
	 *            le facteur d'homothetie
	 * @param base
	 *            la portion-mesure de référence
	 */
	public Serving(float size, Serving base) {
		this(size * base.getWeight(), size * base.getVolume());
	}

	/**
	 * Retourne le poids en gramme
	 * 
	 * @return le poids
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * Retourne le volume en mililitre
	 * 
	 * @return le volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * Indique si la portion-mesure est une unité de volume
	 * 
	 * @return true si unité de mesure de volume, false sinon
	 */
	public boolean isVolumeUnit() {
		return weight == 0;
	}

	/**
	 * Indique si la portion-mesure est une unité de mesure de poids
	 * 
	 * @return true si unité de mesure de masse, false sinon
	 */
	public boolean isWeightUnit() {
		return volume == 0;
	}

	/**
	 * Indique si la portion-mesure est une unité de mesure
	 * 
	 * @return true si unité de mesure, false sinon
	 */
	public boolean isUnit() {
		return isVolumeUnit() || isWeightUnit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Serving clone() {
		Serving o = null;
		try {
			o = (Serving) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}

		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;

		return ((Serving) o).weight == weight && ((Serving) o).volume == volume;
	}
}
