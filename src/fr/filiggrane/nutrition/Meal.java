package fr.filiggrane.nutrition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Olivier Maillard (architry@yahoo.fr)
 *
 */
public class Meal extends Food {

	/**
	 * Liste des ingr�dients
	 */
	private ArrayList<Food> foods = new ArrayList<Food>();

	/**
	 * Initialise un aliment compos�
	 * 
	 * @param name
	 */
	public Meal(String name) {
		super(name);
	}

	/**
	 * @return the foods
	 */
	public ArrayList<Food> getFoods() {
		return foods;
	}

	/**
	 * @param foods
	 *            the foods to set
	 */
	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}

	/**
	 * Ajoute un ingredient Si l'ingredient existe, ajuste la quantit� en
	 * cons�quence
	 * 
	 * @param food
	 *            l'aliment � ajouter
	 * @return true si ajout�, false sinon
	 */
	public boolean addFood(Food food) {
		if (food == null)
			throw new IllegalArgumentException("food argument can't be null");
		Food copyFood = (Food) food.clone();
		Iterator<Food> itr = foods.iterator();
		while (itr.hasNext()) {
			Food fd = itr.next();
			if (fd.getName() == food.getName()) {
				float size = fd.getPortion().getSize()
						+ food.getPortion().getSize();
				fd.getPortion().setSize(size);
				return true;
			}
		}
		return foods.add(copyFood);
	}

	/**
	 * Ajoute un aliment dans la portion sp�cifi�e. Si l'ingredient existe,
	 * ajuste la quantit� en cons�quence
	 * 
	 * @param food
	 *            l'aliment � ajouter
	 * @param portion
	 *            la portion de l'aliment
	 * @return true si ajout�, false sinon
	 */
	public boolean addFood(Food food, Serving serving) {
		if (food == null)
			throw new IllegalArgumentException("food can't be null");

		Food copyFood = (Food) food.clone();
		Portion newPortion = new Portion(1, serving);
		copyFood.setPortion(newPortion);
		return addFood(copyFood);
	}

	/**
	 * Ajoute un aliment avec la taille de portion sp�cifi�e Si l'ingredient
	 * existe, ajuste la quantit� en cons�quence
	 * 
	 * @param food
	 *            l'aliment � ajout�
	 * @param size
	 *            la taille de la proportion
	 * @return true si ajout�, false sinon
	 */
	public boolean addFood(Food food, float size) {
		if (food == null)
			throw new IllegalArgumentException("food can't be null");

		Food copyFood = (Food) food.clone();
		float portionSize = size * food.getPortion().getSize();
		copyFood.getPortion().setSize(portionSize);
		return addFood(copyFood);
	}

	/**
	 * Retire l'aliment sp�cifi�e.
	 * 
	 * @param food
	 * @return
	 */
	public boolean removeFood(Food food) {
		if (food == null)
			throw new IllegalArgumentException("food can't be null");

		Iterator<Food> itr = foods.iterator();
		while (itr.hasNext()) {
			Food fd = itr.next();
			if (fd.getName() == food.getName()) {
				if (food.getPortion().getWeight() >= fd.getPortion()
						.getWeight()) {
					return foods.remove(food);
				}

				float size = fd.getPortion().getSize()
						- food.getPortion().getSize();
				fd.getPortion().setSize(size);
				return true;
			}
		}
		return true;
	}

	public boolean removeFood(Food food, Serving serving) {
		if (food == null)
			throw new IllegalArgumentException("food can't be null");

		Food copyFood = (Food) food.clone();
		Portion newPortion = new Portion(1, serving);
		copyFood.setPortion(newPortion);
		return removeFood(copyFood);
	}

	public boolean removeFood(Food food, float size) {
		if (food == null)
			throw new IllegalArgumentException("food can't be null");

		Food copyFood = (Food) food.clone();
		float portionSize = size * food.getPortion().getSize();
		copyFood.getPortion().setSize(portionSize);
		return removeFood(copyFood);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.filiggrane.nutrition.Food#getPortion()
	 */
	@Override
	public Portion getPortion() {
		Portion portion = super.getPortion();
		if (portion != null)
			return portion;
		Iterator<Food> iterator = foods.iterator();
		float w = 0;
		while (iterator.hasNext()) {
			w += iterator.next().getPortion().getWeight();
		}
		portion = new Portion(w, Serving.GRAM);
		return portion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.filiggrane.nutrition.Food#getComposition()
	 */
	@Override
	public FoodComposition getComposition() {
		FoodComposition mealComposition = super.getComposition();
		if (mealComposition != null)
			return mealComposition;
		// calcul par la composition de chaque aliment
		mealComposition = new FoodComposition();
		Iterator<Food> iterator = foods.iterator();
		float weightMeal = getPortion().getWeight();
		// pour chaque ingr�dient ...
		while (iterator.hasNext()) {
			Food food = iterator.next();
			// chaque nutriment de l'ingredient ...
			Map<String, Float> nutrimentMaps = food.getComposition()
					.getNutrients();
			for (Map.Entry<String, Float> entry : nutrimentMaps.entrySet()) {
				float value = food.getComposition().getNutrient(entry.getKey());
				value *= food.getPortion().getWeight() / weightMeal;
				Float prev = mealComposition.getNutrient(entry.getKey());
				mealComposition.setNutrient(entry.getKey(),prev + value);
			}
			// et l'eau
			float water = food.getComposition().getWater();
			water *= food.getPortion().getWeight() / weightMeal;
			mealComposition.setWater(mealComposition.getWater()+water);
		}
		return mealComposition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Meal [foods=" + foods + "]";
	}

}
