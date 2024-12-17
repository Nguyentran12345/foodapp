package Food;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Order {

    private String foodItem;
    private int quantity;
    private double totalPrice;
    private String orderId;

    // Maximum allowed quantity
    private static final int MAX_QUANTITY = 10;

    // Random generator for order ID
    private static final Random random = new Random();

    //food items
    private static final String[] FOOD_ITEMS = {
            "1. Chicken dumplings: $10",
            "2. Vegetable dumplings: $8",
            "3. Chicken buns: $10",
            "4. Vegetable buns: $8"
    };

    // Method to create 4-digit order ID
    private void generateOrderId() {
        // Generate a random number between 0 and 9999
        int randomNumber = random.nextInt(10000);

        // Format 4 digits
        this.orderId = String.format("%04d", randomNumber);
    }

    // Method for selecting food
    public void selectFood(Scanner scanner) {
        System.out.println("Select food item:");
        for (String item : FOOD_ITEMS) {
            System.out.println(item);
        }

        int foodChoice = Main.getValidInput(scanner, 1, 4);

        // Validate quantity input
        int qty;
        while (true) {
            try {
                System.out.println("Please choose quantity (limit 10):");
                String input = scanner.nextLine().trim();

                // Check for empty input
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Quantity cannot be blank. Please enter a valid number.");
                    continue;
                }

                qty = Integer.parseInt(input);

                // Check for positive quantity and within limit
                if (qty <= 0) {
                    JOptionPane.showMessageDialog(null, "Quantity must be greater than 0. Please try again.");
                    continue;
                }

                // Check for quantity limit
                if (qty > MAX_QUANTITY) {
                    JOptionPane.showMessageDialog(null,
                            "Sorry, the quantity limit for the order is 10. Please adjust the quantity.");
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        }

        this.quantity = qty;

        switch (foodChoice) {
            case 1:
                this.foodItem = "Chicken dumplings";
                this.totalPrice = qty * 10;
                break;
            case 2:
                this.foodItem = "Vegetable dumplings";
                this.totalPrice = qty * 8;
                break;
            case 3:
                this.foodItem = "Chicken buns";
                this.totalPrice = qty * 10;
                break;
            case 4:
                this.foodItem = "Vegetable buns";
                this.totalPrice = qty * 8;
                break;
        }

        // Generate Order ID after food selection
        generateOrderId();

        // Print selection message and oder id
        System.out.println("You chose " + this.foodItem + " with quantity " + this.quantity);
        System.out.println("The Order ID is: " + this.orderId);
    }

    // Method to calculate and print total price
    public void calculateTotal() {
        System.out.println("Your total price is: $" + this.totalPrice);
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    // Method to get Order ID
    public String getOrderId() {
        return this.orderId;
    }
}