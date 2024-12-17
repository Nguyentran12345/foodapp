package Food;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Customer {
    public static void handleCustomer(Scanner scanner) {
        // Get Customer information
        System.out.println("Enter customer name:");
        String name = "";
        while (true) {
            name = scanner.nextLine().trim();

            if (name.isBlank()) {
                System.out.println("Name cannot be blank. Please enter a valid name:");
            } else if (!name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
                System.out.println("Name cannot contain numbers or special characters");
                System.out.println("Please enter a valid name:");
            } else {
                break; // Name is valid, exit loop
            }
        }

        // Phone number validation loop
        String phone = "";
        while (true) {
            System.out.println("Enter customer phone (10 digits):");
            phone = scanner.nextLine();
            if (phone.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit number.");
            }
        }

        // Get Address
        System.out.println("Enter customer address:");
        String address = "";
        while (address.isBlank()) {
            address = scanner.nextLine();
            if (address.isBlank()) {
                System.out.println("Address cannot be blank. Please enter a valid address:");
            }
        }

        // Display collected information
        System.out.println("Your name is " + name);
        System.out.println("Phone number is " + phone);
        System.out.println("Address is " + address);


        // Menu options
        System.out.println("-----------------------");
        System.out.println("Choose an option:");
        System.out.println("1. Place order");
        System.out.println("2. Request cancel order");
        System.out.println("3. Exit");
        System.out.println("-----------------------");

        // Get user's choice
        int option = Main.getValidInput(scanner, 1, 3);

        // Handle user's choice
        if (option == 1) {
            placeOrder(scanner);
        } else if (option == 2) {
            cancelOrder(scanner);
        } else {
            System.out.println("Thank you for using our service!");
        }
    }

    // method place order
    public static void placeOrder(Scanner scanner) {
        // Create a new order
        Order order = new Order();
        order.selectFood(scanner);

        // Delivery method selection
        selectDeliveryMethod(scanner);

        // Calculate total
        order.calculateTotal();

        // Confirm order with input validation
        while (true) {
            try {
                System.out.println("Is that correct?");
                System.out.println("1. Yes");
                System.out.println("2. No");

                // Read the entire line
                String input = scanner.nextLine().trim();

                // Check if input is empty
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Input cannot be blank. Please enter 1 or 2.");
                    continue;
                }

                // Parse the input
                int confirmation = Integer.parseInt(input);

                // Validate input range
                if (confirmation < 1 || confirmation > 2) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1 or 2.");
                    continue;
                }

                // Process based on confirmation
                if (confirmation == 1) {
                    Payment.processPayment(scanner);
                    break; // Exit the method after successful payment
                } else {
                    // If user chooses No, restart the order process
                    placeOrder(scanner);
                    break;
                }
            } catch (NumberFormatException e) {
                // Catch non-numeric input
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1 or 2.");
            }
        }
    }

    // Method for delivery method selection
    public static void selectDeliveryMethod(Scanner scanner) {
        System.out.println("How would you like to receive your order?");
        System.out.println("1. Pickup");
        System.out.println("2. Delivery");

        int deliveryOption = Main.getValidInput(scanner, 1, 2);

        if (deliveryOption == 1) {
            System.out.println("You have chosen to pick up your order.");
        } else if (deliveryOption == 2) {
            System.out.println("Delivery information will be sent to your phone number shortly after placing the order.");
        }
    }

    // Method for canceling the order
    public static void cancelOrder(Scanner scanner) {
        // Provide customer service contact for order cancellation
        System.out.println("To cancel your order, please get in touch with our customer service at 571-234-5678. Thank you.");
        // Terminate the program
        System.exit(0);
    }
}
