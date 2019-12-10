package de.fasterfood.fasterfood.ingredient;

import de.fasterfood.fasterfood.meal.Meal;
import javax.persistence.*;
import java.util.List;


@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double purchasePrice;
    private int stock;

    @ManyToMany
    private List<Meal> mealList;  // Bitte lesen
//    @ManyToMany             // Muss Meal in der Zeile drüber wirklich eine List sein? Ist nicht eigentlich das Verhältnis von
//    private Meal mealList;  // Meal zu Ingredient ManyToMany,  statt  List<Meal> zu Ingredient? Oder passt das wirklich? AK


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

    public void setPurchasePrice(int purchasePrice) {
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
