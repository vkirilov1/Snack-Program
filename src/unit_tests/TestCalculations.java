package unit_tests;

import project.Main;
import project.Product;

public class TestCalculations {
    public static void main(String[] args) {
        testCalculations_WithValidInput();
        System.out.println();
        testCalculations_WithCostAndQuantityZero();
        System.out.println();
        testCalculations_WithWrongMarkUp();
        System.out.println();
        testCalculations_WithZeroPercentPromotion();
        System.out.println();
        testCalculationsLineTotal_WithZeroQuantity();
        System.out.println();
        testCalculationsLineTotal_WithValidQuantity();
    }

    public static void testCalculations_WithValidInput(){
        double quantity = 10000;
        Product p = new Product(1, "Test", 5.67, "1.0EUR/unit","30%");
        String correctOutput = "EUR 4.66900";
        if(Main.applyProductPromotion(quantity, p).equals(correctOutput)){
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method works with valid input!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method does not work with valid input!");
        }
    }

    public static void testCalculations_WithCostAndQuantityZero(){
        double quantity = 0;
        Product p = new Product(1, "Test", 0, "0EUR/unit","30%");
        String correctOutput = "EUR 0.00000";
        if(Main.applyProductPromotion(quantity, p).equals(correctOutput)){
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method works with zero quantity and cost input!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method does not work with zero quantity and cost input!");
        }
    }

    public static void testCalculations_WithWrongMarkUp(){
        double quantity = 0;
        Product p = new Product(1, "Test", 1, "assdasdEUR/unit","30%");
        String correctOutput = "EUR 0.70000";
        if(Main.applyProductPromotion(quantity, p).equals(correctOutput)){
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method works with wrong markup input!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method does not work with wrong markup input!");
        }
    }

    public static void testCalculations_WithZeroPercentPromotion(){
        double quantity = 0;
        Product p = new Product(1, "Test", 1, "1EUR/unit","0%");
        String correctOutput = "EUR 2.00000";
        if(Main.applyProductPromotion(quantity, p).equals(correctOutput)){
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method works with zero promotion percent input!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.applyProductPromotion(quantity, p));
            System.out.println("applyProductPromotion method does not work with zero promotion percent input!");
        }
    }

    public static void testCalculationsLineTotal_WithZeroQuantity(){
        double quantity = -10;
        Product p = new Product(1, "Test", 5.67, "1.0EUR/unit","30%");
        double correctOutput = 0;
        if(Main.calculateLineTotal(quantity, p) == correctOutput){
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.calculateLineTotal(quantity, p));
            System.out.println("calculateLineTotal method works with negative quantity input!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.calculateLineTotal(quantity, p));
            System.out.println("applyProductPromotion method does not work with negative quantity input!");
        }
    }

    public static void testCalculationsLineTotal_WithValidQuantity(){
        double quantity = 10000;
        Product p = new Product(1, "Test", 5.67, "1.0EUR/unit","30%");
        double correctOutput = 46689.99999999999;
        if(Main.calculateLineTotal(quantity, p) == correctOutput){
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.calculateLineTotal(quantity, p));
            System.out.println("calculateLineTotal method works with valid quantity input!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s\n", correctOutput, Main.calculateLineTotal(quantity, p));
            System.out.println("applyProductPromotion method does not work with valid quantity input!");
        }
    }
}
