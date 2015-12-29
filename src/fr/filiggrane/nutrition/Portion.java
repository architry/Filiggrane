package fr.filiggrane.nutrition;

/**
 * Représente une portion
 * 
 * @author Olivier Maillard
 */
public class Portion implements Cloneable {

	/**
	 * Portion de base
	 */
	private Serving serving;

	/**
	 * Taille de la portion de base
	 */
	private float size;

	/**
	 * Densité pour la conversion poids-volume
	 */
	private float density = 1;

	/**
	 * Initialise une portion avec une taille est une portion de base
	 * 
	 * @param size
	 *            la taille de la portion
	 * @param serving
	 *            la portion de référence
	 */
	public Portion(float size, Serving serving) {
		this.size = size;
		this.serving = serving;
	}

	/**
	 * Initialise une portion avec un poids et un volume spécifiés
	 * 
	 * @param weight
	 *            le poids en gramme
	 * @param volume
	 *            le volume en mililitre
	 */
	public Portion(float weight, float volume) {
		this(1, new Serving(weight, volume));
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
		this.serving = serving;
	}

	/**
	 * @return the size
	 */
	public float getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(float size) {
		this.size = size;
	}

	/**
	 * Retourne le poinds de la portion en gramme
	 * 
	 * @return le poids en gramme
	 */
	public float getWeight() {
		if (serving.isVolumeUnit())
			return density * getVolume();
		return size * serving.getWeight();
	}

	/**
	 * Retourne le volume de la portion
	 * 
	 * @return le volume de la portion en millilitres
	 */
	public float getVolume() {
		if (serving.isWeightUnit())
			return getWeight() / density;
		return size * serving.getVolume();
	}

	/**
	 * Convertit la portion de référence et ajuste la quantité en conséquence
	 * 
	 * @param serving
	 * @return
	 */
	public boolean convert(Serving serving) {
		float newSize = 1;
		if (serving.isWeightUnit())
			newSize = getWeight() / serving.getWeight();
		else if (serving.isVolumeUnit())
			newSize = getVolume() / serving.getVolume();
		else
			return false;

		setServing(serving);
		setSize(newSize);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Portion clone() {
		Portion portion = null;
		try {
			portion = (Portion) super.clone();
			portion.serving = (Serving) serving.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}

		return portion;
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
		return ((Portion) o).serving.equals(this.serving) && ((Portion) o).size == size
				&& ((Portion) o).density == density;
	}

}
