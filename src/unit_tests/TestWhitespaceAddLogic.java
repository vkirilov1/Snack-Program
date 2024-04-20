package unit_tests;
import project.Main;

public class TestWhitespaceAddLogic {
    public static void main(String[] args) {
        testWhitespaceAddLogic_ValidInput();
        System.out.println();
        testWhitespaceAddLogic_NegativeSum();
        System.out.println();
        testWhitespaceAddLogic_ZeroSum();
    }

    public static void testWhitespaceAddLogic_ValidInput(){
        int whitespacesCount = 10;
        String str = "AAAAAAA";
        String correctOutput = "   ";
        if(Main.addWhitespaces(str, whitespacesCount).equals(correctOutput)){
            System.out.printf("Result: Expected-%s|Received-%s%s\n", correctOutput, Main.addWhitespaces(str, whitespacesCount), "|");
            System.out.println("addWhitespaces method works with valid input!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s%s\n", correctOutput, Main.addWhitespaces(str, whitespacesCount), "|");
            System.out.println("addWhitespaces method does not work with valid input!");
        }
    }

    public static void testWhitespaceAddLogic_NegativeSum(){
        int whitespacesCount = 10;
        String str = "AAAAAAAAAAAAAAAAAA";
        String correctOutput = "        ";
        if(Main.addWhitespaces(str, whitespacesCount).equals(correctOutput)){
            System.out.printf("Result: Expected-%s|Received-%s%s\n", correctOutput, Main.addWhitespaces(str, whitespacesCount), "|");
            System.out.println("addWhitespaces method works with negative sum between string length and whitespace count!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s%s\n", correctOutput, Main.addWhitespaces(str, whitespacesCount), "|");
            System.out.println("addWhitespaces method does not work with negative sum between string length and whitespace count!");
        }
    }

    public static void testWhitespaceAddLogic_ZeroSum(){
        int whitespacesCount = 10;
        String str = "AAAAAAAAAA";
        String correctOutput = " ";
        if(Main.addWhitespaces(str, whitespacesCount).equals(correctOutput)){
            System.out.printf("Result: Expected-%s|Received-%s%s\n", correctOutput, Main.addWhitespaces(str, whitespacesCount), "|");
            System.out.println("addWhitespaces method works with zero sum between string length and whitespace count!");
        }
        else {
            System.out.printf("Result: Expected-%s|Received-%s%s\n", correctOutput, Main.addWhitespaces(str, whitespacesCount), "|");
            System.out.println("addWhitespaces method does not work with zero sum between string length and whitespace count!");
        }
    }
}
