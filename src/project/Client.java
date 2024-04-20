package project;

public class Client {
    private int id;
    private String name;
    private int discount;
    private int additionalDiscount1;
    private int additionalDiscount2;

    public Client(int id, String name, int discount, int additionalDiscount1, int additionalDiscount2){
        try {
            if (id < 0 || discount < 0 || additionalDiscount1 < 0 || additionalDiscount2 < 0) {
                throw new InvalidClientException("ID, discount or any additional discounts can't be negative numbers!");
            }
            this.id = id;
            this.name = name;
            this.discount = discount;
            this.additionalDiscount1 = additionalDiscount1;
            this.additionalDiscount2 = additionalDiscount2;
        } catch(InvalidClientException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int getDiscount() {
        return discount;
    }

    public int getAdditionalDiscount1() {
        return additionalDiscount1;
    }

    public int getAdditionalDiscount2() {
        return additionalDiscount2;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Client Data: " + getId() + ", " + getName() + ", " + getDiscount() + ", " + getAdditionalDiscount1() + ", " + getAdditionalDiscount2();
    }
}
