package de.fasterfood.fasterfood.ingredient;

import de.fasterfood.fasterfood.editMeal.Meal;
import de.fasterfood.fasterfood.recipe.Recipe;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double purchasePrice;
    private int stock;

    @ManyToMany
    private List<Meal> mealList;

    @OneToMany(mappedBy = "ingredient")
    List<Recipe> recipes;


    public Ingredient(String name, double purchasePrice, int stock) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.stock = stock;
    }

    public Ingredient() {
    }

    public void addMeal(Meal meal){
        if(!this.mealList.contains(meal)){
            this.mealList.add(meal);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addStock(int stock) { this.stock += stock;}

    public List<Meal> getMealList() {
        return mealList;
    }
}
