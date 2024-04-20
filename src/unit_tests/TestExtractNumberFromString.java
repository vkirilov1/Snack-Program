package unit_tests;
import project.Main;
public class TestExtractNumberFromString {
    public static void main(String[] args) {
        testExtractNumberFromString_ValidInput();
        System.out.println();
        testExtractNumberFromString_EmptyString();
        System.out.println();
        testExtractNumberFromString_InvalidInput();
    }
    public static void testExtractNumberFromString_ValidInput(){
        String input = "aaaAAaaAa14.6ADf.514";
        double correctOutput = 14.6;
        if(Main.extractNumberFromString(input) == correctOutput){
            System.out.printf("Result: Expected-%.2f|Received-%.2f\n", correctOutput, Main.extractNumberFromString(input));
            System.out.println("extractNumberFromString method works with valid input!");
        }
        else {
            System.out.printf("Result: Expected-%.2f|Received-%.2f\n", correctOutput, Main.extractNumberFromString(input));
            System.out.println("extractNumberFromString method does not work with valid input!");
        }
    }

    public static void testExtractNumberFromString_EmptyString(){
        String input = "";
        double correctOutput = 0;
        if(Main.extractNumberFromString(input) == correctOutput){
            System.out.printf("Result: Expected-%.2f|Received-%.2f\n", correctOutput, Main.extractNumberFromString(input));
            System.out.println("extractNumberFromString method works with empty input!");
        }
        else {
            System.out.printf("Result: Expected-%.2f|Received-%.2f\n", correctOutput, Main.extractNumberFromString(input));
            System.out.println("extractNumberFromString method does not work with empty input!");
        }
    }

    public static void testExtractNumberFromString_InvalidInput(){
        String input = "ADasdfb.vfbfbgf.fsdfFWQS.DDvbbFf";
        double correctOutput = 0;
        if(Main.extractNumberFromString(input) == correctOutput){
            System.out.printf("Result: Expected-%.2f|Received-%.2f\n", correctOutput, Main.extractNumberFromString(input));
            System.out.println("extractNumberFromString method works with invalid input!");
        }
        else {
            System.out.printf("Result: Expected-%.2f|Received-%.2f\n", correctOutput, Main.extractNumberFromString(input));
            System.out.println("extractNumberFromString method does not work with invalid input!");
        }
    }
}
