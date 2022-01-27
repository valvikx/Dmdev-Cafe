package by.valvik.dmdevcaffe.entity;

import java.util.Map;

public class Order {
    
    private Integer id;
    
    private Visitor visitor;
    
    private Cashier cashier;

    private Map<Dish, Integer> dishes;
    
    private boolean isCompleted;

    public Order() {
    }

    public Order(Integer id, Visitor visitor, Cashier cashier, Map<Dish, Integer> dishes, boolean isCompleted) {
        this.id = id;
        this.visitor = visitor;
        this.cashier = cashier;
        this.dishes = dishes;
        this.isCompleted = isCompleted;
    }

    public Integer getCost() {
        return dishes.entrySet()
                     .stream()
                     .mapToInt(e -> e.getKey().cost() * e.getValue())
                     .sum();
    }

    public Integer getTotalElements() {
        return dishes.values()
                     .stream()
                     .mapToInt(Integer::intValue)
                     .sum();
    }

    public Integer getTotalCalories() {
        return dishes.keySet()
                     .stream()
                     .mapToInt(Dish::calories)
                     .sum();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Dish, Integer> dishes) {
        this.dishes = dishes;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
               "id=" + id +
               ", visitor=" + visitor +
               ", cashier=" + cashier +
               ", dishes=" + dishes +
               ", isCompleted=" + isCompleted +
               '}';
    }
    
}
