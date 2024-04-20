package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        List<Product> products = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        List<String> orders = new ArrayList<>();

        fillProductAndClientLists(products, clients, "src/clients_and_products.txt");
        fillOrdersList(orders, "src/inputs.txt");

        for(String order : orders){
            completeOrder(order, products, clients);
            System.out.println();
        }
    }
    public static void completeOrder(String order, List<Product> products, List<Client> clients){
        String[] orderDetails = order.split(",");

        try{
            Client client = findClientByID(clients, Integer.parseInt(orderDetails[0]));
            double totalSum = 0;

            if(client == null){
                throw new InvalidIDException("Client ID does not exist!");
            }

            System.out.println("Client:               " + client.getName());
            System.out.println("Product                    Quantity     Standard Unit Price     Promotional Unit Price     Line Total");

            for(int i = 1; i < orderDetails.length; i++){
                String[] currentProductPurchase = orderDetails[i].split("=");

                Product currentProduct = findProductByID(products, Integer.parseInt(currentProductPurchase[0]));

                if(currentProduct == null){
                    throw new InvalidIDException("Product ID does not exist!");
                }
                if(Double.parseDouble(currentProductPurchase[1]) < 0){
                    throw new InvalidQuantityException("Quantity can't be below 0!");
                }

                String quantityEdit = String.format("%,.0f", Double.parseDouble(currentProductPurchase[1]));

                String printOrder = String.format("%s%s%s%s%s%s%s%s%s",
                        currentProduct.getName(),
                        addWhitespaces(currentProduct.getName(),30),
                        quantityEdit,
                        addWhitespaces(quantityEdit, 22),
                        String.format(addMarkupToCost(currentProduct)),
                        addWhitespaces(addMarkupToCost(currentProduct), 25),
                        applyProductPromotion(Double.parseDouble(currentProductPurchase[1]), currentProduct),
                        addWhitespaces(applyProductPromotion(Double.parseDouble(currentProductPurchase[1]), currentProduct), 1),
                        String.format("EUR %,.2f", calculateLineTotal(Double.parseDouble(currentProductPurchase[1]), currentProduct))
                        );

                System.out.println(printOrder);

                totalSum += calculateLineTotal(Double.parseDouble(currentProductPurchase[1]), currentProduct);
            }

            System.out.println();

            String discount = calculateDiscount(client, totalSum);
            double discountNum = discountTotal(totalSum, Double.parseDouble(discount));
            double sumAfterDiscount = totalSum - discountNum;

            String totalString = "Total Before Client Discounts:   ";
            String discountString = String.format("\nAdditional Volume Discount at %s%%:", discount);
            String orderTotal = "\nOrder Total Amount:              ";

            String printTotal = String.format("%s%s%s%s%s%s%s%s%s",
                    totalString,
                    addWhitespacesTotalSums(10, String.format("EUR %,.2f", totalSum), String.format("EUR %,.2f", totalSum)),
                    String.format("EUR %,.2f", totalSum),
                    discountString,
                    addWhitespacesTotalSums(10, String.format("EUR %,.2f", discountNum), String.format("EUR %,.2f", totalSum)),
                    String.format("EUR %,.2f", discountNum),
                    orderTotal,
                    addWhitespacesTotalSums(10, String.format("EUR %,.2f", sumAfterDiscount), String.format("EUR %,.2f", totalSum)),
                    String.format("EUR %,.2f", sumAfterDiscount)
                    );

            System.out.println(printTotal);
        } catch(InvalidIDException | InvalidQuantityException e){
            e.printStackTrace();
            System.out.println();
        }
    }

    public static void fillOrdersList(List<String> orders, String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            String line = reader.readLine();
            while(line != null){
                orders.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fillProductAndClientLists(List<Product> productsList, List<Client> clientsList, String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            String line = reader.readLine();
            char indicator = ' ';
            while(line != null) {
                indicator = line.equals("Products:") ? 'p' : line.equals("Clients:") ? 'c': indicator;

                String[] objectProperties = line.split(";");

                if(!objectProperties[0].equals("Products:") && !objectProperties[0].equals("Clients:")) {
                    if(indicator == 'p') {
                        Product p = createProduct(objectProperties);
                        productsList.add(p);
                    }
                    else {
                        Client c = createClient(objectProperties);
                        clientsList.add(c);
                    }
                }
                line = reader.readLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Product createProduct(String[] properties){
        int id = Integer.parseInt(properties[0]);
        String name = properties[1];
        double cost = Double.parseDouble(properties[2]);
        String markup = properties[3];
        String promotion = properties[4];
        return new Product(id, name, cost, markup, promotion);
    }

    public static Client createClient(String[] properties){
        int id = Integer.parseInt(properties[0]);
        String name = properties[1];
        int discount = Integer.parseInt(properties[2]);
        int additionalDiscount1 = Integer.parseInt(properties[3]);
        int additionalDiscount2 = Integer.parseInt(properties[4]);
        return new Client(id, name, discount, additionalDiscount1, additionalDiscount2);
    }

    public static Client findClientByID(List<Client> clients, int id){
        for(Client c : clients){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public static Product findProductByID(List<Product> products, int id){
        for(Product p : products){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public static double extractNumberFromString(String number){
        String pattern = "\\d+(\\.\\d+)?";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(number);

        if(matcher.find()){
            return Double.parseDouble(matcher.group());
        }
        else{
            return 0;
        }
    }

    public static String addWhitespacesTotalSums(int whitespacesCount, String current, String previous){
        int sum = whitespacesCount + (previous.length() - current.length());
        if (sum == 0) {
            return " ";
        }
        return String.format("%1$" + sum + "s", "");
    }

    public static String addWhitespaces(String previous, int whitespacesCount){
        int sum = Math.abs(previous.length() - whitespacesCount);
        if (sum == 0) {
            return " ";
        }
        return String.format("%1$" + sum + "s", "");
    }

    public static String addMarkupToCost(Product product){
        if(product.getMarkup().contains("%")){
            return String.format("EUR %.2f", product.getCost() + (product.getCost() * (extractNumberFromString(product.getMarkup()) / 100)));
        }
        else{
            return String.format("EUR %.2f", product.getCost() + extractNumberFromString(product.getMarkup()));
        }
    }

    public static String applyProductPromotion(double quantity, Product product) {
        if(!product.getPromotion().equals("none")){
            double costAfterMarkup = extractNumberFromString(addMarkupToCost(product));
            if(product.getPromotion().contains("%")){
                return String.format("EUR %.5f", costAfterMarkup - (costAfterMarkup * (extractNumberFromString(product.getPromotion()) / 100)));
            }
            else{
                /* For such specific promotions such as "Buy 2, get 3rd free" we create specific cases to resolve them */
                if (product.getId() == 4) {
                    return String.format("EUR %.5f", ((quantity % 3 * costAfterMarkup) + ((quantity - (quantity % 3)) * costAfterMarkup * 2/3)) / quantity);
                }
            }
        }
        return String.format("%" + 11 + "s", "");
    }

    public static double calculateLineTotal(double quantity, Product product){
        if(quantity < 0){
            return 0;
        }
        if(!product.getPromotion().equals("none")){
            return quantity * extractNumberFromString(applyProductPromotion(quantity, product));
        }
        return quantity * extractNumberFromString(addMarkupToCost(product));
    }

    public static String calculateDiscount(Client client, double totalSum){
        int totalDiscount = client.getDiscount();
        if(totalSum > 10000 && totalSum < 30000){
            totalDiscount += client.getAdditionalDiscount1();
        }
        if(totalSum > 30000){
            totalDiscount += client.getAdditionalDiscount2();
        }
        return String.format("%d", totalDiscount);
    }

    public static double discountTotal(double totalSum, double discount){
        return totalSum * discount / 100;
    }
}