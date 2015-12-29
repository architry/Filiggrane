package fr.filiggrane.nutrition;

/**
 * Food représente un aliment.
 * 
 * @author Olivier Maillard
 */
public class Food implements Cloneable {

	/**
	 * Nom de l'aliment
	 */
	private String name;

	/**
	 * Composition nutritionnelle de l'aliment
	 */
	private FoodComposition composition;

	/**
	 * Portion de l'aliment
	 */
	private Portion portion;

	/**
	 * Initialise un nouvel aliment avec un nom
	 * 
	 * @param name
	 *            le nom de l'aliment
	 */
	public Food(String name) {
		this.name = name;
	}
	
	/**
	 * Initialise un nouvel aliment.
	 * 
	 * @param name
	 *            le nom de l'aliment
	 * @param composition
	 *            la composition de l'aliment
	 * @param portion
	 *            la portion d'aliment
	 */
	public Food(String name, FoodComposition composition, Portion portion) {
		this(name);
		if(composition==null)
			throw new IllegalArgumentException("composition can't be null");
		
		FoodComposition copyFC = (FoodComposition) composition.clone();
		this.composition = copyFC;
		
		if(portion==null)
			this.portion = new Portion(1,composition.getServing());
		else
			this.portion = (Portion) portion.clone();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the composition
	 */
	public FoodComposition getComposition() {
		return composition;
	}

	/**
	 * @param composition
	 *            the composition to set
	 */
	public void setComposition(FoodComposition composition) {
		if(composition==null)
			throw new IllegalArgumentException("composition can't be null");
		this.composition = (FoodComposition) composition.clone();
	}

	/**
	 * @return the portion
	 */
	public Portion getPortion() {
		return portion;
	}

	/**
	 * @param portion
	 *            the portion to set
	 */
	public void setPortion(Portion portion) {
		if(portion==null)
			throw new IllegalArgumentException("portion can't be null");
		this.portion = (Portion) portion.clone();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		Food food = null;
		try {
			food = (Food)super.clone();
			food.composition = (FoodComposition)composition.clone();
			food.portion = (Portion)portion.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}
		
		return food;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Food [name=" + name + ", composition=" + composition
				+ ", portion=" + portion + "]";
	}

}
