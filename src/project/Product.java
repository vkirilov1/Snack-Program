package project;

public class Product {
    private int id;
    private String name;
    private double cost;
    private String markup;
    private String promotion;

    public Product(int id, String name, double cost, String markup, String promotion) {
        try {
            if(id < 0 || cost < 0){
                throw new InvalidProductException("ID/Cost can't have negative values!");
            }
            this.id = id;
            this.name = name;
            this.cost = cost;
            this.markup = markup;
            this.promotion = promotion;
        } catch(InvalidProductException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public String getMarkup() {
        return markup;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Product Data: " + getId() + ", " + getName() + ", " + getCost() + ", " + getMarkup() + ", " + getPromotion();
    }
}
